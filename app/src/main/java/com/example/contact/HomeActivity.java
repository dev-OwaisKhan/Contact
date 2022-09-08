package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.service.controls.actions.FloatAction;
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
    private ArrayList<Contact> contactArrayList;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Object of the DBHandler class to perform all the DataBase Operations
        DBHandler obj = new DBHandler(HomeActivity.this);

        // Initializing the button and the recyclerView
        add = findViewById(R.id.add);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerViewAdapter = new RecyclerViewAdapter(HomeActivity.this,);
        contactArrayList = new ArrayList<>();

        List<Contact> contactList = obj.all_contact();
        for(Contact contact : contactList)
        {
            contactArrayList.add(contact);
        }

        recyclerViewAdapter = new RecyclerViewAdapter(HomeActivity.this,contactArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}