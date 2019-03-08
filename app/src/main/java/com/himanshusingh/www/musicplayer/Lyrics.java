package com.himanshusingh.www.musicplayer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by himanshu on 26/2/19.
 */

public class Lyrics extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics);
        RecyclerView allLyricsList = findViewById(R.id.idAllLyrics);
        allLyricsList.setLayoutManager(new LinearLayoutManager(this));
        String[] desc = {"hello", "hellojijisjid","kdsfk", "hello", "hellojijisjid","kdsfk", "hello", "hellojijisjid","kdsfk", "hello", "hellojijisjid","kdsfk"};
        allLyricsList.setAdapter(new LyricsAdapterRecyclerView_Vertical(desc));
    }
}
