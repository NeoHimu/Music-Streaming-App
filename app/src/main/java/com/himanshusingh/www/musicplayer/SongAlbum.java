package com.himanshusingh.www.musicplayer;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.himanshusingh.www.musicplayer.models.AlbumModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by himanshu on 20/3/19.
 */
interface OnFetchSongInAlbumListener
{
    public void onUrlsFetched(ArrayList<String> urls);
    public void onUrlsError(String error);
}

public class SongAlbum extends AppCompatActivity implements OnFetchSongInAlbumListener, ItemAlbumSongAdapterRV_Vertical.OnSongClickListener {
    AlbumModel albumModel;
    ImageView imageView;
    String current_url, current_title;
    static int pos = 0;
    private static final int PERMISSION_REQUEST_READ_WRITE_EXTERNAL_STORAGE = 20; //Arbitrary >= 0
    String TAG = "SongAlbum : download file ";
    ProgressDialog progress;
    String new_playlist_name = "";
    ImageView albumImage;
    ImageView mPlayerControl;
    TextView tvCurrentSong;
    int current_song_position=0;
    long downloadID;
    BroadcastReceiver onDownloadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Fetching the download id received with the broadcast
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            //Checking if the received broadcast is for our enqueued download by matching download id
            if (downloadID == id) {
                Toast.makeText(SongAlbum.this, "Download Completed", Toast.LENGTH_LONG).show();
            }
        }
    };

    ArrayList<String> song_urls = new ArrayList<String>();
    ArrayList<String> song_names = new ArrayList<String>();
    ArrayList<String> song_icon_urls = new ArrayList<String>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_album);
        imageView = findViewById(R.id.idAlbumCoverImageView);
        albumImage = findViewById(R.id.idSongAlbumImage);
        mPlayerControl = findViewById(R.id.idSongAlbumPlayPause);
        tvCurrentSong = findViewById(R.id.idSongAlbumName);

        registerReceiver(onDownloadComplete,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        tvCurrentSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SongAlbum.this, Player.class);
                i.putStringArrayListExtra("song_urls", song_urls);
