<script>$(document).click(function(e) {
if (!$(e.target).is('a')) {
    $('.collapse').collapse('hide');	   
   }
});
</script>
	<style>
.navbar-header>a>img{
height:75px;
 width:150px;
}

 .navbar-inverse .navbar-nav>li>a {
    color: #FFF;
}

.navbar-inverse .navbar-nav .dropdown .dropdown-menu>li>a
{
color: #FFF;
font-size: 12px;font-weight: bold;
}

@media screen and (max-width:767px) {

.navbar-header>a>img{
margin-top:1.5%;
height:40px;
 width:85px;
}

.dropdown-menu>li>a,.navbar-header, .dropdown-menu, .navbar-inverse .navbar-nav>li>a ,
.navbar-inverse .navbar-collapse{
          background-color: rgba(27, 4, 4, 0.3) !important;
 }


}
</style>
	
	
	<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse"
            data-target="#myNavbar">
          <span class="icon-bar">
          </span> 
          <span class="icon-bar">
          </span> 
          <span
            class="icon-bar">
          </span>
          </button>
          <a href="index.php"><img src="images/logo.png"></a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
          <ul class="nav navbar-nav navbar-right"
            style="padding-top:2%;font-family:'Open Sans', sans-serif;font-size: 14px;font-weight: bold;">
            <li >
              <a  href="index.php">HOME
              </a>
            </li>
            <li class="active dropdown">
              <a class="dropdown-toggle" data-hover="dropdown" data-toggle="dropdown" href="#" style:"border-color:green;color:white">BHAJANS 
              <span class="caret">
              </span>
              </a>
              <ul class="dropdown-menu" style="background-color:#222">
                <li>
                  <a href="albums.php">ALBUMS
                  </a>
                </li>
                <li>
                  <a href="tracklisting.php">TRACKLISTING
                  </a>
                </li>
              </ul>
            </li>
            <li >
              <a  href="satsang.php">SATSANG
              </a>
            </li>
            <li >
              <a  href="lyrics.php">LYRICS
              </a>
            </li>
            <li >
              <a href="suggestions.php">SUGGESTIONS
              </a>
            </li>
            <li class="dropdown">
	              <a class="dropdown-toggle" data-hover="dropdown" data-toggle="dropdown" href="#" style:"border-color:green">SUBMIT 
	                <span class="caret">
	                </span>
	              </a>
	              <ul class="dropdown-menu" style="background-color:#222 ">
	                <li>
	                  <a  href="userBhajansForm.php">BHAJANS
	                  </a>
	                </li>
	                <li>
	                  <a  href="userLyricsForm.php">LYRICS
	                  </a>
	                </li>
	              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>

<?php
include("./database/db_connection.php");
require_once 'Mobile_Detect.php';
$detect = new Mobile_Detect;



if( $detect->is('Opera')){

echo "<script>alert('If you have your Data Savings On in an Extreme Mode ,Please off it for better experience with our website');</script>";
 
}

?>

