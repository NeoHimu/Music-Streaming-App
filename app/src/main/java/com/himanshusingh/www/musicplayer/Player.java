package com.himanshusingh.www.musicplayer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
//
///* This file contains the source code for examples discussed in Tutorials 1-9 of developerglowingpigs YouTube channel.
// *  The source code is for your convenience purposes only. The source code is distributed on an "AS IS" BASIS,
// *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//*/

//---Implement OnSeekBarChangeListener to keep track of seek bar changes ---
public class Player extends Activity  {
    Intent serviceIntent;
    Intent temp_serviceIntent = null;
    Intent temp_Intent = null;
    private ImageView buttonPlayStop;
    TextView tvSongLabel;
    int pos = 0;
    String playing_song_id = ""; //song id of playing song
    String prev_song_id = "";
    Bundle currentInstance;
    ImageView btPrev, btNext;

    // -- PUT THE NAME OF YOUR AUDIO FILE HERE...URL GOES IN THE SERVICE
    String strAudioLink = "";
//    private boolean isOnline;
//    private boolean boolMusicPlaying = false;
//    TelephonyManager telephonyManager;
//    PhoneStateListener listener;
    ProgressDialog progress;
    // --Seekbar variables --
    private SeekBar seekBar;
    private int seekMax;
//    private static int songEnded = 0;
//    boolean mBroadcastIsRegistered;

    // --Set up constant ID for broadcast of seekbar position--
    public static final String BROADCAST_SEEKBAR = "com.himanshusingh.www.musicplayer.sendseekbar";
//    Intent intent;

    // Progress dialogue and broadcast receiver variables
//    boolean mBufferBroadcastIsRegistered;
//    private ProgressDialog pdBuff = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initViews();
        buttonPlayStop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MusicManager.isPlaying == true) {
                    MusicManager.player.pause();
                    MusicManager.isPlaying = false;
                    buttonPlayStop.setBackgroundResource(R.drawable.icon_play);
                } else {
                    MusicManager.isPlaying = true;
                    MusicManager.player.start();
                    buttonPlayStop.setBackgroundResource(R.drawable.icon_pause);
                }
            }
        });
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        final ArrayList<String> song_urls = bundle.getStringArrayList("song_urls");
        pos = bundle.getInt("song_pos", 0);
        tvSongLabel.setText(bundle.getString("current_song_name"));
        assert song_urls != null;
        String url = song_urls.get(pos);
        strAudioLink = Uri.parse(url).toString();
        String[] temp = url.split("/");
        final String song_name = temp[temp.length - 1];
        tvSongLabel.setText(song_name);
//        setListeners();
//        try {
//                serviceIntent = new Intent(this, PlayerService.class);
//
//                // --- set up seekbar intent for broadcasting new position to service ---
//                intent = new Intent(BROADCAST_SEEKBAR);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            Toast.makeText(getApplicationContext(),
//                    e.getClass().getName() + " " + e.getMessage(),
//                    Toast.LENGTH_LONG).show();
//        }


        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos = (pos+1)%(song_urls.size());
                String[] temp = song_urls.get(pos).split("/");
                tvSongLabel.setText(temp[temp.length-1]);
                buttonPlayStop.setBackgroundResource(R.drawable.icon_pause);
                strAudioLink = song_urls.get(pos);
                progress = new ProgressDialog(v.getContext());
                progress.setTitle("Loading");
                progress.setMessage("Wait while loading...");
                progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
                progress.show();
                MusicManager.SoundPlayer(v.getContext(), strAudioLink, progress);

            }
        });

        btPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos = (song_urls.size()+pos-1)%(song_urls.size());
                String[] temp = song_urls.get(pos).split("/");
                tvSongLabel.setText(temp[temp.length-1]);
                buttonPlayStop.setBackgroundResource(R.drawable.icon_pause);
                strAudioLink = song_urls.get(pos);
                progress = new ProgressDialog(v.getContext());
                progress.setTitle("Loading");
                progress.setMessage("Wait while loading...");
                progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
                progress.show();
                MusicManager.SoundPlayer(v.getContext(), strAudioLink, progress);

            }
        });


    }

