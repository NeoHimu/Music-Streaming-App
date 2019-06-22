<?php 

 
  
  $Location=$_GET['location'];
  $BhajanName=$_GET['bhajan'];
 
  include("./database/db_connection.php");

  $fetchSql="SELECT downloads FROM bhajans where Bhajan='$BhajanName' ";
  $query_result = mysqli_query($conn,$fetchSql);
  $record = mysqli_fetch_array($query_result);
  $count=$record['downloads'];
  
  $count++;
 
  $sql="update bhajans set downloads=$count where Bhajan='$BhajanName' ";
  $runSql = mysqli_query($conn,$sql );

    
   

  	
  
  if(! $runSql ) {
  	die('Could not enter data: ' . mysqli_error($conn));
  }
  else { 
  header("Content-type: audio/mpeg");
  header ('Content-disposition: attachment; filename='.basename($Location));
  header("Content-Transfer-Encoding: binary");
  header("Content-Length: ".filesize($Location));
 readfile($Location);
 exit();
  }
  ?>
 
  