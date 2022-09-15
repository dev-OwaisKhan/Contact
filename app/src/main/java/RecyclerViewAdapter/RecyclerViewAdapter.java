package RecyclerViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.contact.ContactView;
import com.example.contact.R;

import java.util.List;

import Model.Contact;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static Context context;
    private static List<Contact> contactList;

    public RecyclerViewAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
        return new RecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

        Contact contact = contactList.get(position);

        holder.name.setText(contact.getName());

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            name = itemView.findViewById(R.id.name);

        }

        @Override
        public void onClick(View view) {
            int index = this.getAdapterPosition();
            Contact contact = contactList.get(index);
            String name = contact.getName();
            String phone = contact.getPhone();
            int id = contact.getId();
            Intent intent =  new Intent(context, ContactView.class);
            intent.putExtra("name", name );
            intent.putExtra("phone", phone );
            intent.putExtra("id", id);
            context.startActivity(intent);

        }
    }
}
