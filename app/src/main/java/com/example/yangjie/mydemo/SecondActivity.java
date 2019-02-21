package com.example.yangjie.mydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.yangjie.mydemo.widge.MyView;

/**
 * Created on 2018/12/27 13:27
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final MyView myView = (MyView) findViewById(R.id.second_myview);

        findViewById(R.id.second_erjian).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myView.setEr();
            }
        });
        findViewById(R.id.second_sanjian).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myView.setSan();
            }
        });
        findViewById(R.id.second_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondActivity.this, RedPointActivity.class));
            }
        });
    }
}
