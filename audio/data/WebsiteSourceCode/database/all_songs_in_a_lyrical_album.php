<?php
	//http://localhost/audio/data/WebsiteSourceCode/database/all_songs_in_a_lyrical_album.php/?lyrical_album_name=Akhand Dhuni Sahib (Hindi)
	header("Content-Type:application/json");
	include("api_helper.php");
	if(!empty($_GET['lyrical_album_name'])){
		$temp = $_GET['lyrical_album_name'];
		$all_songs_in_lyrical_albums = get_all_songs_in_a_lyrical_album($temp);
		
		if(empty($all_songs_in_lyrical_albums))
		{
			deliver_response(200, "song not found", NULL);
		}	
		else
		{
			deliver_response(200, "song found", $all_songs_in_lyrical_albums);
		}
	
	}
	else
	{
		deliver_response(400, "Invalid Request", NULL);
	
	}
	
	function deliver_response($status, $status_message, $data)
	{
		header("HTTP/1.1 $status $status_message");
	
	    $url = "/audio/data/WebsiteSourceCode/Lyrics/".$_GET['lyrical_album_name']."/"; // will be fully defined when ngrok command is fired
	    $temp_array = array();
	
		$response['status'] = $status;
		$response['status_message'] = $status_message;
		
		if($data==NULL)
		{
			$response['data'] = NULL;
		}
	    else
	    {  
	        foreach($data as $song_name)
	 		{
			 	array_push($temp_array, $url.$song_name);
	 		}
	 		$response['data'] = $temp_array;
	    }
		
		$json_response = json_encode($response);
		echo $json_response;
	
	}
