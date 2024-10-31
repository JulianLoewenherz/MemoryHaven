package com.example.projectprototype;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectprototype.databinding.ActivityArchiveBinding;
import com.example.projectprototype.databinding.ActivityProfileBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.projectprototype.databinding.ActivityProfileBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        //initializing view binding
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //initialize firebase authentication
        mAuth = FirebaseAuth.getInstance();

        // Get the current user
        FirebaseUser currentUser = mAuth.getCurrentUser();


        //display uerserID
        String userId = currentUser.getUid();
        binding.userIdTextView.setText("User ID: " + userId);

        //set up bottom navigation
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
}