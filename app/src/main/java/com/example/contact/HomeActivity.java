package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import DataBaseHandler.DBHandler;
import Model.Contact;
import RecyclerViewAdapter.RecyclerViewAdapter;


public class HomeActivity extends AppCompatActivity {

    private FloatingActionButton add;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<Contact> contactArrayList;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Object of the DBHandler class to perform all the DataBase Operations
        DBHandler obj = new DBHandler(HomeActivity.this);
        Contact contacta = new Contact();
        contacta.setName("Owais");
        contacta.setPhone("1234569870");
        obj.add(contacta);

        // Initializing the button and the recyclerView
        add = findViewById(R.id.home_add);
        recyclerView = findViewById(R.id.home_recyclerview);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contactArrayList = new ArrayList<>();
        List<Contact> contactList = obj.all_contact();
        Log.d("hhhhh","kkoko");
        for(Contact contact : contactList)
        {
            contactArrayList.add(contact);
            Log.d("owais","id" +contact.getId()+"\n"+
                    "Name " +contact.getName()+ "\n"+
                    "Phone Number " +contact.getPhone());

        }




        recyclerViewAdapter = new RecyclerViewAdapter(HomeActivity.this, contactArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (HomeActivity.this,AddContact.class);
                startActivity(intent);
            }
        });
    }
}