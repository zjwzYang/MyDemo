package com.example.yangjie.mydemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created on 2018/12/27 15:15
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
public class RedPointActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_point);
        findViewById(R.id.red_point_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.red_point_red).setVisibility(View.VISIBLE);
            }
        });
    }
}
