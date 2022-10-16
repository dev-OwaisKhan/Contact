package com.example.contact;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import DataBaseHandler.DBHandler;
import Model.Contact;

public class ContactView extends AppCompatActivity {

    static AlertDialog.Builder builder;
    AlertDialog dialog_delete, dialog_update;
    ImageButton cv_cancel, cv_delete;
    static TextView gl_phone, gl_name;
    Button cv_edit;
    static boolean condition;
    Contact contact = new Contact();
    DBHandler obj = new DBHandler(ContactView.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_view);


        cv_cancel = findViewById(R.id.cv_cancel);
        cv_delete = findViewById(R.id.cv_delete);
        cv_edit = findViewById(R.id.cv_edit);
        gl_name  = findViewById(R.id.cv_name);
        gl_phone = findViewById(R.id.cv_phone);


        String contact_name, contact_phone;
        int contact_id,index;

        contact_name = getIntent().getStringExtra("name");
        contact_phone = getIntent().getStringExtra("phone");
        contact_id = getIntent().getIntExtra("id",0);
        index = getIntent().getIntExtra("index",1);

        gl_name.setText(contact_name);
        gl_phone.setText(contact_phone);

        cv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        cv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            delete(contact_id, index);
            }
        });

        cv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               condition = edit(contact_name, contact_phone, contact_id);

            }
        });
    }

    /**
     * Method to call when clicked on edit button
     */
    public boolean edit( String n, String p, int id)
    {

        View view = getLayoutInflater().inflate(R.layout.dialog_edit,null);
        builder = new AlertDialog.Builder(this);
        builder.setView(view);
        dialog_update = builder.show();

        Button edit_cancel, edit_update;
        EditText edit_name, edit_phone;
        final String[] updated_name = new String[1];
        final String[] updated_phone = new String[1];

         edit_cancel = view.findViewById(R.id.dialog_edit_cancel);
         edit_update = view.findViewById(R.id.dialog_edit_update);
         edit_name = view.findViewById(R.id.dialog_edit_name);
         edit_phone = view.findViewById(R.id.dialog_edit_phone);

         edit_name.setText(n, TextView.BufferType.EDITABLE);
         edit_phone.setText(p,TextView.BufferType.EDITABLE);

         edit_cancel.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
               dialog_update.cancel();

             }
         });

         edit_update.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 updated_name[0] = edit_name.getText().toString();
                 updated_phone[0] = edit_phone.getText().toString();
                 contact.setName(updated_name[0]);
                 contact.setPhone(updated_phone[0]);
                 contact.setId(id);
                 obj.update(contact);
                 dialog_update.cancel();
                 Log.d("test002",contact.getName());
                 gl_name.setText(contact.getName());
                 gl_phone.setText(contact.getPhone());
             }
         });

        return true;
    }

    /**
     * Method to be call when clicked on delete button
     */
    public void delete (int id, int index)
    {

        View view = getLayoutInflater().inflate(R.layout.dialog_delete,null);
        builder = new AlertDialog.Builder(this);

        Button delete_cancel,delete_delete;
        delete_cancel = view.findViewById(R.id.dialog_delete_cancel);
        delete_delete = view.findViewById(R.id.dialog_delete_delete);

        builder.setView(view);
        dialog_delete = builder.create();
        dialog_delete.show();

        delete_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_delete.cancel();
            }
        });


        delete_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                obj.delete(id);
                dialog_delete.cancel();

                Intent intent = new Intent(ContactView.this,HomeActivity.class);

                intent.putExtra("bol_delete",true);
                intent.putExtra("contact_index",index);

                startActivity(intent);
                finish();

            }
        });
    }
}