//
//    // -- Broadcast Receiver to update position of seekbar from service --
//    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent serviceIntent) {
//            updateUI(serviceIntent);
//        }
//    };
//
//    private void updateUI(Intent serviceIntent) {
//        String counter = serviceIntent.getStringExtra("counter");
//        String mediamax = serviceIntent.getStringExtra("mediamax");
//        String strSongEnded = serviceIntent.getStringExtra("song_ended");
//        int seekProgress = Integer.parseInt(counter);
//        seekMax = Integer.parseInt(mediamax);
//        songEnded = Integer.parseInt(strSongEnded);
//        seekBar.setMax(seekMax);
//        seekBar.setProgress(seekProgress);
//        if (songEnded == 1) {
//            buttonPlayStop.setBackgroundResource(R.drawable.icon_pause);
//        }
//    }
//
//    // --End of seekbar update code--
//
    // --- Set up initial screen ---
    private void initViews() {
        buttonPlayStop = findViewById(R.id.idPlayPause);
        buttonPlayStop.setBackgroundResource(R.drawable.icon_play);
        tvSongLabel = findViewById(R.id.idSongLabel);
        btNext = findViewById(R.id.idNext);
        btPrev = findViewById(R.id.idPrev);

        // --Reference seekbar in main.xml
        seekBar = (SeekBar) findViewById(R.id.idSeekBar);
    }
//
//    // --- Set up listeners ---
//    private void setListeners() {
//        buttonPlayStop.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                buttonPlayStopClick();
//            }
//        });
//        seekBar.setOnSeekBarChangeListener(this);
//
//    }
//
//    // --- invoked from ButtonPlayStop listener above ----
//    private void buttonPlayStopClick() {
//        if (!boolMusicPlaying) {
//            buttonPlayStop.setBackgroundResource(R.drawable.icon_pause);
//            playAudio();
//            boolMusicPlaying = true;
//        } else {
//            if (boolMusicPlaying) {
//                buttonPlayStop.setBackgroundResource(R.drawable.icon_play);
//                stopMyPlayService();
//                boolMusicPlaying = false;
//            }
//        }
//    }
//
//    // --- Stop service (and music) ---
//    private void stopMyPlayService() {
//        // --Unregister broadcastReceiver for seekbar
//        if (mBroadcastIsRegistered) {
//            try {
//                unregisterReceiver(broadcastReceiver);
//                mBroadcastIsRegistered = false;
//            } catch (Exception e) {
//                // Log.e(TAG, "Error in Activity", e);
//                // TODO Auto-generated catch block
//
//                e.printStackTrace();
//                Toast.makeText(
//
//                        getApplicationContext(),
//
//                        e.getClass().getName() + " " + e.getMessage(),
//
//                        Toast.LENGTH_LONG).show();
//            }
//        }
//
//        try {
//            stopService(serviceIntent);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            Toast.makeText(getApplicationContext(),
//                    e.getClass().getName() + " " + e.getMessage(),
//                    Toast.LENGTH_LONG).show();
//        }
//        boolMusicPlaying = false;
//    }
//
//    // --- Start service and play music ---
//    private void playAudio() {
//
//        checkConnectivity();
//        if (isOnline) {
//            stopMyPlayService();
//
//            serviceIntent.putExtra("sentAudioLink", strAudioLink);
//
//            try {
//                startService(serviceIntent);
//            } catch (Exception e) {
//
//                e.printStackTrace();
//                Toast.makeText(
//
//                        getApplicationContext(),
//
//                        e.getClass().getName() + " " + e.getMessage(),
//
//                        Toast.LENGTH_LONG).show();
//            }
//
//            // -- Register receiver for seekbar--
//            registerReceiver(broadcastReceiver, new IntentFilter(
//                    PlayerService.BROADCAST_ACTION));
//            mBroadcastIsRegistered = true;
//
//        } else {
//            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
//            alertDialog.setTitle("Network Not Connected...");
//            alertDialog.setMessage("Please connect to a network and try again");
//            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    // here you can add functions
//                }
//            });
//            alertDialog.setIcon(R.drawable.icon_cover);
//            buttonPlayStop.setBackgroundResource(R.drawable.icon_play);
//            alertDialog.show();
//        }
//    }
//
//    // Handle progress dialogue for buffering...
//    private void showPD(Intent bufferIntent) {
//        String bufferValue = bufferIntent.getStringExtra("buffering");
//        int bufferIntValue = Integer.parseInt(bufferValue);
//
//        // When the broadcasted "buffering" value is 1, show "Buffering"
//        // progress dialogue.
//        // When the broadcasted "buffering" value is 0, dismiss the progress
//        // dialogue.
//
//        switch (bufferIntValue) {
//            case 0:
//                // Log.v(TAG, "BufferIntValue=0 RemoveBufferDialogue");
//                // txtBuffer.setText("");
//                if (pdBuff != null) {
//                    pdBuff.dismiss();
//                }
//                break;
//
//            case 1:
//                BufferDialogue();
//                break;
//
//            // Listen for "2" to reset the button to a play button
//            case 2:
//                buttonPlayStop.setBackgroundResource(R.drawable.icon_play);
//                break;
//
//        }
//    }
//
//    // Progress dialogue...
//    private void BufferDialogue() {
//
//        pdBuff = ProgressDialog.show(Player.this, "Buffering...",
//                "Acquiring song...", true);
//    }
//
//    // Set up broadcast receiver
//    private BroadcastReceiver broadcastBufferReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent bufferIntent) {
//            showPD(bufferIntent);
//        }
//    };
//
//    private void checkConnectivity() {
//        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        if (cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
//                .isConnectedOrConnecting()
//                || cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
//                .isConnectedOrConnecting())
//            isOnline = true;
//        else
//            isOnline = false;
//    }
//
//    // -- onPause, unregister broadcast receiver. To improve, also save screen data ---
//    @Override
//    protected void onPause() {
//        // Unregister broadcast receiver
//        if (mBufferBroadcastIsRegistered) {
//            unregisterReceiver(broadcastBufferReceiver);
//            mBufferBroadcastIsRegistered = false;
//        }
//        super.onPause();
//    }
//
//
//    // -- onResume register broadcast receiver. To improve, retrieve saved screen data ---
//    @Override
//    protected void onResume() {
//        // Register broadcast receiver
//        if (!mBufferBroadcastIsRegistered) {
//            registerReceiver(broadcastBufferReceiver, new IntentFilter(
//                    PlayerService.BROADCAST_BUFFER));
//            mBufferBroadcastIsRegistered = true;
//        }
//        super.onResume();
//    }
//
//    protected void onDestroy() {
//        // --Unregister broadcastReceiver for seekbar
//        if (mBroadcastIsRegistered) {
//            try {
//                unregisterReceiver(broadcastReceiver);
//                mBroadcastIsRegistered = false;
//            } catch (Exception e) {
//                // Log.e(TAG, "Error in Activity", e);
//                // TODO Auto-generated catch block
//
//                e.printStackTrace();
//                Toast.makeText(
//
//                        getApplicationContext(),
//
//                        e.getClass().getName() + " " + e.getMessage(),
//
//                        Toast.LENGTH_LONG).show();
//            }
//        }
//        super.onDestroy();
//    }
//
//    // --- When user manually moves seekbar, broadcast new position to service ---
//    @Override
//    public void onProgressChanged(SeekBar sb, int progress, boolean fromUser) {
//        // TODO Auto-generated method stub
//        if (fromUser) {
//            int seekPos = sb.getProgress();
//            intent.putExtra("seekpos", seekPos);
//            sendBroadcast(intent);
//        }
//    }
//
//
//    // --- The following two methods are alternatives to track seekbar if moved.
//    @Override
//    public void onStartTrackingTouch(SeekBar seekBar) {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public void onStopTrackingTouch(SeekBar seekBar) {
//        // TODO Auto-generated method stub
//
//    }
}


