<%@page import="beans.StudentBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">  	
  		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  		
		<title>UniShare Login</title>
		<link href="styles/style.css" type="text/css" rel="stylesheet">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
 		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	</head>
	<body>
	
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
			<a class="navbar-brand" href="#">Navbar</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			  <span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="collapsibleNavbar">
			  <ul class="navbar-nav">
				<li class="nav-item">
				  <a class="nav-link" href="#">Sign In</a>
				</li>
				<li class="nav-item">
				  <a class="nav-link" href="#">Sign Up</a>
				</li>
				
			  </ul>
			</div>  
		  </nav>

		  <div class="container" style="margin-top: 90px">
		 	<div class="row">
    			<div class="col-sm-6" >
					<form action="Controller?action=login" method="POST">
					<div class="form-group">
						<label for="username">Username:</label>
						<input type="text" class="form-control" id="username" placeholder="Enter username" name="username">
					</div>
					<div class="form-group">
						<label for="pwd">Password:</label>
						<input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password">
					</div>
					<button type="submit" class="btn btn-primary">Submit</button>
					</form>
				</div>
				<div class="col-sm-6">
					<img src="https://www.acronis.com/sites/default/public_files/styles/product_top_image/public/product_image/acronis_files_advanced_logo_dark%20%281%29.png?itok=FdI8RLq-" class="mx-auto d-block">
					
				</div>
			</div>
		  </div>


	</body>
</html>