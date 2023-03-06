package com.example.slidingconflict;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

//自定义控件
public class HorizontalScrollViewEx extends ViewGroup {
    //1. 初始化变量
    private static final String TAG = "HorizontalScrollViewEx";

    private int mChildrenSize;
    private int mChildWidth;
    private int mChildIndex;
    // 分别记录上次滑动的坐标
    private int mLastX = 0;
    private int mLastY = 0;
    // 分别记录上次滑动的坐标(onInterceptTouchEvent)
    private int mLastXIntercept = 0;
    private int mLastYIntercept = 0;
    //帮助弹性滑动
    private Scroller scroller;
    //帮助测量速度
    private VelocityTracker mVelocityTracker;

    public HorizontalScrollViewEx(Context context) {
        super(context);
        init();
    }

    public HorizontalScrollViewEx(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HorizontalScrollViewEx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childLeft = 0;
        final int childCount = getChildCount();
        //子元素的数量
        mChildrenSize = childCount;

        for (int i = 0; i < childCount; i++) {
            //获取子元素
            final View childView = getChildAt(i);
            //子元素可见
            if (childView.getVisibility() != View.GONE) {
                final int childWidth = childView.getMeasuredWidth();
                mChildWidth = childWidth;
                childView.layout(childLeft, 0, childLeft + childWidth, childView.getMeasuredHeight());
                childLeft += childWidth;
            }
        }
    }


    //初始化
    private void init() {
        scroller = new Scroller(getContext());
        mVelocityTracker = VelocityTracker.obtain();
    }

    //重写方法，修改其中ACTION_MOVE的条件
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                intercepted = false;
                //这一句话是用来优化滑动体验而加入的
                if (!scroller.isFinished()) {
                    scroller.abortAnimation();
                    intercepted = true;
                }
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                //mLastXIntercept上次滑动到达的坐标
                //当前坐标减去上一次获得两次之间的距离差
                int deltaX = x - mLastXIntercept;
                int deltaY = y - mLastYIntercept;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    //如果水平距离长，那么就截断，交给父容器处理
                    intercepted = true;
                } else {
                    //如果垂直距离长，交给子元素的ListView处理
                    intercepted = false;
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                intercepted = false;
                break;
            }
        }
        Log.d(TAG, "intercepted=" + intercepted);
        mLastX = x;
        mLastY = y;
        mLastXIntercept = x;
        mLastYIntercept = y;
        return intercepted;
    }

    //处理点击事件，拦截下来之后的消耗
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //滑动速度
        mVelocityTracker.addMovement(event);
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                //处理点击
                if (!scroller.isFinished()) {
                    scroller.abortAnimation();
                }
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastX;
                //不用改变Y的相对位置
                int deltaY = y - mLastY;
                //负数表示向右滑动
                scrollBy(-deltaX, 0);
            }
            case MotionEvent.ACTION_UP: {
                //获取滑动的距离
                int scrollX = getScrollX();
                //滑动到哪个父布局上的item
                int scrollToChildIndex = scrollX / mChildWidth;
                mVelocityTracker.computeCurrentVelocity(1000);
                float xVelocity = mVelocityTracker.getXVelocity();
                if (Math.abs(xVelocity) >= 50) {
                    mChildIndex = xVelocity > 0 ? mChildIndex - 1 : mChildIndex + 1;
                } else {
                    mChildIndex = (scrollX + mChildWidth / 2) / mChildWidth;
                }
                mChildIndex = Math.max(0, Math.min(mChildIndex, mChildrenSize - 1));
                int dx = mChildIndex * mChildWidth - scrollX;
                smoothScrollBy(dx, 0);
                mVelocityTracker.clear();
                break;
            }
            default:
                break;
        }

        mLastX = x;
        mLastY = y;
        return true;
    }

    private void smoothScrollBy(int dx , int dy) {
        scroller.startScroll(getScrollX(), 0, dx, 0, 500);
        invalidate();
    }
    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }

    //以下部分和滑动冲突无关
    @Override
    protected void onDetachedFromWindow() {
        mVelocityTracker.recycle();
        super.onDetachedFromWindow();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = 0;
        int measuredHeight = 0;
        final int childCount = getChildCount();
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthSpaceSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpaceSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        if (childCount == 0) {
            setMeasuredDimension(0, 0);
        } else if(widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST){
            final View childView = getChildAt(0);
            measuredWidth = childView.getMeasuredWidth() * childCount;
            measuredHeight = childView.getMeasuredHeight();
            setMeasuredDimension(measuredWidth, measuredHeight);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            final View childView = getChildAt(0);
            measuredHeight = childView.getMeasuredHeight();
            setMeasuredDimension(widthSpaceSize, childView.getMeasuredHeight());
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            final View childView = getChildAt(0);
            measuredWidth = childView.getMeasuredWidth() * childCount;
            setMeasuredDimension(measuredWidth, heightSpaceSize);
        }
    }

}
