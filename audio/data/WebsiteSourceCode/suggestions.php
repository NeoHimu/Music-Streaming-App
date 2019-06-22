<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Suggestions
    </title>
    
     <meta property="og:url"           content="http://sachosatrambhajan.in/suggestions.php" />
  <meta property="og:type"          content="website" />
  <meta property="og:title"         content="Kindly Provide Your Suggestions" />
  <meta property="og:description"   content="We would like to know if there is anything we could improve in our website or
anything else you would like to let us know.
Share it among your friends and family to make them aware of this facility and use it to the
fullest." />
  <meta property="og:image"         content="http://sachosatrambhajan.in/images/suggestions.png" />
  
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
    
     <?php include("navigation.php");?>
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
            suggestions: {
              validators: {
                notEmpty: {
                  message: 'Suggestion is required and cannot be empty'
                }
              }
            }
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
          Suggestions 
           <i class="glyphicon glyphicon-menu-right">
          </i>
           <!-- Your share button code -->
  <div class="fb-share-button" 
    data-href="http://sachosatrambhajan.in/suggestions.php" 
    data-layout="button_count" style="float:right">
  </div>
        </div>
        <div class="panel-body" id="albumsBlocks">
          <div class=row>
            <div class=col-md-12 style="border:8px solid #303030;color:#9d9d9d" >
              <br>
              <div class=col-md-12>
                We would like to know if there is anything we could improve in our website or 
                anything else you would like to let us know.
              </div>
              <br>
              <br>
              <form action="suggestions.php" method="post" role="form" id=form>
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
                <div class="form-group col-md-6">
                  <label>Mobile Number
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
                <div class="form-group col-md-12">
                  <label>Suggestions
                  </label>
                  <div class="input-group">
                    <textarea rows="4" cols="150" style="resize:none" class="form-control" onkeypress="if (this.value.length > 999) { return false; }"  name="suggestions"/></textarea>
                    
                    <div class="input-group-addon">
                      <span class="glyphicon glyphicon-th-list">
                      </span>
                    </div>
                    <div class="help-block with-errors">
                    </div>
                  </div>
                  <i> Max 1000 characters only </i>
                </div>
                <div class="form-group col-md-12 ">
                  <button type="submit" name='user_suggestions' class="btn">Suggest !
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
  if(isset($_POST['user_suggestions'])) {
  $name = $_POST['name'];
  $mobile = $_POST['mobile'];
  $suggestions =  $_POST['suggestions'];
  $sql= "INSERT INTO user_suggestions(Name,Mobile,Suggestions)VALUES('$name','$mobile','$suggestions') " ;
  $retval = mysqli_query($conn, $sql);
  if(! $retval ) {
  die('Could not enter data: ' . mysqli_error($conn));
  }
  echo "<script>window.open('thank.php?name=$name','_self')</script>";
  mysqli_close($conn);
  }else {}
  ?>