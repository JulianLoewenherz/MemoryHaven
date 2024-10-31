package com.example.projectprototype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {

    private Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        EditText passwordInput = findViewById(R.id.inputPassword);
        EditText confirmPasswordinput = findViewById(R.id.inputConfirmPassword);
        EditText usernameInput = findViewById(R.id.inputUsername);
        EditText emailInput = findViewById(R.id.inputEmail);

        register = findViewById(R.id.btnRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = passwordInput.getText().toString();
                String confirmPassword = confirmPasswordinput.getText().toString();
                String username = usernameInput.getText().toString();
                String email = emailInput.getText().toString();

                if(password.isEmpty() || confirmPassword.isEmpty() || username.isEmpty() || email.isEmpty()){ //if password and confirm password field is empty, then show "please fill in all field"
                    Toast.makeText(RegisterActivity.this,"Please fill in all field", Toast.LENGTH_SHORT).show();
                } else if (password.equals(confirmPassword)) { //if password and confirm password matched, then show "register success"
                    Toast.makeText(RegisterActivity.this,"Register Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);

                }
                else { //if password and confirm password does not match,
                    Toast.makeText(RegisterActivity.this, "Password do not match", Toast.LENGTH_SHORT).show();
                }

            }
        });
        TextView btn = findViewById(R.id.alreadyHaveAccount);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //when user click on already have account, then switch back to login screen
                startActivity(new Intent(RegisterActivity.this,MainActivity.class));
            }
        });

        }
}
