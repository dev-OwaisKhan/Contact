package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import DataBaseHandler.DBHandler;
import Model.Contact;

public class AddContact extends AppCompatActivity {

    EditText name, phone;
    Button okay;
    ImageButton cancel;
    DBHandler obj = new DBHandler(AddContact.this);
    Contact ob = new Contact();
    HomeActivity abc = new HomeActivity();

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
                    String name_text = name.getText().toString();
                    String phone_text = phone.getText().toString();
                    ob.setName(name_text);
                    ob.setPhone(phone_text);
                    obj.add(ob);
                    Toast message = Toast.makeText(AddContact.this,"Contact Added",Toast.LENGTH_SHORT);
                    message.show();
                    Intent intent = new Intent(AddContact.this,HomeActivity.class);
                    intent.putExtra("bol_added",true);
                    intent.putExtra("name",name_text);
                    intent.putExtra("phone",phone_text);
                    startActivity(intent);
                    finish();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddContact.this,HomeActivity.class);
                intent.putExtra("check",false);
                startActivity(intent);
                finish();
            }
        });


    }
}