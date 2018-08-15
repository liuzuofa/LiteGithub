package com.summer.litegithub.util.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import com.summer.litegithub.contract.HomeContract;

/*
 *  项目名：  LiteGitHub
 *  包名：    com.summer.litegithub.util.widget
 *  文件名:   TabLayoutBehavior
 *  创建者:   Summers
 *  创建时间: 2018/8/1520:27
 *  描述：    TODO
 */
public class TabLayoutBehavior extends CoordinatorLayout.Behavior<View> {

    public TabLayoutBehavior(){

    }

    public TabLayoutBehavior(Context context, AttributeSet attrs) {
        super(context,attrs);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
    }
}
