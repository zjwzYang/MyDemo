package com.example.yangjie.mydemo.widge;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.BounceInterpolator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2020-01-07 13:43
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
public class GlobeGravityView extends View {

    private int radius = 100;
    private float pointX;
    private float pointY;
    private Paint circularPaint;
    private float viewWidth;
    private float viewHight;
    private float speed = 800;
    private float changePointY;
    private boolean isGravity;
    private ValueAnimator valueAnimator;

    private List<Float> saveCircle;

    public GlobeGravityView(Context context) {
        this(context, null);
    }

    public GlobeGravityView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GlobeGravityView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        circularPaint = new Paint();
        circularPaint.setColor(Color.parseColor("#FFCCCC"));
        circularPaint.setStyle(Paint.Style.FILL);
        saveCircle = new ArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = getMeasuredWidth();
        viewHight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (pointX != 0 && pointY != 0 && !isGravity) {
            if (pointX - radius < 0) {
                pointX = radius;
            } else if (pointX + radius > viewWidth) {
                pointX = viewWidth - radius;
            }
            if (pointY - radius < 0) {
                pointY = radius;
            } else if (pointY + radius > viewHight) {
                pointY = viewHight - radius;
            }
            canvas.drawCircle(pointX, pointY, radius, circularPaint);
        } else if (isGravity) {
//            Log.i("12345678", "changePointY: "+changePointY+"  "+());
            canvas.drawCircle(pointX, changePointY, radius, circularPaint);
        }

        canvas.save();
        for (int i = 0; i < saveCircle.size(); i++) {
            Float f = saveCircle.get(i);
            canvas.drawCircle(f, viewHight - radius, radius, circularPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isGravity = false;
                if (valueAnimator != null) {
                    valueAnimator.cancel();
                }
                pointX = event.getX();
                pointY = event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                isGravity = false;
                if (valueAnimator != null) {
                    valueAnimator.cancel();
                }
                pointX = event.getX();
                pointY = event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                isGravity = true;
                startAnimator();
                break;
        }
        return true;
    }

    private void startAnimator() {
        float endPoint = viewHight - radius;
        int time = (int) (endPoint / speed) * 1000;
        valueAnimator = ValueAnimator.ofFloat(pointY, endPoint);
        valueAnimator.setDuration(time);
        valueAnimator.setInterpolator(new BounceInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                changePointY = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                saveCircle.add(pointX);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        valueAnimator.start();
    }
}
