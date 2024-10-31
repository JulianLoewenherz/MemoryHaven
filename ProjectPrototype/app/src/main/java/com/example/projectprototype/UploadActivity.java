package com.example.projectprototype;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.projectprototype.databinding.ActivitySecondBinding;
import com.example.projectprototype.databinding.ActivityUploadBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UploadActivity extends AppCompatActivity {

    // The uploading page
    ActivityUploadBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityUploadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener((item)->{
            if(item.getItemId() == R.id.nav_home){ //if user click home icon, switch to home page
                Intent intent = new Intent(UploadActivity.this, SecondActivity.class);
                startActivity(intent);
            } else if (item.getItemId() == R.id.nav_profile) { //if user click profile icon, switch to profile page
                Intent intent = new Intent(UploadActivity.this,ProfileActivity.class);
                startActivity(intent);
            } else if (item.getItemId() == R.id.nav_archive) { //if user click archive icon, switch to archive page
                Intent intent = new Intent(UploadActivity.this, ArchiveActivity.class);
                startActivity(intent);

            }


            return true;

        });


    }


}