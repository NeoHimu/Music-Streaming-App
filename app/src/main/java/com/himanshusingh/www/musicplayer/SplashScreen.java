package com.himanshusingh.www.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by himanshu on 27/2/19.
 */

interface OnFetchUrlsListener
{
    public void onUrlsFetched(ArrayList<LyricsModel> arrayList, ArrayList<AlbumModel> albumModelArrayList, ArrayList<AlbumLyricsModel> albumLyricsModelArrayList, ArrayList<BhajanModel> bhajanModelArrayList, ArrayList<String> allSongs, ArrayList<String> arrayListSatsangSongs);
    public void onUrlsError(String error);
}

public class SplashScreen extends AppCompatActivity implements OnFetchUrlsListener{

    @Override
    public void onUrlsFetched(ArrayList<LyricsModel> lyricsModelArrayList, ArrayList<AlbumModel> albumModelArrayList, ArrayList<AlbumLyricsModel> albumLyricsModelArrayList, ArrayList<BhajanModel> bhajanModelArrayList, ArrayList<String> allSongs, ArrayList<String> arrayListSatsangSongs) {
        Intent i = new Intent(SplashScreen.this, MainActivity.class);
        i.putParcelableArrayListExtra("lyrics", lyricsModelArrayList);
        i.putParcelableArrayListExtra("album", albumModelArrayList);
        i.putParcelableArrayListExtra("album_lyrics", albumLyricsModelArrayList);
        i.putParcelableArrayListExtra("bhajan", bhajanModelArrayList);
        i.putStringArrayListExtra("allsongs", allSongs);
        i.putStringArrayListExtra("allsatsangsongs", arrayListSatsangSongs);
        startActivity(i);
        finish();
    }

