package com.example.yangjie.mydemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.yangjie.mydemo.R;

public class CustomerLayoutManagerActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_layout_manager);
        mRecyclerView = (RecyclerView) findViewById(R.id.customer_recyclerview);

    }

}
