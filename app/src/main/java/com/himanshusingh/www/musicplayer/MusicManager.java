package com.himanshusingh.www.musicplayer;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by himanshu on 8/3/19.
 */

public class MusicManager {
    public static MediaPlayer player=new MediaPlayer();
    public static boolean isPlaying = false;
    public static void SoundPlayer(Context ctx, String song_url, final ProgressDialog progressDialog){
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //togglePlayPause();
                mp.start();
                isPlaying = true;
                progressDialog.dismiss();
            }
        });
        player.setLooping(false); // Set looping
        player.setVolume(100, 100);
        if(player!=null)
        {
            player.stop();
            player.reset();
        }

        try {
            player.setDataSource(song_url);
            player.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
