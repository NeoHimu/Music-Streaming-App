package com.himanshusingh.www.musicplayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by himanshu on 27/2/19.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "playlist";

    public static final String PLAYLIST_TABLE_NAME = "playlist_names";
    public static final String COL01 = "COL_ID";
    public static final String COL02 = "PLAYLIST_NAME";

    //public static String TABLE_NAME = "playlist_names";
    public static final String COL1 = "COL_ID";
    public static final String COL2 = "SONG_URL";
    public static final String COL3 = "SONG_NAME";
    public static final String COL4 = "SONG_ICON_URL";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create table for each playlist
        //db.execSQL("create table " + TABLE_NAME + " ( COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, SONG_URL TEXT, SONG_NAME TEXT, SONG_ICON_URL TEXT );");

        // create table for playlist
        db.execSQL("create table " + PLAYLIST_TABLE_NAME + " ( COL_ID INTEGER , PLAYLIST_NAME TEXT PRIMARY KEY );");

        ContentValues contentValues = new ContentValues();
        // by default, Favourite is a playlist
        contentValues.put(COL02, "Favourites");
        long result  = db.insert(PLAYLIST_TABLE_NAME, null, contentValues);
    }

    public void createTable(String table_name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("create table if not exists " + table_name + " ( COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, SONG_URL TEXT, SONG_NAME TEXT, SONG_ICON_URL TEXT );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+PLAYLIST_TABLE_NAME);
    }

    public boolean insertSongInPlaylist(String song_url, String song_icon_url, String song_name, String TABLE_NAME)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, song_url);
        contentValues.put(COL3, song_name);
        contentValues.put(COL4, song_icon_url);

        long result  = db.insert(TABLE_NAME, null, contentValues);
        if(result==-1)
            return false;
        return true;
    }

    public boolean insertPlaylistName(String playlist_name)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        // create a table for each playlist
        db.execSQL("create table " + playlist_name + " ( COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, SONG_URL TEXT, SONG_NAME TEXT, SONG_ICON_URL TEXT );");


        ContentValues contentValues = new ContentValues();
        // by default, Favourite is a playlist
        contentValues.put(COL02, playlist_name);
        long result  = db.insert(PLAYLIST_TABLE_NAME, null, contentValues);

        if(result==-1)
            return false;
        return true;
    }

    Cursor getAllSongs(String TABLE_NAME)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME, null);
        return res;
    }

    Cursor getAllPlaylistNames()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+PLAYLIST_TABLE_NAME, null);
        return res;
    }

    public void eraseAll(String TABLE_NAME)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME);
    }
}
