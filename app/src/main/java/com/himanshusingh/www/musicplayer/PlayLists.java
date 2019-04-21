package com.himanshusingh.www.musicplayer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by himanshu on 20/4/19.
 */

public class PlayLists extends AppCompatActivity implements PlayListsAdapterRecyclerView_Vertical.OnPlayListsSongClickListener{
    DividerItemDecoration itemDecorator;
    ProgressDialog progress;
    DatabaseHelper myDb;
    String song_url="";
    String song_icon_url="";
    String song_name="";
    static ArrayList<String> playlists = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlists);
        itemDecorator = new DividerItemDecoration(PlayLists.this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(PlayLists.this, R.drawable.divider));


        myDb = new DatabaseHelper(this);// by default table is "Favourites"
        //myDb.insertSongInPlaylist("aa", "aa","aa");

        //get all playlists
        Cursor res = myDb.getAllPlaylistNames();
//        playlist_names.add("hello");
        ArrayList<String> playlist_names = new ArrayList<>();
        if(res.getCount()!=0)
        {
            while (res.moveToNext())
            {
                playlist_names.add(res.getString(1));
            }
        }
        playlists = playlist_names;
        progress = new ProgressDialog(this);
        RecyclerView playlist = findViewById(R.id.idPlaylists);
        playlist.addItemDecoration(itemDecorator);
        playlist.setLayoutManager(new LinearLayoutManager(this));
        playlist.setAdapter(new PlayListsAdapterRecyclerView_Vertical(playlist_names, this));
    }

    @Override
    public void onPlayListsSongTitleClick(int position) {
        if(SongAlbum.isFromMenu)
        {
            Intent i = getIntent();
            song_url = i.getExtras().getString("song_url");
            song_icon_url = i.getExtras().getString("song_icon_url");
            song_name = i.getExtras().getString("song_name");
            // add the current song to the playlist
            DatabaseHelper myDb = new DatabaseHelper(getApplicationContext());
            myDb.createTable(playlists.get(position));
            myDb.insertSongInPlaylist(song_url, song_icon_url, song_name, playlists.get(position));
            Toast.makeText(this, "Song added in "+playlists.get(position)+" playlist!", Toast.LENGTH_SHORT).show();
            SongAlbum.isFromMenu = false;
        }
        else
        {
            Intent i = new Intent(PlayLists.this, SongsInPlaylist.class);
            i.putExtra("playlist_name", playlists.get(position));
            startActivity(i);
        }
    }

    @Override
    public void onPlayListsSongImageClick(int position) {
        if(SongAlbum.isFromMenu)
        {
            Intent i = getIntent();
            song_url = i.getExtras().getString("song_url");
            song_icon_url = i.getExtras().getString("song_icon_url");
            song_name = i.getExtras().getString("song_name");
            // add the current song to the playlist
            DatabaseHelper myDb = new DatabaseHelper(getApplicationContext());
            myDb.createTable(playlists.get(position));
            myDb.insertSongInPlaylist(song_url, song_icon_url, song_name, playlists.get(position));
            Toast.makeText(this, "Song added in "+playlists.get(position)+" playlist!", Toast.LENGTH_SHORT).show();
            SongAlbum.isFromMenu = false;
        }
        else
        {
            Intent i = new Intent(PlayLists.this, SongsInPlaylist.class);
            i.putExtra("playlist_name", playlists.get(position));
            startActivity(i);
        }
    }
}

