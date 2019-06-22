<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Upload Lyrics
    </title>
    
    <meta property="og:url"           content="http://sachosatrambhajan.in/userLyricsForm.php" />
  <meta property="og:type"          content="website" />
  <meta property="og:title"         content="Share SSD Bhajan's Lyrics With Us" />
  <meta property="og:description"   content="If you have Lyrics of any of the bhajans on our website and if they are not there in
our Lyrics Section,Kindly Share ! You can either upload them in image format or text or both.
Share it among your friends and family to make them aware of this facility and use it to the
fullest." />
  <meta property="og:image"         content="http://sachosatrambhajan.in/images/userLyricsForm.png" />
    
   <?php include("libraries.php");?>
  
   <style>
                  .file {
                  visibility: hidden;
                  position: absolute;
                  }
                  .input-group-btn{
                  background-color:#ccc;
                  }
                </style>
                
     <script type="text/javascript">
                  $(document).on('click', '.browse', function(){
                    var file = $(this).parent().parent().parent().find('.file');
                    file.trigger('click');
                  });
                         
                  $(document).on('change', '.file', function(){
                    $(this).parent().find('.form-control').val($(this).val().replace(/C:\\fakepath\\/i, ''));
                  });


                  $(document).ready(function() {
                      $('#form').bootstrapValidator({
                        feedbackIcons: {
                          valid: 'glyphicon glyphicon-ok',
                          invalid: 'glyphicon glyphicon-remove',
                          validating: 'glyphicon glyphicon-refresh'
                        }
                        ,
                        fields: {
                          name: {
                            validators: {
                              notEmpty: {
                                message: 'Name is required and cannot be empty'
                              }
                            }
                          }
                          ,
                        
                          lyricsTitle: {
                            validators: {
                              notEmpty: {
                                message: 'Title is required and cannot be empty'
                              }
                            }
                          }
                        }

                      });
                        
                    });

                          
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
  
 <?php include("navigationSubmit.php");?>
    <div class=container>
    <div class="panel" id="albumPanel">
      <div class="panel-heading"style=" font-size: 12px; color: white">
        <a href="index.php">
        <i class="glyphicon glyphicon-home">
        </i>
        </a> 
        <i class="glyphicon glyphicon-menu-right">
        </i> 
        Submit <i class="glyphicon glyphicon-menu-right">
        </i> Lyrics
        <i class="glyphicon glyphicon-menu-right">
          </i>
           <!-- Your share button code -->
  <div class="fb-share-button" 
    data-href="http://sachosatrambhajan.in/userLyricsForm.php" 
    data-layout="button_count" style="float:right">
  </div>
      </div>
      <div class="panel-body" id="albumsBlocks">
        <div class=row>
          <div class=col-md-12 style="border:8px solid #303030;color:#9d9d9d" id="musicPlayer">
            <br>
           <div class=col-md-12 style="margin-bottom:2%;">
              If you have Lyrics of any of the bhajans on our website and if they are not there in our Lyrcis Section too,Kindly Share !
          	  <br>You can either upload them in image format or text or both.
          	  </div> 
          	 
            
              <form action="userLyricsForm.php" method="post" role="form" id=form  enctype="multipart/form-data">
                <div class="form-group col-md-6" >
                  <label>Name
                  </label>
                  <div class="input-group">
                    <input class="form-control"  type="text" name="name"/>
                    <div class="input-group-addon">
                      <span class="glyphicon glyphicon-user">
                      </span>
                    </div>
                    <div class="help-block with-errors">
                    </div>
                  </div>
                </div>
                <div class="form-group col-md-6" >
                  <label>Mobile No
                  </label>
                  <div class="input-group">
                    <input class="form-control"  type="text" name="mobile"/>
                    <div class="input-group-addon">
                      <span class="glyphicon glyphicon-phone">
                      </span>
                    </div>
                    <div class="help-block with-errors">
                    </div>
                  </div>
                </div>
                <div class="form-group col-md-6">
                  <label>Lyrics Title
                  </label>
                  <div class="input-group">
                    <input class="form-control"  type="text" name="lyricsTitle">
                    <div class="input-group-addon">
                      <span class="glyphicon glyphicon-th-list">
                      </span>
                    </div>
                    <div class="help-block with-errors">
                    </div>
                  </div>
                </div>
               
          
                <div class="form-group col-md-6">
                  <label>Lyrics Upload
                  </label> 
                  <input type="file" name="lyricsPath" class="file">
                  <div class="input-group ">
                    <input type="text" style="background-color: transparent" class="form-control" disabled >
                    <span class="input-group-btn">
                    <button class="browse btn btn-default" type="button">
                    <i class="glyphicon glyphicon-cloud-upload">
                    </i> 
                    </button>
                    </span>
                  </div>
                <i>  Only JPEG/JPG/PNG Formats Allowed. Max Image Size:2MB </i>
                </div>
                <div class="form-group col-md-12">
                  <label>Lyrics
                  </label>
                  <div class="input-group">
                    <textarea rows="4" cols="150" style="resize:none" class="form-control"   name="lyrics"></textarea>
                      
                    <div class="input-group-addon">
                      <span class="glyphicon glyphicon-th-list">
                      </span>
                    </div>
                    <div class="help-block with-errors">
                    </div>
                  </div>
                  <i>Lyrics in text format can be entered here. </i>
                </div>
                <div class="form-group col-md-12 ">
                  <button type="submit" name='user_lyrics' class="btn">Submit !
                  </button>
                </div>
              </form>
             
              
            </div>
          </div>
        </div>
      </div>
    </div>
     <?php include("footer.php");?>
  </body>
</html>
<?php
  include("database/db_connection.php");
  if(isset($_POST['user_lyrics'])) {
  $name = $_POST['name'];
  $mobile = $_POST['mobile'];
  $lyricsTitle =  $_POST['lyricsTitle'];
  $lyrics =  $_POST['lyrics'];
  $errors= array();
  $file_name = $_FILES['lyricsPath']['name'];
  $file_size =$_FILES['lyricsPath']['size'];
  $file_tmp =$_FILES['lyricsPath']['tmp_name'];
  $file_type=$_FILES['lyricsPath']['type'];
  $lyricsPath= "./user_lyrics/".$file_name;
  $file_ext=strtolower(end(explode('.',$_FILES['lyricsPath']['name'])));
  $expensions= array("mp3","jpg","png");
  if(in_array($file_ext,$expensions)=== false){
  $errors[]="extension not allowed, please choose a JPEG or PNG file.";
  }
  if($file_size > 2097152){
  $errors[]='File size must be less than 2 MB';
  }
  if(empty($errors)==true){
  move_uploaded_file($file_tmp,"./user_lyrics/".$file_name);
  echo "<script>alert($file_name,Success);</script>";
  }else{
  print_r($errors);
  }
  $sql= "INSERT INTO user_lyrics(Name,Mobile,LyricsTitle,LyricsPath,Lyrics)VALUES('$name','$mobile','$lyricsTitle','$lyricsPath','$lyrics') " ;
  $retval = mysqli_query($conn, $sql);
  if(! $retval ) {
  die('Could not enter data: ' . mysqli_error($conn));
  }
  echo "<script>window.open('thank.php?name=$name','_self')</script>";
  mysqli_close($conn);
  }else {}
  ?>