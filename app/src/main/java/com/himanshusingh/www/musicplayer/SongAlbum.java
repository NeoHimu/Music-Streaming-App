package com.himanshusingh.www.musicplayer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

import java.util.ArrayList;

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
    ProgressDialog progress;

    ImageView albumImage;
    ImageView mPlayerControl;
    TextView tvCurrentSong;
    int current_song_position=0;

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
            public void onCompletion(MediaPlayer mp) {
                mPlayerControl.setImageResource(R.drawable.icon_play);
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
//        toolbar.setBackgroundColor(333333);
        tvCurrentSong.setText(song_names.get(position));
//        albumImage.setBackgroundResource(R.drawable.icon_cover);
        if(!MusicManager.current_song_icon_url.matches(""))
            Picasso.get().load(MusicManager.current_song_icon_url).into(albumImage);
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
