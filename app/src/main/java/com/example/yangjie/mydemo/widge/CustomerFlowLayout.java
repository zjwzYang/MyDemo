package com.example.yangjie.mydemo.widge;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yangjie.mydemo.R;

import java.util.List;

/**
 * Created on 2020-01-01 15:20
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
public class CustomerFlowLayout extends ViewGroup {

    private int lineSpace;
    private int rowSpace;

    public CustomerFlowLayout(Context context) {
        this(context, null);
    }

    public CustomerFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomerFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomerFlowLayout);
        lineSpace = array.getDimensionPixelSize(R.styleable.CustomerFlowLayout_lineSpace, 20);
        rowSpace = array.getDimensionPixelSize(R.styleable.CustomerFlowLayout_rowSpace, 10);
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = widthSize;
        int height;

        int emptyWidth = widthSize;

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            int row = 1;
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                int childWidth = child.getMeasuredWidth();
                if (emptyWidth >= childWidth + lineSpace) {
                    emptyWidth -= (childWidth + lineSpace);
                } else {
                    row++;
                    emptyWidth = widthSize - childWidth;
                }
            }
            int childHeight = getChildAt(0).getMeasuredHeight();
            height = childHeight * row + (row - 1) * rowSpace;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int row = 0;
        int right = 0;
        int bottom = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            int childHeight = child.getMeasuredHeight();
            int childWidth = child.getMeasuredWidth();

            right += childWidth;
            bottom = (childHeight + rowSpace) * row + childHeight;

            if (right > r) {
                row++;
                right = childWidth;
                bottom = (childHeight + rowSpace) * row + childHeight;
            }
            child.layout(right - childWidth, bottom - childHeight, right, bottom);
            right += lineSpace;
        }
    }

    public void addLables(List<String> lables) {
        if (lables == null || lables.size() == 0) {
            return;
        }
        removeAllViews();
        for (String lable : lables) {
            TextView textView = new TextView(getContext());
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            textView.setText(lable);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
            textView.setBackgroundColor(Color.RED);
            textView.setLayoutParams(params);
            addView(textView);
        }
    }

    public void setLineSpace(int lineSpace) {
        this.lineSpace = lineSpace;
        invalidate();
    }

    public void setRowSpace(int rowSpace) {
        this.rowSpace = rowSpace;
        invalidate();
    }
}
