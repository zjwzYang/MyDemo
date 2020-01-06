package com.example.yangjie.mydemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.yangjie.mydemo.R;
import com.example.yangjie.mydemo.widge.WaveActionView;

/**
 * Created on 2020-01-06 10:46
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
public class WaveActivity extends AppCompatActivity {

    private WaveActionView mWaveActionView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave);
        mWaveActionView = (WaveActionView) findViewById(R.id.wave_action);
    }

    @Override
    protected void onDestroy() {
        if (mWaveActionView != null) {
            mWaveActionView.stopAnimation();
        }
        super.onDestroy();
    }
}
