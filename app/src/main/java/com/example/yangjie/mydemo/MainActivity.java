package com.example.yangjie.mydemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.yangjie.mydemo.activity.AnimatorActivity;
import com.example.yangjie.mydemo.activity.MusicActivity;
import com.example.yangjie.mydemo.activity.NumberActivity;
import com.example.yangjie.mydemo.activity.WebActivity;
import com.tencent.bugly.beta.Beta;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                bug();
                fit();
            }
        });
        findViewById(R.id.btnLoadPatch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Beta.applyTinkerPatch(getApplicationContext(), Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.apk");
            }
        });
        findViewById(R.id.btnDownloadPatch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Beta.downloadPatch();
            }
        });
        findViewById(R.id.btnPatchDownloaded).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Beta.applyDownloadedPatch();
            }
        });

        findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });

        findViewById(R.id.btnNext2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AnimatorActivity.class));
            }
        });
        findViewById(R.id.btnNext3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NumberActivity.class));
            }
        });
        findViewById(R.id.btnNext4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, WebActivity.class));
            }
        });
        findViewById(R.id.btnNext5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MusicActivity.class));
            }
        });
    }

    private void bug() {
        Toast.makeText(this, "这是bug！", Toast.LENGTH_SHORT).show();
    }

    private void fit() {
        FragmentManager fm = getSupportFragmentManager();
        BottomDialogFragment bottomDialogFragment = new BottomDialogFragment();
        bottomDialogFragment.show(fm, "111");
    }
}
