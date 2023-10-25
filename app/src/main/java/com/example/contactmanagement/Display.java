package com.example.contactmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Display extends AppCompatActivity {

    private RecyclerView recyclerView;
    EditText ed_search_display;
    ArrayList<Contact> data =  new ArrayList<Contact>();

    static Boolean CALL_PHONE_PERMISSION= false;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                CALL_PHONE_PERMISSION = true;
            } else {
                Toast.makeText(this, "Call phone permission should be granted!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        recyclerView = findViewById(R.id.rvContact_display);
        ed_search_display = findViewById(R.id.ed_search_display);

        ContactManager contactManager = new ContactManager(Display.this);
        contactManager.open();
        data = contactManager.getAllContact(Home.USER_ID) ;

        MyRecyclerContactAdapter ad = new MyRecyclerContactAdapter(Display.this, data);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(ad);



        ed_search_display.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchText = s.toString();

                ArrayList<Contact> filteredList = new ArrayList<>();

                // Handle empty search
                if (searchText.isEmpty()) {
                    ad.updateData(data);
                } else {
                    for (Contact contact : data) {
                        if (contact.phone.contains(searchText)) {
                            filteredList.add(contact);
                        }
                    }
                    ad.updateData(filteredList);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        /*  Methode 1
        ArrayAdapter ad = new ArrayAdapter( Display.this,
                android.R.layout.simple_list_item_1,
                Home.data
        );
        listView.setAdapter(ad);
        */
    }


}