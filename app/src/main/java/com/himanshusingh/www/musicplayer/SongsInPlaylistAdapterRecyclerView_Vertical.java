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

public class SongsInPlaylistAdapterRecyclerView_Vertical extends RecyclerView.Adapter<SongsInPlaylistAdapterRecyclerView_Vertical.SongViewHolder> {

    OnSongsInPlaylistSongClickListener mOnSongsInPlaylistSongClickListener;
    ArrayList<String> song_icon_urls;
    ArrayList<String> song_names;


    public SongsInPlaylistAdapterRecyclerView_Vertical(ArrayList<String> song_icon_urls, ArrayList<String> song_names, OnSongsInPlaylistSongClickListener onSongsInPlaylistSongClickListener)
    {
        this.mOnSongsInPlaylistSongClickListener = onSongsInPlaylistSongClickListener;
        this.song_icon_urls = song_icon_urls;
        this.song_names = song_names;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.songs_item_recycler_view_vertical, parent, false);
        return new SongViewHolder(view, mOnSongsInPlaylistSongClickListener);
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        String title = song_names.get(position);
        holder.textView.setText(title);
        Picasso.get().load(song_icon_urls.get(position)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return song_names.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;
        OnSongsInPlaylistSongClickListener onSongsInPlaylistSongClickListener;
        public SongViewHolder(View itemView, OnSongsInPlaylistSongClickListener onSongsInPlaylistSongClickListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.idImgSongRV_Vertical);
            textView = itemView.findViewById(R.id.idDescSongRV_Vertical);
            this.onSongsInPlaylistSongClickListener = onSongsInPlaylistSongClickListener;

            imageView.setOnClickListener(this);
            textView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v==imageView)
                onSongsInPlaylistSongClickListener.onSongsInPlaylistSongImageClick(getAdapterPosition());
            else if(v==textView)
                onSongsInPlaylistSongClickListener.onSongsInPlaylistSongTitleClick(getAdapterPosition());
        }
    }

    public interface OnSongsInPlaylistSongClickListener
    {
        void onSongsInPlaylistSongTitleClick(int position);
        void onSongsInPlaylistSongImageClick(int position);
    }
}