package com.himanshusingh.www.musicplayer;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.himanshusingh.www.musicplayer.models.AlbumLyricsModel;
import com.himanshusingh.www.musicplayer.models.LyricsModel;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SongsAdapterRecyclerView.OnSongClickListener, LyricsAdapterRecyclerView.OnLyricsClickListener{
    TextView tvMoreSongs, tvMoreAlbums, tvMoreLyrics;
    private ImageView mPlayerControl;
    Toolbar toolbar;
    int pos=0;
    ProgressDialog progress;
    RecyclerView songsList;
    ArrayList<LyricsModel> lyricsModelArrayList;
    ArrayList<AlbumLyricsModel> albumLyricsModelArrayList;
    ArrayList<String> songs_name;
    private TextView mSelectedTrackTitle;
    private ImageView mSelectedTrackImage;
    public boolean initialStage=true;
    public boolean playPause = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.idToolbar);
        mSelectedTrackTitle = (TextView)findViewById(R.id.id_selected_track_title);
        mSelectedTrackImage = (ImageView)findViewById(R.id.id_selected_track_image);
        tvMoreSongs = findViewById(R.id.idMoreSongs);
        tvMoreAlbums = findViewById(R.id.idMoreAlbums);
        tvMoreLyrics = findViewById(R.id.idMoreLyrics);
        mPlayerControl = (ImageView)findViewById(R.id.id_player_control);

        lyricsModelArrayList = getIntent().getParcelableArrayListExtra("lyrics");
        albumLyricsModelArrayList = getIntent().getParcelableArrayListExtra("album_lyrics");

        songs_name = new ArrayList<>();
        songs_name.add("test");
        mPlayerControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePlayPause();
            }
        });
        MusicManager.player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mPlayerControl.setImageResource(R.drawable.icon_play);
            }
        });

        mSelectedTrackTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, Player.class);
//                i.putStringArrayListExtra("song_urls", song_urls);
//                i.putExtra("current_song_name", song_names.get(pos));
//                i.putExtra("song_pos", pos);
//                startActivity(i);
            }
        });

        mSelectedTrackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, Player.class);
//                i.putStringArrayListExtra("song_urls", song_urls);
//                i.putExtra("current_song_name", song_names.get(pos));
//                i.putExtra("song_pos", pos);
//                startActivity(i);
            }
        });


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
        songsList = findViewById(R.id.idRVSongs);
        songsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        songsList.setAdapter(new SongsAdapterRecyclerView(songs_name, this));

        RecyclerView albumList = findViewById(R.id.idRVAlbums);
        albumList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        String[] desc1 = {"hello", "heoo","kdsfk", "hello", "hellojijisjid","kdsfk"};
        albumList.setAdapter(new AlbumAdapterRecyclerView(desc1));

        RecyclerView lyricsList = findViewById(R.id.idRVLyrics);
        lyricsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        lyricsList.setAdapter(new LyricsAdapterRecyclerView(albumLyricsModelArrayList, this));
    }

    private void togglePlayPause() {
        if (MusicManager.isPlaying == true) {
            MusicManager.player.pause();
            MusicManager.isPlaying = false;
            mPlayerControl.setImageResource(R.drawable.icon_play);
        } else {
            MusicManager.isPlaying = true;
            MusicManager.player.start();
            mPlayerControl.setImageResource(R.drawable.icon_pause);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(MusicManager.isPlaying==true)
            mPlayerControl.setImageResource(R.drawable.icon_pause);
        else
            mPlayerControl.setImageResource(R.drawable.icon_play);
    }

    @Override
    public void onSongClick(int position) {
//        pos = position;
//        mPlayerControl.setImageResource(R.drawable.icon_pause);
////        toolbar.setBackgroundColor(333333);
//        mSelectedTrackTitle.setText(song_names.get(position));
//        mSelectedTrackImage.setBackgroundResource(R.drawable.icon_cover);
//
//        progress = new ProgressDialog(this);
//        progress.setTitle("Loading");
//        progress.setMessage("Wait while loading...");
//        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
//        progress.show();
//        MusicManager.SoundPlayer(this, song_urls.get(position), progress);
    }

    @Override
    public void onImageClick(int position) {
        Log.d("Image: ", position+"");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLyricsTitleClick(int position) {

    }

    @Override
    public void onLyricsImageClick(int position) {
        Intent i = new Intent(MainActivity.this, LyricsAlbum.class);
        //i.putExtra("image_url", Endpoints.BASE_URL+albumLyricsModelArrayList.get(position).getAlbumCoverImagePath().substring(2));
        i.putExtra("album_lyrics_one_item", albumLyricsModelArrayList.get(position));
        startActivity(i);

    }
}
