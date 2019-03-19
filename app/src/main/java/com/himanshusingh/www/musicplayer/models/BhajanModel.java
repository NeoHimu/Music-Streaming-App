package com.himanshusingh.www.musicplayer.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by himanshu on 18/3/19.
 */

public class BhajanModel implements Parcelable{
    private String Lyrics;
    private String Album;
    private String Location;
    private int Downloads;

    public BhajanModel(String lyrics, String album, String location, int downloads) {
        Lyrics = lyrics;
        Album = album;
        Location = location;
        Downloads = downloads;
    }

    protected BhajanModel(Parcel in) {
        Lyrics = in.readString();
        Album = in.readString();
        Location = in.readString();
        Downloads = in.readInt();
    }

    public static final Creator<BhajanModel> CREATOR = new Creator<BhajanModel>() {
        @Override
        public BhajanModel createFromParcel(Parcel in) {
            return new BhajanModel(in);
        }

        @Override
        public BhajanModel[] newArray(int size) {
            return new BhajanModel[size];
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

    public int getDownloads() {
        return Downloads;
    }

    public void setDownloads(int downloads) {
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
        dest.writeInt(Downloads);
    }
}
