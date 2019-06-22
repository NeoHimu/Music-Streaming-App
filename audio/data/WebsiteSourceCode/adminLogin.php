<?php 
session_start();//session starts here
?>
<!DOCTYPE html>
<html lang="en">
  <head>
  <title>Login</title>
    <?php include("libraries.php");?> 
  </head>
  <body>
    <?php include("navigation.php");?>
   <script type="text/javascript">
   $(document).ready(function() {
	    $('#form').bootstrapValidator({
	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	            
	            
	            email: {
	                validators: {
	                    notEmpty: {
	                        message: 'Email Address is required and cannot be empty'
	                    },
	                    emailAddress: {
	                        message: 'It is not a valid Email Address'
	                    }
	                }
	            },
	            pwd: {
	                validators: {
	                    notEmpty: {
	                        message: 'Password is required and cannot be empty'
	                    }
	                }
	            }
	        },
	        submitHandler: function(validator, form, submitButton) {
	            var name = validator.getFieldElements('ame').val();
	                            
	            alert('Hello ' + email);
	        }
	    });
	});


   </script>
   
   
   <div class=container>
	    <div class="panel" id="albumPanel">
			  <div class="panel-heading"style=" font-size: 12px; color: white">
		<a href="index.php"><i class="glyphicon glyphicon-home"></i></a> <i class="glyphicon glyphicon-menu-right"></i> 
		Suggestions 
			  </div>
			  <div class="panel-body" id="albumsBlocks">
				 <div class=row>
				  	 <div class=col-md-12 style="border:8px solid #303030;color:#9d9d9d" id="musicPlayer">
				  	<br>
				  	
				 	<h1 class="text-center"> Login ! </h1>

				 	<br>
				 	
					<form action="adminLogin.php" method="post" role="form" id=form>
								<div class="form-group col-md-6" >
									<label>Email ID</label>
									<div class="input-group">
										<input class="form-control"  type="email" name="email"/>
										<div class="input-group-addon">
											<span class="glyphicon glyphicon-envelope"></span>
										</div>
										<div class="help-block with-errors"></div>
									</div>
								</div>
							
								<div class="form-group col-md-6">
									<label>Password</label>
									<div class="input-group">
										<input class="form-control"  type="password" name="pwd">
										<div class="input-group-addon">
											<span class="glyphicon glyphicon-lock"></span>
										</div>
										<div class="help-block with-errors"></div>
									</div>
								</div>
								
							
								<div class="form-group col-md-12 ">
								<button type="submit" name='admin_login' class="btn pull-right">Login</button>
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
	
	$_SESSION['loggedin'] = '';
	include("database/db_connection.php");
	if(isset($_POST['admin_login']))
	{ 
		
		$admin_email=$_POST['email'];
		$admin_pwd=$_POST['pwd'];
	
		$check_admin_login_details="select * from admin_login WHERE Email ='$admin_email' AND Pwd ='$admin_pwd'";
		$run=mysqli_query($conn,$check_admin_login_details);
	
		if(mysqli_num_rows($run))
		{   $admin=mysqli_fetch_array($run);
		    
		    $_SESSION['name']= '';
		    
		    $_SESSION['loggedin'] = true;
			$_SESSION['name']=$admin['Name']; //here session is used and value of $user_name is store in $_SESSION.
			
			echo "<script>window.open('adminPage.php','_self')</script>";			
					
	
		}
		
		else
		{   
			echo "<script>alert('Email or password is incorrect!')</script>";
			echo "<script>window.open('adminLogin.php','_self')</script>";
		} 
	}
	
	?>


