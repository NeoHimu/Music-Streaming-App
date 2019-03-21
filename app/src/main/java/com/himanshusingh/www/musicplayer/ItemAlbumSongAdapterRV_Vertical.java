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
 * Created by himanshu on 20/3/19.
 */

public class ItemAlbumSongAdapterRV_Vertical extends RecyclerView.Adapter<ItemAlbumSongAdapterRV_Vertical.SongViewHolder> {

    private String image_url;
    private ArrayList<String> urls;
    private ItemAlbumSongAdapterRV_Vertical.OnSongClickListener mOnSongClickListener;
    public ItemAlbumSongAdapterRV_Vertical(String image_url, ArrayList<String> urls, ItemAlbumSongAdapterRV_Vertical.OnSongClickListener onSongClickListener)
    {
        this.mOnSongClickListener = onSongClickListener;
        this.image_url = image_url;
        this.urls = urls;
    }

    @Override
    public ItemAlbumSongAdapterRV_Vertical.SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.album_song_item_recycler_view_vertical, parent, false);
        return new ItemAlbumSongAdapterRV_Vertical.SongViewHolder(view, mOnSongClickListener);
    }

    @Override
    public void onBindViewHolder(ItemAlbumSongAdapterRV_Vertical.SongViewHolder holder, int position) {
        String title = urls.get(position);
        holder.textView.setText(title);
        Picasso.get().load(image_url).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;
        ItemAlbumSongAdapterRV_Vertical.OnSongClickListener onSongClickListener;
        public SongViewHolder(View itemView, ItemAlbumSongAdapterRV_Vertical.OnSongClickListener onSongClickListener) {
            super(itemView);
            this.onSongClickListener = onSongClickListener;
            imageView = itemView.findViewById(R.id.idImgSongRV_Vertical_Album_Item);
            textView = itemView.findViewById(R.id.idDescSongRV_Vertical_Album_Item);
            imageView.setOnClickListener(this);
            textView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v==textView)
                onSongClickListener.onSongTitleClick(getAdapterPosition());
            else
                onSongClickListener.onSongImageClick(getAdapterPosition());

        }
    }

    public interface OnSongClickListener
    {
        void onSongTitleClick(int position);
        void onSongImageClick(int position);
    }
}
