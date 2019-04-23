package com.example.yangjie.mydemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yangjie.mydemo.R;

/**
 * Created on 2019/4/22 14:31
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
public class CooAdapter extends RecyclerView.Adapter<CooAdapter.CooViewHolder> {

    private Context context;
    private LayoutInflater inflater;

    public CooAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public CooViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.coo_item, parent, false);
        return new CooViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CooViewHolder holder, int position) {
        holder.mTextView.setText("第 " + position + " 个");
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    class CooViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;

        public CooViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.coo_item_text);
        }
    }
}
