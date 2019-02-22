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
 * Created on 2019/2/22 16:44
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
public class ArrowDiviView extends View {

    private Paint mPaint;
    private Path mPath;

    private float aHeight = 10;

    public ArrowDiviView(Context context) {
        this(context, null);
    }

    public ArrowDiviView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ArrowDiviView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#DCDCDC"));
        mPaint.setStrokeWidth(6);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath = new Path();
        setBackgroundColor(Color.WHITE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureWidth(heightMeasureSpec));
    }

    private int measureWidth(int widthMeasureSpec) {
        int result = 0;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) { // 父视图希望子视图的大小应该是specSize中指定的
            result = width;
        } else {
            result = 60 + dip2px(aHeight);
            if (widthMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, width);
            }
        }
        return result;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int preWidth = getWidth() / 5;
        int y = dip2px(aHeight);

        mPath.moveTo(0, y);
        mPath.lineTo(preWidth * 3.5f, y);
        mPath.lineTo(preWidth * 3.5f + y, 0);
        mPath.lineTo(preWidth * 3.5f + 2 * y, y);
        mPath.lineTo(getWidth(), y);
        canvas.drawPath(mPath, mPaint);
    }
}
