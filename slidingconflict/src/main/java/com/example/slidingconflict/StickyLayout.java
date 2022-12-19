package com.example.slidingconflict;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/*
public class StickyLayout extends LinearLayout {
    public StickyLayout(Context context) {
        super(context);
    }

    private int mTouchSlop;

    //分别记录上次滑动的坐标
    private int mLastX = 0;
    private int mLastY = 0;
    //分别记录上次滑动的坐标(onInterceptTouchEvent)
    private int mLastXIntercept = 0;
    private int mLastYIntercept = 0;
    ...

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int intercepted = 0;
        int x = (int)ev.getX();
        int y = (int)ev.getY();

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                mLastXIntercept = x;
                mLastYIntercept = y;
                mLastX = x;
                mLastY = y;
                intercepted = 0;
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                //获得当前的位移
                int deltaX = x - mLastXIntercept;
                int deltaY = y - mLastYIntercept;
                if (mDisallowInterceptTouchEventOnHeader && y <= getHeaderHeight()){
                    intercepted = 0;
                }else if(Math.abs(deltaY) < Math.abs(deltaX)){
                    intercepted = 0;
                }else if(mStatus == STATUS_EXPANDED && deltaY <= -mTouchSlop){
                    intercepted = 1;
                }else if(mGiveUpTouchEventListener != null){
                    if(mGiveUpTouchEventListener.giveUpTouchEvent(event) && deltaY >= mTouchSlop){
                        intercepted = 1;
                    }
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                intercepted = 0;
                mLastXIntercept = mLastYIntercept = 0;
                break;
            }
            default:{
                break;
            }
        }
        return intercepted !=0 && mIsSticky;
    }

    //处理事件

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(!mIsSticky){
            return true;
        }
        int x = (int)event.getX();
        int y = (int) event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                break;
            }
            case MotionEvent.ACTION_MOVE:{
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                mHeaderHeight += deltaY;
                setHeaderHeight(mHeaderHeight);
                break;
            }
            case MotionEvent.ACTION_UP:{
                //这里做一下判断，当松开手的时候，会自动向两边滑动
                //滑动的方向，要看当前所处的位置
                int destHeight = 0;
                if(mHeaderHeight <= mOriginalHeaderHeight * 0.5){
                    destHeight = 0;
                    mStatus = STATUS_COLLAPSED;
                }else{
                    destHeight = mOriginalHeaderHeight;
                    mStatus = STATUS_EXPANDED;
                }
                //缓慢滑向终点
                this.smoothSetHeaderHeight(mHeaderHeight , destHeight , 500);
                break;
            }
            default:{
                break;
            }
        }
        mLastX = x;
        mLastY = y;
        return true;
    }
    ...
}
*/
