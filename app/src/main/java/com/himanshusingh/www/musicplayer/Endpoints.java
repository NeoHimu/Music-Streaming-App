package com.himanshusingh.www.musicplayer;

/**
 * Created by himanshu on 19/3/19.
 */

public class Endpoints {
    public static final String SITE_URL = "http://28b7b34b.ngrok.io";
    public static final String BASE_URL = SITE_URL+"/audio/data/WebsiteSourceCode/";
    public static final String API_URL = SITE_URL+"/audio/data/WebsiteSourceCode/database/lyrics_api.php/?query=everything";
    public static final String LYRICS_IN_ALBUM_URL = SITE_URL+"/audio/data/WebsiteSourceCode/database/all_songs_in_a_lyrical_album.php/?lyrical_album_name=";
    public static final String SONG_IN_ALBUM_URL = SITE_URL+"/audio/data/WebsiteSourceCode/database/all_songs_in_a_shabd.php/?shabd_name=";
    public static final String ALL_SONG_URL = SITE_URL+"/audio/data/WebsiteSourceCode/database/all_songs.php/?allsongs=allsongs";
}
