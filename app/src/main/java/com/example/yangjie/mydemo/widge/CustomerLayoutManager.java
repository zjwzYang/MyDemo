package com.example.yangjie.mydemo.widge;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created on 2019-07-01 14:06
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
public class CustomerLayoutManager extends RecyclerView.LayoutManager {

    @Override
    public void measureChild(View child, int widthUsed, int heightUsed) {
        super.measureChild(child, widthUsed, heightUsed);
    }

    @Override
    public void measureChildWithMargins(View child, int widthUsed, int heightUsed) {
        super.measureChildWithMargins(child, widthUsed, heightUsed);
    }

    @Override
    public int getDecoratedMeasuredHeight(View child) {
        return super.getDecoratedMeasuredHeight(child);
    }

    @Override
    public int getDecoratedMeasuredWidth(View child) {
        return super.getDecoratedMeasuredWidth(child);
    }

    @Override
    public void layoutDecorated(View child, int left, int top, int right, int bottom) {
        super.layoutDecorated(child, left, top, right, bottom);
    }

    @Override
    public void layoutDecoratedWithMargins(View child, int left, int top, int right, int bottom) {
        super.layoutDecoratedWithMargins(child, left, top, right, bottom);
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return null;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
    }
}
