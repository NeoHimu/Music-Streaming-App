package com.himanshusingh.www.musicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.himanshusingh.www.musicplayer.models.AlbumLyricsModel;
import com.himanshusingh.www.musicplayer.models.AlbumModel;

import java.util.ArrayList;

/**
 * Created by himanshu on 26/2/19.
 */

public class MoreLyrics extends AppCompatActivity implements LyricsAdapterRecyclerView_Vertical.OnLyricsClickListenerMore {
    ArrayList<AlbumLyricsModel> albumLyricsModelArrayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics);
        albumLyricsModelArrayList = getIntent().getParcelableArrayListExtra("more_lyrics");
        RecyclerView allLyricsList = findViewById(R.id.idAllLyrics);
        allLyricsList.setLayoutManager(new LinearLayoutManager(this));
        allLyricsList.setAdapter(new LyricsAdapterRecyclerView_Vertical(albumLyricsModelArrayList, this));
    }

    @Override
    public void onLyricsTitleClickMore(int position) {
        //Toast.makeText(this, "Title is clicked "+position, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MoreLyrics.this, LyricsAlbum.class);
        //i.putExtra("image_url", Endpoints.BASE_URL+albumLyricsModelArrayList.get(position).getAlbumCoverImagePath().substring(2));
        i.putExtra("album_lyrics_one_item", albumLyricsModelArrayList.get(position));
        startActivity(i);
    }

    @Override
    public void onLyricsImageClickMore(int position) {
        //Toast.makeText(this, "Image is clicked "+position, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MoreLyrics.this, LyricsAlbum.class);
        //i.putExtra("image_url", Endpoints.BASE_URL+albumLyricsModelArrayList.get(position).getAlbumCoverImagePath().substring(2));
        i.putExtra("album_lyrics_one_item", albumLyricsModelArrayList.get(position));
        startActivity(i);
    }
}
