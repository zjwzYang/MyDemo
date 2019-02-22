package com.example.yangjie.mydemo.widge;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created on 2019/2/21 14:26
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
public class ArrowTextView extends android.support.v7.widget.AppCompatTextView {

    private Paint paint;
    private Path path;

    private int arrowWid;

    private boolean isNeedBg = true;

    public ArrowTextView(Context context) {
        this(context, null);
    }

    public ArrowTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ArrowTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);   //设置画笔抗锯齿
        paint.setStrokeWidth(2);    //设置线宽
        paint.setColor(Color.parseColor("#34AD39"));  //设置线的颜色
        paint.setStyle(Paint.Style.STROKE);

        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isNeedBg) {
            int height = getHeight();   //获取View的高度
            int width = getWidth();     //获取View的宽度
            arrowWid = height / 9;

            //框定文本显示的区域
            RectF rect = new RectF(getPaddingLeft(),
                    arrowWid,
                    width - getPaddingRight(),
                    height - arrowWid);
            canvas.drawRoundRect(rect, 14, 14, paint);

            //以下是绘制文本的那个箭头
            path.moveTo(width / 2 - arrowWid, arrowWid);   //三角形左边的点
            path.lineTo(width / 2, 0);// 三角形顶点
            path.lineTo(width / 2 + arrowWid, arrowWid);   //三角形右边的点
//            path.close();
            canvas.drawPath(path, paint);

            paint.setColor(Color.WHITE);
            canvas.drawLine(width / 2 - arrowWid, arrowWid,
                    width / 2 + arrowWid, arrowWid, paint);
        }

        super.onDraw(canvas);
    }

    public void setNeedBg(boolean needBg) {
        isNeedBg = needBg;
        invalidate();
    }

    public void setBgColor(String color) {
        paint.setColor(Color.parseColor(color));
    }
}
