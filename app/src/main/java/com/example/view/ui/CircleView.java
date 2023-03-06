package com.example.view.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.view.R;

public class CircleView extends View {
    //定义变量
    private int mColor = Color.RED;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //首先加载自定义属性集合CircleView
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        //接着解析CircleView集合中的circle_color属性(如果没有指定红色为默认值)
        mColor = a.getColor(R.styleable.CircleView_circle_color,Color.RED);
        //recycle来首先资源
        a.recycle();
        init();
    }

    //方法

    //初始化
    private void init(){
        mPaint.setColor(mColor);
    }

    //重写onDraw方法
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //处理padding问题
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();

        //padding是内部到当前View边框的距离
        //width是当前View的宽度
        //所以减去之后的宽高就是绘制出来的结果
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;
        int radius = Math.min(width , height) / 2;
        //绘制的过程
        canvas.drawCircle(paddingLeft + width / 2 , paddingTop + height / 2 , radius , mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //对于继承View的自定义View需要处理wrap_content
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if(widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(200 , 200);
        }else if(widthSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(200 , heightSpecSize);
        }else if(heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSpecSize,200);
        }
    }
}
