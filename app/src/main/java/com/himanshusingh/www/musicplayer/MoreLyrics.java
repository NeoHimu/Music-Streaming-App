package com.himanshusingh.www.musicplayer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.himanshusingh.www.musicplayer.models.AlbumLyricsModel;

import java.util.ArrayList;

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
        ArrayList<String> desc=new ArrayList<String>();
        ArrayList<String> desc1=new ArrayList<String>();
        desc1.add("test1");
        desc.add("test");
        allLyricsList.setAdapter(new LyricsAdapterRecyclerView_Vertical(desc, desc1));
    }
}
