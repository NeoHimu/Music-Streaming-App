package com.himanshusingh.www.musicplayer;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by himanshu on 26/2/19.
 */

public class MoreSatsang extends AppCompatActivity implements SongsAdapterRecyclerView_Vertical.OnSatsangClickListenerMore{
    String satsang_icon_url;
    ProgressDialog progress;
    ArrayList<String> allSatsangUrls;
    DividerItemDecoration itemDecorator;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satsang_more);
        itemDecorator = new DividerItemDecoration(MoreSatsang.this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(MoreSatsang.this, R.drawable.divider));

        progress = new ProgressDialog(this);
        RecyclerView allSatsangList = findViewById(R.id.idAllSongs);
        allSatsangList.addItemDecoration(itemDecorator);
        allSatsangList.setLayoutManager(new LinearLayoutManager(this));
        allSatsangUrls = getIntent().getStringArrayListExtra("more_satsang_song_urls");
        ArrayList<String> allSatsangNames = getIntent().getStringArrayListExtra("more_satsang_names");
        satsang_icon_url = getIntent().getStringExtra("satsang_cover_image_url");
        allSatsangList.setAdapter(new SongsAdapterRecyclerView_Vertical(allSatsangNames, satsang_icon_url, this));
    }

    @Override
    public void onSatsangTitleClickMore(int position) {
        MusicManager.current_song_icon_url = satsang_icon_url;
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        MusicManager.SoundPlayer(this, allSatsangUrls.get(position), progress);
    }

    @Override
    public void onSatsangImageClickMore(int position) {

        MusicManager.current_song_icon_url = satsang_icon_url;
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        MusicManager.SoundPlayer(this, allSatsangUrls.get(position), progress);
    }
}
