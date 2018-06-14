package com.mrzhangcustom_master.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mrzhangcustom_master.R;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        String imageUrl = getIntent().getStringExtra("imageUrl");
        ImageView image = findViewById(R.id.image);
        Glide
                .with(this)
                .load(imageUrl)
                .placeholder(R.drawable.imageload)
                .into(image);
    }
}
