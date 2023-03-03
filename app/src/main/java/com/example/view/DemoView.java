package com.example.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DemoView extends View {
    public DemoView(Context context) {
        super(context);
    }

    public DemoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DemoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DemoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        //重写View的layout方法，改变它的宽高
        //因为layout过程和measure过程执行时间的不同
        //就会导致出现最终宽/高不等于测量宽/高的情况
        super.layout(l, t, r + 100, b + 100);
    }
}
