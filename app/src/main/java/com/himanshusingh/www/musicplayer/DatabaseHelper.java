package com.himanshusingh.www.musicplayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by himanshu on 27/2/19.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "urls";
    public static final String SONG_TABLE_NAME = "songs";
    public static final String COL1 = "ID";
    public static final String COL2 = "URL";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + SONG_TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, URL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+SONG_TABLE_NAME);
    }

    public boolean insertSongURL(String url)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, url);
        long result  = db.insert(SONG_TABLE_NAME, null, contentValues);
        if(result==-1)
            return false;
        return true;
    }

    Cursor getAllSongs()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+SONG_TABLE_NAME, null);
        return res;
    }

    public void eraseOldValues()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+SONG_TABLE_NAME);
    }
}
