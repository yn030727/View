package com.example.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.Scroller;

import java.util.concurrent.RecursiveTask;

public class MainActivity extends AppCompatActivity  {
    GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);







//        //1.输出最短滑动距离
//        int number = ViewConfiguration.get(getApplicationContext()).getScaledTouchSlop();
//        Log.d("Ning app_MainActivity","TouchSLopo : "+number);
//
//        //Activity activity = new Activity();
//        //Window
//        //1.创建GestureDetector对象
//        gestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
//            @Override
//            public boolean onDown(MotionEvent e) {
//                //手指轻轻触摸屏幕的一瞬间，由一个ACTION_DOWN的触发
//                return false;
//            }
//
//            @Override
//            public void onShowPress(MotionEvent e) {
//                //手指轻轻触摸屏幕，尚未松开或拖动，由一个ACTION_DOWN触发
//            }
//
//            @Override
//            public boolean onSingleTapUp(MotionEvent e) {
//                //手指轻触屏幕后送开，伴随着一个MotionEventACTION_UP而触发，这是单击行为
//                return false;
//            }
//
//            @Override
//            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//                //手指按下屏幕并拖动，由1个ACTION_DOWN，多个ACTION_MOVE触发，这是拖动行为
//                return false;
//            }
//
//            @Override
//            public void onLongPress(MotionEvent e) {
//                //用户一直按
//            }
//
//            @Override
//            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//                //用户按下触摸屏，快速滑动后松开
//                return false;
//            }
//        });
//        //2.解决长按屏幕无法拖动的现象
//        gestureDetector.setIsLongpressEnabled(false);
//
//
//        RecyclerView recyclerView = new RecyclerView(this);
//
//
//        //将目标View(targetView)在100ms内从原始位置向右移动100像素
//        ObjectAnimator.ofFloat(recyclerView,"translationX",0,100).setDuration(100).start();
//
//
//        final int startX = 0;
//        final int deltaX = 100;
//        ValueAnimator animator = ValueAnimator.ofInt(0,1).setDuration(1000);
//        //在动画每一帧到来的时候获取动画的完成的比例
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float fraction = animator.getAnimatedFraction();
//                //根据这个比例计算出View所要滑动的距离
//                recyclerView.scrollTo(startX + (int)(deltaX * fraction) , 0);
//            }
//        });
//        animator.start();
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        //2.追踪当前单击事件的速度
//        VelocityTracker velocityTracker = VelocityTracker.obtain();
//        velocityTracker.addMovement(event);
//        //3.得到当前的滑动速度
//        //在获取速度之前必须先计算速度。如果将时间设置为1000ms，如果你在1m内手指划过100像素
//        //那么水平速度就是100
//        velocityTracker.computeCurrentVelocity(1000);
//        int xVelocity = (int) velocityTracker.getXVelocity();//水平方向上的速度
//        int yVelocity = (int) velocityTracker.getYVelocity();
//        //不需要使用的时候就重置回收内存
//        velocityTracker.clear();
//        velocityTracker.recycle();
//
//        //3.接管目标View的onTouchEvent方法，在待监听View的onTouchEvent方法中实现
//        boolean consume = gestureDetector.onTouchEvent(event);
//        return consume;
//
//
//    }

//
//    Scroller scroller = new Scroller(this);
//
//    //缓慢移动到指定位置
//    private void smoothScrollTo(int destX , int destY){
//        int scrollX = getCurrentFocus().getScrollX();
//        int delta = destX - scrollX;
//        //1000ms内滑动到destX，效果就是慢慢滑动
//        scroller.startScroll(scrollX,0,delta,0,1000);
//
//    }



}