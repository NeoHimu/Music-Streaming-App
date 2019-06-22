<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Lyrics
    </title>
  <!-- Meta Data -->
 	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <!--  Favicon -->
    <link rel='shortcut icon' href='./images/favicon.png' sizes="96x96" type='image/x-icon'/ > 
    
    <!-- Style Sheets -->
     <link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css"  >
     <link rel="stylesheet" type="text/css" href="./css/bhajanWebsite.css" >
     <link rel="stylesheet" type="text/css" href="./css/jquery.carousel.fullscreen.css" >
     <link rel="stylesheet" type="text/css" href="./css/bootstrapValidator.min.css" >
     <link rel="stylesheet" type="text/css" href="./css/jquery.dataTables.css" >
    
    <link rel="stylesheet" type="text/css" href="./css/jplayer.css" >
   
    <!-- Scripts -->
    <script src="./js/jquery.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <script src="./js/bootstrap-hover-dropdown.min.js"></script>
    <script src="./js/bootstrapValidator.min.js"></script>
    <script src="./js/jquery.carousel.fullscreen.js"></script>
    <script src="./js/jquery.dataTables.js"></script>
   
    <script src="./js/jplayer.js" ></script>
    <script src="./js/jplayerBhajans.js" ></script>
   
     
   <link href="https://fonts.googleapis.com/css?family=Cormorant+Upright" rel="stylesheet">
   <link href="https://fonts.googleapis.com/css?family=Chathura" rel="stylesheet">
     <link href="./nanogp/css/nanogallery2.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="https://unpkg.com/nanogallery2/dist/jquery.nanogallery2.min.js"></script>
    
    <!--[if lt IE 9]>
 <script src="/js/html5shiv.js" type="text/javascript"></script>
 <script src="/js/respond.min.js" type="text/javascript"></script>
<![endif]-->

<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-85302018-1', 'auto');
  ga('send', 'pageview');

</script>
    <script type="text/javascript">
    $(document).ready(function () {
          $("#nanogallery2").nanogallery2({
              kind: 'google2',
              userID: '116522599969308009124',
              google2URL: "http://jaihindcollection.online/nanogp/nanogp.php",
              gallerySorting:'titleasc',
	      "blackList": "Auto|Backup",
galleryMaxRows: 4,
"thumbnailHeight": "300",
  "thumbnailWidth": "250",
 "thumbnailGutterWidth": 10,
 "thumbnailGutterHeight": 10,
              galleryDisplayMode:'pagination',
              galleryPaginationMode:'numbers' 
             
          });
      });



    </script>
    
    <style>
    .nGY2 {
    
    padding: 0;
    /* box-sizing: content-box; */
    -webkit-box-sizing: border-box;
    </style>
    
    
  </head>
  <body>
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
          
          Lyrics
          
          <i class="glyphicon glyphicon-menu-right">
          </i> 
         


          
        </div>
		<div style="float:right;">
 <input id="ngy2search" type="text" placeholder="Enter the album name" style="color:black;" >  
<input type="submit" id="btnsearch"  style="border: 2px solid #E52303; margin:20px 10px 10px 5px; color: white;background-color: transparent; " value="Search"/>   
      </div>
        <div class="panel-body" id="albumsBlocks">
          <div class=row>
            <div class=col-md-12>
              <h6 style="font-family: 'Chathura', sans-serif; font-size: 50px; color: white">
             
              </h6>
            </div>
          </div>
          <div class=row>
            
            <div class=col-md-12 id="nanogallery2" style="border:8px solid #303030">
             </div>
           <script>
	$('#btnsearch').on('click', function() {
	 $("#nanogallery2").nanogallery2('search', $('#ngy2search').val() );
	
	});
</script>
          </div>
        </div>
      </div>
    </div>
    </div>
      <?php include("footer.php");?>
  </body>
</html>
