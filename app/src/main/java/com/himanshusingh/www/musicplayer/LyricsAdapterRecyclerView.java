package com.himanshusingh.www.musicplayer;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.himanshusingh.www.musicplayer.models.AlbumLyricsModel;
import com.himanshusingh.www.musicplayer.models.LyricsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by himanshu on 26/2/19.
 */



public class LyricsAdapterRecyclerView extends RecyclerView.Adapter<LyricsAdapterRecyclerView.LyricsViewHolder> {

    private ArrayList<AlbumLyricsModel> albumLyricsModelArrayList;
    OnLyricsClickListener mOnLyricsClickListener;
    public LyricsAdapterRecyclerView(ArrayList<AlbumLyricsModel> albumLyricsModelArrayList, OnLyricsClickListener onLyricsClickListener)
    {
        this.albumLyricsModelArrayList = albumLyricsModelArrayList;
        this.mOnLyricsClickListener = onLyricsClickListener;
    }

    @Override
    public LyricsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.lyrics_item_recycler_view, parent, false);
        return new LyricsViewHolder(view, mOnLyricsClickListener);
    }

    @Override
    public void onBindViewHolder(final LyricsViewHolder holder, int position) {
        String title = albumLyricsModelArrayList.get(position).getAlbum();
        holder.textView.setText(title);
        // load image here
        Picasso.get().load(Endpoints.BASE_URL+albumLyricsModelArrayList.get(position).getAlbumCoverImagePath().substring(2)).into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return albumLyricsModelArrayList.size();
    }

    public class LyricsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView textView;
        OnLyricsClickListener onLyricsClickListener;
        public LyricsViewHolder(View itemView, OnLyricsClickListener onLyricsClickListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.idImgLyricsRV);
            textView = itemView.findViewById(R.id.idDescLyricsRV);
            imageView.setOnClickListener(this);
            this.onLyricsClickListener = onLyricsClickListener;
        }

        @Override
        public void onClick(View v) {
            onLyricsClickListener.onLyricsImageClick(getAdapterPosition());
        }
    }

    public interface OnLyricsClickListener
    {
        void onLyricsTitleClick(int position);
        void onLyricsImageClick(int position);
    }

}
