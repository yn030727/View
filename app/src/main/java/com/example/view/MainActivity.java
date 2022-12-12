package com.example.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1.输出最短滑动距离
        int number = ViewConfiguration.get(getApplicationContext()).getScaledTouchSlop();
        Log.d("Ning app_MainActivity","TouchSLopo : "+number);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //2.追踪当前单击事件的速度
        VelocityTracker velocityTracker = VelocityTracker.obtain();
        velocityTracker.addMovement(event);
        //3.得到当前的滑动速度
        //在获取速度之前必须先计算速度。如果将时间设置为1000ms，如果你在1m内手指划过100像素
        //那么水平速度就是100
        velocityTracker.computeCurrentVelocity(1000);
        int xVelocity = (int) velocityTracker.getXVelocity();//水平方向上的速度
        int yVelocity = (int) velocityTracker.getYVelocity();
        //不需要使用的时候就重置回收内存
        velocityTracker.clear();
        velocityTracker.recycle();
        return super.onTouchEvent(event);
    }
}