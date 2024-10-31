package com.example.projectprototype;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailaddress, password;
    private MaterialButton login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize views
        emailaddress = findViewById(R.id.emailaddress);
        password = findViewById(R.id.password);
        login = findViewById(R.id.loginbtn);
        TextView btnSignUp = findViewById(R.id.textViewSignUp);

        // Set up "Sign Up" button to navigate to RegisterActivity
        btnSignUp.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, RegisterActivity.class))
        );

        // Set up login button click event
        login.setOnClickListener(v -> {
            String email = emailaddress.getText().toString().trim();
            String pass = password.getText().toString().trim();

            // Check if fields are empty
            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Attempt to log in the user
            loginUser(email, pass);
        });
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Login successful
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();

                        // Proceed to the next activity
                        startActivity(new Intent(MainActivity.this, SecondActivity.class));
                        finish();  // Finish this activity so it can't be returned to with back button
                    } else {
                        // Login failed
                        Toast.makeText(MainActivity.this, "Login failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
