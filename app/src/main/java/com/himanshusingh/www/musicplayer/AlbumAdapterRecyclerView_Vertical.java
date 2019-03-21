package com.himanshusingh.www.musicplayer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.himanshusingh.www.musicplayer.models.AlbumModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by himanshu on 26/2/19.
 */

public class AlbumAdapterRecyclerView_Vertical extends RecyclerView.Adapter<AlbumAdapterRecyclerView_Vertical.AlbumViewHolder> {

    private ArrayList<AlbumModel> albumModelArrayList;
    private OnAlbumClickListenerMore mOnAlbumClickListenerMore;
    public AlbumAdapterRecyclerView_Vertical(ArrayList<AlbumModel> albumModelArrayList, OnAlbumClickListenerMore onAlbumClickListenerMore)
    {
        this.albumModelArrayList = albumModelArrayList;
        this.mOnAlbumClickListenerMore = onAlbumClickListenerMore;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.album_item_recycler_view_vertical, parent, false);
        return new AlbumViewHolder(view, mOnAlbumClickListenerMore);
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        String title = albumModelArrayList.get(position).getAlbum();
        holder.textView.setText(title);
        Picasso.get().load(Endpoints.BASE_URL+albumModelArrayList.get(position).getAlbumCoverImagePath().substring(2)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return albumModelArrayList.size();
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;
        OnAlbumClickListenerMore onAlbumClickListenerMore;
        public AlbumViewHolder(View itemView, OnAlbumClickListenerMore onAlbumClickListenerMore) {
            super(itemView);
            imageView = itemView.findViewById(R.id.idImgAlbumRV_Vertical);
            textView = itemView.findViewById(R.id.idDescAlbumRV_Vertical);
            this.onAlbumClickListenerMore = onAlbumClickListenerMore;
            imageView.setOnClickListener(this);
            textView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v==imageView)
                onAlbumClickListenerMore.onAlbumImageClickMore(getAdapterPosition());
            else if(v==textView)
                onAlbumClickListenerMore.onAlbumTitleClickMore(getAdapterPosition());
        }
    }
    interface OnAlbumClickListenerMore
    {
        void onAlbumTitleClickMore(int position);
        void onAlbumImageClickMore(int position);
    }
}
