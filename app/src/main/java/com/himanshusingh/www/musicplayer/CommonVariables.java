package com.himanshusingh.www.musicplayer;

import java.util.ArrayList;

/**
 * Created by himanshu on 26/3/19.
 */

public class CommonVariables {
    static String RECENT_TABLE_NAME = "recents";
    static String QUEUE_TABLE_NAME = "queue";

    static ArrayList<String> recent_song_url = new ArrayList<String>();
    static ArrayList<String> recent_song_icon_url = new ArrayList<String>();
    static ArrayList<String> recent_song_name = new ArrayList<String>();
    static int recent_pos=-1;

    static ArrayList<String> queue_song_url = new ArrayList<String>();
    static ArrayList<String> queue_song_icon_url = new ArrayList<String>();
    static ArrayList<String> queue_song_name = new ArrayList<String>();
    static int queue_pos=-1;

}
