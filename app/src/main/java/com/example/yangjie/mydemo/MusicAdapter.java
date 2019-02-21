package com.example.yangjie.mydemo;

import android.content.Context;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2019/1/21 13:17
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {

    private Context context;
    private List<MediaBrowserCompat.MediaItem> list;
    private LayoutInflater inflater;
    private OnItemBtnClickListen mOnItemBtnClickListen;
    private int playPosition = -1;

    public MusicAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public MusicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_music, parent, false);
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MusicViewHolder holder, final int position) {
        final MediaBrowserCompat.MediaItem mediaItem = list.get(position);
        holder.textTitle.setText(mediaItem.getDescription().getTitle());
        if (position == playPosition) {
            holder.mPlayBtn.setText("暂停");
        } else {
            holder.mPlayBtn.setText("播放");
        }
        holder.mPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemBtnClickListen != null) {
                    if (playPosition == position) {
                        mOnItemBtnClickListen.onStop(mediaItem);
                        playPosition = -1;
                    } else {
                        mOnItemBtnClickListen.onPlay(mediaItem);
                        int lastPosition = playPosition;
                        playPosition = position;
                        if (playPosition != -1) {
                            notifyItemChanged(lastPosition);
                        }
                    }
                    notifyItemChanged(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void add(MediaBrowserCompat.MediaItem mediaItem) {
        this.list.add(mediaItem);
    }

    public interface OnItemBtnClickListen {
        void onPlay(MediaBrowserCompat.MediaItem mediaItem);

        void onStop(MediaBrowserCompat.MediaItem mediaItem);
    }

    public void setOnItemBtnClickListen(OnItemBtnClickListen onItemBtnClickListen) {
        mOnItemBtnClickListen = onItemBtnClickListen;
    }

    class MusicViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        Button mPlayBtn;

        public MusicViewHolder(View view) {
            super(view);
            textTitle = view.findViewById(R.id.text_title);
            mPlayBtn = view.findViewById(R.id.btn_play);
        }

    }
}
