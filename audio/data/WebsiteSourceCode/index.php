<?php
include("./database/db_connection.php");
require_once 'Mobile_Detect.php';
$detect = new Mobile_Detect;
if( $detect->is('Opera')){
echo "<script>alert('If you have your Data Savings On in an Extreme Mode ,Please off it for better experience with our website');</script>";
}
?>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Sacho Satram
    </title>
    
    <!-- FB Start-->
    
  <meta property="og:url"           content="http://sachosatrambhajan.in/index.php" />
  <meta property="og:type"          content="website" />
  <meta property="og:title"         content="Sacho Satram Bhajans" />
  <meta property="og:description"   content="Sacho Satram Bhajan is a one stop destination to find all SSD’s Bhajans,Satsang
and Lyrics for free.Listen it online,Download it for offline usage or Share it on facebook.Share it among your friends and family to make them aware of this facility and use it to the
fullest." />
  <meta property="og:image"         content="http://sachosatrambhajan.in/images/1.jpg" />
    
    <!-- FB Close -->
    
    <!-- CSS Files -->
    <link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css"  >
    <link rel="stylesheet" type="text/css" href="./css/jquery.carousel.fullscreen.css" >
    <!-- JS Files -->
    <script src="./js/jquery 2.2.4.min.js">
    </script>
    <script src="./js/bootstrap.min.js">
    </script>
    <script src="./js/bootstrap-hover-dropdown.min.js">
    </script>
    <script src="./js/jquery.carousel.fullscreen.js">
    </script>
	<script>$(document).click(function(e) {
if (!$(e.target).is('a')) {
    $('.collapse').collapse('hide');	   
   }
});
</script>
    <link href="https://fonts.googleapis.com/css?family=Cormorant+Upright" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Cormorant+Upright|Fredericka+the+Great" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet"> 
    <style>
      .dropdown-menu>li>a {
        display: block;
        padding: 3px 10px;
        clear: both;
        font-weight: 400;
        line-height: 1.428571429;
        color: white;
        white-space: nowrap;
        background-color:#428bca;
        font-size:15px;
      }
      .dropdown-menu>li>a:hover {
        color: #414dca;
      }
      .dropdown-menu {
        background-color:#428bca;
        min-width:85px;
      }
      .nav>li>a {
        padding:7px;
      }
      .navbar,.navbar-inverse .navbar-toggle:focus, .navbar-inverse .navbar-toggle:hover {
        background-color:transparent;
        border-color:transparent;
      }
      .navbar-inverse .navbar-toggle {
      }
      .navbar-inverse .navbar-brand ,.navbar-inverse .navbar-nav>li>a {
        color:#337ab7;
      }
      
      @media only screen and (max-width : 768px) {
/*         .dropdown-menu>li>a , .navbar-inverse .navbar-nav>li>a { */
/*           background-color:#02141b; */
/*           opacity:0.7; */
/*           padding:0px 0px 0px 5px; */
/*           color:#34c4f8; */
/*           border:1px solid #34c4f8; */
/*         } */
		.dropdown-menu>li>a, .navbar-inverse .navbar-nav>li>a {
          background-color: rgba(27, 4, 4, 0.3) !important;
          /* opacity: 0.7; */
          padding: 6px 0px 0px 5px !important;
          color: #ffffff  !important;
          border: .2px solid rgba(255, 255, 255,0.28) !important;
          padding-bottom: 9px !important;
          border-bottom: white !important;
        }
        
/*         .navbar-inverse .navbar-toggle,.icon-bar{ */
/*           background-color:#07384c; */
/*           opacity:0.7; */
/*           padding:0px 2px 0px 2px; */
/*           color:#34c4f8; */
/*         } */
        
        .navbar-inverse .navbar-toggle,.icon-bar{
          background-colorrgba(0, 0, 0, 0);
          opacity:1;
          padding:0px 2px 0px 2px;
          color:#ffffff;
		  border : none;
		  margin-top: 10px;
        }
        
        .navbar-inverse .navbar-collapse, .navbar-inverse .navbar-form {
          border-color: #101010;
          border-top: 0px !important;
        }
        .navbar-inverse .navbar-brand{
          color:#34c4f8;
        }
        #stop{
          display:none;
        }
      }
      
      
      @media only screen and (max-width : 330px) {
        .navbar-inverse .navbar-brand{
          font-size:15px;
        }
      }
      
      /*
      Bootstrap Carousel Fade Transition (for Bootstrap 3.3.x)
      CSS from:       http://codepen.io/transportedman/pen/NPWRGq
      and:            http://stackoverflow.com/questions/18548731/bootstrap-3-carousel-fading-to-new-slide-instead-of-sliding-to-new-slide
      Inspired from:  http://codepen.io/Rowno/pen/Afykb
      */
      .carousel-fade .carousel-inner .item {
        opacity: 0;
      }
      .carousel-inner>.item {
        transition: transform 5.9s ease-out;
      }
      .carousel-fade .carousel-inner .active {
        opacity: 1;
      }
      .carousel-fade .carousel-inner .active.left,
      .carousel-fade .carousel-inner .active.right {
        left: 0;
        opacity: 0;
        z-index: 1;
      }
      .carousel-fade .carousel-inner .next.left,
      .carousel-fade .carousel-inner .prev.right {
        opacity: 1;
      }
      .carousel-fade .carousel-control {
        z-index: 2;
      }
      /*
      WHAT IS NEW IN 3.3: "Added transforms to improve carousel performance in modern browsers."
      Need to override the 3.3 new styles for modern browsers & apply opacity
      */
      @media all and (transform-3d), (-webkit-transform-3d) {
        .carousel-fade .carousel-inner > .item.next,
        .carousel-fade .carousel-inner > .item.active.right {
          opacity: 0;
          -webkit-transform: translate3d(0, 0, 0);
          transform: translate3d(0, 0, 0);
        }
        .carousel-fade .carousel-inner > .item.prev,
        .carousel-fade .carousel-inner > .item.active.left {
          opacity: 0;
          -webkit-transform: translate3d(0, 0, 0);
          transform: translate3d(0, 0, 0);
        }
        .carousel-fade .carousel-inner > .item.next.left,
        .carousel-fade .carousel-inner > .item.prev.right,
        .carousel-fade .carousel-inner > .item.active {
          opacity: 1;
          -webkit-transform: translate3d(0, 0, 0);
          transform: translate3d(0, 0, 0);
        }
      }
      
     
        
        
    </style>
    <!--[if lt IE 9 ]>
