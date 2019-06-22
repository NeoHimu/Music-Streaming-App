<?php
    //http://localhost/audio/data/WebsiteSourceCode/api/lyrical_album_names.php/?lyrical_album=lyrical_album
	header("Content-Type:application/json");
	include("api_helper.php");
	if(!empty($_GET['lyrical_album'])){
		$temp = $_GET['lyrical_album'];
		$lyrical_album = get_all_lyrics_album_names($temp);
		
		if(empty($lyrical_album))
		{
			deliver_response(200, "song not found", NULL);
		}	
		else
		{
			deliver_response(200, "song found", $lyrical_album);
		}
	
	}
	else
	{
		deliver_response(400, "Invalid Request", NULL);
	
	}
	
	function deliver_response($status, $status_message, $data)
	{
		header("HTTP/1.1 $status $status_message");
	
	    $url = "/audio/data/WebsiteSourceCode/Lyrics/"; // will be fully defined when ngrok command is fired
	    $temp_array = array();
	
		$response['status'] = $status;
		$response['status_message'] = $status_message;
		
		if($data==NULL)
		{
			$response['data'] = NULL;
		}
	    else
	    {  
	        foreach($data as $shabd_name)
	 		{
			 	array_push($temp_array, $url.$shabd_name);
	 		}
	 		$response['data'] = $temp_array;
	    }
		
		$json_response = json_encode($response);
		echo $json_response;
	
	}
