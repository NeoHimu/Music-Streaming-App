package com.himanshusingh.www.musicplayer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.himanshusingh.www.musicplayer.models.AlbumLyricsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by himanshu on 26/2/19.
 */

public class LyricsAdapterRecyclerView_Vertical extends RecyclerView.Adapter<LyricsAdapterRecyclerView_Vertical.LyricsViewHolder> {

    private ArrayList<AlbumLyricsModel> albumLyricsModelArrayList;
    private OnLyricsClickListenerMore mOnLyricsClickListenerMore;
    public LyricsAdapterRecyclerView_Vertical(ArrayList<AlbumLyricsModel> albumLyricsModelArrayList, OnLyricsClickListenerMore onLyricsClickListenerMore)
    {
        this.albumLyricsModelArrayList = albumLyricsModelArrayList;
        this.mOnLyricsClickListenerMore = onLyricsClickListenerMore;
    }

    @Override
    public LyricsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.lyrics_item_recycler_view_vertical, parent, false);
        return new LyricsViewHolder(view, mOnLyricsClickListenerMore);
    }

    @Override
    public void onBindViewHolder(LyricsViewHolder holder, int position) {
        String title = albumLyricsModelArrayList.get(position).getAlbum();
        holder.textView.setText(title);
        // load image here
        Picasso.get().load(Endpoints.BASE_URL+albumLyricsModelArrayList.get(position).getAlbumCoverImagePath().substring(2)).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return albumLyricsModelArrayList.size();
    }

    public class LyricsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;
        OnLyricsClickListenerMore onLyricsClickListenerMore;
        public LyricsViewHolder(View itemView, OnLyricsClickListenerMore onLyricsClickListenerMore) {
            super(itemView);
            imageView = itemView.findViewById(R.id.idImgLyricsRV_Vertical);
            textView = itemView.findViewById(R.id.idDescLyricsRV_Vertical);
            imageView.setOnClickListener(this);
            textView.setOnClickListener(this);
            this.onLyricsClickListenerMore = onLyricsClickListenerMore;
        }

        @Override
        public void onClick(View v) {
            if(v==imageView)
                onLyricsClickListenerMore.onLyricsImageClickMore(getAdapterPosition());
            else if(v==textView)
                onLyricsClickListenerMore.onLyricsTitleClickMore(getAdapterPosition());
        }
    }

    public interface OnLyricsClickListenerMore
    {
        void onLyricsTitleClickMore(int position);
        void onLyricsImageClickMore(int position);
    }
}
