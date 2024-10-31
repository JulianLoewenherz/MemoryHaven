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

public class ProfileActivity extends AppCompatActivity {

    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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