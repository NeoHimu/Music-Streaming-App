package com.himanshusingh.www.musicplayer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by himanshu on 26/2/19.
 */

public class LyricsAdapterRecyclerView_Vertical extends RecyclerView.Adapter<LyricsAdapterRecyclerView_Vertical.LyricsViewHolder> {

    private String[] data;
    public LyricsAdapterRecyclerView_Vertical(String[] data)
    {
        this.data = data;
    }

    @Override
    public LyricsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.lyrics_item_recycler_view_vertical, parent, false);
        return new LyricsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LyricsViewHolder holder, int position) {
        String title = data[position];
        holder.textView.setText(title);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class LyricsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public LyricsViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.idImgLyricsRV_Vertical);
            textView = itemView.findViewById(R.id.idDescLyricsRV_Vertical);
        }
    }
}
