package com.himanshusingh.www.musicplayer;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by himanshu on 18/4/19.
 */

public class Recents extends AppCompatActivity implements RecentsAdapterRecyclerView_Vertical.OnRecentSongClickListener{
    DividerItemDecoration itemDecorator;
    ProgressDialog progress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recents);
        itemDecorator = new DividerItemDecoration(Recents.this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(Recents.this, R.drawable.divider));

        progress = new ProgressDialog(this);
        RecyclerView allSatsangList = findViewById(R.id.idRecentSongs);
        allSatsangList.addItemDecoration(itemDecorator);
        allSatsangList.setLayoutManager(new LinearLayoutManager(this));
        allSatsangList.setAdapter(new RecentsAdapterRecyclerView_Vertical(this));
    }

    @Override
    public void onRecentSongTitleClick(int position) {
        CommonVariables.recent_pos = position;
        MusicManager.current_song_icon_url = CommonVariables.recent_song_icon_url.get(position);
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        MusicManager.SoundPlayer(this, CommonVariables.recent_song_url.get(position), progress);
    }

    @Override
    public void onRecentSongImageClick(int position) {
        CommonVariables.recent_pos = position;
        MusicManager.current_song_icon_url = CommonVariables.recent_song_icon_url.get(position);
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        MusicManager.SoundPlayer(this, CommonVariables.recent_song_url.get(position), progress);
    }
}