<script>
var is_ie_lt9 = true;
if(is_ie_lt9){
alert('Our Website is unsupported below IE9 version,Kindly update to the latest version to have better experience');
}
</script>
<![endif]-->
    <script>
    
      function toggle_visibility()
      {
        var e = document.getElementById('brand');
        if (e.style.display == 'block' || e.style.display=='')
        {
          e.style.display = 'none';
        }
        else
        {
          e.style.display = 'block';
        }
      }
        function togglePlay()
        {
          document.getElementById('music').play();
          document.getElementById('play').style.display='none';
          document.getElementById('stop').style.display='block';
        }
        function toggleStop()
        {
          var aud = document.getElementById("music");
          aud.pause();
          aud.currentTime = 0;
          document.getElementById('stop').style.display='none';
          document.getElementById('play').style.display='block';
        }
      
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
    
    
    
    <audio id="music">
      <source src="bg-music/GarhoCholoPagPunalJi.mp3" type="audio/mp3">
    </audio>
    <nav class="navbar navbar-inverse navbar-fixed-bottom">
      <div class="container-fluid">
        <div class="navbar-header">
          <?php
if( $detect->isMobile() )
{
echo '<a onclick="toggleStop();" class="navbar-brand" style="display:none;color:#fff" id="stop" title="Stop Background Music" >' ;
echo  '<i class="glyphicon glyphicon-stop" >';
echo   "</i>";
echo   "</a>";
echo   '<a onclick="togglePlay();" class="navbar-brand" id="play" title="Play Background Music" style="color:#fff">';
echo   '<i class="glyphicon glyphicon-play" >';
echo  "</i>";
echo "</a>" ;
}
?>
          <button type="button" class="navbar-toggle" onclick="toggle_visibility()" data-toggle="collapse" data-target="#myNavbar" >
            <span class="icon-bar" >
            </span>
            <span class="icon-bar">
            </span>
            <span class="icon-bar">
            </span>
          </button>
          <a class="navbar-brand " href="#" id="brand" style="font-family: 'Fredericka the Great', cursive;">Sacho Satram Dhyae Sada Sukh Paye
          </a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
          <ul class="nav navbar-nav navbar-right" style="font-family:'Open Sans', sans-serif;font-size: 14px;font-weight: bold;">
            <li class="dropdown">
              <a class="dropdown-toggle" data-hover="dropdown" data-toggle="dropdown" href="#" style:"border-color:green">BHAJANS
                <span class="caret">
                </span>
              </a>
              <ul class="dropdown-menu">
                <li>
                  <a href="albums.php">ALBUMS
                  </a>
                </li>
                <li>
                  <a href="tracklisting.php">TRACKLISTINGS
                  </a>
                </li>
              </ul>
            </li>
            <li>
              <a href="satsang.php">SATSANG
              </a>
            </li>
            <li>
              <a href="lyrics.php">LYRICS
              </a>
            </li>
            <li>
              <a href="suggestions.php">SUGGESTIONS
              </a>
            </li>
            <li class="dropdown">
              <a class="dropdown-toggle" data-hover="dropdown" data-toggle="dropdown" href="#" style:"border-color:green">SUBMIT
                <span class="caret">
                </span>
              </a>
              <ul class="dropdown-menu">
                <li>
                  <a href="userBhajansForm.php">BHAJANS
                  </a>
                </li>
                <li>
                  <a href="userLyricsForm.php">LYRICS
                  </a>
                </li>
              </ul>
            </li>
            <li>
            <a class="fb-share-button" 
    data-href="http://sachosatrambhajan.in/index.php" 
    data-layout="button_count" style="float:right">
  </a>
            </li>
            <?php
if( !($detect->isMobile() ) )
{
echo "<li>";
echo   '<a onclick="toggleStop();" style="display:none" id="stop" title="Stop Background Music">';
echo   '<i class="glyphicon glyphicon-stop" >';
echo  '</i>';
echo '</a>';
echo '<a onclick="togglePlay();" id="play" title="Play Background Music">';
echo  '<i class="glyphicon glyphicon-play" >';
echo   "</i>";
echo "</a>";
echo "</li>";
}
?>
          </ul>
        </div>
      </div>
    </nav>
    <!-- Bootstrap Carousel -->
    <div id="carousel-example-generic" class="carousel carousal-fade" data-ride="carousel">
      <!-- Wrapper for slides -->
      <div class="carousel-inner">
        <div class="item active">
          <img src="images/1.jpg" alt="" />
        </div>
        <div class="item">
          <img src="images/1.jpg" alt="" />
        </div>
        <div class="item">
          <img src="images/2.jpg" alt="" />
        </div>
        <div class="item" >
          <img src="images/3.jpg" alt="" />
        </div>
      </div>
    </div>
    <!-- Bootstrap Carousel -->
  </body>
</html>
