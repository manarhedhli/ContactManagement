package com.example.contactmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText edusername, edpassword, edconfirmpassword;
    private Button  btnregister  ;
    TextView tvlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edusername = findViewById(R.id.ed_username_register);
        edpassword = findViewById(R.id.ed_password_register);
        edconfirmpassword = findViewById(R.id.ed_cpassword_register);
        btnregister = findViewById(R.id.btn_register_register);
        tvlogin = findViewById(R.id.tv_login_register);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edusername.getText().toString();
                String password = edpassword.getText().toString();
                String confirmpassword = edconfirmpassword.getText().toString();
                if (username.isEmpty() || password.isEmpty() || confirmpassword.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "All fields are required! ", Toast.LENGTH_SHORT).show();
                } else if (! password.equals(confirmpassword)) {
                    Toast.makeText(RegisterActivity.this, "Confirm password should be equals to password!", Toast.LENGTH_SHORT).show();
                } else {
                    UserManager manager = new UserManager(RegisterActivity.this);
                    manager.open();
                    long result = manager.register(username, password);
                    if (result != -1) {
                        edusername.setText("");
                        edpassword.setText("");
                        edconfirmpassword.setText("");

                        Toast.makeText(RegisterActivity.this, "Account added Successfuly. Try to connect!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });

        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(i);

            }
        });
    }
}