package com.example.contact;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import DataBaseHandler.DBHandler;

public class ContactView extends AppCompatActivity {

    static AlertDialog.Builder builder;
    AlertDialog dialog_delete, dialog_update;
    ImageButton cv_cancel, cv_delete;
    TextView phone, name;
    Button cv_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_view);

        DBHandler obj = new DBHandler(ContactView.this);

        cv_cancel = findViewById(R.id.cv_cancel);
        cv_delete = findViewById(R.id.cv_delete);
        cv_edit = findViewById(R.id.cv_edit);
        name  = findViewById(R.id.cv_name);
        phone = findViewById(R.id.cv_phone);

        String contact_name, contact_phone;
        int contact_id;
        contact_name = getIntent().getStringExtra("name");
        contact_phone = getIntent().getStringExtra("phone");
        contact_id = getIntent().getIntExtra("id",0);

        name.setText(contact_name);
        phone.setText(contact_phone);

        cv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        cv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            delete();
            }
        });

        cv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    public void delete ()
    {

        View view = getLayoutInflater().inflate(R.layout.dialog_delete,null);
        builder = new AlertDialog.Builder(this);

        Button cancel,delete;
        cancel = view.findViewById(R.id.dialog_cancel);
        delete = view.findViewById(R.id.dialog_delete);

        builder.setView(view);
        dialog_delete = builder.create();
        dialog_delete.show();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}