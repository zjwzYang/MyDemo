package com.example.yangjie.mydemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.yangjie.mydemo.R;
import com.example.yangjie.mydemo.widge.ArrowLinearLayout;
import com.example.yangjie.mydemo.widge.ArrowTextView;
import com.example.yangjie.mydemo.widge.PathView;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

/**
 * Created on 2019/1/2 13:58
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
public class NumberActivity extends AppCompatActivity {

    private TickerView mTickerView;
    private PathView pathView;
    private ArrowLinearLayout mArrowLinearLayout;
    private int curr;

    private ArrowTextView arrowOne;
    private ArrowTextView arrowTwo;
    private ArrowTextView arrowThree;
    private ArrowTextView arrowFour;
    private ArrowTextView arrowFive;

    private String mString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
        mTickerView = (TickerView) findViewById(R.id.number_ticker);
        mTickerView.setCharacterLists(TickerUtils.provideNumberList());
        mTickerView.setAnimationDuration(1000);
        findViewById(R.id.number_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int random = (int) (Math.random() * 1000);
                mTickerView.setText(random + "");
            }
        });

        pathView = (PathView) findViewById(R.id.pathview);
        mArrowLinearLayout = (ArrowLinearLayout) findViewById(R.id.arrow_linear);
        mArrowLinearLayout.setPathView(pathView);
        findViewById(R.id.number_change2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pathView.setCurrPos(curr++);

                if (curr > pathView.getPosition()) {
                    curr = 0;
                }
            }
        });

        pathView.setOnItemChangeListener(new PathView.OnItemChangeListener() {
            @Override
            public void itemChange(int currPosition) {
                mArrowLinearLayout.setCurrpos(currPosition);
            }
        });

        arrowOne = (ArrowTextView) findViewById(R.id.arrow_one);
        arrowTwo = (ArrowTextView) findViewById(R.id.arrow_two);
        arrowThree = (ArrowTextView) findViewById(R.id.arrow_three);
        arrowFour = (ArrowTextView) findViewById(R.id.arrow_four);
        arrowFive = (ArrowTextView) findViewById(R.id.arrow_five);

        mString = "+成交";
        arrowOne.setText(mString);
        arrowTwo.setText(mString);
        arrowThree.setText(mString);
        arrowFour.setText(mString);
        arrowFive.setText(mString);
    }
}
