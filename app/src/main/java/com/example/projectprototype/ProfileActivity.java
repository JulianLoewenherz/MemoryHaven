package com.example.projectprototype;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


import org.w3c.dom.Text;


public class ProfileActivity extends AppCompatActivity {


    private ActivityProfileBinding binding;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
   



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
        binding.inputJoinCode.setText(userId);


        String email = currentUser.getEmail();
        binding.inputEmail.setText(email);






        Button resetPasswordButton = findViewById(R.id.resetPasswordButton);
        resetPasswordButton.setOnClickListener(view ->{
            resetPassword(email); //Get user's email
        });










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
    //Set up a password reset function
    private void resetPassword(String email){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.sendPasswordResetEmail(email).addOnCompleteListener(task->{
            if(task.isSuccessful()){ //notify user the reset email has been sent
                Toast.makeText(ProfileActivity.this,"Reset email sent.",Toast.LENGTH_SHORT).show();
            }
            else { //notify user that reset email has failed to sent
                Toast.makeText(ProfileActivity.this, "Failed to send reset email.", Toast.LENGTH_SHORT).show();
            }


        });
    }








}
