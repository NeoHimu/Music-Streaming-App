<?php
$Name=$_GET['name']
?>

<!DOCTYPE html>
<html lang="en">
  <head>
  <title></title>
    <?php include("libraries.php");?>
  </head>
  <body>
    <?php include("navigation.php");?>
   <div class=container>
	    <div class="panel" id="albumPanel">
			  <div class="panel-heading"style=" font-size: 12px; color: white">
		<a href="index.php"><i class="glyphicon glyphicon-home"></i></a> <i class="glyphicon glyphicon-menu-right"></i> 
		<a href="suggestions.php">Suggestions </a><i class="glyphicon glyphicon-menu-right"></i>
			  </div>
			  <div class="panel-body" id="albumsBlocks">
				 <div class=row>
				  	 <div class=col-md-12 style="border:8px solid #303030;color:#9d9d9d;text-align:center;font-size:30px;" id="musicPlayer">
				  <i class="glyphicon glyphicon glyphicon-check"  i></i>
				  <br>
				  	Thanks <?php echo "$Name";?>,For Your Inputs! We will look into it :)	
				  	 </div>
				 </div>
				  </div>
			  </div>
		</div>
   
    
   
      <?php include("footer.php");?>
  </body>
</html>



