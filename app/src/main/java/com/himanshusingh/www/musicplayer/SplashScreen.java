package com.himanshusingh.www.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by himanshu on 27/2/19.
 */

interface OnFetchUrlsListener
{
    public void onUrlsFetched(ArrayList<String> arrayList);
    public void onUrlsError(String error);
}

public class SplashScreen extends AppCompatActivity implements OnFetchUrlsListener{

    @Override
    public void onUrlsFetched(ArrayList<String> arrayList) {
        //for(int i=0;i<arrayList.size();i++)
        //    Toast.makeText(this, "result size : "+arrayList.get(i), Toast.LENGTH_SHORT).show();

        Intent i = new Intent(SplashScreen.this, MainActivity.class);
        i.putStringArrayListExtra("data", arrayList);
//        Toast.makeText(SplashScreen.this, "songs array ka size : "+songsArray.size(), Toast.LENGTH_SHORT).show();
        startActivity(i);
        finish();
    }

    @Override
    public void onUrlsError(String error) {

    }

//    private static int SPLASH_TIME = 4000; //This is 4 seconds
    private static RequestQueue mQueue;
    public ArrayList<String> songsArray = new ArrayList<String>();
    public static ArrayList<String> songsName = new ArrayList<String>();
    private boolean songs_fetched=false;

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().getDecorView().setBackgroundColor(Color.BLACK);
        databaseHelper = new DatabaseHelper(this);
        //Since there is no fixed time to start the activity, splash screen should be done as
        //Async task
        mQueue = Volley.newRequestQueue(this);
        String url = "http://9bf6f434.ngrok.io/audio/data/WebsiteSourceCode/api/all_songs.php/?allsongs=allsongs";
        VolleyCall call = new VolleyCall(SplashScreen.this, url, SplashScreen.this);
        call.parse();
//        String uri = Uri.parse("http://ef2e9248.ngrok.io/audio/data/WebsiteSourceCode/api/all_songs.php/?allsongs=allsongs").toString();
//        new FetchSongs(this).execute(uri);
//        Toast.makeText(this, startHeavyProcessing(uri).size()+"", Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, songsArray.size()+"", Toast.LENGTH_SHORT).show();
    }

    private ArrayList<String> startHeavyProcessing(String uri) {
        final ArrayList<String> result = new ArrayList<String>();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, uri, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                result.add(jsonArray.getString(i));
                                songsArray.add(jsonArray.getString(i));
                                //Toast.makeText(SplashScreen.this, jsonArray.getString(i), Toast.LENGTH_SHORT).show();
                            }
                            songs_fetched = true;

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                            songs_fetched = false;
                            Log.d("JSON Parse", String.valueOf(e));
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(SplashScreen.this, "Load error!!", Toast.LENGTH_SHORT).show();
            }
        });
        mQueue.add(request);
        return result;
    }
    public class VolleyCall {
        private String TAG = VolleyCall.class.getSimpleName();
        private String mUrl;
        private OnFetchUrlsListener mOnFetchUrlsListener;
        private ArrayList<String> mSongs;
        private RequestQueue mQueue;

        public VolleyCall(Context context, String url, OnFetchUrlsListener onFetchUrlsListener) {
            mSongs = new ArrayList<>();
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
                                JSONArray jsonArray = response.getJSONArray("data");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    mSongs.add(jsonArray.getString(i));
                                }

                                mOnFetchUrlsListener.onUrlsFetched(mSongs);

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

//
//    private class FetchSongs extends AsyncTask<String, String, ArrayList<String>> {
//        private OnFetchUrlsListener mListener;
//        public FetchSongs(OnFetchUrlsListener listener){
//            mListener = listener;
//        }
//
//        @Override
//        protected ArrayList<String> doInBackground(String... params) {
//            //some heavy processing resulting in a Data String
////            databaseHelper.eraseOldValues();
////            final ArrayList<String> s = new ArrayList<String>();
//            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, params[0], null,
//                    new Response.Listener<JSONObject>() {
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            try {
//                                JSONArray jsonArray = response.getJSONArray("data");
//
//                                for (int i = 0; i < jsonArray.length(); i++) {
//                                    songsArray.add(jsonArray.getString(i));
////                                    String[] str = song_url.split("/");
////                                    songsName.add(str[str.length-1]+ "\n");
////                                    Log.d("Inside Json", jsonArray.getString(i));
////                                    databaseHelper.insertSongURL(jsonArray.getString(i));
////                                    onProgressUpdate(jsonArray.getString(i));
//                                }
//                                songs_fetched = true;
//
//                            }
//                            catch (JSONException e) {
//                                e.printStackTrace();
//                                songs_fetched = false;
//                                Log.d("JSON Parse", String.valueOf(e));
//                            }
//
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    error.printStackTrace();
//                    Toast.makeText(SplashScreen.this, "Load error!!", Toast.LENGTH_SHORT).show();
//                }
//            });
//            mQueue.add(request);
//            return songsArray;
//        }
//
//        @Override
//        protected void onPreExecute() {}
//
////        @Override
//////        protected void onProgressUpdate(String... values) {
//////            super.onProgressUpdate(values);
//////            Toast.makeText(SplashScreen.this, ""+values[0], Toast.LENGTH_SHORT).show();
//////            songsArray.add(values[0]);
//////            String[] str = values[0].split("/");
//////            songsName.add(str[str.length-1]+ "\n");
//////        }
//        @Override
//        protected void onPostExecute(ArrayList<String> result) {
//
//            if(mListener!=null)
//            {
//                mListener.onUrlsFetched(result);
//            }
//
////            Toast.makeText(SplashScreen.this, result.size()+"", Toast.LENGTH_SHORT).show();
////            Intent i = new Intent(SplashScreen.this, MainActivity.class);
////            i.putExtra("data", result);
////            Toast.makeText(SplashScreen.this, "songs array ka size : "+songsArray.size(), Toast.LENGTH_SHORT).show();
////            startActivity(i);
////            finish();
//        }
//    }
}
