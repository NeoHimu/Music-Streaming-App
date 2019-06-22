<?php
  error_reporting(0);
  $Album=$_GET['Album'];
  $AlbumCoverImagePath=$_GET['AlbumCoverImagePath'];
  $PageURL=$_GET['pageURL'];
  $PageURL1=urlencode($PageURL);
include("./database/db_connection.php");
include_once 'Mobile_Detect.php';
$detect = new Mobile_Detect;

$sql1 = "select * from lyrics where Album='$Album' ";

$adjacents = 5;
$targetpage = "single-lyrics.php"; //your file name
$limit = 5; //how many items to show per page

if ( $detect->isMobile() ) {
	$limit = 5;
}

if(!isset($_GET['page'])){

	$page = 0;

}
else
{

	$page = $_GET['page'];


}

if($page){
	$start = ($page - 1) * $limit; //first item to display on this page

}else{
	$start = 0;

}

/* Setup page vars for display. */
if ($page == 0) $page = 1; //if no page var is given, default to 1.

$prev = $page - 1; //previous page is current page - 1

$next = $page + 1; //next page is current page + 1

	$sql2 = "select * from lyrics where Album='$Album' ";
	$sql2 .= "order by Lyrics ASC limit $start ,$limit ";




$sql = mysqli_query($conn,$sql1);
$total = mysqli_num_rows($sql);

$lastpage = ceil($total/$limit); //lastpage.

$lpm1 = $lastpage - 1; //last page minus 1

$sql_query = mysqli_query($conn,$sql2);
$rows= mysqli_num_rows($sql_query);


/* CREATE THE PAGINATION */
$counter=0;

$pagination = "";
if($lastpage > 1)
{
$pagination .= "<div class='pagination1'> <ul class='pagination pagination-sm'>";
if ($page > $counter+1) {
$pagination.= "<li><a href=\"$targetpage?page=$prev&&Album=$Album&&pageURL=$PageURL1\">&larr;  &nbsp; Prev</a></li>";
}

if ($lastpage < 7 + ($adjacents * 2))
{
for ($counter = 1; $counter <= $lastpage; $counter++)
{
if ($counter == $page)
$pagination.= "<li class='active' ><a href='#' class='active'>$counter</a></li>";
else
	$pagination.= "<li><a href=\"$targetpage?page=$counter&&Album=$Album&&pageURL=$PageURL1\">$counter</a></li>";
}
}
elseif($lastpage > 5 + ($adjacents * 2)) //enough pages to hide some
{
//close to beginning; only hide later pages
if($page < 1 + ($adjacents * 2))
{
for ($counter = 1; $counter < 4 + ($adjacents * 2); $counter++)
{
if ($counter == $page)
$pagination.= "<li class='active' ><a href='#' class='active'>$counter</a></li>";
else
	$pagination.= "<li><a href=\"$targetpage?page=$counter&&Album=$Album&&pageURL=$PageURL1\">$counter</a></li>";
}
//$pagination.= "<li>...</li>";
$pagination.= "<li><a href=\"$targetpage?page=$lpm1&&Album=$Album&&pageURL=$PageURL1\">$lpm1</a></li>";
$pagination.= "<li><a href=\"$targetpage?page=$lastpage&&Album=$Album&&pageURL=$PageURL1\">$lastpage</a></li>";
}
//in middle; hide some front and some back
elseif($lastpage - ($adjacents * 2) > $page && $page > ($adjacents * 2))
{
	$pagination.= "<li><a href=\"$targetpage?page=1&&Album=$Album&&pageURL=$PageURL1\">1</a></li>";
	$pagination.= "<li><a href=\"$targetpage?page=2&&Album=$Album&&pageURL=$PageURL1\">2</a></li>";
//$pagination.= "<li>...</li>";
for ($counter = $page - $adjacents; $counter <= $page + $adjacents; $counter++)
{
if ($counter == $page)
$pagination.= "<li class='active' ><a href='#' class='active'>$counter</a></li>";
else
	$pagination.= "<li><a href=\"$targetpage?page=$counter&&Album=$Album&&pageURL=$PageURL1\">$counter</a></li>";
}
//$pagination.= "<li>...</li>";
$pagination.= "<li><a href=\"$targetpage?page=$lpm1&&Album=$Album&&pageURL=$PageURL1\">$lpm1</a></li>";
$pagination.= "<li><a href=\"$targetpage?page=$lastpage&&Album=$Album&&pageURL=$PageURL1\">$lastpage</a></li>";
}
//close to end; only hide early pages
else
{
	$pagination.= "<li><a href=\"$targetpage?page=1&&Album=$Album&&pageURL=$PageURL1\">1</a></li>";
	$pagination.= "<li><a href=\"$targetpage?page=2&&Album=$Album&&pageURL=$PageURL1\">2</a></li>";
//$pagination.= "<li>...</li>";
for ($counter = $lastpage - (2 + ($adjacents * 2)); $counter <= $lastpage;
$counter++)
{
if ($counter == $page)
$pagination.= "<li class='active'><a href='#' class='active'>$counter</a></li>";
else
	$pagination.= "<li><a href=\"$targetpage?page=$counter&&Album=$Album&&pageURL=$PageURL1\">$counter</a></li>";
}
}
}

//next button
if ($page < $counter - 1)
	$pagination.= "<li><a href=\"$targetpage?page=$next&&Album=$Album&&pageURL=$PageURL1\">Next &nbsp; &rarr;</a></li>";
else
$pagination.= "";
$pagination.= "</ul></div>\n";
}


