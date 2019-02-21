package com.example.yangjie.mydemo.widge;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yangjie.mydemo.R;
import com.example.yangjie.mydemo.bean.PointValue;

/**
 * Created on 2018/12/29 16:08
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
public class RedView extends android.support.v7.widget.AppCompatTextView {

    private DragView mDragView;

    private Paint redPaint;
    private float mWidth, mHeight;//View的宽和高

    public RedView(Context context) {
        this(context, null);
    }

    public RedView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public RedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        redPaint = new Paint();
        redPaint.setStyle(Paint.Style.STROKE);
        redPaint.setColor(Color.RED);
        redPaint.setStrokeWidth(4);
        redPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        int smallX = getWidth() / 2;
//        int smallY = getHeight() / 2;
//        canvas.drawCircle(smallX, smallY, 30, redPaint);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        View rootView = getRootView();
        float x = event.getRawX();
        float y = event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDragView = new DragView(getContext());
                int[] cLocation = new int[2];
                getLocationOnScreen(cLocation);
                mDragView.setSmall(cLocation[0] + mWidth / 2, cLocation[1] + mHeight / 2, mWidth / 2);
                mDragView.setCurrPosition(x, y);
                //获得缓存的bitmap，滑动时直接通过drawBitmap绘制出来
                setDrawingCacheEnabled(true);
                Bitmap bitmap = getDrawingCache();
                if (bitmap != null) {
                    mDragView.setCacheBitmap(bitmap);
                    ((ViewGroup) rootView).addView(mDragView);
                }
                setVisibility(INVISIBLE);
                break;
            case MotionEvent.ACTION_MOVE:
                mDragView.setCurrPosition(x, y);
                break;
            case MotionEvent.ACTION_UP:
                mDragView.setDragUp(x, y);
                //setVisibility(VISIBLE);
                break;
        }
        return true;
    }

    public class DragView extends View {

        private final int STATE_MOVE = 1;
        private final int STATE_LONG = 3;
        private final int STATE_UP = 2;
        private final int STATE_UP_LONG = 4;

        private Paint redPaint;
        private Paint greenPaint;
        private Path greenPath;

        private float defaultSmallRadio;
        private float smallRadio;
        private float bigRadio;

        private float smallX;
        private float smallY;

        private float moveX;
        private float moveY;

        private float centerX;
        private float centerY;

        private float mWidth;
        private float mHeight;

        private Bitmap cashBitmap;

        private int state;

        private int[] explode_res = {R.drawable.explode1, R.drawable.explode2, R.drawable.explode3, R.drawable.explode4, R.drawable.explode5};
        private int explodeIndex;
        private Bitmap[] bitmaps;

        private double desc;

        public DragView(Context context) {
            this(context, null);
        }

        public DragView(Context context, @Nullable AttributeSet attrs) {
            this(context, attrs, -1);
        }

        public DragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }

        private void init() {
            redPaint = new Paint();
            //redPaint.setStyle(Paint.Style.STROKE);
            redPaint.setColor(Color.RED);
            redPaint.setStrokeWidth(4);
            redPaint.setAntiAlias(true);

            greenPaint = new Paint();
            //greenPaint.setStyle(Paint.Style.STROKE);
            greenPaint.setColor(Color.RED);
            greenPaint.setStrokeWidth(4);
            greenPaint.setAntiAlias(true);

            greenPath = new Path();
            //初始化消失动画资源
            bitmaps = new Bitmap[explode_res.length];
            for (int i = 0; i < explode_res.length; i++) {
                bitmaps[i] = BitmapFactory.decodeResource(getResources(), explode_res[i]);
            }
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            centerX = (moveX + smallX) / 2;
            centerY = (moveY + smallY) / 2;
            if ((moveY != 0 || moveX != 0) && moveY != smallY && (state == STATE_MOVE || state == STATE_LONG)) {
                smallRadio = defaultSmallRadio - desc / 20 < 20 ? 20 : (float) (defaultSmallRadio - desc / 20);

                if (state != STATE_LONG) {
                    canvas.drawCircle(smallX, smallY, smallRadio, redPaint);
                }
                // 斜率
                float k = (moveX - smallX) / (moveY - smallY);
                double radian = Math.atan(k);
                float bigXOff = (float) (Math.cos(radian) * bigRadio);
                float bigYOff = (float) (Math.sin(radian) * bigRadio);
                float smallXOff = (float) (Math.cos(radian) * smallRadio);
                float smallYOff = (float) (Math.sin(radian) * smallRadio);

                if (state != STATE_LONG) {
                    greenPath.reset();
                    greenPath.moveTo(moveX + bigXOff, moveY - bigYOff);
                    greenPath.lineTo(moveX - bigXOff, moveY + bigYOff);
                    greenPath.quadTo(centerX, centerY, smallX - smallXOff, smallY + smallYOff);
                    greenPath.lineTo(smallX + smallXOff, smallY - smallYOff);
                    greenPath.quadTo(centerX, centerY, moveX + bigXOff, moveY - bigYOff);
                    canvas.drawPath(greenPath, greenPaint);
                }

                //canvas.drawCircle(moveX, moveY, bigRadio, redPaint);

                canvas.drawBitmap(cashBitmap, moveX - mWidth / 2, moveY - mHeight / 2, redPaint);

//                canvas.drawPoint(centerX, centerY, redPaint);

            } else if (state == STATE_UP_LONG && explodeIndex < bitmaps.length) {
                canvas.drawBitmap(bitmaps[explodeIndex], moveX - mWidth / 2, moveY - mHeight / 2, redPaint);
            }
        }

        public void setSmall(float x, float y, float smallRadio) {
            this.smallX = x;
            this.smallY = y;
            this.defaultSmallRadio = smallRadio;
        }

        public void setCacheBitmap(Bitmap bitmap) {
            cashBitmap = bitmap;
            mWidth = bitmap.getWidth();
            mHeight = bitmap.getHeight();
            bigRadio = mWidth / 2;
        }

        public void setCurrPosition(float x, float y) {
            moveX = x;
            moveY = y;
            this.state = STATE_MOVE;
            desc = Math.sqrt((smallX - x) * (smallX - x) + (smallY - y) * (smallY - y));
            if (desc > 500) {
                this.state = STATE_LONG;
            }
            invalidate();
        }

        public void setDragUp(float x, float y) {
            double desc = Math.sqrt((smallX - x) * (smallX - x) + (smallY - y) * (smallY - y));
            if (desc > 500) {
                state = STATE_UP_LONG;
                startExplodeAnim();
            } else {
                state = STATE_UP;
                //invalidate();
                backAnim();
            }
        }

        /**
         * 爆炸动画
         */
        private void startExplodeAnim() {
            ValueAnimator animator = ValueAnimator.ofInt(0, explode_res.length);
            animator.setDuration(300);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    explodeIndex = (int) animation.getAnimatedValue();
                    invalidate();
                }
            });
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    Toast.makeText(getContext(), "消失", Toast.LENGTH_SHORT).show();
                }
            });
            animator.start();
        }

        private void backAnim() {
            PointValue movePoint = new PointValue(moveX, moveY);
            PointValue smallPoint = new PointValue(smallX, smallY);
            ValueAnimator animator = ValueAnimator.ofObject(new TypeEvaluator<PointValue>() {
                @Override
                public PointValue evaluate(float v, PointValue o, PointValue t1) {
                    float x = o.getX() + (t1.getX() - o.getX()) * v;
                    float y = o.getY() + (t1.getY() - o.getY()) * v;
                    return new PointValue(x, y);
                }
            }, movePoint, smallPoint);
            animator.setDuration(400);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    PointValue pointValue = (PointValue) valueAnimator.getAnimatedValue();
                    setCurrPosition(pointValue.getX(), pointValue.getY());
                }
            });
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    RedView.this.setVisibility(VISIBLE);
                }
            });
            animator.start();
        }
    }
}
