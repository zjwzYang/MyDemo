package com.example.yangjie.mydemo.widge;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.yangjie.mydemo.R;

/**
 * Created on 2019/2/19 13:53
 * 进度view.
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
    private Path mPath;

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

        grayPaint = new Paint();
        grayPaint.setColor(Color.parseColor("#DCDCDC"));
        grayPaint.setStyle(Paint.Style.STROKE);
        grayPaint.setStrokeWidth(dip2px(2f));
        grayPaint.setPathEffect(new DashPathEffect(new float[]{20f, 10f}, 0));
        mPath = new Path();

        greenPaint = new Paint();
        greenPaint.setColor(Color.parseColor("#34AD39"));
        greenPaint.setStyle(Paint.Style.STROKE);
        greenPaint.setStrokeWidth(dip2px(2f));
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
            result = 60 + dip2px(2f);
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
                } else if (i == position - 2) {
                    lineStopX = lineStopX - preWid / 2;
                } else if (i == position - 1) {
                    lineStartX = lineStartX - preWid / 2;
                    lineStopX = lineStopX - preWid / 2;
                }
                canvas.drawLine(lineStartX, circleY, lineStopX, circleY, greenPaint);
            } else {
                if (i == 0) {
                    lineStartX = preWid / 2 + preWid * i;
                    lineStopX = lineStartX + preWid / 2;
                    canvas.drawLine(lineStartX, circleY, lineStopX, circleY, grayPaint);
                } else if (i == position - 2) {
                    lineStopX = lineStartX + preWid / 2;
                    canvas.drawLine(lineStartX, circleY, lineStopX, circleY, grayPaint);
                } else if (i == position - 1) {
                    lineStartX = lineStartX - preWid / 2;
                    lineStopX = lineStopX - preWid / 2;
                    mPath.reset();
                    mPath.moveTo(lineStartX, circleY);
                    mPath.lineTo(lineStopX, circleY);
                    canvas.drawPath(mPath, grayPaint);
                } else {
                    canvas.drawLine(lineStartX, circleY, lineStopX, circleY, grayPaint);
                }
            }
        }
    }

    private void drawCircle(Canvas canvas) {
        int preWid = mWidth / position;
        for (int i = 0; i < position; i++) {
            Bitmap bitmap;
            int bWidth;
            int bHeight;
            Bitmap bitmap1;
            int ieWidth;
            int ieHeight;
            if (i != position - 1) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.todo_icon);
                bWidth = bitmap.getWidth() / 2;
                bHeight = bitmap.getHeight() / 2;

                bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.already_icon);
                ieWidth = bitmap1.getWidth() / 2;
                ieHeight = bitmap1.getHeight() / 2;
            } else {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.to_do_tui_icon);
                bWidth = bitmap.getWidth() / 2;
                bHeight = bitmap.getHeight() / 2;

                bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.already_tui_icon);
                ieWidth = bitmap1.getWidth() / 2;
                ieHeight = bitmap1.getHeight() / 2;
            }
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
                if (i == 0) {
                    lineStartX = preWid / 2 + preWid * i;
                    canvas.drawLine(lineStartX, circleY, lineStartX + preWid / 2, circleY, grayPaint);
                    lineStopX = (int) (lineStartX + preWid / 2 * currProgress);
                } else if (i == position - 2) {
                    lineStopX = lineStartX + preWid / 2;
                    canvas.drawLine(lineStartX, circleY, lineStopX, circleY, grayPaint);
                    lineStopX = (int) (lineStartX + preWid / 2 * currProgress);
                } else if (i == position - 1) {
                    lineStartX = lineStartX - preWid / 2;
                    mPath.reset();
                    mPath.moveTo(lineStartX, circleY);
                    mPath.lineTo(lineStopX, circleY);
                    canvas.drawPath(mPath, grayPaint);
                    lineStopX = (int) (lineStartX + preWid * currProgress);
                } else {
                    canvas.drawLine(lineStartX, circleY, lineStopX, circleY, grayPaint);
                    lineStopX = (int) (lineStartX + preWid * currProgress);
                }
                canvas.drawLine(lineStartX, circleY, lineStopX, circleY, greenPaint);
            } else if (i < currPos) {
                if (i == 0) {
                    lineStartX = preWid / 2 + preWid * i;
                    lineStopX = lineStartX + preWid / 2;
                } else if (i == position - 2) {
                    lineStopX = lineStopX - preWid / 2;
                }
                canvas.drawLine(lineStartX, circleY, lineStopX, circleY, greenPaint);
            } else {
                if (i == 0) {
                    lineStartX = preWid / 2 + preWid * i;
                    lineStopX = lineStartX + preWid / 2;
                    canvas.drawLine(lineStartX, circleY, lineStopX, circleY, grayPaint);
                } else if (i == position - 2) {
                    lineStopX = lineStartX + preWid / 2;
                    canvas.drawLine(lineStartX, circleY, lineStopX, circleY, grayPaint);
                } else if (i == position - 1) {
                    lineStartX = lineStartX - preWid / 2;
                    mPath.reset();
                    mPath.moveTo(lineStartX, circleY);
                    mPath.lineTo(lineStopX, circleY);
                    canvas.drawPath(mPath, grayPaint);
                } else {
                    canvas.drawLine(lineStartX, circleY, lineStopX, circleY, grayPaint);
                }
            }
        }
    }

    private void drawCircleWithAnimator(Canvas canvas) {
        int preWid = mWidth / position;
        for (int i = 0; i < position; i++) {
            Bitmap bitmap;
            int bWidth;
            int bHeight;
            Bitmap bitmap1;
            int ieWidth;
            int ieHeight;
            if (i != position - 1) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.todo_icon);
                bWidth = bitmap.getWidth() / 2;
                bHeight = bitmap.getHeight() / 2;

                bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.already_icon);
                ieWidth = bitmap1.getWidth() / 2;
                ieHeight = bitmap1.getHeight() / 2;
            } else {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.to_do_tui_icon);
                bWidth = bitmap.getWidth() / 2;
                bHeight = bitmap.getHeight() / 2;

                bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.already_tui_icon);
                ieWidth = bitmap1.getWidth() / 2;
                ieHeight = bitmap1.getHeight() / 2;
            }
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

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
