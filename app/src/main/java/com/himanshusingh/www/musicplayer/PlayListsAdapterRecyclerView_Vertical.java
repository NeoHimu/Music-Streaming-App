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
 * Created by himanshu on 20/4/19.
 */


public class PlayListsAdapterRecyclerView_Vertical extends RecyclerView.Adapter<PlayListsAdapterRecyclerView_Vertical.SongViewHolder> {

    OnPlayListsSongClickListener mOnPlayListsSongClickListener;
    ArrayList<String> playlist_names;


    public PlayListsAdapterRecyclerView_Vertical(ArrayList<String> playlist_names, OnPlayListsSongClickListener onPlayListsSongClickListener)
    {
        this.mOnPlayListsSongClickListener = onPlayListsSongClickListener;
        this.playlist_names = playlist_names;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.songs_item_recycler_view_vertical, parent, false);
        return new SongViewHolder(view, mOnPlayListsSongClickListener);
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        String title = playlist_names.get(position);
        holder.textView.setText(title);
        holder.imageView.setImageResource(R.drawable.icon_cover);
    }

    @Override
    public int getItemCount() {
        return playlist_names.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;
        OnPlayListsSongClickListener onPlayListsSongClickListener;
        public SongViewHolder(View itemView, OnPlayListsSongClickListener onPlayListsSongClickListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.idImgSongRV_Vertical);
            textView = itemView.findViewById(R.id.idDescSongRV_Vertical);
            this.onPlayListsSongClickListener = onPlayListsSongClickListener;

            imageView.setOnClickListener(this);
            textView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v==imageView)
                onPlayListsSongClickListener.onPlayListsSongImageClick(getAdapterPosition());
            else if(v==textView)
                onPlayListsSongClickListener.onPlayListsSongTitleClick(getAdapterPosition());
        }
    }

    public interface OnPlayListsSongClickListener
    {
        void onPlayListsSongTitleClick(int position);
        void onPlayListsSongImageClick(int position);
    }
}