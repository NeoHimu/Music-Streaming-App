package com.himanshusingh.www.musicplayer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by himanshu on 20/3/19.
 */

public class ItemAlbumSongAdapterRV_Vertical extends RecyclerView.Adapter<ItemAlbumSongAdapterRV_Vertical.SongViewHolder> {

    private String image_url;
    private ArrayList<String> titles;
    private ItemAlbumSongAdapterRV_Vertical.OnSongClickListener mOnSongClickListener;
    public ItemAlbumSongAdapterRV_Vertical(String image_url, ArrayList<String> titles, ItemAlbumSongAdapterRV_Vertical.OnSongClickListener onSongClickListener)
    {
        this.mOnSongClickListener = onSongClickListener;
        this.image_url = image_url;
        this.titles = titles;
    }

    @Override
    public ItemAlbumSongAdapterRV_Vertical.SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.album_song_item_recycler_view_vertical, parent, false);
        return new ItemAlbumSongAdapterRV_Vertical.SongViewHolder(view, mOnSongClickListener);
    }

    @Override
    public void onBindViewHolder(ItemAlbumSongAdapterRV_Vertical.SongViewHolder holder, int position) {
        String title = titles.get(position);
        holder.textView.setText(title);
        Picasso.get().load(image_url).into(holder.imageView);
        holder.addToQueue.setBackgroundResource(R.drawable.icon_add);
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;
        ImageView addToQueue;
        TextView optionMenuDigits;
        ItemAlbumSongAdapterRV_Vertical.OnSongClickListener onSongClickListener;
        public SongViewHolder(final View itemView, ItemAlbumSongAdapterRV_Vertical.OnSongClickListener onSongClickListener) {
            super(itemView);
            this.onSongClickListener = onSongClickListener;
            imageView = itemView.findViewById(R.id.idImgSongRV_Vertical_Album_Item);
            textView = itemView.findViewById(R.id.idDescSongRV_Vertical_Album_Item);
            addToQueue = itemView.findViewById(R.id.idAddToQueue);
            optionMenuDigits = itemView.findViewById(R.id.idTextViewOptions);

            imageView.setOnClickListener(this);
            textView.setOnClickListener(this);
            addToQueue.setOnClickListener(this);
            optionMenuDigits.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v==textView)
                onSongClickListener.onSongTitleClick(getAdapterPosition());
            else if(v==imageView)
                onSongClickListener.onSongImageClick(getAdapterPosition());
            else if(v==addToQueue)
                onSongClickListener.onAddToQueueClick(getAdapterPosition());
            else if(v==optionMenuDigits)
                onSongClickListener.onOptionMenuClick(getAdapterPosition(), optionMenuDigits);
        }
    }

    public interface OnSongClickListener
    {
        void onSongTitleClick(int position);
        void onSongImageClick(int position);
        void onAddToQueueClick(int position);
        void onOptionMenuClick(int position, TextView textView);
    }
}
