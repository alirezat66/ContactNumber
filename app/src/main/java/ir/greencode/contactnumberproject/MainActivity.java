package ir.greencode.contactnumberproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import ir.greencode.contactnumberproject.adapter.ContactAdapter;
import ir.greencode.contactnumberproject.database.object.Contact;
import ir.greencode.contactnumberproject.database.object.DataBaseHandler;
import ir.greencode.contactnumberproject.dialog.QuestionDialog;
import ir.greencode.contactnumberproject.dialog.QuestionListener;

public class MainActivity extends AppCompatActivity implements ContactAdapter.ContactListener {
    RecyclerView rcContact;
    FloatingActionButton btnAdd;
    List<Contact> contacts  = new ArrayList<>();
    ContactAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        btnAdd = findViewById(R.id.floatingActionButton);
        rcContact = findViewById(R.id.rvContact);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddContactActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        createList();
    }

    private void createList() {
        DataBaseHandler db = new DataBaseHandler(this);
        contacts = db.selectAllContacts();
        adapter = new ContactAdapter(contacts,this);
        rcContact.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        rcContact.setAdapter(adapter);
    }

    @Override
    public void onClickForEdit(Contact contact) {
        Intent intent = new Intent(this,AddContactActivity.class);
        intent.putExtra("id",contact.getId());
        intent.putExtra("name",contact.getfName());
        intent.putExtra("lastName",contact.getlName());
        intent.putExtra("phone",contact.getPhone());
        startActivity(intent);
    }

    @Override
    public void onLongClickForDelet(final Contact contact) {
        final QuestionDialog dialog = new QuestionDialog(this,"Delete Contact","Are you sure for delete?");
        dialog.setListener(new QuestionListener() {
            @Override
            public void onReject() {
                dialog.dismiss();
            }

            @Override
            public void onSuccess() {
                dialog.dismiss();
                // TODO: 9/6/2019
                DataBaseHandler db = new DataBaseHandler(MainActivity.this);
                db.delete(contact.getId());
                adapter.deleteOneContact(contact);
                adapter.notifyDataSetChanged();
            }
        });
        dialog.show();
    }
}
