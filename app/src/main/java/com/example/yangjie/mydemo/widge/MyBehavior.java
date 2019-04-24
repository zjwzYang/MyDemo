package com.example.yangjie.mydemo.widge;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created on 2019/4/22 16:20
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
public class MyBehavior extends CoordinatorLayout.Behavior<View> {

    public MyBehavior() {
    }

    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    //用于响应从属布局的变化
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        float translationY = Math.abs(dependency.getTop());//获取更随布局的顶部位置
        Log.i("12345678", "onDependentViewChanged: " + dependency.getTop() + "   " + translationY);
        child.setTranslationY(translationY);
        return true;
    }
}
