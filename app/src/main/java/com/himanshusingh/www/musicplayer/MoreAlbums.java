package com.himanshusingh.www.musicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.himanshusingh.www.musicplayer.models.AlbumModel;

import java.util.ArrayList;

/**
 * Created by himanshu on 26/2/19.
 */

public class MoreAlbums extends AppCompatActivity implements AlbumAdapterRecyclerView_Vertical.OnAlbumClickListenerMore{
    ArrayList<AlbumModel> albumModelArrayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);

        albumModelArrayList = getIntent().getParcelableArrayListExtra("more_albums");

        RecyclerView allAlbumsList = findViewById(R.id.idAllAlbums);
        allAlbumsList.setLayoutManager(new LinearLayoutManager(this));
        allAlbumsList.setAdapter(new AlbumAdapterRecyclerView_Vertical(albumModelArrayList, this));

    }

    @Override
    public void onAlbumTitleClickMore(int position) {
        //Toast.makeText(this, "title position : "+position, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MoreAlbums.this, SongAlbum.class);
        i.putExtra("album_song_one_item", albumModelArrayList.get(position));
        startActivity(i);
    }

    @Override
    public void onAlbumImageClickMore(int position) {
        //Toast.makeText(this, "image position : "+position, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MoreAlbums.this, SongAlbum.class);
        i.putExtra("album_song_one_item", albumModelArrayList.get(position));
        startActivity(i);
    }
}
