<?php
    //http://localhost/audio/data/WebsiteSourceCode/api/all_songs.php/?allsongs=allsongs
	header("Content-Type:application/json");
	include("api_helper.php");
	if(!empty($_GET['allsongs'])){
		$temp = $_GET['allsongs'];
		$all_songs = get_all_songs($temp);
		
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
	
	    $url = "https://fcf3da50.ngrok.io/audio/data/WebsiteSourceCode/"; // will be fully defined when ngrok command is fired
	    $temp_array = array();
	
		$response['status'] = $status;
		$response['status_message'] = $status_message;
		
		if($data==NULL)
		{
			$response['data'] = NULL;
		}
	    else
	    {  
	        foreach($data as $song)
	 		{
			 	array_push($temp_array, $url.$song);
	 		}
	 		$response['data'] = $temp_array;
	    }
		
		$json_response = json_encode($response);
		echo $json_response;
	
	}
