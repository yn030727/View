package com.example.drawable;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.drawable.AnimationDrawable;
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
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @SuppressLint({"MissingInflatedId", "ObjectAnimatorBinding"})
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
//        Button button = (Button) findViewById(R.id.button);
//        Animation animation = AnimationUtils.loadAnimation(this,R.anim.filename);
//        button.startAnimation(animation);
//        button.setOnClickListener(this);


        //代码设置动画
        //创建了一个透明度动画，在300ms内将一个Button从0变为1
//        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
//        alphaAnimation.setDuration(3000);
//        button.startAnimation(alphaAnimation);
//        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });

//        Button mButton = (Button) findViewById(R.id.button);
//        mButton.setBackgroundResource(R.drawable.animation_drawable);
//        AnimationDrawable drawable = (AnimationDrawable) mButton.getBackground();
//        drawable.start();

        ListView listView = (ListView) findViewById(R.id.list1);
        String[] data = {"apply","banana","sfaf","sfafaw","fawerwe","24","fwefa","fawfawf"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this , android.R.layout.simple_list_item_1,data);
        listView.setAdapter(arrayAdapter);
//        Animation animation = AnimationUtils.loadAnimation(this , R.anim.anim_item);
//        LayoutAnimationController controller = new LayoutAnimationController(animation);
//        controller.setDelay(0.5f);
//        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
//        listView.setLayoutAnimation(controller);

//        Object mObject = new Object();
//        ObjectAnimator.ofFloat(mObject , "translationY" , -35).start();

        ValueAnimator colorAnim = ObjectAnimator.ofInt(listView , "backgroundColor" , 0xFFFF8080 , 0xFF8080FF);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.button:
//                Log.d("Ning","click");
        }
    }
}