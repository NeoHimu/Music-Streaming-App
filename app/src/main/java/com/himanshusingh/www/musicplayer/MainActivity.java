package com.himanshusingh.www.musicplayer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
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

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SongsAdapterRecyclerView.OnSongClickListener{

    TextView tvMoreSongs, tvMoreAlbums, tvMoreLyrics;
    private MediaPlayer mMediaPlayer;
    private ImageView mPlayerControl;
    Toolbar toolbar;
    public boolean isPlaying = false;
    int pos=0;
    ProgressDialog progress;
//    DatabaseHelper databaseHelper;
    RecyclerView songsList;
    ArrayList<String> song_urls;
    ArrayList<String> song_names;
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

//        mMediaPlayer = new MediaPlayer();
//        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                //togglePlayPause();
//                mp.start();
//                isPlaying = true;
//                mPlayerControl.setImageResource(R.drawable.icon_pause);
//                // To dismiss the dialog
//                progress.dismiss();
//            }
//        });
        Bundle bundle = getIntent().getExtras();
        song_urls = bundle.getStringArrayList("data");
        song_names = new ArrayList<String>();
        String temp_url;
        for(int i=0;i<song_urls.size();i++)
        {
            temp_url = song_urls.get(i);
            song_names.add(temp_url.split("/")[temp_url.split("/").length-1]);
        }

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
                Intent i = new Intent(MainActivity.this, Player.class);
                i.putStringArrayListExtra("song_urls", song_urls);
                i.putExtra("current_song_name", song_names.get(pos));
                i.putExtra("song_pos", pos);
                startActivity(i);
            }
        });

        mSelectedTrackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Player.class);
                i.putStringArrayListExtra("song_urls", song_urls);
                i.putExtra("current_song_name", song_names.get(pos));
                i.putExtra("song_pos", pos);
                startActivity(i);
            }
        });

//        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                mPlayerControl.setImageResource(R.drawable.icon_play);
//            }
//        });

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
        songsList.setAdapter(new SongsAdapterRecyclerView(song_names, this));

        RecyclerView albumList = findViewById(R.id.idRVAlbums);
        albumList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        String[] desc1 = {"hello", "heoo","kdsfk", "hello", "hellojijisjid","kdsfk"};
        albumList.setAdapter(new AlbumAdapterRecyclerView(desc1));

        RecyclerView lyricsList = findViewById(R.id.idRVLyrics);
        lyricsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        String[] desc2 = {"hello", "whatsup","kdsfk", "hello", "hellojijisjid","kdsfk"};
        lyricsList.setAdapter(new LyricsAdapterRecyclerView(desc2));

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
//        if (isPlaying == true) {
//            mMediaPlayer.pause();
//            isPlaying = false;
//            mPlayerControl.setImageResource(R.drawable.icon_play);
//        } else {
//            isPlaying = true;
//            mMediaPlayer.start();
//            mPlayerControl.setImageResource(R.drawable.icon_pause);
//        }
    }

    @Override
    public void onSongClick(int position) {
//        Log.d("Song: ",song_urls.get(position));
//        Intent i = new Intent(MainActivity.this, Player.class);
//        i.putExtra("urls", song_urls);
//        i.putExtra("pos", position);
//        i.putExtra("song_id", "song_horizontal_"+position);
//        startActivity(i);
        pos = position;
        mPlayerControl.setImageResource(R.drawable.icon_pause);
//        toolbar.setBackgroundColor(333333);
        mSelectedTrackTitle.setText(song_names.get(position));
        mSelectedTrackImage.setBackgroundResource(R.drawable.icon_cover);

        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
//        isPlaying = true;
//        if(mMediaPlayer!=null)
//        {
//            mMediaPlayer.stop();
//            mMediaPlayer.reset();
//        }
//
//        try {
//            mMediaPlayer.setDataSource(song_urls.get(position));
//            mMediaPlayer.prepareAsync();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        MusicManager.SoundPlayer(this, song_urls.get(position), progress);
    }

    @Override
    public void onImageClick(int position) {
        Log.d("Image: ", position+"");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (mMediaPlayer != null) {
//            if (mMediaPlayer.isPlaying()) {
//                mMediaPlayer.stop();
//            }
//            mMediaPlayer.release();
//            mMediaPlayer = null;
//        }
    }
}
