package com.example.drawable;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
////        EditText editText = (EditText) findViewById(R.id.text1);
//        ImageView imageView = (ImageView) findViewById(R.id.imageView);
//        //如果是View，就是getBackground
//        TransitionDrawable drawable = (TransitionDrawable) imageView.getDrawable();
//        drawable.startTransition(1000);
//
//        ImageView imageView1 = (ImageView) findViewById(R.id.imageView2);
//        ScaleDrawable scaleDrawable = (ScaleDrawable) imageView1.getDrawable();
//        scaleDrawable.setLevel(1);
//
//        //剪切
//        ImageView testClip = (ImageView) findViewById(R.id.imageView3);
//        ClipDrawable clipDrawable = (ClipDrawable) testClip.getDrawable();
//        clipDrawable.setLevel(5000);
//        Log.d("Ning","1");
       Button button = (Button) findViewById(R.id.button);
//        Animation animation = AnimationUtils.loadAnimation(this,R.anim.filename);
//        button.startAnimation(animation);
//        button.setOnClickListener(this);


        //代码设置动画
        //创建了一个透明度动画，在300ms内将一个Button从0变为1
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        alphaAnimation.setDuration(3000);
        button.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                Log.d("Ning","click");
        }
    }
}