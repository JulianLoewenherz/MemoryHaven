package com.example.projectprototype;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectprototype.databinding.ActivityArchiveBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ArchiveActivity extends AppCompatActivity {
    ActivityArchiveBinding binding;
    GridView gridView;

    Integer[] image = {
      R.drawable.sky,R.drawable.mountain1,R.drawable.trees,R.drawable.lake,
            R.drawable.sky,R.drawable.mountain1,R.drawable.trees,R.drawable.lake,
            R.drawable.lake,R.drawable.mountain1
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityArchiveBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        gridView = findViewById(R.id.gridImage);
        gridView.setAdapter(new ImageAdapterGridView(this));



        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener((item)->{
            if(item.getItemId() == R.id.nav_home){
                Intent intent = new Intent(ArchiveActivity.this, SecondActivity.class);
                startActivity(intent);
            } else if (item.getItemId() == R.id.nav_profile) {
                Intent intent = new Intent(ArchiveActivity.this,ProfileActivity.class);
                startActivity(intent);
            } else if (item.getItemId() == R.id.nav_upload) {
                Intent intent = new Intent(ArchiveActivity.this, UploadActivity.class);
                startActivity(intent);
            }
            return true;
        });


    }
    private class ImageAdapterGridView extends BaseAdapter {
        private Context mContext;
        public ImageAdapterGridView(Context context){
            mContext = context;

        }

        @Override
        public int getCount() {
            return image.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if(convertView == null){
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(300,300));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(16,16,16,16);
            } else{
                imageView = (ImageView) convertView;

            }
            imageView.setImageResource(image[position]);
            return imageView;
        }
    }
}