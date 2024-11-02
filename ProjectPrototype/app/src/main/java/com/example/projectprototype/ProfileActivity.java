package com.example.projectprototype;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectprototype.databinding.ActivityArchiveBinding;
import com.example.projectprototype.databinding.ActivityProfileBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    ActivityProfileBinding binding;
    EditText profileUsername, profileEmail, profilePassword, profileConfirmpassword, profileJoincode;
    TextView titleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        profileUsername = findViewById(R.id.inputUsername);
        profileEmail = findViewById(R.id.inputEmail);
        profilePassword = findViewById(R.id.inputPassword);
        profileConfirmpassword = findViewById(R.id.inputConfirmPassword);
        profileJoincode = findViewById(R.id.inputJoinCode);
        titleName = findViewById(R.id.titlename);

        showUserData();


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener((item)->{
            if(item.getItemId() == R.id.nav_home){ //if user click profile icon, switch to profile page
                Intent intent = new Intent(ProfileActivity.this,SecondActivity.class);
                startActivity(intent);
            } else if (item.getItemId() == R.id.nav_upload) { //if user click upload icon, switch to upload page
                Intent intent = new Intent(ProfileActivity.this, UploadActivity.class);
                startActivity(intent);
            } else if (item.getItemId() == R.id.nav_archive) { //if user click archive icon, switch to archive page
                Intent intent = new Intent(ProfileActivity.this,ArchiveActivity.class);
                startActivity(intent);

            }

            return true;

        });
    }

    public void showUserData(){
        Intent intent = getIntent();

        String usernameUser = intent.getStringExtra("username");
        String emailUser = intent.getStringExtra("email");
        String passwordUser = intent.getStringExtra("password");
        String confirmPasswordUser = intent.getStringExtra("confirm password");
        String joinCodeUser = intent.getStringExtra("join code");
        titleName.setText(usernameUser);
        profileUsername.setText(usernameUser);
        profileEmail.setText(emailUser);
        profilePassword.setText(passwordUser);
        profileConfirmpassword.setText(confirmPasswordUser);
        profileJoincode.setText(joinCodeUser);
    }
}