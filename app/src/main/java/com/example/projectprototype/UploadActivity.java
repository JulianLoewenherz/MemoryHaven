package com.example.projectprototype;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.Manifest;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import com.example.projectprototype.databinding.ActivityUploadBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UploadActivity extends AppCompatActivity {

    // Binding and button references
    private ActivityUploadBinding binding;

    private static final int REQUEST_CAMERA_PERMISSION = 100; // Constant for permission request
    private static final int pic_id = 101; // Unique request code for camera intent
    private Uri imageUri; // URI for storing the image;


    //creating varibles for buttons for uploading images
    private Button uploadGalleryButton;
    private Button uploadCameraButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //link ui with file
        setContentView(R.layout.activity_upload);

        // Set up view binding
        binding = ActivityUploadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Capture buttons from XML code
        uploadGalleryButton = findViewById(R.id.upload_gallery);
        uploadCameraButton = findViewById(R.id.upload_camera);


        // Set onClickListener for upload from gallery button
        uploadGalleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        // Set onClickListener for upload from camera button
        uploadCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCamera();
            }
        });

    }

    // Method to handle gallery upload
    private void openGallery() {
        // Implement gallery upload logic
    }

    // Method to handle camera upload
    private void openCamera() {
        // Check for camera permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Request permission if not granted
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        } else {
            // Create the URI for the image file
            imageUri = createURI();

            // Create the camera intent
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            // Add the URI to the intent to save the photo
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

            // Check if there is a camera app available to handle the intent
            if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                // Start the camera activity
                startActivityForResult(cameraIntent, pic_id);
            } else {
                // Show a toast if no camera app is available
                Toast.makeText(this, "No camera app available.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //createURI method is responsible for creating a file in the app's internal storage
    //and returning a URI (Uniform Resource Identifier) for that file.
    private Uri createURI() {
        // Create a file object for the image
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File imageFile = new File(getApplicationContext().getFilesDir(), "camera_photo_" + timeStamp + ".jpg");

        // Get the URI for the file using the FileProvider
        return FileProvider.getUriForFile(
                getApplicationContext(),
                getApplicationContext().getPackageName() + ".fileProvider",

                imageFile
        );
    }


    //once we get the image this helps us place it somewhere on the page
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check if the request code matches and the result is OK
        if (requestCode == pic_id && resultCode == RESULT_OK) {
            // You can set the image in an ImageView or process it as needed
            binding.capturedImageView.setImageURI(imageUri); //  ImageView in layout to display the image TEMP
        }
    }

    // Handle permissions request result
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, open the camera
                openCamera();
            } else {
                // Permission denied
                Toast.makeText(this, "Camera permission is required to take photos.", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
