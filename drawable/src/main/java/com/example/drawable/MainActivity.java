package com.example.drawable;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        EditText editText = (EditText) findViewById(R.id.text1);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        TransitionDrawable drawable = (TransitionDrawable) imageView.getDrawable();
        drawable.startTransition(1000);
    }
}