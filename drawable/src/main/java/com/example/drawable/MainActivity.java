package com.example.drawable;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
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
        //如果是View，就是getBackground
        TransitionDrawable drawable = (TransitionDrawable) imageView.getDrawable();
        drawable.startTransition(1000);

        ImageView imageView1 = (ImageView) findViewById(R.id.imageView2);
        ScaleDrawable scaleDrawable = (ScaleDrawable) imageView1.getDrawable();
        scaleDrawable.setLevel(1);

        //剪切
        ImageView testClip = (ImageView) findViewById(R.id.imageView3);
        ClipDrawable clipDrawable = (ClipDrawable) testClip.getDrawable();
        clipDrawable.setLevel(5000);
    }
}