package com.example.yangjie.mydemo.widge;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created on 2018/12/27 13:30
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
public class MyView extends View {

    private Paint mPaint;

    private Path mPath;

    private float startX;
    private float startY;

    private float moveX = 50;
    private float moveY = 500;

    private float centerX;
    private float centerY;

    private float endX;
    private float endY;

    private int heigth;
    private int width;

    private Paint bluePaint;
    private Path bluePath;

    private boolean isEr = true;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);

        mPath = new Path();

        DisplayMetrics dm = getResources().getDisplayMetrics();
        heigth = dm.heightPixels;
        width = dm.widthPixels;

        startX = 50;
        startY = heigth / 2;
        moveX = width / 2;
        moveY = heigth / 4;
        endX = width - 50;
        endY = heigth / 2;

        bluePaint = new Paint();
        bluePaint.setColor(Color.BLUE);
        bluePaint.setStyle(Paint.Style.STROKE);
        bluePaint.setStrokeWidth(10);
        bluePath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (isEr) {
            mPath.reset();
            mPath.moveTo(startX, startY);
            mPath.quadTo(moveX, moveY, endX, endY);
            canvas.drawPath(mPath, mPaint);

            bluePath.reset();
            bluePath.moveTo(startX, startY);
            bluePath.lineTo(moveX, moveY);
            bluePath.lineTo(endX, endY);
            canvas.drawPath(bluePath, bluePaint);
        } else {
            mPath.reset();
            mPath.moveTo(startX, startY);
            mPath.cubicTo(centerX, centerY, moveX, moveY, endX, endY);
            canvas.drawPath(mPath, mPaint);

            bluePath.reset();
            bluePath.moveTo(startX, startY);
            bluePath.lineTo(centerX, centerY);
            bluePath.lineTo(moveX, moveY);
            bluePath.lineTo(endX, endY);
            canvas.drawPath(bluePath, bluePaint);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                moveX = event.getX();
                moveY = event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                moveX = event.getX();
                moveY = event.getY();
                invalidate();
                break;
        }
        return true;
    }

    public void setEr() {
        isEr = true;
        startX = 50;
        startY = heigth / 2;
        moveX = width / 2;
        moveY = heigth / 4;
        endX = width - 50;
        endY = heigth / 2;
    }

    public void setSan() {
        isEr = false;
        startX = 50;
        startY = heigth / 2;
        moveX = width / 2;
        moveY = heigth / 4;
        centerX = 150;
        centerY = heigth / 4 * 3;
        endX = width - 50;
        endY = heigth / 2;
    }
}
