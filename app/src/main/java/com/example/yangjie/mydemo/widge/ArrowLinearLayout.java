package com.example.yangjie.mydemo.widge;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created on 2019/2/21 18:33
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
public class ArrowLinearLayout extends LinearLayout {

    private PathView mPathView;

    public ArrowLinearLayout(Context context) {
        this(context, null);
    }

    public ArrowLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ArrowLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(HORIZONTAL);
        setBackgroundColor(Color.WHITE);
    }

    public void setPathView(PathView pathView) {
        mPathView = pathView;
        int position = mPathView.getPosition();
        int currPos = mPathView.getCurrPos();
        for (int i = 0; i < position; i++) {
            ArrowTextView arrowTextView = new ArrowTextView(getContext());
            LayoutParams layoutParams = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
            arrowTextView.setLayoutParams(layoutParams);
            arrowTextView.setGravity(Gravity.CENTER);
            arrowTextView.setText("+成交");
            if (i <= currPos) {
                arrowTextView.setNeedBg(false);
                arrowTextView.setTextColor(Color.parseColor("#A0A0A0"));
            } else if (i == currPos + 1) {
                arrowTextView.setNeedBg(true);
                arrowTextView.setTextColor(Color.parseColor("#34AD39"));
                arrowTextView.setBgColor("#34AD39");
            } else {
                arrowTextView.setNeedBg(true);
                arrowTextView.setTextColor(Color.parseColor("#A0A0A0"));
                arrowTextView.setBgColor("#A0A0A0");
            }
            arrowTextView.setPadding(dip2px(4), dip2px(11), dip2px(4), dip2px(11));
            addView(arrowTextView);
        }
    }

    public void setCurrpos(int currPos) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ArrowTextView child = (ArrowTextView) getChildAt(i);
            if (i <= currPos) {
                child.setTextColor(Color.parseColor("#A0A0A0"));
                child.setNeedBg(false);
            } else if (i == currPos + 1) {
                child.setTextColor(Color.parseColor("#34AD39"));
                child.setBgColor("#34AD39");
                child.setNeedBg(true);
            } else {
                child.setTextColor(Color.parseColor("#A0A0A0"));
                child.setBgColor("#A0A0A0");
                child.setNeedBg(true);
            }
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
