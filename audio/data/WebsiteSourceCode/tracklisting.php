
<?php
include ("./database/db_connection.php");
require_once 'Mobile_Detect.php';
$detect = new Mobile_Detect;
if ($detect->is('UCBrowser')) {
	header('Location:tracklistingUC.php');
	exit();
}
?>

<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Track Listing
    </title>
     	<meta property="og:url"           content="http://sachosatrambhajan.in/tracklisting.php" />
  		<meta property="og:type"          content="website" />
  		<meta property="og:title"         content="Bhajans" />
  		<meta property="og:description"   content="Listen or Download the Bhajans from the huge collection of more than 1000
																								 Bhajans present on the website.
																								 Share it among your friends and family to make them aware of this facility
																								 and use it to the
																								 fullest." />
  		<meta property="og:image"         content="http://sachosatrambhajan.in/images/tracklisting.png" />

<?php
include ("libraries.php");
 ?>
    <script>
      $('.dropdown').hover(function(){
        $('.dropdown-toggle', this).trigger('click');
      });

			<!-- Table Javascript Start -->
      $(document).ready(function() {
        var table = $('#example').DataTable( {
          "order": [[ 3, "desc" ]],
					"processing": true,
					"serverSide":true,
					"ajax":{
							url:"fetch.php"
					}
        });
      });
      <!-- Table Javascript End -->

    </script>





  </head>

  <body>

<!-- FB Stuff Starts-->
	<div id="fb-root"></div>
  <script>(function(d, s, id) {
	  var js, fjs = d.getElementsByTagName(s)[0];
	  if (d.getElementById(id)) return;
	  js = d.createElement(s); js.id = id;
	  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.9";
	  fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));</script>
<!-- FB Stuff Ends-->

<?php
include ("navigation.php");
?>
    <div class=container-fluid >
      <div class="panel" id=albumPanel>

				<!-- Breadcrumbs Starts -->
			  <div class="panel-heading" style=" font-size: 12px; color: white">
          <a href="index.php">
            <i class="glyphicon glyphicon-home">
            </i>
          </a>
          <i class="glyphicon glyphicon-menu-right">
          </i>
          Bhajans
          <i class="glyphicon glyphicon-menu-right">
          </i>
          Track Listing
           <i class="glyphicon glyphicon-menu-right">
          </i>
					<!-- FB share button code -->
  				<div class="fb-share-button"
    				data-href="http://sachosatrambhajan.in/tracklisting.php"
    				data-layout="button_count" style="float:right">
  				</div>
        </div>
				<!-- Breadcrumbs Starts -->
				<script>
				function updateSrc(a)
				{
				var song = a.getAttribute('data-bhajan');
				var player=document.getElementById('audio');
				$('#mySrc').attr('src',song);
				player.load(); //just start buffering (preload)
				player.play();

				$('.stop').hide();
				$('.play').show();
				$(a).closest('td').find('#play').get(0).style.display='none';
				$(a).closest('td').find('#stop').get(0).style.display='block';
				}

				function stopSong(a)
				{

					var aud=document.getElementById('audio');
					aud.pause();
					aud.currentTime = 0;
					 $(a).closest('td').find('#play').get(0).style.display='block';
					 $(a).closest('td').find('#stop').get(0).style.display='none';
				}




				</script>";


        <div class="panel-body">
          <div class=row>
            <div class=col-md-12 style="border:8px solid #303030;padding:0;">
              <audio id="audio" controls  >
                <source src="" type="audio/mpeg" id="mySrc" />
              </audio>
            </div>
          </div>
          <div class=row>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive" style="border:8px solid #303030">
              <table id="example" class="display cell-border" cellspacing="0" width="100%" style="color:#9d9d9d;">
                <thead>
                  <tr>
                    <th>Bhajan</th>
                    <th>Artist</th>
                    <th>Album</th>
                    <th>Year</th>
                    <th>Size</th>
                    <th>Total<i class='glyphicon glyphicon-download-alt'></th>
									  <th class="no-sort" style="width:20px">Play</th>
			           <th class="no-sort"><i class='glyphicon glyphicon-download-alt'></th>
                  </tr>
                </thead>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>

<?php
include ("footer.php");
?>
</body>
</html>


