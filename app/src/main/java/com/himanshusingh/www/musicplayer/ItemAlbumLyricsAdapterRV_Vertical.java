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
 * Created by himanshu on 19/3/19.
 */


public class ItemAlbumLyricsAdapterRV_Vertical extends RecyclerView.Adapter<ItemAlbumLyricsAdapterRV_Vertical.LyricsViewHolder> {

    private ArrayList<String> titles;
    private ArrayList<String> urls;
    private OnLyricsClickListener mOnLyricsClickListener;
    public ItemAlbumLyricsAdapterRV_Vertical(ArrayList<String> titles, ArrayList<String> urls, OnLyricsClickListener onLyricsClickListener)
    {
        this.mOnLyricsClickListener = onLyricsClickListener;
        this.titles = titles;
        this.urls = urls;
    }

    @Override
    public LyricsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.album_lyrics_item_recycler_view_vertical, parent, false);
        return new LyricsViewHolder(view, mOnLyricsClickListener);
    }

    @Override
    public void onBindViewHolder(LyricsViewHolder holder, int position) {
        String title = titles.get(position);
        holder.textView.setText(title);
        Picasso.get().load(urls.get(position)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class LyricsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;
        OnLyricsClickListener onLyricsClickListener;
        public LyricsViewHolder(View itemView, OnLyricsClickListener onLyricsClickListener) {
            super(itemView);
            this.onLyricsClickListener = onLyricsClickListener;
            imageView = itemView.findViewById(R.id.idImgLyricsRV_Vertical_Album_Item);
            textView = itemView.findViewById(R.id.idDescLyricsRV_Vertical_Album_Item);
            imageView.setOnClickListener(this);
            textView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v==textView)
                onLyricsClickListener.onLyricsTitleClick(getAdapterPosition());
            else
                onLyricsClickListener.onLyricsImageClick(getAdapterPosition());

        }
    }

    public interface OnLyricsClickListener
    {
        void onLyricsTitleClick(int position);
        void onLyricsImageClick(int position);
    }
}
