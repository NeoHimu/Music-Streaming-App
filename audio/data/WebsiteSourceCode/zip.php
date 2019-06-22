<?php

$AlbumPath=$_GET['albumPath'];
$Album=$_GET['album'];


include("./database/db_connection.php");

$fetchSql="SELECT Downloads FROM albums where Album='$Album' ";
$query_result = mysqli_query($conn,$fetchSql);
$record = mysqli_fetch_array($query_result);
$count=$record['Downloads'];

$count++;

$sql="update albums set Downloads=$count where Album='$Album' ";
$runSql = mysqli_query($conn,$sql );


if(! $runSql ) {
	die('Could not enter data: ' . mysqli_error($conn));
}

	
	
class Utils
{
	public static function listDirectory($dir)
	{
		$result = array();
		$root = scandir($dir);
		foreach($root as $value) {
			if($value === '.' || $value === '..') {
				continue;
			}
			if(is_file("$dir$value")) {
				$result[] = "$dir$value";
				continue;
			}
			if(is_dir("$dir$value")) {
				$result[] = "$dir$value/";
			}
			foreach(self::listDirectory("$dir$value/") as $value)
			{
				$result[] = $value;
			}
		}
		return $result;
	}
}



$source_dir = $AlbumPath.'/';
$zip_file = $AlbumPath.'.zip';
$file_list = Utils::listDirectory($source_dir);
 
$zip = new ZipArchive();
if ($zip->open($zip_file, ZIPARCHIVE::CREATE) === true) {
  foreach ($file_list as $file) {
    if ($file !== $zip_file) {
      $zip->addFile($file, substr($file, strlen($source_dir)));
    }
  }
  $zip->close();
}


$file = $AlbumPath.'.zip';
$file_name = basename($file);
header("Content-Type: application/zip");
header("Content-Disposition: attachment; filename=" . $file_name);
header("Content-Length: " . filesize($file));
readfile($file);

if(file_exists($AlbumPath.'.zip')){
	unlink($AlbumPath.'.zip');

}

exit;




?>
