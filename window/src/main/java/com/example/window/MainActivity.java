package com.example.window;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mFloatingButton;
    WindowManager.LayoutParams mLayoutParams;
    WindowManager mWindowManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //将一个Button添加到屏幕坐标为(100 ，300)的位置上。

        mFloatingButton = new Button(this);
        mFloatingButton.setText("button");

        mLayoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT , WindowManager.LayoutParams.WRAP_CONTENT , 0 , 0 , PixelFormat.TRANSPARENT);
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED ;
        mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        mLayoutParams.x = 100;
        mLayoutParams.y = 300;

        mWindowManager = new WindowManager() {
            @Override
            public Display getDefaultDisplay() {
                return null;
            }

            @Override
            public void removeViewImmediate(View view) {

            }

            @Override
            public void addView(View view, ViewGroup.LayoutParams params) {

            }

            @Override
            public void updateViewLayout(View view, ViewGroup.LayoutParams params) {

            }

            @Override
            public void removeView(View view) {

            }
        };
        mWindowManager.addView(mFloatingButton , mLayoutParams);

    }
}