<?php 

  
  $Location=$_GET['location'];
  $LyricsName=$_GET['name'];
 
  include("./database/db_connection.php");

  $fetchSql="SELECT Downloads FROM lyrics where Lyrics='$LyricsName' ";
  $query_result = mysqli_query($conn,$fetchSql);
  $record = mysqli_fetch_array($query_result);
  $count=$record['Downloads'];
  
  $count++;
 
  $sql="update lyrics set Downloads=$count where Lyrics='$LyricsName' ";
  $runSql = mysqli_query($conn,$sql );
  
   

  
  if(! $runSql ) {
  	die('Could not enter data: ' . mysqli_error($conn));
  }
  else { 
  header("Content-type: image/jpg");
  header ('Content-disposition: attachment; filename='.basename($Location));
  header("Content-Transfer-Encoding: binary");
  header("Content-Length: ".filesize($Location));
 readfile($Location);
 exit();
  }
  ?>
 
  