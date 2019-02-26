package com.himanshusingh.www.musicplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView songsList = findViewById(R.id.idRVSongs);
        songsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        String[] desc = {"hello", "hellojijisjid","kdsfk", "hello", "hellojijisjid","kdsfk"};
        songsList.setAdapter(new SongsAdapterRecyclerView(desc));

        RecyclerView albumList = findViewById(R.id.idRVAlbums);
        albumList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        String[] desc1 = {"hello", "hellojijisjid","kdsfk", "hello", "hellojijisjid","kdsfk"};
        albumList.setAdapter(new SongsAdapterRecyclerView(desc));

        RecyclerView lyricsList = findViewById(R.id.idRVLyrics);
        lyricsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        String[] desc2 = {"hello", "hellojijisjid","kdsfk", "hello", "hellojijisjid","kdsfk"};
        lyricsList.setAdapter(new SongsAdapterRecyclerView(desc));
    }
}
