package com.himanshusingh.www.musicplayer;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.himanshusingh.www.musicplayer.models.AlbumLyricsModel;
import com.himanshusingh.www.musicplayer.models.AlbumModel;
import com.himanshusingh.www.musicplayer.models.BhajanModel;
import com.himanshusingh.www.musicplayer.models.LyricsModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by himanshu on 19/3/19.
 */

interface OnFetchLyricsInAlbumListener
{
    public void onUrlsFetched(ArrayList<String> urls);
    public void onUrlsError(String error);
}

public class LyricsAlbum extends AppCompatActivity implements OnFetchLyricsInAlbumListener, ItemAlbumLyricsAdapterRV_Vertical.OnLyricsClickListener {
    ArrayList<String> titles;
    ArrayList<String> lyrics_urls;
    String current_url, current_title;
    AlbumLyricsModel albumLyricsModel;
    long downloadID;
    ImageView imageView;
    BroadcastReceiver onDownloadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Fetching the download id received with the broadcast
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            //Checking if the received broadcast is for our enqueued download by matching download id
            if (downloadID == id) {
                Toast.makeText(LyricsAlbum.this, "Download Completed", Toast.LENGTH_LONG).show();
            }
        }
    };
    private static final int PERMISSION_REQUEST_READ_WRITE_EXTERNAL_STORAGE = 20; //Arbitrary >= 0
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics_album);
        imageView = findViewById(R.id.idLyricsAlbumCoverImageView);
        titles = new ArrayList<String>();
        lyrics_urls = new ArrayList<String>();
        albumLyricsModel = getIntent().getParcelableExtra("album_lyrics_one_item");

        registerReceiver(onDownloadComplete,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        Picasso.get().load(Endpoints.BASE_URL+albumLyricsModel.getAlbumCoverImagePath()).into(imageView);
        VolleyCall call = new VolleyCall(LyricsAlbum.this, Endpoints.LYRICS_IN_ALBUM_URL+albumLyricsModel.getLocation().substring(9).replaceAll(" ", "%20"), LyricsAlbum.this);
        call.parse();
    }

    @Override
    public void onLyricsTitleClick(int position) {
        Intent i = new Intent(LyricsAlbum.this, ShowLyrics.class);
        i.putExtra("pos", position);
        i.putStringArrayListExtra("lyrics_urls", lyrics_urls);
        startActivity(i);
    }

    @Override
    public void onLyricsImageClick(int position) {
        Intent i = new Intent(LyricsAlbum.this, ShowLyrics.class);
        i.putExtra("pos", position);
        i.putStringArrayListExtra("lyrics_urls", lyrics_urls);
        startActivity(i);
    }

    @Override
    public void onDownloadLyricsClick(int position) {
        //Runtime permission request required if Android permission >= Marshmallow
        current_url = lyrics_urls.get(position);
        current_title = titles.get(position);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            checkPermission(lyrics_urls.get(position), titles.get(position)+".jpg");
        else
            downloadFile(lyrics_urls.get(position), titles.get(position)+".jpg");
    }

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
                .setDestinationInExternalPublicDir("/MyMusicPlayer", fileName+".jpg");

        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        downloadID = mgr.enqueue(request);
        Toast.makeText(LyricsAlbum.this, "Download started!", Toast.LENGTH_SHORT).show();
    }

    private void checkPermission(String url, String title) {
        // Check if the permission has been granted
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED ) {
            // Permission is already available
            downloadFile(url, title);

        } else {
            // Permission is missing and must be requested.
            requestReadExternalStoragePermission();
        }
    }

    private void requestReadExternalStoragePermission() {
        // Permission has not been granted and must be requested.
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                    ActivityCompat.requestPermissions(LyricsAlbum.this,
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
                downloadFile(current_url, current_title+".jpg");
            } else {
                // Permission request was denied by user
                // Show a snackBar, exit program, close activity, etc.
            }
        }
    }

    public class VolleyCall {
        private String TAG = SplashScreen.VolleyCall.class.getSimpleName();
        private String mUrl;
        private OnFetchLyricsInAlbumListener mOnFetchLyricsInAlbumListener;
        private RequestQueue mQueue;
        private ArrayList<String> mLyrics= new ArrayList<String>();

        public VolleyCall(Context context, String url, OnFetchLyricsInAlbumListener onFetchLyricsInAlbumListener) {
            mUrl = url;
            mOnFetchLyricsInAlbumListener = onFetchLyricsInAlbumListener;
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
                                    mLyrics.add(jsonArray.getString(i));
                                }

                                mOnFetchLyricsInAlbumListener.onUrlsFetched(mLyrics);

                            } catch (JSONException e) {
                                Log.e(TAG, "_log : onResponse : JSONException : " + e.getMessage());
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "_log : onErrorResponse : error : " + error.getMessage());
                    mOnFetchLyricsInAlbumListener.onUrlsError(error.getMessage());
                }
            });

            mQueue.add(request);
            Log.e(TAG, "_log : lyrics array size : " + mLyrics.size());
        }
    }

    @Override
    public void onUrlsFetched(ArrayList<String> urls) {
        for(int i=0;i<urls.size();i++)
        {
            String[] temp_arr = urls.get(i).split("/");
            String temp = temp_arr[temp_arr.length-1];
            titles.add(temp.substring(0, temp.length()-4));//remove .jpg extension
            lyrics_urls.add(Endpoints.SITE_URL+urls.get(i));
            Log.d("lyrics url ", urls.get(i));
        }
        RecyclerView allLyricsList = findViewById(R.id.idLyricsAlbum);
        allLyricsList.setLayoutManager(new LinearLayoutManager(this));
        allLyricsList.setAdapter(new ItemAlbumLyricsAdapterRV_Vertical(titles, lyrics_urls, this));
    }

    @Override
    public void onUrlsError(String error) {

    }
}
