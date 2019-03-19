package com.himanshusingh.www.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

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
    AlbumLyricsModel albumLyricsModel;
    ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics_album);
        imageView = findViewById(R.id.idLyricsAlbumCoverImageView);
        titles = new ArrayList<String>();
        lyrics_urls = new ArrayList<String>();
        albumLyricsModel = getIntent().getParcelableExtra("album_lyrics_one_item");
        Picasso.get().load(Endpoints.BASE_URL+albumLyricsModel.getAlbumCoverImagePath()).into(imageView);
        VolleyCall call = new VolleyCall(LyricsAlbum.this, Endpoints.LYRICS_IN_ALBUM_URL+albumLyricsModel.getLocation().substring(9).replaceAll(" ", "%20"), LyricsAlbum.this);
        call.parse();
    }

    @Override
    public void onLyricsTitleClick(int position) {
        Intent i = new Intent(LyricsAlbum.this, ShowLyrics.class);
        i.putExtra("image_url", lyrics_urls.get(position));
        startActivity(i);
    }

    @Override
    public void onLyricsImageClick(int position) {
        Intent i = new Intent(LyricsAlbum.this, ShowLyrics.class);
        i.putExtra("image_url", lyrics_urls.get(position));
        startActivity(i);
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
