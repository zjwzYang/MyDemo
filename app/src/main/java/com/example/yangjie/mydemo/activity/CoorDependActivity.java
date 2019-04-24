package com.example.yangjie.mydemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.yangjie.mydemo.R;
import com.example.yangjie.mydemo.adapter.CooAdapter;

/**
 * Created on 2019/4/22 13:57
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
public class CoorDependActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coor_depend);

        mRecyclerView = (RecyclerView) findViewById(R.id.coo_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new CooAdapter(this));

        mToolbar = (Toolbar) findViewById(R.id.coo_toolbar);
        setSupportActionBar(mToolbar);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.coo_toollayout);
        mCollapsingToolbarLayout.setTitle("我是标题");
    }
}
