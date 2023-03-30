package com.example.materialdesign;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;


//自定义的Behavior需要继承CoordinatorLayout.Behavior<View>。
//父类Behavior中有很多的方法，但我们这里只需要关心onStartNestedScroll和onNestedPreScroll方法。
public class FooterBehavior extends CoordinatorLayout.Behavior<View> {
    private int directionChange;
    public FooterBehavior(Context context , AttributeSet attrs){
        super(context , attrs);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        //返回值表明这次滑动我们要不要关心
        //这里我们关注的是y轴方向上的滑动
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    //该方法用于处理滑动
    //这里的参数child就是我们的LinearLayout
    //dy则是我们水平方向上滑动的距离(向上滑动为正值，向下滑动为负值)
    //directionChange是滑动距离的累加值
    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {

        if(dy > 0 && directionChange < 0 || dy < 0 && directionChange > 0){
            child.animate().cancel();
            directionChange = 0 ;
        }
        directionChange += dy;
        //如果滑动的累加值大于设置的LinearLayout的高度，并且LinearLayout是VISIBLE状态
        //那么就隐藏LinearLayout
        if(directionChange > child.getHeight() && child.getVisibility() == View.VISIBLE){
            hide(child);

        //如果滑动的累计值小于0，并且LinearLayout是GONE状态
        //那么我们就显示LinearLayout
        }else if(directionChange < 0 && child.getVisibility() == View.GONE){
            show(child);
        }
    }

    //下面用到属性动画
    //在动画结束时，会回调方法onAnimationEnd(Animator animation)中对我们添加的LinearLayout进行处理

    //隐藏动画
    private void hide(final View view){
        ViewPropertyAnimator animator = view.animate()
                .translationY(view.getHeight())
                .setInterpolator(new FastOutSlowInInterpolator())
                .setDuration(200);
        animator.setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });
        animator.start();
    }

    //显示动画
    private void show(final View view){
        ViewPropertyAnimator animator = view.animate()
                .translationY(0)
                .setInterpolator(new FastOutSlowInInterpolator())
                .setDuration(200);
        animator.setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.VISIBLE);
            }
        });
        animator.start();
    }

}

