package com.himanshusingh.www.musicplayer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by himanshu on 20/4/19.
 */

public class SongsInPlaylist extends AppCompatActivity implements SongsInPlaylistAdapterRecyclerView_Vertical.OnSongsInPlaylistSongClickListener{
    DividerItemDecoration itemDecorator;
    ProgressDialog progress;
    ArrayList<String> song_urls = new ArrayList<>();
    ArrayList<String> song_icon_urls = new ArrayList<>();
    ArrayList<String> song_names = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_in_playlist);
        itemDecorator = new DividerItemDecoration(SongsInPlaylist.this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(SongsInPlaylist.this, R.drawable.divider));
        // get items from the table and fill it in the arraylist
        Intent i = getIntent();
        String table_name = i.getExtras().getString("playlist_name");
        DatabaseHelper myDb = new DatabaseHelper(this);
        myDb.createTable(table_name);
        Cursor res = myDb.getAllSongs(table_name);
        if(res.getCount()!=0)
        {
            while (res.moveToNext())
            {
                song_urls.add(res.getString(1));
                song_names.add(res.getString(2));
                song_icon_urls.add(res.getString(3));
            }
        }

        progress = new ProgressDialog(this);
        RecyclerView allSongsInPlaylist = findViewById(R.id.idSongsInPlaylist);
        allSongsInPlaylist.addItemDecoration(itemDecorator);
        allSongsInPlaylist.setLayoutManager(new LinearLayoutManager(this));
        allSongsInPlaylist.setAdapter(new SongsInPlaylistAdapterRecyclerView_Vertical(song_icon_urls, song_names, this));
    }


    @Override
    public void onSongsInPlaylistSongTitleClick(int position) {
        MusicManager.current_song_icon_url = song_icon_urls.get(position).replace(" ", "%20");
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        MusicManager.SoundPlayer(this, song_urls.get(position).replace(" ", "%20"), progress);
    }

    @Override
    public void onSongsInPlaylistSongImageClick(int position) {
        MusicManager.current_song_icon_url = song_icon_urls.get(position).replace(" ","%20");
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        MusicManager.SoundPlayer(this, song_urls.get(position).replace(" ", "%20"), progress);
    }
}
