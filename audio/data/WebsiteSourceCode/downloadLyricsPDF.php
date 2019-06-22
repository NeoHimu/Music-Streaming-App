<?php 
$PDFLocation=$_GET['PDFLocation'];
$Album=$_GET['album'];
  
 
  include("./database/db_connection.php");

  $fetchSql="SELECT PDFDownloads FROM albumlyrics where Album='$Album' ";
  $query_result = mysqli_query($conn,$fetchSql);
  $record = mysqli_fetch_array($query_result);
  $count=$record['PDFDownloads'];
  
  $count++;
 
  $sql="update albumlyrics set PDFDownloads=$count where Album='$Album' ";
  $runSql = mysqli_query($conn,$sql );
 
  if(! $runSql ) {
  	die('Could not enter data: ' . mysqli_error($conn));
  }
  else {  	
  	header("Content-type:application/pdf");
  	header("Content-Disposition:attachment;filename=".basename($PDFLocation));
  	readfile($PDFLocation);
 exit();
  }
  ?>
 
  