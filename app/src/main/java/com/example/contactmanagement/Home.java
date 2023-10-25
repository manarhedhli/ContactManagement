package com.example.contactmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    private TextView tvusername;
    private Button btnadd, btndisplay, btnlogout;
    public static String USER_ID ;

    public static ArrayList<Contact> data = new ArrayList<Contact>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent i = this.getIntent();
        Bundle b = i.getExtras();
        USER_ID = b.getString("USERNAME");
        tvusername = findViewById(R.id.tvusername_home);
        btnadd = Home.this.findViewById(R.id.btnadd_home);
        btndisplay = Home.this.findViewById(R.id.btndisplay_home);
        btnlogout = Home.this.findViewById(R.id.btn_logout_home);


        tvusername.setText("Welecome "+USER_ID );

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Add.class);
                startActivity(i);
            }
        });
        btndisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Display.class);
                startActivity(i);
            }
        });
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, MainActivity.class);
                startActivity(i);

            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}