package com.example.materialdesign;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.AppBarLayout;

import java.util.regex.Matcher;

public class FooterBehaviorAppBar extends CoordinatorLayout.Behavior<View> {
    public FooterBehaviorAppBar(Context context , AttributeSet attrs){
        super(context , attrs);
    }

    //layoutDependsOn方法用来返回我们需要关心的类
    //第一个参数parent是当前的CoordinatorLayout
    //第二个参数child是我们设置这个Behavior的View
    //第三个参数dependency是我们关心的那个View
    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        //返回值这句话表示我们关心的类是AppBarLayout
        return dependency instanceof AppBarLayout;
    }

    //根据我们关心的View的变化，来对我们设置Behavior的View进行一系列的处理
    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        //根据dependency(AppBarLayout) 的垂直移动距离来设置child(LinearLayout)的移动距离
        float translationY = Math.abs(dependency.getY());
        child.setTranslationY(translationY);
        return true;
    }
}
