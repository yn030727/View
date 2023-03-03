package com.example.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

public class MainActivity2 extends AppCompatActivity {

    View view ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        view = new View(this);
//        view.measure();
        ViewGroup viewGroup = new ViewGroup(this) {
            @Override
            protected void onLayout(boolean changed, int l, int t, int r, int b) {

            }
        };


        LinearLayout linearLayout = new LinearLayout(this);


//        手动测量View的宽/高
//        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(100, View.MeasureSpec.EXACTLY);
//        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(100, View.MeasureSpec.EXACTLY);
//        view.measure(widthMeasureSpec,heightMeasureSpec);

        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec((1 << 30) - 1 , View.MeasureSpec.AT_MOST);
        int heigthMeasureSpec = View.MeasureSpec.makeMeasureSpec((1 << 30) - 1 , View.MeasureSpec.AT_MOST);
        view.measure(widthMeasureSpec,heigthMeasureSpec);
        //（1 << 30) - 1  之前提到View的尺寸使用30位二进制表示，也就是最大是30个1
        //我们用理论值上能支持的最大值去构造MeasureSpec是合理的。

//
//        //错误用法
//        int widthMeasureSpec1 = View.MeasureSpec.makeMeasureSpec(-1 , View.MeasureSpec.UNSPECIFIED);
//        int heightMeasureSpec1 = View.MeasureSpec.makeMeasureSpec(-1 , View.MeasureSpec.UNSPECIFIED);
//        view.measure(widthMeasureSpec1,heightMeasureSpec1);

        //错误用法2
        view.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        view.layout(1,1,1,1);


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            int width = view.getMeasuredWidth();
            int height = view.getMeasuredHeight();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
//        view.post(new Runnable() {
//            @Override
//            public void run() {
//                int width = view.getMeasuredWidth();
//                int height = view.getMeasuredHeight();
//            }
//        });

        ViewTreeObserver observer = view.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width = view.getMeasuredWidth();
                int height = view.getMinimumHeight();
            }
        });
    }


}