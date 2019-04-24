package com.example.yangjie.mydemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.yangjie.mydemo.R;
import com.example.yangjie.mydemo.adapter.CooAdapter;

/**
 * Created on 2019/4/24 13:24
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
public class CoorHeadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coor_head);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.head_recy);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new CooAdapter(this));


    }
}
