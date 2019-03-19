package com.himanshusingh.www.musicplayer.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.himanshusingh.www.musicplayer.Lyrics;

import java.io.Serializable;

/**
 * Created by himanshu on 13/3/19.
 */

public class LyricsModel implements Parcelable{
    private String Lyrics;
    private String Album;
    private String Location;
    private String Downloads;

    public LyricsModel(String lyrics, String album, String location, String downloads)
    {
        Lyrics = lyrics;
        Album = album;
        Location = location;
        Downloads = downloads;
    }

    public LyricsModel(Parcel in) {
        Lyrics = in.readString();
        Album = in.readString();
        Location = in.readString();
        Downloads = in.readString();
    }

    public static final Creator<LyricsModel> CREATOR = new Creator<LyricsModel>() {
        @Override
        public LyricsModel createFromParcel(Parcel in) {
            return new LyricsModel(in);
        }

        @Override
        public LyricsModel[] newArray(int size) {
            return new LyricsModel[size];
        }
    };

    public String getLyrics() {
        return Lyrics;
    }

    public void setLyrics(String lyrics) {
        Lyrics = lyrics;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String album) {
        Album = album;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getDownloads() {
        return Downloads;
    }

    public void setDownloads(String downloads) {
        Downloads = downloads;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Lyrics);
        dest.writeString(Album);
        dest.writeString(Location);
        dest.writeString(Downloads);
    }
}

