package com.example.yangjie.mydemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.yangjie.mydemo.R;

/**
 * Created on 2019/4/24 09:20
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
public class CoorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coor);

        findViewById(R.id.coor_depend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CoorActivity.this, CoorDependActivity.class));
            }
        });

        findViewById(R.id.coor_head).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CoorActivity.this, CoorHeadActivity.class));
            }
        });
    }
}
