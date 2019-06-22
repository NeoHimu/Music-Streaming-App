<?php
//http://localhost/audio/data/WebsiteSourceCode/database/lyrics_api.php/?query=everything
require_once('db_connection.php');
include("api_helper.php");
header('content-type:application/json');

$actionName = $_GET["query"];

if($actionName == "everything")
{
	$query = "SELECT * FROM lyrics";
	$result = mysqli_query($conn, $query);
	
	$rowCount = mysqli_num_rows($result);
	
	if($rowCount>0)
	{
		$postData = array();
		while($row=mysqli_fetch_assoc($result))
		{
			$postData[] = $row;
		}
		$getLyrics = $postData;	
	}
	else
	{
		$getLyrics = 'no posts found..';	
	}

	
	$query = "SELECT * FROM albumlyrics";
	$result = mysqli_query($conn, $query);
	
	$rowCount = mysqli_num_rows($result);
	
	if($rowCount>0)
	{
		$postData = array();
		while($row=mysqli_fetch_assoc($result))
		{
			$postData[] = $row;
		}
		$getAlbumLyrics = $postData;	
	}
	else
	{
		$getAlbumLyrics = 'no posts found..';	
	}
	
	$query = "SELECT * FROM albums";
	$result = mysqli_query($conn, $query);
	
	$rowCount = mysqli_num_rows($result);
	
	if($rowCount>0)
	{
		$postData = array();
		while($row=mysqli_fetch_assoc($result))
		{
			$postData[] = $row;
		}
		$getAlbum = $postData;	
	}
	else
	{
		$getAlbum = 'no posts found..';	
	}

	$query = "SELECT * FROM lyrics";
	$result = mysqli_query($conn, $query);
	
	$rowCount = mysqli_num_rows($result);
	
	if($rowCount>0)
	{
		$postData = array();
		while($row=mysqli_fetch_assoc($result))
		{
			$postData[] = $row;
		}
		$getBhajan = $postData;	
	}
	else
	{
		$getBhajan = 'no posts found..';	
	}
	
	$all_songs = get_all_songs('allsongs');
	$url = "/audio/data/WebsiteSourceCode/"; // will be fully defined when ngrok command is fired
	$temp_array = array();
		
		if($all_songs==NULL)
		{
			$allsong_response = NULL;
		}
	    else
	    {  
	        foreach($all_songs as $song)
	 		{
			 	array_push($temp_array, $url.$song);
	 		}
	 		$allsong_response = $temp_array;
	    }
		
	$url = "/audio/data/WebsiteSourceCode/Shabd/Satsang/"; // will be fully defined when ngrok command is fired
	$satsang_songs = get_all_songs_in_a_shabd("Satsang");
	$temp_array = array();
		
		if($satsang_songs==NULL)
		{
			$allsong_response = NULL;
		}
	    else
	    {  
	        foreach($satsang_songs as $song)
	 		{
			 	array_push($temp_array, $url.$song);
	 		}
	 		$allsong_response = $temp_array;
	    }

	echo json_encode(array('getSatsangSongs'=>$allsong_response, 'getLyrics'=>$getLyrics, 'getAlbumLyrics'=>$getAlbumLyrics, 'getAlbum'=>$getAlbum, 'getBhajan'=>$getBhajan, 'allsong'=>$allsong_response));

}
?>
