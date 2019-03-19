package com.himanshusingh.www.musicplayer.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by himanshu on 14/3/19.
 */

public class AlbumModel implements Parcelable {

    private String Album;
    private String TotalSize;
    private int TotalBhajans;
    private String AlbumCoverImagePath;
    private String AlbumPath;
    private String Year;
    private int Downloads;

    public AlbumModel(String album, String totalSize, int totalBhajans, String albumCoverImagePath,
                      String albumPath, String year, int downloads)
    {
        Album = album;
        TotalSize = totalSize;
        TotalBhajans = totalBhajans;
        AlbumCoverImagePath = albumCoverImagePath;
        AlbumPath = albumPath;
        Year = year;
        Downloads = downloads;
    }

    protected AlbumModel(Parcel in) {
        Album = in.readString();
        TotalSize = in.readString();
        TotalBhajans = in.readInt();
        AlbumCoverImagePath = in.readString();
        AlbumPath = in.readString();
        Year = in.readString();
        Downloads = in.readInt();
    }

    public static final Creator<AlbumModel> CREATOR = new Creator<AlbumModel>() {
        @Override
        public AlbumModel createFromParcel(Parcel in) {
            return new AlbumModel(in);
        }

        @Override
        public AlbumModel[] newArray(int size) {
            return new AlbumModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String album) {
        Album = album;
    }

    public String getTotalSize() {
        return TotalSize;
    }

    public void setTotalSize(String totalSize) {
        TotalSize = totalSize;
    }

    public int getTotalBhajans() {
        return TotalBhajans;
    }

    public void setTotalBhajans(int totalBhajans) {
        TotalBhajans = totalBhajans;
    }

    public String getAlbumCoverImagePath() {
        return AlbumCoverImagePath;
    }

    public void setAlbumCoverImagePath(String albumCoverImagePath) {
        AlbumCoverImagePath = albumCoverImagePath;
    }

    public String getAlbumPath() {
        return AlbumPath;
    }

    public void setAlbumPath(String albumPath) {
        AlbumPath = albumPath;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public int getDownloads() {
        return Downloads;
    }

    public void setDownloads(int downloads) {
        Downloads = downloads;
    }

    public static Creator<AlbumModel> getCREATOR() {
        return CREATOR;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Album);
        dest.writeString(TotalSize);
        dest.writeInt(TotalBhajans);
        dest.writeString(AlbumCoverImagePath);
        dest.writeString(AlbumPath);
        dest.writeString(Year);
        dest.writeInt(Downloads);
    }
}
