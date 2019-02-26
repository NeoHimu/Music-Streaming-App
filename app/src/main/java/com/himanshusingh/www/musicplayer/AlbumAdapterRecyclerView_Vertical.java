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

public class AlbumAdapterRecyclerView_Vertical extends RecyclerView.Adapter<AlbumAdapterRecyclerView_Vertical.AlbumViewHolder> {

    private String[] data;
    public AlbumAdapterRecyclerView_Vertical(String[] data)
    {
        this.data = data;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.album_item_recycler_view_vertical, parent, false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        String title = data[position];
        holder.textView.setText(title);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public AlbumViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.idImgAlbumRV_Vertical);
            textView = itemView.findViewById(R.id.idDescAlbumRV_Vertical);
        }
    }
}
