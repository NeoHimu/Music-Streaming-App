<?php
include("./database/db_connection.php");
require_once 'Mobile_Detect.php';
$detect = new Mobile_Detect;
if( $detect->is('UCBrowser')){
echo "<script>alert('For better experience with the music player on this page,Please use some standard browser');</script>";
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
  <meta property="og:description"   content="Description ​ : Listen,Download or Share the Satsang and spread the message of love and
kindness everywhere through his teachings.
Share it among your friends and family to make them aware of this facility and use it to the
fullest." />
  <meta property="og:image"         content="http://sachosatrambhajan.in/images/satsang.png" />
  
    <?php include("libraries.php");?>
    <script>
      (function(i,s,o,g,r,a,m){
        i['GoogleAnalyticsObject']=r;
        i[r]=i[r]||function(){
          (i[r].q=i[r].q||[]).push(arguments)}
          ,i[r].l=1*new Date();
        a=s.createElement(o),
          m=s.getElementsByTagName(o)[0];
        a.async=1;
        a.src=g;
        m.parentNode.insertBefore(a,m)
      }
      )(window,document,'script','https://www.google-analytics.com/analytics.js','ga');
      ga('create', 'UA-101190645-1', 'auto');
      ga('send', 'pageview');
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
            <!--  For UC Browser -->
            <div class="col-xs-12 col-md-9 table-responsive" style="border:8px solid #303030" id="htmlPlayer">
              <table class="display cell-border"  id="example" cellspacing="0" width="100%" style="color:#9d9d9d;">
                <thead>
                  <tr>
                    <th style="width:70%;">Bhajan
                    </th>
                    <th class="no-sort" style="width:10%;">Duration
                    </th>
                    <th class="no-sort" style="width:20%;">Play
                    </th>
                    <th class="no-sort" style="width:0%;">
                      <i class='glyphicon glyphicon-download-alt'>
                      </i>
                    </th>
                  </tr>
                </thead>
                <tbody>
                  <?php
$fetchSql="SELECT * FROM bhajans where Album='Satsang' ";
$query_result = mysqli_query($conn,$fetchSql);
while($record = mysqli_fetch_array($query_result))
{
$Bhajan=$record['Bhajan'];
$Duration=$record['Duration'];
$Location=$record['Location'];
echo "<tr>";
echo "<td style='width:70%;'>$Bhajan</td>";
echo "<td style='width:10%;'>$Duration</td>";
echo "<td style='width:20%;'>
<audio id='audio'><source src='$Location' type='audio/mpeg' /></audio>
<a  id='stop' class='stop' style='display:none;' title='Stop Background Music'>
<i class='glyphicon glyphicon-stop' >
</i>
</a>
<a  id='play' class='play' title='Play Background Music'>
<i class='glyphicon glyphicon-play' >
</i>
</a>
</td>";
echo "<td style='width:0%;'><a href='downloads.php?location=$Location&&bhajan=$Bhajan'><i class='glyphicon glyphicon-download-alt'></i></a></td>";
echo "</tr>";
} ?>
                </tbody>
              </table>
            </div>
            <script>
              $(document).ready(function() {
                var table = $('#example').DataTable( {
                	"lengthMenu": [5,10,15,20]
                });
              });
              
              $("a#play").click(function() {
            	  var audios = document.getElementsByTagName('audio');
          	    for(var i = 0, len = audios.length; i < len;i++){	        
          	            audios[i].pause();
          	            audios[i].currentTime=0;
          	    }
          	    $('.stop').hide();	
          		$('.play').show();	
              $(this).closest('td').find('audio').get(0).play();
              $(this).closest('td').find('#play').get(0).style.display='none';
              $(this).closest('td').find('#stop').get(0).style.display='block';  
              });
              
              $("a#stop").click(function() {
                var aud=$(this).closest('td').find('audio').get(0);
                aud.pause();
                aud.currentTime = 0;
                $(this).closest('td').find('#play').get(0).style.display='block';
                $(this).closest('td').find('#stop').get(0).style.display='none';
              }
                               );
            </script>
            <!--  For UC Browser -->
          </div>
        </div>
      </div>
    </div>
    </div>
  <?php include("footer.php");?>
  </body>
</html>
