package ir.greencode.contactnumberproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import ir.greencode.contactnumberproject.R;
import ir.greencode.contactnumberproject.database.object.Contact;

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Contact> list = new ArrayList<>();
    ContactListener myListener;

    public void deleteOneContact(Contact contact) {
        list.remove(contact);
    }

    public interface ContactListener {
        void onClickForEdit(Contact contact);
        void onLongClickForDelet(Contact contact);
    }
    public ContactAdapter(List<Contact> list,ContactListener listener) {
        this.list = list;
        this.myListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ContactViewHolder myHolder = (ContactViewHolder) holder;
        final Contact data = list.get(position);
        myHolder.txtName.setText(data.getFullName());
        myHolder.txtPhone.setText(data.getPhone());
        myHolder.txtShortName.setText(data.getShortName());
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myListener.onClickForEdit(data);
            }
        });
        myHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                myListener.onLongClickForDelet(data);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ContactViewHolder extends RecyclerView.ViewHolder{
        TextView txtPhone,txtName,txtShortName;
        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtPhone = itemView.findViewById(R.id.txtPhone);
            txtShortName = itemView.findViewById(R.id.txtMokhtasar);

        }
    }
}
