package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import DataBaseHandler.DBHandler;
import Model.Contact;

public class AddContact extends AppCompatActivity {

    EditText name, phone;
    Button okay;
    ImageButton cancel;
    DBHandler obj = new DBHandler(AddContact.this);
    Contact ob = new Contact();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        name = findViewById(R.id.add_name);
        phone = findViewById(R.id.add_phone);
        okay = findViewById(R.id.add_okay);
        cancel = findViewById(R.id.add_cancel);

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phone.getText().toString().length() != 10 )
                {
                    Toast.makeText(AddContact.this,"Invalid Number",Toast.LENGTH_SHORT).show();
                }
                else if (name.getText().toString().isEmpty())
                {
                    Toast.makeText(AddContact.this,"Invalid Name",Toast.LENGTH_SHORT).show();
                }
                else
                {
                ob.setName(name.getText().toString());
                ob.setPhone(phone.getText().toString());
                obj.add(ob);
                Toast message = Toast.makeText(AddContact.this,"Contact Added",Toast.LENGTH_SHORT);
                message.show();
                finish();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}