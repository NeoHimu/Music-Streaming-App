package com.himanshusingh.www.musicplayer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by himanshu on 18/4/19.
 */

public class RecentsAdapterRecyclerView_Vertical extends RecyclerView.Adapter<RecentsAdapterRecyclerView_Vertical.SongViewHolder> {

    OnRecentSongClickListener mOnRecentSongClickListener;


    public RecentsAdapterRecyclerView_Vertical(OnRecentSongClickListener onRecentSongClickListener)
    {
        this.mOnRecentSongClickListener = onRecentSongClickListener;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.songs_item_recycler_view_vertical, parent, false);
        return new SongViewHolder(view, mOnRecentSongClickListener);
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        String title = CommonVariables.recent_song_name.get(position);
        holder.textView.setText(title);
        Picasso.get().load(CommonVariables.recent_song_icon_url.get(position)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return CommonVariables.recent_song_icon_url.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;
        OnRecentSongClickListener onRecentSongClickListener;
        public SongViewHolder(View itemView, OnRecentSongClickListener onRecentSongClickListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.idImgSongRV_Vertical);
            textView = itemView.findViewById(R.id.idDescSongRV_Vertical);
            this.onRecentSongClickListener = onRecentSongClickListener;

            imageView.setOnClickListener(this);
            textView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v==imageView)
                onRecentSongClickListener.onRecentSongImageClick(getAdapterPosition());
            else if(v==textView)
                onRecentSongClickListener.onRecentSongTitleClick(getAdapterPosition());
        }
    }

    public interface OnRecentSongClickListener
    {
        void onRecentSongTitleClick(int position);
        void onRecentSongImageClick(int position);
    }
}

