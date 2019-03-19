package com.himanshusingh.www.musicplayer.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by himanshu on 18/3/19.
 */

public class AlbumLyricsModel implements Parcelable{


    private String Album;
    private String Location;
    private String AlbumCoverImagePath;
    private int TotalLyrics;
    private int Downloads;
    private String PDFLocation;
    private int PDFDownloads;

    public AlbumLyricsModel(String album, String location, String albumCoverImagePath, int totalLyrics, int downloads, String PDFLocation, int PDFDownloads) {
        Album = album;
        Location = location;
        AlbumCoverImagePath = albumCoverImagePath;
        TotalLyrics = totalLyrics;
        Downloads = downloads;
        this.PDFLocation = PDFLocation;
        this.PDFDownloads = PDFDownloads;
    }

    protected AlbumLyricsModel(Parcel in) {
        Album = in.readString();
        Location = in.readString();
        AlbumCoverImagePath = in.readString();
        TotalLyrics = in.readInt();
        Downloads = in.readInt();
        PDFLocation = in.readString();
        PDFDownloads = in.readInt();
    }

    public static final Creator<AlbumLyricsModel> CREATOR = new Creator<AlbumLyricsModel>() {
        @Override
        public AlbumLyricsModel createFromParcel(Parcel in) {
            return new AlbumLyricsModel(in);
        }

        @Override
        public AlbumLyricsModel[] newArray(int size) {
            return new AlbumLyricsModel[size];
        }
    };

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

    public String getAlbumCoverImagePath() {
        return AlbumCoverImagePath;
    }

    public void setAlbumCoverImagePath(String albumCoverImagePath) {
        AlbumCoverImagePath = albumCoverImagePath;
    }

    public int getTotalLyrics() {
        return TotalLyrics;
    }

    public void setTotalLyrics(int totalLyrics) {
        TotalLyrics = totalLyrics;
    }

    public int getDownloads() {
        return Downloads;
    }

    public void setDownloads(int downloads) {
        Downloads = downloads;
    }

    public String getPDFLocation() {
        return PDFLocation;
    }

    public void setPDFLocation(String PDFLocation) {
        this.PDFLocation = PDFLocation;
    }

    public int getPDFDownloads() {
        return PDFDownloads;
    }

    public void setPDFDownloads(int PDFDownloads) {
        this.PDFDownloads = PDFDownloads;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Album);
        dest.writeString(Location);
        dest.writeString(AlbumCoverImagePath);
        dest.writeInt(TotalLyrics);
        dest.writeInt(Downloads);
        dest.writeString(PDFLocation);
        dest.writeInt(PDFDownloads);
    }
}
