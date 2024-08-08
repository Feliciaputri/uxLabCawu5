package com.example.labux_felis;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        ImageView imageView = findViewById(R.id.full_image_view);

        Intent intent = getIntent();
        int drawableResId = intent.getIntExtra("image", -1);
        if (drawableResId != -1) {
            imageView.setImageResource(drawableResId);
        }
    }
}
