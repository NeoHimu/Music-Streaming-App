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
    public static MediaPlayer player = new MediaPlayer();
    public static boolean isPlaying = false;
    public static int duration=0;
    //some global variables
    public static String current_song_name = "";
    public static String current_song_icon_url = "";

    public static void SoundPlayer(Context ctx, final String song_url, final ProgressDialog progressDialog){

        String[] temp = song_url.split("/");
        current_song_name = temp[temp.length-1].replace("%20", " ");

        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //togglePlayPause();
                mp.start();
                isPlaying = true;
                progressDialog.dismiss();
                duration = player.getDuration();

                //if this song is not in the list
                if(CommonVariables.recent_song_url.indexOf(song_url)==-1)
                {
                    CommonVariables.recent_song_icon_url.add(0, current_song_icon_url);
                    CommonVariables.recent_song_name.add(0, current_song_name);
                    CommonVariables.recent_song_url.add(0, song_url);
                }
                else
                {
                    //first remove the song from the list
                    int idx = CommonVariables.recent_song_url.indexOf(song_url);
                    CommonVariables.recent_song_icon_url.remove(idx);
                    CommonVariables.recent_song_name.remove(idx);
                    CommonVariables.recent_song_url.remove(idx);
                    // and then insert at the front of the list
                    CommonVariables.recent_song_icon_url.add(0, current_song_icon_url);
                    CommonVariables.recent_song_name.add(0, current_song_name);
                    CommonVariables.recent_song_url.add(0, song_url);
                }

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
