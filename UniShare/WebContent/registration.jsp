<%@page import="dto.Faculty"%>
<%@page import="beans.FacultyBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:useBean id="facultyBean" class="beans.FacultyBean"/>

<!DOCTYPE html>
<html>
<head>
<title>UniShare Registration</title>
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
			<a class="navbar-brand" href="#">UniShare</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			  <span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="collapsibleNavbar">
			  <ul class="navbar-nav">
				<li class="nav-item">
				  <a class="nav-link" href="http://localhost:8080/UniShare/login.jsp">Sign In</a>
				</li>
				<li class="nav-item">
				  <a class="nav-link" href="http://localhost:8080/UniShare/registration.jsp">Sign Up</a>
				</li>
				
			  </ul>
			</div>  
		  </nav>

		
		<div class="container-fluid" style="margin-top: 30px">
		 	<div class="row">
    			<div class="col-sm-4" >
				<form method="POST" action="Controller?action=registration">
					<div class="form-group">
						<label for="username">Name:</label>
						<input type="text" class="form-control" id="name" placeholder="Enter name" name="name">
					</div>
					<div class="form-group">
						<label for="surname">Surname:</label>
						<input type="password" class="form-control" id="surname" placeholder="Enter surname" name="surname">
					</div>
					<div class="form-group">
						<label for="username">Username:</label>
						<input type="text" class="form-control" id="username" placeholder="Enter username" name="username">
					</div>
					<div class="form-group">
						<label for="password">Password:</label>
						<input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
					</div>
						<div class="form-group">
						<label for="mail">Mail:</label>
						<input type="email" class="form-control" id="mail" placeholder="Enter mail" name="mail">
					</div>
					<button type="submit" class="btn btn-primary">Submit</button>
					</form>
				</div>
				
			</div>
		  </div>
		
		
</body>
</html>