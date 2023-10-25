package com.example.contactmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edusername, edpassword;
    private Button btnquit, btnlogin  ;
    TextView  tvregister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        edusername = findViewById(R.id.ed_username_main);
        edpassword = findViewById(R.id.ed_password_main);
        //btnquit = findViewById(R.id.btnquit_auth);
        btnlogin = findViewById(R.id.btn_login_main);
        tvregister = findViewById(R.id.tv_register_main);

        /*
        btnquit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
            }
        });
        */

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edusername.getText().toString();
                String password = edpassword.getText().toString();
                if (username.isEmpty() || password.isEmpty() ){
                    Toast.makeText(MainActivity.this, "Enter all your credentials! ", Toast.LENGTH_SHORT).show();
                }
                else {
                    UserManager manager = new UserManager(MainActivity.this);
                    manager.open();
                    Boolean result = manager.loginUser(username, password);
                    if (result == true) {
                        Intent i = new Intent(MainActivity.this, Home.class);
                        i.putExtra("USERNAME", username);
                        startActivity(i);
                        //Toast.makeText(MainActivity.this, "Connected!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Check your credentials", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);

            }
        });

        

    }
}