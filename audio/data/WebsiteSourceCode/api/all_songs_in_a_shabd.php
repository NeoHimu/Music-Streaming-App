<?php
	//http://localhost/audio/data/WebsiteSourceCode/api/all_songs_in_a_shabd.php/?shabd_name=Aaradhna
	header("Content-Type:application/json");
	include("api_helper.php");
	if(!empty($_GET['shabd_name'])){
		$temp = $_GET['shabd_name'];
		$all_songs = get_all_songs_in_a_shabd($temp);
		
		if(empty($all_songs))
		{
			deliver_response(200, "song not found", NULL);
		}	
		else
		{
			deliver_response(200, "song found", $all_songs);
		}
	
	}
	else
	{
		deliver_response(400, "Invalid Request", NULL);
	
	}
	
	function deliver_response($status, $status_message, $data)
	{
		header("HTTP/1.1 $status $status_message");
	
	    $url = "/audio/data/WebsiteSourceCode/Shabd/".$_GET['shabd_name']."/"; // will be fully defined when ngrok command is fired
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
