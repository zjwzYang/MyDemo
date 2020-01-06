package com.example.yangjie.mydemo.widge;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created on 2020-01-06 10:51
 * 波浪.
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
public class WaveView extends View {

    private Paint wavePaint;
    private Path wavePath;

    private int waveWidth;
    private int waveHeight;

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        wavePaint = new Paint();
        wavePaint.setColor(Color.RED);
        wavePaint.setAntiAlias(true);
        wavePaint.setStyle(Paint.Style.STROKE);
        wavePaint.setStrokeWidth(10f);

        wavePath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        waveWidth = getMeasuredWidth() / 3;
        waveHeight = getMeasuredHeight() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        wavePath.reset();
        wavePaint.setColor(Color.BLUE);
        wavePath.moveTo(0, waveHeight);
        wavePath.lineTo(waveWidth * 3, waveHeight);
        canvas.drawPath(wavePath, wavePaint);

        wavePaint.setColor(Color.RED);
        wavePath.reset();
        for (int i = 0; i < 3; i++) {
            wavePath.moveTo(waveWidth * i, waveHeight);
            if (i % 2 == 0) {
                wavePath.quadTo(waveWidth * i + waveWidth / 2, 0, waveWidth * (i + 1), waveHeight);
            } else {
                wavePath.quadTo(waveWidth * i + waveWidth / 2, waveHeight * 2, waveWidth * (i + 1), waveHeight);
            }
        }
        canvas.drawPath(wavePath, wavePaint);
    }
}
