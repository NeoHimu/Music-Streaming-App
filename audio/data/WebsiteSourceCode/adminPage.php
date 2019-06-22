<?php
  session_start();
  
  if ($_SESSION['loggedin'] == false || $_SESSION['loggedin'] == '')
  {
  	echo "<script>window.open('adminLogin.php','_self')</script>";
  }
  
  $name = $_SESSION['name'];
  ?>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>User Inputs</title>
    <?php include("libraries.php");?> 
   
   <script>

   $(document).ready(function() {
       var table = $('#no_of_downloads1').DataTable();
      
    } );

   $(document).ready(function() {
       var table = $('#album_details1').DataTable();
      
    } );

   $(document).ready(function() {
       var table = $('#bhajan_details11').DataTable();
      
    } );

   $(document).ready(function() {
       var table = $('#bhajan_details12').DataTable();
      
    } );

   $(document).ready(function() {
       var table = $('#lyrics_details1').DataTable();
      
    } );
   
   $(document).ready(function() {
       var table = $('#user_suggestions1').DataTable();
      
    } );

   $(document).ready(function() {
       var table = $('#user_bhajans1').DataTable();
      
    } );

   $(document).ready(function() {
       var table = $('#user_lyrics1').DataTable();
      
    } );
   </script>
  </head>
  <body>
    <?php include("navigationAdmin.php");?>
    <div class=container-fluid>
      <div class="panel" id="albumPanel">
        <div class="panel-heading"style=" font-size: 12px; color: white">
          <a href="index.php"><i class="glyphicon glyphicon-user"></i></a> <i class="glyphicon glyphicon-menu-right"></i> 
          Login <i class="glyphicon glyphicon-menu-right"></i> User Inputs
        </div>
        <div class="panel-body" id="albumsBlocks">
          <div class=row>
            <div class=col-md-12 style="border:8px solid #303030;color:#9d9d9d" id="musicPlayer">
             
              <br>
              <ul class="nav nav-tabs">
              	<li class="active"><a data-toggle="tab" href="#no_of_downloads">No of Downloads</a></li>
              	<li><a data-toggle="tab" href="#album_details">Album Details</a></li>
              	<li><a data-toggle="tab" href="#bhajan_details1">Bhajan Details_1</a></li>
                <li><a data-toggle="tab" href="#bhajan_details2">Bhajan Details_2</a></li>
                <li><a data-toggle="tab" href="#lyrics_details">Lyrics Details</a></li>
                <li ><a data-toggle="tab" href="#user_suggestions">Suggestions</a></li>
                <li><a data-toggle="tab" href="#user_bhajans">Bhajans</a></li>
                <li><a data-toggle="tab" href="#user_lyrics">Lyrics</a></li>               
              </ul>
              <!--  Tab Content -->
              <div class="tab-content">
              
              
              <!-- Number of Downloads Start --> 
                <div id="no_of_downloads" class="tab-pane fade in active">
                  <div class=row>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive">
                    <br>
                      <table id="no_of_downloads1" class="display cell-border example" cellspacing="0" width="100%" style="color:#9d9d9d;">
                        <thead>
                          <tr>
                            <th>Albums</th>
                            <th>Bhajans</th>
                            <th>Lyrics Album</th>
                            <th>Lyrics PDF</th>
                            <th>Lyrics</th>
                         </tr>
                        </thead>
                        <tbody>
                           <?php
                            include("database/db_connection.php");
                            $totalAlbums = mysqli_query($conn,"SELECT SUM(Downloads) as totalAlbums from albums");
                            $row1 = mysqli_fetch_assoc( $totalAlbums);
                            $sum1=$row1[totalAlbums];
                            
                            $totalBhajans = mysqli_query($conn,"SELECT SUM(Downloads) as totalBhajans from bhajans");
                            $row2 = mysqli_fetch_assoc( $totalBhajans);
                            $sum2=$row2['totalBhajans'];
                            
                            $totalLyricsAlbum = mysqli_query($conn,"SELECT SUM(Downloads) as totalLyricsAlbum from albumlyrics");
                            $row3 = mysqli_fetch_assoc( $totalLyricsAlbum);
                            $sum3=$row3['totalLyricsAlbum'];
                            
                            $totalLyricsPDF = mysqli_query($conn,"SELECT SUM(PDFDownloads) as totalLyricsPDF from albumlyrics");
                            $row4 = mysqli_fetch_assoc($totalLyricsPDF);
                            $sum4=$row4['totalLyricsPDF'];
                            
                            $totalLyrics = mysqli_query($conn,"SELECT SUM(Downloads) as totalLyrics from lyrics");
                            $row5 = mysqli_fetch_assoc($totalLyrics);
                            $sum5=$row5['totalLyrics'];
                            
                            
                            echo "
                                               <tr>
                                               <td>$sum1</td>
                                               <td >$sum2</td>
                                               <td >$sum3</td>
                                                <td >$sum4</td>
                                                 <td >$sum5</td>
                                               </tr>"; ?>
                                              
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
                <!-- Number of Downloads End -->
           
              
              <!-- Album Details Start --> 
                <div id="album_details" class="tab-pane fade">
                  <div class=row>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive">
                    <br>
                      <table id="album_details1"  class="display cell-border example" cellspacing="0" width="100%" style="color:#9d9d9d;">
                        <thead>
                          <tr>
                            <th>Album</th>
                            <th>Downloads</th>
                         </tr>
                        </thead>
                        <tbody>
                          <?php
                            include("database/db_connection.php");
                            
                                               $fetchBhajans = mysqli_query($conn,"SELECT Album,Downloads From albums WHERE Downloads>0 ORDER BY Downloads DESC");
                                               while( $row = mysqli_fetch_array( $fetchBhajans ) ){
                                               	
                                               $Album=$row['Album'];
                                               $Downloads=$row['Downloads'];
                                              
                                              
                                               echo
                                               "<tr>
                                               <td>$Album</td>
                                               <td >$Downloads</td>                                           
                                               </tr>\n";
                                               }
                                               ?>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
                <!--  Album Details End -->
              
              
              <!-- Bhajan Details 1 Start --> 
                <div id="bhajan_details1" class="tab-pane fade">
                  <div class=row>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive">
                    <br>
                      <table id="bhajan_details11"  class="display cell-border example" cellspacing="0" width="100%" style="color:#9d9d9d;">
                        <thead>
                          <tr>
                            <th>Bhajan</th>
                            <th>Album</th>
                            <th>Downloads</th>
                         </tr>
                        </thead>
                        <tbody>
                          <?php
                            include("database/db_connection.php");
                            
                                               $fetchBhajans = mysqli_query($conn,"SELECT Bhajan,Album,Downloads From bhajans WHERE Downloads>0 ORDER BY Downloads DESC");
                                               while( $row = mysqli_fetch_array( $fetchBhajans ) ){
                                               	
                                               $Album=$row['Album'];
                                               $Downloads=$row['Downloads'];
                                              
                                              
                                               $Bhajan=$row['Bhajan'];
                                               $Album=$row['Album'];
                                               $Downloads=$row['Downloads'];
                                               
                                               
                                               echo
                                               "<tr>
                                               <td>$Bhajan</td>
                                               <td>$Album</td>
                                               <td >$Downloads</td>
                                               </tr>\n";
                                               }
                                               ?>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
                <!--  Bhajan Details 1 End -->
                
                <!-- Bhajan Details 2 Start --> 
                <div id="bhajan_details2" class="tab-pane fade">
                  <div class=row>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive">
                    <br>
                      <table id="bhajan_details12"  class="display cell-border example" cellspacing="0" width="100%" style="color:#9d9d9d;">
                        <thead>
                          <tr>
                            <th>Album</th>
                            <th>Downloads</th>
                         </tr>
                        </thead>
                        <tbody>
                          <?php
                            include("database/db_connection.php");
                            
                                               $fetchBhajans = mysqli_query($conn,"SELECT Album, SUM(Downloads) as total from bhajans GROUP BY Album HAVING SUM(Downloads) > 0 order by SUM(Downloads) DESC");
                                               while( $row = mysqli_fetch_array( $fetchBhajans ) ){
                                               	
                                               $Album=$row['Album'];
                                               $Downloads=$row['total'];
                                              
                                              
                                               echo
                                               "<tr>
                                               <td>$Album</td>
                                               <td >$Downloads</td>                                           
                                               </tr>\n";
                                               }
                                               ?>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
                <!--  Bhajan Details 2 End -->
              
              <!-- Lyrics Details Start --> 
                <div id="lyrics_details" class="tab-pane fade">
                  <div class=row>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive">
                    <br>
                      <table id="lyrics_details1"  class="display cell-border example" cellspacing="0" width="100%" style="color:#9d9d9d;">
                        <thead>
                          <tr>
                            <th>Lyrics Album</th>
                            <th>Downloads</th>
                         </tr>
                        </thead>
                        <tbody>
                          <?php
                            include("database/db_connection.php");
                            
                                               $fetchBhajans = mysqli_query($conn,"SELECT Album,Downloads From albumlyrics WHERE Downloads>0 ORDER BY Downloads DESC");
                                               while( $row = mysqli_fetch_array( $fetchBhajans ) ){
                                               	
                                               $Album=$row['Album'];
                                               $Downloads=$row['Downloads'];
                                              
                                              
                                               echo
                                               "<tr>
                                               <td>$Album</td>
                                               <td >$Downloads</td>                                           
                                               </tr>\n";
                                               }
                                               ?>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
                <!--  Lyrics Details End -->
                
                <!--  User Suggestions --> 
                <div id="user_suggestions" class="tab-pane fade">
                  <div class=row>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive">
                    <br>
                      <table id="user_suggestions1"  class="display cell-border example" cellspacing="0" width="100%" style="color:#9d9d9d;">
                        <thead>
                          <tr>
                            <th>Name</th>
                            <th>Mobile</th>
                            <th>Suggestions</th>
                            <th>DateTime</th>
                          </tr>
                        </thead>
                        <tbody>
                          <?php
                            include("database/db_connection.php");
                            
                                               $fetchBhajans = mysqli_query($conn,"SELECT * FROM user_suggestions ORDER BY SID DESC");
                                               while( $row = mysqli_fetch_array( $fetchBhajans ) ){
                                               	
                                               $Name=$row['Name'];
                                               $Mobile=$row['Mobile'];
                                               $Suggestions=$row['Suggestions'];
                                               $DateTime=$row['DateTime'];
                                              
                                               echo
                                               "<tr>
                                               <td>$Name</td>
                                               <td >$Mobile</td>
                                               <td >$Suggestions</td>
                                               <td >$DateTime</td>
                                               </tr>\n";
                                               }
                                               ?>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
                <!--  User Suggestions End -->
                
                
                <!--  User Bhajans -->
                
                <div  id="user_bhajans" class="tab-pane fade">
                  <div class=row>
                  <br>
                    <div  class=col-md-12 style="border:8px solid #303030;padding:0;">
                    
                      <audio id="audio" controls  >
                        <source src="" type="audio/mpeg" id="mySrc" />
                      </audio>
                    </div>
                  </div>
                  <br>
                  <div class=row>
                    <div  class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive">
                      <table id="user_bhajans1" class="display cell-border example" cellspacing="0" width="100%" style="color:#9d9d9d;">
                        <thead>
                          <tr>
                            <th>Name</th>
                            <th>Mobile</th>
                            <th>Bhajan Title</th>
                            <th class="no-sort">Play</th>
                            <th class="no-sort">Download</th>
                            <th>DateTime</th>
                          </tr>
                        </thead>
                        <tbody>
                          <?php
                            include("database/db_connection.php");
                            
                                               $fetchBhajans = mysqli_query($conn,"SELECT * FROM user_bhajans ORDER BY BID DESC");
                                               while( $row = mysqli_fetch_array( $fetchBhajans ) ){
                                               	
                                               $Name=$row['Name'];
                                               $Mobile=$row['Mobile'];
                                               $BhajanTitle=$row['BhajanTitle'];
                                               $BhajanPath=$row['BhajanPath'];
                                             $DateTime=$row['DateTime'];
                                              
                                              echo "<script>
                            function updateSrc(a)
                            {
                             var song = a.getAttribute('data-bhajan');
                             var player=document.getElementById('audio');
                                               		$('#mySrc').attr('src',song);
                                               		player.load(); //just start buffering (preload)
                                               		player.play();
                                               		
                                        
                            
                            
                            }
                                              	</script>";
                                              
                                               echo
                                               "<tr>
                                               <td>$Name</td>
                                               <td >$Mobile</td>
                                               <td >$BhajanTitle</td>
                                              
                                               <td><a href='javascript:;' onclick='updateSrc(this);'  data-bhajan='$BhajanPath' ><i class='glyphicon glyphicon-play' ></i><i class='glyphicon glyphicon-play' style='display:none'></i></a></td>
                                               <td><a href='$BhajanPath' download><i class='glyphicon glyphicon-download-alt'></i></a></td>
                                               <td >$DateTime</td>
                                               </tr>\n";
                                               }
                                               ?>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
                <!--  User Bhajans End -->
                <!--  User Lyrics -->
                <br>
                <div id="user_lyrics"  class="tab-pane fade">
                  <div class=row>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive">
                      <table id="user_lyrics1" class="display cell-border example" cellspacing="0" width="100%" style="color:#9d9d9d;">
                        <thead>
                          <tr>
                            <th>Name</th>
                            <th>Mobile</th>
                            <th>Lyrics Title</th>
                            <th>Lyrics</th>
                            <th class="no-sort">Download</th>
                            <th>DateTime</th>
                          </tr>
                        </thead>
                        <tbody>
                          <?php
                            include("database/db_connection.php");
                            
                                               $fetchBhajans = mysqli_query($conn,"SELECT * FROM user_lyrics ORDER BY LID DESC");
                                               while( $row = mysqli_fetch_array( $fetchBhajans ) ){
                                               	
                                               $Name=$row['Name'];
                                               $Mobile=$row['Mobile'];
                                               $LyricsTitle=$row['LyricsTitle'];
                                               $LyricsPath=$row['LyricsPath'];
                                              $Lyrics=$row['Lyrics'];
                                              $DateTime=$row['DateTime'];
                                              echo "<script>
                                              
                              
                                              	</script>";
                                               echo
                                               "<tr>
                                               <td>$Name</td>
                                               <td >$Mobile</td>
                                               <td >$LyricsTitle</td>
                                               <td >$Lyrics</td>
                                               <td><a href='$LyricsPath' download><i class='glyphicon glyphicon-download-alt'></i></a></td>
                                                <td >$DateTime</td>
                                               </tr>\n";
                                               }
                                               ?>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
                <!--  User Lyrics End -->
              </div>
              <!--  Tab Content End -->
            </div>
          </div>
        </div>
      </div>
    </div>
     <?php include("footer.php");?>
  </body>
</html>