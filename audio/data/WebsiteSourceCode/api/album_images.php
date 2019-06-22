<?php
    //http://localhost/audio/data/WebsiteSourceCode/api/album_images.php/?all_album_images=all_album_images
	header("Content-Type:application/json");
	include("api_helper.php");
	if(!empty($_GET['all_album_images'])){
		$temp = $_GET['all_album_images'];
		$all_album_images = get_all_images($temp);
		
		if(empty($all_album_images))
		{
			deliver_response(200, "song not found", NULL);
		}	
		else
		{
			deliver_response(200, "song found", $all_album_images);
		}
	
	}
	else
	{
		deliver_response(400, "Invalid Request", NULL);
	
	}
	
	function deliver_response($status, $status_message, $data)
	{
		header("HTTP/1.1 $status $status_message");
	
	    $url = "/audio/data/WebsiteSourceCode/album/"; // will be fully defined when ngrok command is fired
	    $temp_array = array();
	
		$response['status'] = $status;
		$response['status_message'] = $status_message;
		
		if($data==NULL)
		{
			$response['data'] = NULL;
		}
	    else
	    {  
	        foreach($data as $album_images)
	 		{
			 	array_push($temp_array, $url.$album_images);
	 		}
	 		$response['data'] = $temp_array;
	    }
		
		$json_response = json_encode($response);
		echo $json_response;
	}
