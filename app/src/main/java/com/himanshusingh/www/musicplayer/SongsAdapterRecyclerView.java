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

public class SongsAdapterRecyclerView extends RecyclerView.Adapter<SongsAdapterRecyclerView.SongViewHolder> {

    private String[] data;
    public SongsAdapterRecyclerView(String[] data)
    {
        this.data = data;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.songs_item_recycler_view, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        String title = data[position];
        holder.textView.setText(title);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class SongViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public SongViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.idImgSongRV);
            textView = itemView.findViewById(R.id.idDescSongRV);
        }
    }
}
