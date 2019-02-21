package com.example.yangjie.mydemo;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

/**
 * 。
 *
 * @author 迅动互联 2017/12/13 新建.
 */
public class BottomDialogFragment extends DialogFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_dialog, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null && dialog.getWindow() != null) {
            Window window = dialog.getWindow();
            window.setGravity(Gravity.BOTTOM);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setWindowAnimations(R.style.animate_dialog);
            setCancelable(true);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        view.findViewById(R.id.sogo1).setOnClickListener(this);
        view.findViewById(R.id.sogo2).setOnClickListener(this);
        view.findViewById(R.id.sogo3).setOnClickListener(this);
        view.findViewById(R.id.qq1).setOnClickListener(this);
        view.findViewById(R.id.qq2).setOnClickListener(this);
        view.findViewById(R.id.qq3).setOnClickListener(this);
        view.findViewById(R.id.ie1).setOnClickListener(this);
        view.findViewById(R.id.ie2).setOnClickListener(this);
        view.findViewById(R.id.ie3).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        v.setSelected(!v.isSelected());
        int id = v.getId();
        switch (id) {
            case R.id.sogo1:
            case R.id.sogo2:
            case R.id.sogo3:
                Toast.makeText(getActivity(), "SoGO", Toast.LENGTH_SHORT).show();
                break;
            case R.id.qq1:
            case R.id.qq2:
            case R.id.qq3:
                Toast.makeText(getActivity(), "QQ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ie1:
            case R.id.ie2:
            case R.id.ie3:
                Toast.makeText(getActivity(), "IE", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
