package com.example.projectprototype;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UploadActivity extends AppCompatActivity {

    // Define the pic id
    private static final int pic_id = 100;
    private static final int gallery_pic_id = 101;

    // Define the buttons and imageview type variable
    Button camera_btn;
    Button gallery_btn;
    ImageView image_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        // Initialize buttons and ImageView from XML
        camera_btn = findViewById(R.id.upload_camera);
        gallery_btn = findViewById(R.id.upload_gallery);
        image_id = findViewById(R.id.captured_image_view);

        // Set onClickListener for upload from gallery button
        gallery_btn.setOnClickListener(v -> {
            openGallery();
        });

        // Set onClickListener for upload from camera button
        camera_btn.setOnClickListener(v -> {
                openCamera();
        });
    }

    private void openCamera() {
        // Create the camera_intent ACTION_IMAGE_CAPTURE it will open the camera for capture the image
        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Start the activity with camera_intent, and request pic id
        startActivityForResult(camera_intent, pic_id);
    }

    private void openGallery() {
        // Create an intent to open the file picker
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

        // Set the type to "*/*" to allow all file types, but we'll narrow it down with EXTRA_MIME_TYPES
        intent.setType("*/*");

        // Specify the MIME types to limit to images, audio, and video files
        String[] mimeTypes = {"image/*", "audio/*", "video/*"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);

        // Start the activity and expect a result
        startActivityForResult(Intent.createChooser(intent, "Select File"), gallery_pic_id);
    }


    // This method will help to retrieve the image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check if the result is for the camera
        if (requestCode == pic_id && resultCode == RESULT_OK) {
            if (data != null && data.getExtras() != null) {
                // Get the photo as a Bitmap
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                // Set the image in ImageView
                image_id.setImageBitmap(photo);
            }
        }

        // Check if the result is for the gallery
        else if (requestCode == gallery_pic_id && resultCode == RESULT_OK) {
            if (data != null) {
                // Get the URI of the selected file
                Uri selectedFileUri = data.getData();
                if (selectedFileUri != null) {
                    // Display the selected image in the ImageView
                    image_id.setImageURI(selectedFileUri);
                }
            }
        }
    }
}
