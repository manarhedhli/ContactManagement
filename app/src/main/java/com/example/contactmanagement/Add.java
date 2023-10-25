package com.example.contactmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add extends AppCompatActivity {

    private EditText edfname, edlname, edphone;
    private Button btnconfirm, btncancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        edfname = findViewById(R.id.edfname_add);
        edlname = findViewById(R.id.edlname_add);
        edphone = findViewById(R.id.edphone_add);
        btnconfirm = findViewById(R.id.btnconfirm_add);
        btncancel = findViewById(R.id.btncancel_add);

        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = edfname.getText().toString();
                String lname = edlname.getText().toString();
                String phone = edphone.getText().toString();
                if (fname.isEmpty() || lname.isEmpty() || phone.isEmpty()){
                    Toast.makeText(Add.this, "Fill in all fiels! ", Toast.LENGTH_SHORT).show();
                } else {
                    Contact c = new Contact(fname, lname, phone);
                    ContactManager manager = new ContactManager(Add.this);
                    manager.open();
                    long result = manager.add(phone, lname, fname, Home.USER_ID);
                    edfname.setText(""); edlname.setText(""); edphone.setText("");
                    if (result != -1) {
                        Toast.makeText(Add.this, "Contact added Successfuly!", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(Add.this, "Phone number already exists!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add.this.finish();
            }
        });

    }
}