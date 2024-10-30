package com.example.projectprototype;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    public int[] imageArray = {
            R.drawable.lake, R.drawable.trees, R.drawable.mountain1, R.drawable.sky,
            R.drawable.lake, R.drawable.trees, R.drawable.mountain1, R.drawable.sky,
            R.drawable.lake, R.drawable.trees, R.drawable.mountain1, R.drawable.sky,
            R.drawable.lake, R.drawable.trees, R.drawable.mountain1, R.drawable.sky,
            R.drawable.lake, R.drawable.trees, R.drawable.mountain1, R.drawable.sky,
            R.drawable.lake, R.drawable.trees, R.drawable.mountain1, R.drawable.sky,

    };

    public ImageAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return imageArray.length;
    }

    @Override
    public Object getItem(int position) {
        return imageArray[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(imageArray[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(340,350));


        return imageView;
    }
}
