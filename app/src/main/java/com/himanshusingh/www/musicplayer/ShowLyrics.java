package com.himanshusingh.www.musicplayer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by himanshu on 19/3/19.
 */

public class ShowLyrics extends AppCompatActivity {
    ImageView imageView;
    ArrayList<String> lyrics_urls = new ArrayList<String>();
    int current_position = 0;
    private GestureDetector gdt;
    private static final int MIN_SWIPPING_DISTANCE = 50;
    private static final int THRESHOLD_VELOCITY = 50;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_lyrics);
        imageView = findViewById(R.id.idLyricsImage);
        Bundle bundle = getIntent().getExtras();
        current_position = bundle.getInt("pos");
        lyrics_urls = bundle.getStringArrayList("lyrics_urls");
        //CommonVariables.recent_lyrics_url.add(lyrics_urls.get(current_position));
        Picasso.get().load(lyrics_urls.get(current_position)).into(imageView);

        gdt = new GestureDetector(new GestureListener());
        imageView.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(final View view, final MotionEvent event) {
                gdt.onTouchEvent(event);
                return true;
            } });

    }
    private class GestureListener extends GestureDetector.SimpleOnGestureListener
    {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
        {
            if (e1.getX() - e2.getX() > MIN_SWIPPING_DISTANCE && Math.abs(velocityX) > THRESHOLD_VELOCITY)
            {
                //Toast.makeText(getApplicationContext(), "You have swipped left side", Toast.LENGTH_SHORT).show();
               /* Code that you want to do on swiping left side*/
               current_position = (current_position+1)%lyrics_urls.size();
                //CommonVariables.recent_lyrics_url.add(lyrics_urls.get(current_position));
                Picasso.get().load(lyrics_urls.get(current_position)).into(imageView);
                return false;
            }
            else if (e2.getX() - e1.getX() > MIN_SWIPPING_DISTANCE && Math.abs(velocityX) > THRESHOLD_VELOCITY)
            {
                //Toast.makeText(getApplicationContext(), "You have swipped right side", Toast.LENGTH_SHORT).show();
              /* Code that you want to do on swiping right side*/
                //CommonVariables.recent_lyrics_url.add(lyrics_urls.get(current_position));
               current_position = (lyrics_urls.size()+current_position-1)%lyrics_urls.size();
                Picasso.get().load(lyrics_urls.get(current_position)).into(imageView);
                return false;
            }
            return false;
        }
    }
}
