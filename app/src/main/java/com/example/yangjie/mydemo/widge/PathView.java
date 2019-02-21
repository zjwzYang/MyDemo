package com.example.yangjie.mydemo.widge;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.yangjie.mydemo.R;

/**
 * Created on 2019/2/19 13:53
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
public class PathView extends View {

    private int currPos = -1;
    private int position = 5;

    private int circleY = 30;

    private Paint grayPaint;
    private Paint greenPaint;

    private int mWidth;

    private float currProgress;
    private boolean isAnimator;

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setBackgroundColor(Color.WHITE);

        grayPaint = new Paint();
        grayPaint.setColor(Color.parseColor("#DCDCDC"));
        grayPaint.setStyle(Paint.Style.STROKE);
        grayPaint.setStrokeWidth(10);

        greenPaint = new Paint();
        greenPaint.setColor(Color.parseColor("#34AD39"));
        greenPaint.setStyle(Paint.Style.STROKE);
        greenPaint.setStrokeWidth(10);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureWidth(heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    private int measureWidth(int widthMeasureSpec) {
        int result = 0;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) { // 父视图希望子视图的大小应该是specSize中指定的
            result = width;
        } else {
            result = 70;
            if (widthMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, width);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mWidth = getWidth();

        //canvas.drawLine(0, circleY, mWidth, circleY, grayPaint);
        if (!isAnimator) {
            drawLine(canvas);
            drawCircle(canvas);
        } else {
            drawLineWithAnimator(canvas);
            drawCircleWithAnimator(canvas);
        }
        super.onDraw(canvas);
    }

    private void drawLine(Canvas canvas) {
        int preWid = mWidth / position;
        for (int i = 0; i < position; i++) {
            int lineStartX = preWid * i;
            int lineStopX = lineStartX + preWid;
            if (i <= currPos) {
                if (i == 0) {
                    lineStartX = preWid / 2 + preWid * i;
                    lineStopX = lineStartX + preWid / 2;
                } else if (i == position - 1) {
                    lineStopX = lineStopX - preWid / 2;
                }
                canvas.drawLine(lineStartX, circleY, lineStopX, circleY, greenPaint);
            } else {
                if (i == 0) {
                    lineStartX = preWid / 2 + preWid * i;
                    lineStopX = lineStartX + preWid / 2;
                } else if (i == position - 1) {
                    lineStopX = lineStopX - preWid / 2;
                }
                canvas.drawLine(lineStartX, circleY, lineStopX, circleY, grayPaint);
            }
        }
    }

    private void drawCircle(Canvas canvas) {
        int preWid = mWidth / position;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.todo_icon);
        int bWidth = bitmap.getWidth() / 2;
        int bHeight = bitmap.getHeight() / 2;

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.already_icon);
        int ieWidth = bitmap1.getWidth() / 2;
        int ieHeight = bitmap1.getHeight() / 2;
        for (int i = 0; i < position; i++) {
            int circleX = preWid / 2 + preWid * i;
            if (i <= currPos) {
                canvas.drawBitmap(bitmap1, circleX - ieWidth, circleY - ieHeight, grayPaint);
            } else {
                canvas.drawBitmap(bitmap, circleX - bWidth, circleY - bHeight, grayPaint);
            }
        }
    }

    public boolean setCurrPos(int currPos) {
//        if (currPos == 0) {
//            this.currPos = currPos;
//            invalidate();
//            return true;
//        } else
        if (currPos <= position) {
            isAnimator = true;
            this.currPos = currPos;
            startAnimator();
            return true;
        } else {
            return false;
        }
    }

    public int getCurrPos() {
        return currPos;
    }

    private void startAnimator() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                currProgress = (Float) valueAnimator.getAnimatedValue();
                if (Math.abs(currProgress - 0.5) < 0.1 && mOnItemChangeListener != null) {
                    mOnItemChangeListener.itemChange(currPos);
                }
                invalidate();
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isAnimator = false;
                currProgress = 0;
            }
        });
        animator.start();
    }

    private void drawLineWithAnimator(Canvas canvas) {
        int preWid = mWidth / position;
        for (int i = 0; i < position; i++) {
            int lineStartX = preWid * i;
            int lineStopX = lineStartX + preWid;
            if (i == position - 1) {
                lineStopX = lineStartX + preWid / 2;
            }
            if (i == currPos) {
                canvas.drawLine(lineStartX, circleY, lineStopX, circleY, grayPaint);
                if (i == 0) {
                    lineStartX = preWid / 2 + preWid * i;
                    lineStopX = lineStartX + preWid / 2;
                } else if (i == position - 1) {
                    lineStopX = (int) (lineStartX + preWid / 2 * currProgress);
                } else {
                    lineStopX = (int) (lineStartX + preWid * currProgress);
                }
                canvas.drawLine(lineStartX, circleY, lineStopX, circleY, greenPaint);
            } else if (i < currPos) {
                if (i == 0) {
                    lineStartX = preWid / 2 + preWid * i;
                    lineStopX = lineStartX + preWid / 2;
                }
                canvas.drawLine(lineStartX, circleY, lineStopX, circleY, greenPaint);
            } else {
                if (i == 0) {
                    lineStartX = preWid / 2 + preWid * i;
                    lineStopX = lineStartX + preWid / 2;
                }
                canvas.drawLine(lineStartX, circleY, lineStopX, circleY, grayPaint);
            }
        }
    }

    private void drawCircleWithAnimator(Canvas canvas) {
        int preWid = mWidth / position;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.todo_icon);
        int bWidth = bitmap.getWidth() / 2;
        int bHeight = bitmap.getHeight() / 2;

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.already_icon);
        int ieWidth = bitmap1.getWidth() / 2;
        int ieHeight = bitmap1.getHeight() / 2;
        for (int i = 0; i < position; i++) {
            int circleX = preWid / 2 + preWid * i;
            if (i == currPos && i == position - 1 && currProgress - 0.98 > 0) {
                canvas.drawBitmap(bitmap1, circleX - ieWidth, circleY - ieHeight, grayPaint);
            } else if (i == currPos && i != position - 1) {
                canvas.save();
                float scale = 1;
                Bitmap temp = bitmap1;
                if (currProgress - 0.5 < 0) {
                    scale = 1 - currProgress / 0.5f;
                    temp = bitmap;
                } else if (currProgress - 0.5 < 0.5) {
                    scale = (currProgress - 0.5f) / 0.5f;
                }
                canvas.scale(scale, scale, circleX - ieWidth * scale, circleY - ieHeight * scale);
                canvas.drawBitmap(temp, circleX - ieWidth * scale, circleY - ieHeight * scale, grayPaint);
                canvas.restore();

            } else if (i < currPos) {
                canvas.drawBitmap(bitmap1, circleX - ieWidth, circleY - ieHeight, grayPaint);
            } else {
                canvas.drawBitmap(bitmap, circleX - bWidth, circleY - bHeight, grayPaint);
            }
        }
    }

    public int getPosition() {
        return position;
    }

    private OnItemChangeListener mOnItemChangeListener;

    public void setOnItemChangeListener(OnItemChangeListener onItemChangeListener) {
        mOnItemChangeListener = onItemChangeListener;
    }

    public interface OnItemChangeListener {
        void itemChange(int currPosition);
    }
}
