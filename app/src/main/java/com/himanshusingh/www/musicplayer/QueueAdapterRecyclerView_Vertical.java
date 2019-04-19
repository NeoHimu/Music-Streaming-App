package com.himanshusingh.www.musicplayer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by himanshu on 18/4/19.
 */


public class QueueAdapterRecyclerView_Vertical extends RecyclerView.Adapter<QueueAdapterRecyclerView_Vertical.SongViewHolder> {

    QueueAdapterRecyclerView_Vertical.OnQueueSongClickListener mOnQueueAdapterRecyclerView_Vertical;


    public QueueAdapterRecyclerView_Vertical(OnQueueSongClickListener onQueueSongClickListener)
    {
        this.mOnQueueAdapterRecyclerView_Vertical = onQueueSongClickListener;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.songs_item_recycler_view_vertical, parent, false);
        return new SongViewHolder(view, mOnQueueAdapterRecyclerView_Vertical);
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        String title = CommonVariables.queue_song_name.get(position);
        holder.textView.setText(title);
        Picasso.get().load(CommonVariables.queue_song_icon_url.get(position)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return CommonVariables.queue_song_icon_url.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;
        OnQueueSongClickListener onQueueSongClickListener;
        public SongViewHolder(View itemView, OnQueueSongClickListener onQueueSongClickListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.idImgSongRV_Vertical);
            textView = itemView.findViewById(R.id.idDescSongRV_Vertical);
            this.onQueueSongClickListener = onQueueSongClickListener;

            imageView.setOnClickListener(this);
            textView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v==imageView)
                onQueueSongClickListener.onQueueSongImageClick(getAdapterPosition());
            else if(v==textView)
                onQueueSongClickListener.onQueueSongTitleClick(getAdapterPosition());
        }
    }

    public interface OnQueueSongClickListener
    {
        void onQueueSongTitleClick(int position);
        void onQueueSongImageClick(int position);
    }
}


