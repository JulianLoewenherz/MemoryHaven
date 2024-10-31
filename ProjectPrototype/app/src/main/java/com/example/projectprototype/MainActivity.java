package com.example.projectprototype;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;

import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.button.MaterialButton;


public class MainActivity extends AppCompatActivity {

    // The Login Page
    // delete the forgot password and remember me
    // Create another page for family and friends
    // Archive - grid view, search bar
    private Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TextView emailaddress = (TextView) findViewById(R.id.emailaddress);
        TextView password = (TextView) findViewById(R.id.password);
        TextView btn = findViewById(R.id.textViewSignUp);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //when user click on the "Sign up" then switch to register page
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));

            }
        });



        login = findViewById(R.id.loginbtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //after user type in username and password, and press login button, if the password is correct, then login
                // successful, otherwise login failed
                if (emailaddress.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    //correct
                    Toast.makeText(MainActivity.this, "LOGIN SUCCESS", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                } else {
                    //incorrect
                    Toast.makeText(MainActivity.this, "LOGIN FAILED !!!", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}

