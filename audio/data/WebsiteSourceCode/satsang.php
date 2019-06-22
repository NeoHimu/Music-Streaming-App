<?php
include("./database/db_connection.php");
require_once 'Mobile_Detect.php';
$detect = new Mobile_Detect;
if( $detect->is('UCBrowser')){
header('Location:satsangUC.php');
exit();
}
?>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Satsang
    </title>
    
     <meta property="og:url"           content="http://sachosatrambhajan.in/satsang.php" />
  <meta property="og:type"          content="website" />
  <meta property="og:title"         content="Satsang" />
  <meta property="og:description"   content="Listen,Download or Share the Satsang and spread the message of love and
kindness everywhere through his teachings.
Share it among your friends and family to make them aware of this facility and use it to the
fullest." />
  <meta property="og:image"         content="http://sachosatrambhajan.in/images/satsang.png" />
    
    <?php include("libraries.php");?>
    <script type="text/javascript">
      <?php
      include("./database/db_connection.php");
      $fetchSql="SELECT * FROM bhajans where Album='Satsang'";
      $query_result = mysqli_query($conn,$fetchSql);
      ?>
        var myPlaylist = [];
      <?php 	while($record = mysqli_fetch_array($query_result))
      {
      	$Bhajan=$record['Bhajan'];
      	$Duration=$record['Duration'];
      	$Size=$record['Size'];
      	$Album=$record['Album'];
      	$Location=$record['Location'];
      	$Downloads=$record['Downloads'];
        ?>
          obj = {
        		  'mp3':'<?php echo "$Location"?>',
                  'title':'<?php echo "$Bhajan"?>',            
                  'buy':'downloads.php?location=<?php echo "$Location"?>&bhajan=<?php echo "$Bhajan"?>',        
                  'price':'<i class="glyphicon glyphicon-download-alt"></i>', 
                  'duration':'<?php echo "$Downloads"?>',
                  'buy1':'#',
                  'price1':'<i class="glyphicon glyphicon-play title1" id="play"></i><i id="stop" class="glyphicon glyphicon-stop title2" style="display:none"></i>'
        };
        myPlaylist.push(obj);
        <?php
      }
      ?>
    </script>
    <script type="text/javascript">
      jQuery(document).ready(function($){
        $('#musicPlayer').ttwMusicPlayer(myPlaylist, {
          tracksToShow:3,
          autoPlay:false,
          jPlayer:{
            swfPath:'./jquery-jplayer' //You need to override the default swf path any time the directory structure changes
          }
        }
                                        );
      }
                            );
      $(document).ready(function() {
        $("body").tooltip({
          selector: '[data-toggle=tooltip]' }
                         );
      }
                       );
    </script>
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
    
  
    <?php include("navigation.php");?>
    <div class=container-fluid >
      <div class="panel" id="albumPanel">
        <div class="panel-heading" style=" font-size: 12px; color: white">
          <a href="index.php">
            <i class="glyphicon glyphicon-home">
            </i>
          </a>
          <i class="glyphicon glyphicon-menu-right">
          </i>
          Satsang
           <i class="glyphicon glyphicon-menu-right">
          </i>
           <!-- Your share button code -->
  <div class="fb-share-button" 
    data-href="http://sachosatrambhajan.in/satsang.php" 
    data-layout="button_count" style="float:right">
  </div>
        </div>
        <div class="panel-body" id="albumsBlocks">
          <div class=row>
            <div class=col-md-12>
              <h6 style="font-family: 'Chathura', sans-serif; font-size: 50px; color: white">Satsang
              </h6>
            </div>
          </div>
          <div class=row>
            <div class="col-xs-12 col-md-3">
              <div class=col-md-12 style="padding:0;border:8px solid red">
                <img src="album/Satsang.jpg" width=100% height=auto>
              </div>
            </div>
            <div class="col-xs-12 col-md-9" style="border:8px solid #303030" id="musicPlayer">
            </div>
            <style>
              div.player.jp-interface{
                padding:0;
                margin:0;
                border-bottom: 1px solid #303030;
                padding-bottom: 10px;
              }
              div.player-controls{
                padding:0;
                margin:0;
              }
              #musicPlayer{
                padding:0;
                margin:0;
              }
            </style>
          </div>
        </div>
      </div>
    </div>
    </div>
  <?php include("footer.php");?>
  </body>
</html>