//                i.putStringArrayListExtra("song_icon_urls", song_icon_urls);
                i.putExtra("current_song_name", song_urls.get(current_song_position));
                i.putExtra("song_pos", current_song_position);
                startActivity(i);
            }
        });

        mPlayerControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePlayPause();
            }
        });
        MusicManager.player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {  mPlayerControl.setImageResource(R.drawable.icon_play);
            }
        });

        albumModel = getIntent().getParcelableExtra("album_song_one_item");
        Picasso.get().load(Endpoints.BASE_URL+albumModel.getAlbumCoverImagePath()).into(imageView);
        MusicManager.current_song_icon_url = Endpoints.BASE_URL+albumModel.getAlbumCoverImagePath();
        Log.d("song url", Endpoints.SONG_IN_ALBUM_URL+albumModel.getAlbumPath().substring(8));
        VolleyCall call = new VolleyCall(SongAlbum.this, Endpoints.SONG_IN_ALBUM_URL+albumModel.getAlbumPath().substring(8).replaceAll(" ", "%20"), SongAlbum.this);
        call.parse();
    }

    public class VolleyCall {
        private String TAG = SplashScreen.VolleyCall.class.getSimpleName();
        private String mUrl;
        private OnFetchSongInAlbumListener mOnFetchSongInAlbumListener;
        private RequestQueue mQueue;
        private ArrayList<String> mSong = new ArrayList<String>();

        public VolleyCall(Context context, String url, OnFetchSongInAlbumListener onFetchSongInAlbumListener) {
            mUrl = url;
            mOnFetchSongInAlbumListener = onFetchSongInAlbumListener;
            mQueue = Volley.newRequestQueue(context);
        }

        public void parse() {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, mUrl, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.e(TAG, "_log : onResponse : response : " + response.toString());
                                JSONArray jsonArray = response.getJSONArray("data");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    mSong.add(jsonArray.getString(i));
                                }

                                mOnFetchSongInAlbumListener.onUrlsFetched(mSong);

                            } catch (JSONException e) {
                                Log.e(TAG, "_log : onResponse : JSONException : " + e.getMessage());
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "_log : onErrorResponse : error : " + error.getMessage());
                    mOnFetchSongInAlbumListener.onUrlsError(error.getMessage());
                }
            });

            mQueue.add(request);
            Log.e(TAG, "_log : song array size : " + mSong.size());
        }
    }
    @Override
    public void onUrlsFetched(ArrayList<String> urls) {
        //fill the recycler view adapter
        for(int i=0;i<urls.size();i++)
        {
            song_urls.add(Endpoints.SITE_URL+urls.get(i));
            String []temp = urls.get(i).split("/");
            song_names.add(temp[temp.length-1]);
        }

        RecyclerView allLyricsList = findViewById(R.id.idAlbum);
        allLyricsList.setLayoutManager(new LinearLayoutManager(this));
        allLyricsList.setAdapter(new ItemAlbumSongAdapterRV_Vertical(Endpoints.BASE_URL+albumModel.getAlbumCoverImagePath(), song_names, this));
    }

    @Override
    public void onUrlsError(String error) {

    }

    @Override
    public void onSongTitleClick(int position) {
        current_song_position = position;
        mPlayerControl.setImageResource(R.drawable.icon_pause);
        tvCurrentSong.setText(song_names.get(position));
        //CommonVariables.recent_song_name.add(song_names.get(position));
        //CommonVariables.recent_song_url.add(song_urls.get(position));
        if (!MusicManager.current_song_icon_url.matches(""))
        {
            Picasso.get().load(MusicManager.current_song_icon_url).into(albumImage);
           // CommonVariables.recent_song_icon_url.add(MusicManager.current_song_icon_url);
        }
        else
        {
            albumImage.setBackgroundResource(R.drawable.icon_cover);
            //CommonVariables.recent_song_icon_url.add("R.drawable.icon_cover");
        }
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        MusicManager.SoundPlayer(this, song_urls.get(position).replace(" ","%20"), progress);

    }

    @Override
    public void onSongImageClick(int position) {

    }


    @Override
    public void onAddToQueueClick(int position) {
        CommonVariables.queue_song_url.add(0, song_urls.get(position));
        CommonVariables.queue_song_icon_url.add(0, MusicManager.current_song_icon_url);
        CommonVariables.queue_song_name.add(0, song_names.get(position));
        Toast.makeText(this, "Added to the Queue", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOptionMenuClick(int position, TextView textView) {
        pos = position;
        //Toast.makeText(this, "Option menu clicked!", Toast.LENGTH_SHORT).show();
        PopupMenu popupMenu = new PopupMenu(this, textView);
        popupMenu.inflate(R.menu.option_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.id_menu_share:
                        Toast.makeText(getApplicationContext(), "Share", Toast.LENGTH_SHORT).show();
                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody = "Listen this bhajan on SSD app "+song_urls.get(pos);
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "SSD bhajan");
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(sharingIntent, "Share via"));
                        break;

                    case R.id.id_menu_download:
                        Toast.makeText(getApplicationContext(), "Download is clicked", Toast.LENGTH_SHORT).show();
                        //Runtime permission request required if Android permission >= Marshmallow
                        current_url = song_urls.get(pos);
                        current_title = song_names.get(pos);
                        Log.d("url of the song ", current_url);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                            checkPermission(current_url, current_title);
                        else
//                          new DownloadFile().execute(current_url, current_title);
                            downloadFile(current_url, current_title);
                        break;

                    case R.id.id_menu_create_playlist:
                        final EditText playlist_name = new EditText(SongAlbum.this);

// Set the default text to a link of the Queen
                        playlist_name.setHint("Enter playlist name");

                        new AlertDialog.Builder(SongAlbum.this)
                                .setTitle("New Playlist")
                                .setView(playlist_name)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        String playlist_name_string = playlist_name.getText().toString();

                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                    }
                                })
                                .show();
                    default:
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }


    private void checkPermission(String url, String title) {
        // Check if the permission has been granted
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED ) {
            // Permission is already available
            //new DownloadFile().execute(url, title);
            downloadFile(url, title);

        } else {
            // Permission is missing and must be requested.
            requestReadExternalStoragePermission();
        }
    }

    //download using Download Manager
    public void downloadFile(String uRl, String fileName) {
        File direct = new File(Environment.getExternalStorageDirectory()
                + "/MyMusicPlayer");

        if (!direct.exists()) {
            direct.mkdirs();
        }

        DownloadManager mgr = (DownloadManager) getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);

        Uri downloadUri = Uri.parse(uRl.replace(" ", "%20"));
        DownloadManager.Request request = new DownloadManager.Request(
                downloadUri);

        request.setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI
                        | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false).setTitle(fileName)
                .setDescription("Lyrics getting downloaded!")
                .setDestinationInExternalPublicDir("/MyMusicPlayer", fileName);

        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        downloadID = mgr.enqueue(request);
        Toast.makeText(SongAlbum.this, "Download started!", Toast.LENGTH_SHORT).show();
    }

    private void requestReadExternalStoragePermission() {
        // Permission has not been granted and must be requested.
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            ActivityCompat.requestPermissions(SongAlbum.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_READ_WRITE_EXTERNAL_STORAGE);

        } else {
            // Request the permission.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_READ_WRITE_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_READ_WRITE_EXTERNAL_STORAGE) {
            // Request for permission.
            if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                // Permission has been granted.
//                new DownloadFile().execute(current_url, current_title);
                downloadFile(current_url, current_title);
            } else {
                // Permission request was denied by user
                // Show a snackBar, exit program, close activity, etc.
            }
        }
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
        else if(!tvCurrentSong.getText().toString().matches(""))
            mPlayerControl.setImageResource(R.drawable.icon_play);
    }

}
