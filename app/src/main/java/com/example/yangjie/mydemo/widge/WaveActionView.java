package com.example.yangjie.mydemo.widge;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created on 2020-01-06 10:51
 * 波浪.
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
public class WaveActionView extends View {

    private Paint wavePaint;
    private Path wavePath;

    private int waveWidth;
    private int waveHeight;

    private ValueAnimator animator;
    private int value;

    public WaveActionView(Context context) {
        this(context, null);
    }

    public WaveActionView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveActionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        wavePaint = new Paint();
        wavePaint.setColor(Color.RED);
        wavePaint.setAntiAlias(true);
//        wavePaint.setStyle(Paint.Style.STROKE);
        wavePaint.setStrokeWidth(10f);

        wavePath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        waveWidth = getMeasuredWidth() / 3;
        waveHeight = getMeasuredHeight() / 4;

        startAnimation();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        wavePath.reset();
//        wavePaint.setColor(Color.BLUE);
//        wavePath.moveTo(0, waveHeight);
//        wavePath.lineTo(waveWidth * 3, waveHeight);
//        canvas.drawPath(wavePath, wavePaint);

        wavePaint.setColor(Color.RED);
        wavePath.reset();
        int start = -2 * waveWidth + value;
        wavePath.moveTo(start, waveHeight);
        for (int i = 0; i < 5; i++) {
            if (i % 2 == 0) {
                wavePath.quadTo(start + waveWidth * i + waveWidth / 2, 0,
                        start + waveWidth * (i + 1), waveHeight);
            } else {
                wavePath.quadTo(start + waveWidth * i + waveWidth / 2, waveHeight * 2,
                        start + waveWidth * (i + 1), waveHeight);
            }
        }

        wavePath.lineTo(waveWidth * 3, waveHeight * 4);
        wavePath.lineTo(0, waveHeight * 4);
        wavePath.close();

        canvas.drawPath(wavePath, wavePaint);
    }

    private void startAnimation() {
        animator = ValueAnimator.ofInt(0, waveWidth * 2);
        animator.setDuration(1000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                value = (int) valueAnimator.getAnimatedValue();
                Log.i("12345678", "value: " + value);
                invalidate();
            }
        });
        animator.start();
    }

    public void stopAnimation() {
        if (animator != null) {
            animator.cancel();
        }
    }
}