?>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Lyrics
    </title>

    <meta property="og:url"           content="http://sachosatrambhajan.in/single-lyrics.php?Album=<?php echo $Album?>&AlbumCoverImagePath=<?php echo  $AlbumCoverImagePath ?>" />
  <meta property="og:type"          content="website" />
  <meta property="og:title"         content="Lyrics" />
  <meta property="og:description"   content="Download the Bhajan’s Lyrics,Aarti Sahib,Dhuni Sahib,Ardas Sahib and many
more in Image or PDF format.
Share it among your friends and family to make them aware of this facility and use it to the
fullest." />
  <meta property="og:image"         content="<?php echo $AlbumCoverImagePath?>" />

    <?php include("libraries.php");?>
     <style>

            .btnSort
            {
            border: 2px solid #E52303;
    		color: white;
    		background-color:transparent;

    		}

        </style>
  </head>
  <body>

  <div id="fb-root"></div>
  <script>(function(d, s, id) {
	  var js, fjs = d.getElementsByTagName(s)[0];
	  if (d.getElementById(id)) return;
	  js = d.createElement(s); js.id = id;
	  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.9";
	  fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));</script>

    <?php include("navigationLyrics.php");?>
    <div class=container-fluid >
      <div class="panel" id="albumPanel" >
        <div class="panel-heading" style=" font-size: 12px; color: white">
          <a href="index.php">
          <i class="glyphicon glyphicon-home">
          </i>
          </a>
          <i class="glyphicon glyphicon-menu-right">
          </i>
          <a href="lyrics.php">Lyrics</a>
          <i class="glyphicon glyphicon-menu-right">
          </i>
       <a href=" <?php echo $PageURL ?>">  <?php echo "$Album";?> </a>
        <i class="glyphicon glyphicon-menu-right">
          </i>
           <!-- Your share button code -->
  <div class="fb-share-button"
    data-href="http://sachosatrambhajan.in/single-lyrics.php?Album=<?php echo $Album?>&AlbumCoverImagePath=<?php echo $AlbumCoverImagePath ?>"
    data-layout="button_count" style="float:right">
  </div>
        </div>






        <div class="panel-body" id="albumsBlocks" style="color:#9d9d9d">
          <?php
          if(mysqli_num_rows($sql_query) > 0){
		   $id=0;
		   while($record = mysqli_fetch_array($sql_query))
			{
				$Lyrics=$record['Lyrics'];
				$LyricsImagePath=$record['Location'];
				$TotalDownloads=$record['Downloads'];
				$id++;
				echo "<style>#img$id{ background-image:url('$LyricsImagePath')}</style>";
				echo "<div class='col-xs-2 lyricsBhajan'>";
				//echo "<a href='single-lyrics.php?album=$Album'>";
				echo "<div class='lyricsHoverEffectBhajan' id='img$id'>";
				echo "</div>";
			        echo "</a>";
				echo "<ul class='list-unstyled'>";
				echo "<li>$Lyrics <a href='downloadLyrics.php?name=$Lyrics&&location=$LyricsImagePath'><i class='glyphicon glyphicon-download-alt pull-right' style='padding-top:2px' ></i></a></li>";
				echo "<li>Total Downloads - $TotalDownloads</li>";
				echo "</ul>";
				echo "</div>";

				}}

				else
				{
					echo "<b>Lyrics not found <b>";
				}
		?>

        </div>
         <div class='row'>	<?php echo $pagination;?></div>
      </div>
    </div>
 <?php
       include("footer.php");?>
  </body>
</html>
