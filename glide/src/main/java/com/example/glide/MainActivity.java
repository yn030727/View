package com.example.glide;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView image1 = findViewById(R.id.image_1);

        Glide.with(this)
                .load(R.drawable.draw_shape1)
                .placeholder(R.drawable.draw_shape2)
                .error(R.drawable.draw_shape2)
                .into(image1);
    }
}