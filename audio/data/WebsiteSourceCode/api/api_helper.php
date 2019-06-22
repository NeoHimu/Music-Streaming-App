<?php
          
     function get_shabd_names($shabd){
        if($shabd=="shabd")
        {
        	$path  = '../Shabd';
        	$shabd_names = array_diff(scandir($path), array('.', '..'));
     		return $shabd_names;
        }
     }
     
     function get_all_songs_in_a_shabd($shabd_name){
        
        	$path  = '../Shabd/'.$shabd_name;
        	$songs = array_diff(scandir($path), array('.', '..'));
     		return $songs;
     }
     
     function get_all_images($all_album_images){
        
        	if($all_album_images=="all_album_images")
        	{
		    	$path  = '../album';
		    	$images = array_diff(scandir($path), array('.', '..'));
		 		return $images;
        	}
     }
     
     
     function get_all_lyrics_album_names($all_lyrics_album){
        if($all_lyrics_album=="lyrical_album")
        {
        	$path  = '../Lyrics';
        	$all_lyrics_album_names = array_diff(scandir($path), array('.', '..'));
     		return $all_lyrics_album_names;
        }
     }
     
     function get_all_songs_in_a_lyrical_album($album_name){
        
        	$path  = '../Lyrics/'.$album_name;
        	$songs = array_diff(scandir($path), array('.', '..'));
     		return $songs;
     }
     
     function get_all_songs($temp)
     {
     	$all_songs = array();
     	$path  = '../Shabd';
        $shabd_names = array_diff(scandir($path), array('.', '..'));	
        foreach($shabd_names as $shabd_name)
	 	{
	 			$songs = array_diff(scandir($path.'/'.$shabd_name), array('.', '..'));	
			 	foreach($songs as $song)
	 			{
			 		array_push($all_songs, 'Shabd/'.$shabd_name.'/'.$song);
			 	}
	 	}
     	return $all_songs;
     }
     
     

/*	 function get_song($find){
	 
	 $songs=array(
	 	"file_name"=>"audio.mp3"
	 	"url" => "/audio/audio.mp3"
	 );
 
	 foreach($songs as $serial_no=>$name)
	 {
	 	if($serial_no==$find)
	 	{
	 		return $name;
	 		break;
	 	
	 	}
	 }
 
 	}
 	

<?php

    $audio = array("audio.mp3");
    foreach($audio as $file) { ?>
       <audio controls src="<?php echo $file;?>" type="audio/mp3">Your browser does not support the HTML5 audio element.
       </audio>
<?php } ?>
*/
