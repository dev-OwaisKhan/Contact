package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import DataBaseHandler.DBHandler;
import Model.Contact;
import RecyclerViewAdapter.RecyclerViewAdapter;


public class HomeActivity extends AppCompatActivity {

    private FloatingActionButton add;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<Contact> contactArrayList;
    Contact added_contact = new Contact();
    boolean test = false;
    // Object of the DBHandler class to perform all the DataBase Operations
    DBHandler obj = new DBHandler(HomeActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initializing the button and the recyclerView
        add = findViewById(R.id.home_add);
        recyclerView = findViewById(R.id.home_recyclerview);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setRecyclerView();


        if (contactArrayList.size() == 0)
        {
            Toast.makeText(HomeActivity.this,"Add Contact",Toast.LENGTH_SHORT);
            Intent intent = new Intent (HomeActivity.this, AddContact.class);
            startActivity(intent);
        }

        add.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {

                boolean bol_added,bol_delete;
                int index;

                Intent intent = new Intent (HomeActivity.this, AddContact.class);
                startActivity(intent);

                added_contact.setName(getIntent().getStringExtra("name"));
                added_contact.setPhone(getIntent().getStringExtra("phone"));
                bol_added = getIntent().getBooleanExtra("bol_added",false);

                bol_delete = getIntent().getBooleanExtra("bol_delete",false);
                index = getIntent().getIntExtra("contact_index",1);

                if (bol_added)
                {
                contactArrayList.add(added_contact);
                recyclerViewAdapter.notifyDataSetChanged();
                }

                if(bol_delete)
                {
                    contactArrayList.remove(index);
                    recyclerViewAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    private void setRecyclerView()
    {
        contactArrayList = new ArrayList<>();
        List<Contact> contactList = obj.all_contact();
        contactArrayList.addAll(contactList);


        recyclerViewAdapter = new RecyclerViewAdapter(HomeActivity.this, contactArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        test = true;

    }

        @Override
    protected void onResume() {
        super.onResume();
            if (test)
            {
                setRecyclerView();
            }
    }
}