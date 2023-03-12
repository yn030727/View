package com.example.window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.service.textservice.SpellCheckerService;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.InputQueue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {

    Button mFloatingButton;
    WindowManager.LayoutParams mLayoutParams;
    WindowManager mWindowManager;
    Button create_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        create_button.setOnClickListener(this);

        Activity activity = new Activity();


    }

    public void initView(){
        //初始化View的步骤
        create_button = (Button)findViewById(R.id.create_button);
        mWindowManager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);


    }

    @Override
    public void onClick(View v) {
        //将Window显示
        if(v == create_button){
            //将一个Button添加到屏幕坐标为(100 ，300)的位置上。

            mFloatingButton = new Button(this);
            mFloatingButton.setText("button");

            mLayoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT , WindowManager.LayoutParams.WRAP_CONTENT , 0 , 0 , PixelFormat.TRANSPARENT);
            mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED ;
            mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
            mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
            mLayoutParams.x = 100;
            mLayoutParams.y = 300;
            //移动Touch事件
            mFloatingButton.setOnTouchListener(this);
            mWindowManager.addView(mFloatingButton , mLayoutParams);
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d("Ning","onTouch");
        int rawX = (int)event.getX();
        int rawY = (int)event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE: {
                mLayoutParams.x = rawX;
                mLayoutParams.y = rawY;
                mWindowManager.updateViewLayout(mFloatingButton, mLayoutParams);
                break;
            }
            default:
                break;
        }
        return false;
    }
    
    
    
    @Override
    protected void onDestroy() {
        //删除Window，就是删除其中的View。
        try {
            mWindowManager.removeView(mFloatingButton);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        super.onDestroy();
    }



}