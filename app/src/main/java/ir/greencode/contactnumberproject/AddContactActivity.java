package ir.greencode.contactnumberproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ir.greencode.contactnumberproject.database.object.Contact;
import ir.greencode.contactnumberproject.database.object.DataBaseHandler;

public class AddContactActivity extends AppCompatActivity {

    Button   btnAdd;
    EditText edtName;
    EditText edtLName;
    EditText edtphone;

    long id;
    boolean isEdit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        btnAdd = findViewById(R.id.btn_add_contact);
        edtName = findViewById(R.id.edtFName);
        edtLName = findViewById(R.id.edtLName);
        edtphone = findViewById(R.id.edtPhone);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            id = bundle.getLong("id");
            String name = bundle.getString("name");
            String lastName = bundle.getString("lastName");
            String phoneNumber = bundle.getString("phone");
            isEdit = true;
            edtName.setText(name);
            edtLName.setText(lastName);
            edtphone.setText(phoneNumber);


        }
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHandler db = new DataBaseHandler(AddContactActivity.this);
                if (!isEdit) {
                if(edtLName.getText().toString().trim().length()!=0 && edtphone.getText().toString().trim().length()!=0 ) {



                        long a = db.insertContact(new Contact(edtName.getText().toString(), edtLName.getText().toString(), edtphone.getText().toString()));
                        if (a >= 0) {
                            edtName.setText("");
                            edtLName.setText("");
                            edtphone.setText("");
                            Toast.makeText(AddContactActivity.this, "Your Number inserted! at " + a, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddContactActivity.this, "Something is wrong", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(AddContactActivity.this, "Name AND Phone is neccessery", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    db.editContact(new Contact(id,edtName.getText().toString(),edtLName.getText().toString(),edtphone.getText().toString()));
                    finish();
                }
            }
        });
    }
}
