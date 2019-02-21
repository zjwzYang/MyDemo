package com.example.yangjie.mydemo.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.yangjie.mydemo.R;
import com.example.yangjie.mydemo.bean.PointValue;

/**
 * Created on 2018/12/30 15:47
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
public class AnimatorActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextOne;
    private TextView mTextTwo;
    private int currWidth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        mTextOne = (TextView) findViewById(R.id.animator_text);
        mTextTwo = (TextView) findViewById(R.id.animator_text_two);

        findViewById(R.id.animator_one).setOnClickListener(this);
        findViewById(R.id.animator_two).setOnClickListener(this);
        findViewById(R.id.animator_three).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.animator_one:
                currWidth = mTextOne.getWidth();
                Log.i("12345678", "onClick: " + mTextOne.getWidth());
                ValueAnimator oneAnimator = ValueAnimator.ofInt(mTextOne.getWidth(), 500);
                oneAnimator.setDuration(1000);
                oneAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        int width = (int) valueAnimator.getAnimatedValue();
                        mTextOne.setWidth(width);
                        mTextOne.requestLayout();
                    }
                });
                oneAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        mTextOne.setWidth(currWidth);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                oneAnimator.start();
                break;
            case R.id.animator_two:
                PointValue sValue = new PointValue(mTextTwo.getX(), mTextTwo.getY());
                PointValue eValue = new PointValue(500, 500);
                ValueAnimator twoAnimator = ValueAnimator.ofObject(new TypeEvaluator<PointValue>() {
                    @Override
                    public PointValue evaluate(float v, PointValue o, PointValue t1) {
                        float x = o.getX() + (t1.getX() - o.getX()) * v;
                        float y = o.getY() + (t1.getY() - o.getY()) * v;
                        return new PointValue(x, y);
                    }
                }, sValue, eValue);
                twoAnimator.setDuration(3000);
                twoAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        PointValue pointValue = (PointValue) valueAnimator.getAnimatedValue();
                        mTextTwo.setX(pointValue.getX());
                        mTextTwo.setY(pointValue.getY());
                    }
                });
                twoAnimator.start();
                break;

            case R.id.animator_three:
                ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(mTextOne, "alpha", 1, 0);
                alphaAnimator.setDuration(2000);
                alphaAnimator.start();
                break;
        }
    }
}
