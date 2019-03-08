package com.himanshusingh.www.musicplayer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by himanshu on 26/2/19.
 */

public class SongsAdapterRecyclerView extends RecyclerView.Adapter<SongsAdapterRecyclerView.SongViewHolder> {

    private ArrayList<String> data;
    private  OnSongClickListener onSongClickListener;
    public SongsAdapterRecyclerView(ArrayList<String> data, OnSongClickListener onSongClickListener)
    {
        this.data = data;
        this.onSongClickListener = onSongClickListener;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.songs_item_recycler_view, parent, false);
        return new SongViewHolder(view, onSongClickListener);
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        String title = data.get(position);
        holder.textView.setText(title);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView textView;
        OnSongClickListener onSongClickListener;
        public SongViewHolder(View itemView, OnSongClickListener onSongClickListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.idImgSongRV);
            textView = itemView.findViewById(R.id.idDescSongRV);
            this.onSongClickListener = onSongClickListener;
            imageView.setOnClickListener(this);
            textView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v==textView)
                onSongClickListener.onSongClick(getAdapterPosition());
            else
                onSongClickListener.onImageClick(getAdapterPosition());
        }
    }
    public interface OnSongClickListener
    {
        void onSongClick(int position);
        void onImageClick(int position);
    }
}
