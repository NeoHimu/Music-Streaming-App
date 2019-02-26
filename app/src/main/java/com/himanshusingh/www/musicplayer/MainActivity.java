package com.himanshusingh.www.musicplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvMoreSongs, tvMoreAlbums, tvMoreLyrics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMoreSongs = findViewById(R.id.idMoreSongs);
        tvMoreAlbums = findViewById(R.id.idMoreAlbums);
        tvMoreLyrics = findViewById(R.id.idMoreLyrics);

        tvMoreSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Songs.class);
                startActivity(i);
            }
        });

        tvMoreAlbums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Albums.class);
                startActivity(i);
            }
        });

        tvMoreLyrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Lyrics.class);
                startActivity(i);
            }
        });

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
