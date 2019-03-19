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

public class LyricsAdapterRecyclerView_Vertical extends RecyclerView.Adapter<LyricsAdapterRecyclerView_Vertical.LyricsViewHolder> {

    private ArrayList<String> titles;
    private ArrayList<String> urls;
    public LyricsAdapterRecyclerView_Vertical(ArrayList<String> titles, ArrayList<String> urls)
    {
        this.titles = titles;
        this.urls = urls;
    }

    @Override
    public LyricsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.lyrics_item_recycler_view_vertical, parent, false);
        return new LyricsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LyricsViewHolder holder, int position) {
        String title = titles.get(position);
        holder.textView.setText(title);
    }

    @Override
    public int getItemCount() {
        return titles.size();
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
