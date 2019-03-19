package com.himanshusingh.www.musicplayer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by himanshu on 19/3/19.
 */

public class ShowLyrics extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_lyrics);
        imageView = findViewById(R.id.idLyricsImage);
        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("image_url");
        Picasso.get().load(url).into(imageView);
    }
}