/**
 * Created by himanshu on 27/2/19.
 */
//
//public class Player extends AppCompatActivity {
//    Button btPause, btNext, btPrev;
//    TextView tvSongLabel;
//    SeekBar seekBar;
//    static MediaPlayer mMediaPlayer;
//    Thread updateSeekBar;
//    int pos;
//    private static ProgressDialog progressDialog;
//    private static boolean initialStage = true;
//    int totalDuration;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_player);
//
//        getSupportActionBar().setTitle("Now Playing");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//
//        btPause = findViewById(R.id.idPlayPause);
//        btNext  = findViewById(R.id.idNext);
//        btPrev = findViewById(R.id.idPrev);
//        seekBar = findViewById(R.id.idSeekBar);
//        tvSongLabel = findViewById(R.id.idSongLabel);
//
//        progressDialog = new ProgressDialog(this);
//        mMediaPlayer = new MediaPlayer();
//        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//
//        Bundle bundle = getIntent().getExtras();
//        assert bundle != null;
//        final ArrayList<String> song_urls = bundle.getStringArrayList("urls");
//        pos = bundle.getInt("pos", 0);
//        assert song_urls != null;
//        String url = song_urls.get(pos);
//        String[] temp = url.split("/");
//        final String song_name = temp[temp.length-1];
//        tvSongLabel.setText(song_name);
//        tvSongLabel.setSelected(true);
////
//        updateSeekBar = new Thread(){
//            @Override
//            public void run(){
//                Log.d("total duration", ""+totalDuration);
//                int currentPosition = 0;
//
//                while (currentPosition<totalDuration)
//                {
//                    try{
//                        sleep(500);
//                        currentPosition = mMediaPlayer.getCurrentPosition();
//                        seekBar.setProgress(currentPosition);
//                    }
//                    catch (InterruptedException e){
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//        };
//
////        if(mMediaPlayer!=null)
////        {
//////            mMediaPlayer.stop();
//////            mMediaPlayer.release();
////            mMediaPlayer.reset();
////        }
//
////        mMediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(url));
//        if (initialStage) {
//            new MusicPlayer().execute(Uri.parse(url).toString());
//
//        } else {
//            if (mMediaPlayer!=null && !mMediaPlayer.isPlaying())
//                mMediaPlayer.start();
//        }
//
//
//        updateSeekBar.start();
//        seekBar.setMax(totalDuration);
//        Log.d("getDuration 104", "");
//        seekBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.MULTIPLY);
//        seekBar.getThumb().setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_IN);
//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                Log.d("getDuration 120", "");
//                mMediaPlayer.seekTo(mMediaPlayer.getDuration());
//            }
//        });
//
//
//        btPause.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                seekBar.setMax(mMediaPlayer.getDuration());
//                if(mMediaPlayer.isPlaying())
//                {
//                    btPause.setBackgroundResource(R.drawable.icon_play);
//                    mMediaPlayer.pause();
//                }
//                else
//                {
//                    btPause.setBackgroundResource(R.drawable.icon_pause);
//                    mMediaPlayer.start();
//                }
//            }
//        });
//
//
//        btNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mMediaPlayer.stop();
//                mMediaPlayer.release();
//                pos = (pos+1)%(song_urls.size());
//                String[] temp = song_urls.get(pos).split("/");
//                tvSongLabel.setText(temp[temp.length-1]);
//                initialStage = true;
////              mMediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(song_urls.get(pos)));
//                if (initialStage) {
//                    new MusicPlayer().execute(Uri.parse(song_urls.get(pos)).toString());
//
//                } else {
//                    if (mMediaPlayer!=null && !mMediaPlayer.isPlaying())
//                        mMediaPlayer.start();
//                }
//
//
//
////                mMediaPlayer.start();
//            }
//        });
//
//        btPrev.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mMediaPlayer.stop();
//                mMediaPlayer.release();
//                pos = (pos+pos-1)%(song_urls.size());
////              mMediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(song_urls.get(pos)));
////                if (initialStage) {
//                    new MusicPlayer().execute(Uri.parse(song_urls.get(pos)).toString());
//
////                } else {
////                    if (mMediaPlayer!=null && !mMediaPlayer.isPlaying())
////                        mMediaPlayer.start();
////                }
//                String[] temp = song_urls.get(pos).split("/");
//                tvSongLabel.setText(temp[temp.length-1]);
//                mMediaPlayer.start();
//            }
//        });
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if(item.getItemId() == android.R.id.home)
//        {
//            onBackPressed();
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//    }
//
//    protected void onDestroy()
//    {
//        super.onDestroy();
////        if (mMediaPlayer != null) {
////            mMediaPlayer.reset();
////            mMediaPlayer.release();
////            mMediaPlayer = null;
////        }
//
//    }
//    class MusicPlayer extends AsyncTask<String, Void, Boolean> implements MediaPlayer.OnPreparedListener{
//        @Override
//        protected Boolean doInBackground(String... strings) {
//            Boolean prepared = false;
//            Uri uri = Uri.parse(strings[0]);
//            try {
//                Log.d("Datasource", uri.toString());
//                mMediaPlayer.setDataSource(uri.toString());
//                mMediaPlayer.setOnPreparedListener(this);
//                mMediaPlayer.prepare();
//                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    @Override
//                    public void onCompletion(MediaPlayer mediaPlayer) {
//                        initialStage = true;
//                        mediaPlayer.stop();
//                        mediaPlayer.reset();
//                        mediaPlayer.release();
//                    }
//                });
//                prepared = true;
//
//            } catch (Exception e) {
//                Log.e("Player --> MusicPlayer", e.getMessage());
//                prepared = false;
//            }
//
//            return prepared;
//        }
//
//        @Override
//        protected void onPostExecute(Boolean aBoolean) {
//            super.onPostExecute(aBoolean);
//
//            if (progressDialog.isShowing()) {
//                progressDialog.cancel();
//            }
//
//            initialStage = false;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//            progressDialog.setMessage("Buffering...");
//            progressDialog.show();
//        }
//
//        @Override
//        public void onPrepared(MediaPlayer mp) {
//            totalDuration = mMediaPlayer.getDuration();
//            mp.start();
//        }
//
//
//    }
//
//}
//