    @Override
    public void onUrlsError(String error) {

    }

//    private static int SPLASH_TIME = 4000; //This is 4 seconds
    public ArrayList<String> songsArray = new ArrayList<String>();
    public static ArrayList<String> songsName = new ArrayList<String>();
    private boolean songs_fetched=false;
    Button btReload;
    public ArrayList<String> satsang_songs = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(isNetworkAvailable()) {
            setContentView(R.layout.activity_splash_screen);
            getWindow().getDecorView().setBackgroundColor(Color.BLACK);
            //Since there is no fixed time to start the activity, splash screen should be done as
            //Async task
            VolleyCall call = new VolleyCall(SplashScreen.this, Endpoints.API_URL, SplashScreen.this);
            call.parse();
        }
        else {
            setContentView(R.layout.activity_no_internet);
            btReload = findViewById(R.id.idReloadButton);

            btReload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isNetworkAvailable()) {
                        setContentView(R.layout.activity_splash_screen);
                        getWindow().getDecorView().setBackgroundColor(Color.BLACK);
                        //Since there is no fixed time to start the activity, splash screen should be done as
                        //Async task
                        VolleyCall call = new VolleyCall(SplashScreen.this, Endpoints.API_URL, SplashScreen.this);
                        call.parse();
                    }
                }
            });
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public class VolleyCall {
        private String TAG = VolleyCall.class.getSimpleName();
        private String mUrl;
        private OnFetchUrlsListener mOnFetchUrlsListener;
        private ArrayList<String> mSongs;
        private ArrayList<LyricsModel> mLyrics;
        private ArrayList<AlbumModel> mAlbums;
        private ArrayList<AlbumLyricsModel> mAlbumLyrics;
        private ArrayList<BhajanModel> mBhajan;
        private ArrayList<String> mAllSongs;
        private ArrayList<String> mAllSatsangSongs;
        private RequestQueue mQueue;

        public VolleyCall(Context context, String url, OnFetchUrlsListener onFetchUrlsListener) {
            mSongs = new ArrayList<>();
            mLyrics = new ArrayList<>();
            mAlbumLyrics = new ArrayList<>();
            mAlbums = new ArrayList<>();
            mBhajan = new ArrayList<>();
            mAllSongs = new ArrayList<>();
            mAllSatsangSongs = new ArrayList<>();

            mUrl = url;
            mOnFetchUrlsListener = onFetchUrlsListener;

            mQueue = Volley.newRequestQueue(context);
        }

        public void parse() {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, mUrl, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.e(TAG, "_log : onResponse : response : " + response.toString());
                                JSONArray jsonArray = response.getJSONArray("getLyrics");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                                    String album = "Err";
                                    if(jsonObject.has("Album"))
                                        album = jsonObject.getString("Album");

                                    String lyrics="Err";
                                    if(jsonObject.has("MoreLyrics"))
                                        lyrics = jsonObject.getString("MoreLyrics");

                                    String location="Err";
                                    if(jsonObject.has("Location"))
                                        location = jsonObject.getString("Location");

                                    String download="Err";
                                    if(jsonObject.has("Downloads"))
                                        download = jsonObject.getString("Downloads");

                                    LyricsModel lyricsModel = new LyricsModel(album, lyrics, location, download);
                                    mLyrics.add(lyricsModel);
                                }


//                                Log.e(TAG, "_log : onResponse : response : " + response.toString());
                                JSONArray jsonAlbumArray = response.getJSONArray("getAlbum");

                                for (int i = 0; i < jsonAlbumArray.length(); i++) {
                                    JSONObject jsonObject = jsonAlbumArray.getJSONObject(i);
                                    String album="Err";
                                    if(jsonObject.has("Album"))
                                        album = jsonObject.getString("Album");

                                    String totalSize="Err";
                                    if(jsonObject.has("TotalSize"))
                                        totalSize = jsonObject.getString("TotalSize");

                                    int totalBhajan=0;
                                    if(jsonObject.has("TotalBhajan"))
                                       totalBhajan = jsonObject.getInt("TotalBhajan");

                                    String albumCoverImagePath="Err";
                                    if(jsonObject.has("AlbumCoverImagePath"))
                                        albumCoverImagePath = jsonObject.getString("AlbumCoverImagePath");

                                    String albumPath="Err";
                                    if(jsonObject.has("AlbumPath"))
                                        albumPath = jsonObject.getString("AlbumPath");

                                    String year="Err";
                                    if(jsonObject.has("Year"))
                                        year = jsonObject.getString("Year");

                                    int downloads=0;
                                    if(jsonObject.has("Downloads"))
                                        downloads = jsonObject.getInt("Downloads");

                                    AlbumModel albumModel = new AlbumModel(album, totalSize, totalBhajan, albumCoverImagePath, albumPath, year, downloads);
                                    mAlbums.add(albumModel);
                                }


                                JSONArray jsonAlbumLyricsArray = response.getJSONArray("getAlbumLyrics");

                                for (int i = 0; i < jsonAlbumLyricsArray.length(); i++) {
                                    JSONObject jsonObject = jsonAlbumLyricsArray.getJSONObject(i);
                                    String album = "Err";
                                    if(jsonObject.has("Album"))
                                        album = jsonObject.getString("Album");
                                    String location = "Err";
                                    if(jsonObject.has("Location"))
                                        location = jsonObject.getString("Location");
                                    String albumCoverImagePath = "Err";
                                    if(jsonObject.has("AlbumCoverImagePath"))
                                        albumCoverImagePath = jsonObject.getString("AlbumCoverImagePath");
                                    int totalLyrics=0;
                                    if(jsonObject.has("TotalLyrics"))
                                        totalLyrics = jsonObject.getInt("TotalLyrics");
                                    int downloads=0;
                                    if(jsonObject.has("Downloads"))
                                        downloads = jsonObject.getInt("Downloads");
                                    String pdfLocation="Err";
                                    if(jsonObject.has("PDFLocation"))
                                        pdfLocation = jsonObject.getString("PDFLocation");
                                    int pdfDownloads=0;
                                    if(jsonObject.has("PDFDownloads"))
                                        pdfDownloads = jsonObject.getInt("PDFDownloads");
                                    AlbumLyricsModel albumLyricsModel = new AlbumLyricsModel(album, location, albumCoverImagePath, totalLyrics, downloads, pdfLocation, pdfDownloads);
                                    mAlbumLyrics.add(albumLyricsModel);
                                }

                                JSONArray jsonBhajanArray = response.getJSONArray("getBhajan");

                                for (int i = 0; i < jsonBhajanArray.length(); i++) {
                                    JSONObject jsonObject = jsonBhajanArray.getJSONObject(i);
                                    String lyrics="Err";
                                    if(jsonObject.has("MoreLyrics"))
                                        lyrics = jsonObject.getString("MoreLyrics");
                                    String album = "Err";
                                    if(jsonObject.has("Album"))
                                        album = jsonObject.getString("Album");

                                    String location = "Err";
                                    if(jsonObject.has("Location"))
                                        location = jsonObject.getString("Location");

                                    int downloads = 0;
                                    if(jsonObject.has("Downloads"))
                                        downloads = jsonObject.getInt("Downloads");

                                    BhajanModel bhajanModel = new BhajanModel(lyrics, album, location, downloads);
                                    mBhajan.add(bhajanModel);
                                }

                                Log.e(TAG, "_log : onResponse : response : " + response.toString());
                                JSONArray jsonAllSongsArray = response.getJSONArray("allsong");

                                for (int i = 0; i < jsonAllSongsArray.length(); i++) {
                                    String song = jsonAllSongsArray.getString(i);
                                    mAllSongs.add(song);
                                    Log.d("Song url", song);
                                }

                                Log.e(TAG, "_log : onResponse : response : " + response.toString());
                                JSONArray jsonAllSatsangSongs = response.getJSONArray("getSatsangSongs");

                                for (int i = 0; i < jsonAllSatsangSongs.length(); i++) {
                                    String song = jsonAllSatsangSongs.getString(i);
                                    mAllSatsangSongs.add(song);
                                    Log.d("Satsang Song url", song);
                                }


                                mOnFetchUrlsListener.onUrlsFetched(mLyrics, mAlbums, mAlbumLyrics, mBhajan, mAllSongs, mAllSatsangSongs);

                            } catch (JSONException e) {
                                Log.e(TAG, "_log : onResponse : JSONException : " + e.getMessage());
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "_log : onErrorResponse : error : " + error.getMessage());
                    mOnFetchUrlsListener.onUrlsError(error.getMessage());
                }
            });

            mQueue.add(request);
            Log.e(TAG, "_log : songsArray_size : " + mSongs.size());
        }
    }

}
