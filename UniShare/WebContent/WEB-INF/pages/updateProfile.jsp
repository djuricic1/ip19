<%@page import="dto.Faculty"%>
<%@page import="beans.FacultyBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>

<jsp:useBean id="facultyBean" class="beans.FacultyBean"/>
<jsp:useBean id="studentBean" class="beans.StudentBean" scope="session"/>


<!DOCTYPE html>
<html>
<head>
	<title>UniShare Update</title>
	<meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">  	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script src="js/script.js"></script>
	<script src="js/main.js"></script>
</head>
<body>
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
			<a class="navbar-brand" href="#">UniShare</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			  <span class="navbar-toggler-icon"></span>
			</button>
			<!--<div class="collapse navbar-collapse" id="collapsibleNavbar">
			  <ul class="navbar-nav">
				<li class="nav-item">
				  <a class="nav-link" href="http://localhost:8080/UniShare/login.jsp">Sign In</a>
				</li>
				<li class="nav-item">
				  <a class="nav-link" href="http://localhost:8080/UniShare/registration.jsp">Sign Up</a>
				</li>
				
			  </ul>
			</div>  --> 
		  </nav>

		<div class="container-fluid" style="margin-top: 30px">
		 	<div class="row">
    			<div class="col-sm-4" >
				<form method="POST" action="Controller?action=update" enctype="multipart/form-data">
					<div class="form-group">
						<label for="username">Name:</label>
						<input type="text" class="form-control" id="name" placeholder="Enter name" name="name" value="<%=studentBean.getStudent().getName()%>">
					</div>
					<div class="form-group">
						<label for="surname">Surname:</label>
						<input type="text" class="form-control" id="surname" placeholder="Enter surname" name="surname" value="<%=studentBean.getStudent().getSurname()%>">
					</div>
					<div class="form-group">
						<label for="username">Username:</label>
						<input type="text" class="form-control" id="username" placeholder="Enter username" name="username" value="<%=studentBean.getStudent().getUsername()%>">
					</div>
					<div class="form-group">
						<label for="password">Password:</label>
						<input type="password" class="form-control" id="password" placeholder="Enter password" name="password" value="<%=studentBean.getStudent().getPassword()%>" >
					</div>
					<div class="form-group">
						<label for="mail">Mail:</label>
						<input type="email" class="form-control" id="mail" placeholder="Enter mail" name="mail" value="<%=studentBean.getStudent().getMail()%>">
					</div>
					<div class="form-group">
						<label for="faculty">Faculty:</label>
						<select id="faculty" name="faculty" class="form-control">
							<%
								for(Faculty faculty : facultyBean.getAllFaculties()) {
									if(studentBean.getStudent().getFaculty().getId() != faculty.getId())
										out.println("<option selected>" + faculty.getName() + "</option>");
									else
										out.println("<option>" + faculty.getName() + "</option>");
								}
							%>
						</select> 
					</div>
					<%
						String temp =studentBean.getStudent().getStudyProgram() == null ? "" : studentBean.getStudent().getStudyProgram();
					%>
					<div class="form-group">
						<label for="studyProgram">Study Program: </label>
						<input class="form-control" type="text" name="studyProgram" id="studyProgram" value="<%=temp%>"/>
					</div>
					<div class="form-group">
						<label for="facultyYear">Faculty Year:</label>
						<select name="facultyYear" class="form-control">
							<option value="1"> 1st Year </option>
							<option value="2"> 2nd Year </option>
							<option value="3"> 3rd Year </option>
							<option value="4"> 4th Year </option>
						</select> 
					</div>
					<div class="form-group">
						<label for="img">Choose image </label>
						<input type="file" id="file" name="file"> 
					</div>
					<div class="form-group">
						<label for="description">Description:</label>
						<textarea class="form-control" id="description" name="description" rows="10" cols="30" value="<%=studentBean.getStudent().getDescription()%>"> </textarea>
					</div>
					<button type="submit" class="btn btn-primary">Update</button>
					</form>
				</div>
				
			</div>
		  </div>
</body>
</html>