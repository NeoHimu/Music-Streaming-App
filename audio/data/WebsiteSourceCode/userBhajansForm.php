<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Upload Bhajans
    </title>
    
    <meta property="og:url"           content="http://sachosatrambhajan.in/userBhajansForm.php" />
  <meta property="og:type"          content="website" />
  <meta property="og:title"         content="Share SSD Bhajans With Us" />
  <meta property="og:description"   content="If you have any collection of SSD Bhajans which are not on our website,you can
share with us.Will upload them if it gets approved from our moderators !
Share it among your friends and family to make them aware of this facility and use it to the
fullest." />
  <meta property="og:image"         content="http://sachosatrambhajan.in/images/userBhajansForm.png" />
  
    
     <?php include("libraries.php");?>
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
    <script type="text/javascript">
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
            bhajanPath : {
                validators: {
                  notEmpty: {
                    message: 'Bhajan is required and cannot be empty'
                  }
                }
              }
              ,
            bhajanTitle: {
              validators: {
                notEmpty: {
                  message: 'Title is required and cannot be empty'
                }
              }
            }
          }
          ,
          submitHandler: function(validator, form, submitButton) {
            var name = validator.getFieldElements('ame').val();
            alert('Hello ' + name);
          }
        }
                                     );
      }
                       );
    </script>
    <div class=container>
    <div class="panel" id="albumPanel">
      <div class="panel-heading"style=" font-size: 12px; color: white">
        <a href="index.php">
        <i class="glyphicon glyphicon-home">
        </i>
        </a> 
        <i class="glyphicon glyphicon-menu-right">
        </i> 
        Submit <i class="glyphicon glyphicon-menu-right"> </i>
        Bhajans
         <i class="glyphicon glyphicon-menu-right">
          </i>
           <!-- Your share button code -->
  <div class="fb-share-button" 
    data-href="http://sachosatrambhajan.in/userBhajansForm.php" 
    data-layout="button_count" style="float:right">
  </div>
      </div>
      <div class="panel-body" id="albumsBlocks">
        <div class=row>
          <div class=col-md-12 style="border:8px solid #303030;color:#9d9d9d" id="musicPlayer">
            <br>
            <div class=col-md-12>
              				 	 If you have any collection of SSD Bhajans which are not on our website,you can share with us.Will upload them if it gets approved from our moderators ! 
             </div> 
              <br>
              <br>
              <form action="userBhajansForm.php" method="post" role="form" id=form enctype="multipart/form-data">
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
                  <label>Bhajan Title
                  </label>
                  <div class="input-group">
                    <input class="form-control"  type="text" name="bhajanTitle">
                    <div class="input-group-addon">
                      <span class="glyphicon glyphicon-th-list">
                      </span>
                    </div>
                    <div class="help-block with-errors">
                    </div>
                  </div>
                </div>
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
                  }
                                );
                  $(document).on('change', '.file', function(){
                    $(this).parent().find('.form-control').val($(this).val().replace(/C:\\fakepath\\/i, ''));
                  }
                                );
                </script>
                <div class="form-group col-md-6">
                  <label>Bhajan Upload
                  </label> 
                  <input type="file" name="bhajanPath" class="file">
                  <div class="input-group ">
                    <input type="text" style="background-color: transparent" class="form-control" disabled >
                    <span class="input-group-btn">
                    <button class="browse btn btn-default" type="button">
                    <span class="glyphicon glyphicon-cloud-upload">
                    </span> 
                    </button>
                    </span>
                  </div>
                    <i>  Only MP3 Format Allowed. Max File Size:20MB </i>
                </div>
              
                <div class="form-group col-md-12 ">
                  <button type="submit" name='user_bhajans' class="btn">Submit !
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
  if(isset($_POST['user_bhajans'])) {
  $name = $_POST['name'];
  $mobile = $_POST['mobile'];
  $bhajanTitle =  $_POST['bhajanTitle'];
  $driveLink =  $_POST['driveLink'];
  //	$bhajan=$_POST['bhajanPath'];
  //	$file_name='';
  $errors= array();
  $file_name = $_FILES['bhajanPath']['name'];
  $file_size =$_FILES['bhajanPath']['size'];
  $file_tmp =$_FILES['bhajanPath']['tmp_name'];
  $file_type=$_FILES['bhajanPath']['type'];
  $bhajanPath = "./user_bhajans/".$file_name;
  $file_ext=strtolower(end(explode('.',$_FILES['bhajanPath']['name'])));
  $expensions= array("mp3");
  if(in_array($file_ext,$expensions)=== false){
  $errors[]="extension not allowed, please choose a Mp3 file.";
  }
  if($file_size > 26214400){
  $errors[]='File size must be less than 25 MB';
  }
  if(empty($errors)==true){
  move_uploaded_file($file_tmp,"./user_bhajans/".$file_name);
  echo "<script>alert($file_name,Success);</script>";
  }else{
  echo "$errors";
  }
  $sql= "INSERT INTO user_bhajans(Name,Mobile,BhajanTitle,BhajanPath)VALUES('$name','$mobile','$bhajanTitle','$bhajanPath') " ;
  $retval = mysqli_query($conn, $sql);
  if(! $retval ) {
  die('Could not enter data: ' . mysqli_error($conn));
  }
  echo "<script>window.open('thank.php?name=$name','_self')</script>";
  mysqli_close($conn);
  }else {}
  ?>