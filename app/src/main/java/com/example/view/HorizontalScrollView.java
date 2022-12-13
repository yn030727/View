package com.example.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Scroller;

public class HorizontalScrollView extends ViewGroup {
    public HorizontalScrollView(Context context) {
        super(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
    Scroller scroller = new Scroller(getContext());

    //缓慢移动到指定位置
    private void smoothScrollTo(int destX , int destY){
        int scrollX = getScrollX();
        int delta = destX - scrollX;
        //1000ms内滑动到destX，效果就是慢慢滑动
        scroller.startScroll(scrollX,0,delta,0,1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if(scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);

    }


}
