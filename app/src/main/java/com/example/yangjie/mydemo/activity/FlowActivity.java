package com.example.yangjie.mydemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.yangjie.mydemo.R;
import com.example.yangjie.mydemo.widge.CustomerFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2020-01-01 15:35
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
public class FlowActivity extends AppCompatActivity {

    private CustomerFlowLayout mCustomerFlowLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);
        mCustomerFlowLayout = (CustomerFlowLayout) findViewById(R.id.custom_flow_layout);

        final List<String> labels = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            labels.add("这是第" + (i + 1) + "个");
//        }
        labels.add("a");
        labels.add("ab");
        labels.add("abc");
        labels.add("abcd");
        labels.add("abcde");
        labels.add("abcdef");
        labels.add("abcdefg");
        labels.add("abcdefgh");
        labels.add("abcdefghi");
        labels.add("abcdefghij");
        labels.add("abcdefghijk");
        labels.add("abcdefghijkl");
        labels.add("abcdefghijklm");
        labels.add("abcdefghijklmn");
        mCustomerFlowLayout.addLables(labels);

        findViewById(R.id.flow_add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                labels.add("12345");
                mCustomerFlowLayout.addLables(labels);
            }
        });
    }
}
