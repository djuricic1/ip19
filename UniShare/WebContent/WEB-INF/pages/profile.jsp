<%@page import="dto.Faculty"%>
<%@page import="beans.FacultyBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>

<jsp:useBean id="facultyBean" class="beans.FacultyBean"/>
<jsp:useBean id="studentBean" class="beans.StudentBean" scope="session"/>


<!DOCTYPE html>
<html>
<head>
	<title>Profile</title>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">  	
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script src="js/script.js"></script>
	<script src="js/main.js"></script>
	<script src="js/update.js"></script>
	<script src="js/login.js"></script>
	

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/style.css">
	
	<style>
		label {
			float: left;
		}
	</style>

</head>
<body>
		
		<nav class="navbar navbar-expand-sm navbar-custom">
            
            <a class="navbar-brand" href="Controller" style="font-size:20px;">UniShare</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>

			<div class="collapse navbar-collapse" id="collapsibleNavbar">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item">
						<a class="nav-link" href="Controller">Home</a>
					</li>
					<li class="nav-item dropdown">
					  <a class="nav-link dropdown-toggle" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="color:white" href="#">Profile</a>
					   <div class="dropdown-menu" aria-labelledby="navbarDropdown">
				          <a class="dropdown-item" href="Controller?action=viewProfile&userId=<%=studentBean.getStudent().getId()%>">Profile</a>
				          <a class="dropdown-item" href="Controller?action=toUpdate">Update profile</a>				          
				        </div>
					</li>
					
					<li class="nav-item">
						<a class="nav-link" href="Controller?action=connections">Connections</a>
					</li>
				</ul>
				<ul class="navbar-nav">
					<li class="nav-item">
						<a class="nav-link" href="Controller?action=logout">Sign Out</a>
					</li>	
				</ul>
			</div>  
          
        </nav>
		
		<%
			int userId = (int)session.getAttribute("userId");
		%>		
			
		<div class="container-fluid" style="margin-top: 30px" align="center">

    			<div class="col-sm-3" >
					<form class="needs-validation" method="POST" action="Controller?action=update" enctype="multipart/form-data">
						<% 
							if(studentBean.getStudentById(userId).getImage() != null) {
								
						%>	
								<div class="form-group" id="imgContainer">
						<%  } else { %>
								<div class="form-group" id="imgContainer" style="display: none;"> 
						<%} %>
									<label for="img">Profile picture:</label>
									<img src="/UniShare<%=studentBean.getStudentById(userId).getImage()%>" id="img" class="img-fluid">				
								</div>
						
						<div class="form-group">
							<label for="username">Name:</label>
							<input type="text" class="form-control" id="name" placeholder="Enter name" name="name" disabled value="<%=studentBean.getStudentById(userId).getName()%>">				
						</div>
						<div class="form-group">
							<label for="surname">Surname:</label>
							<input type="text" class="form-control" id="surname" placeholder="Enter surname"  name="surname" disabled value="<%=studentBean.getStudentById(userId).getSurname()%>">
						
						</div>
						<div class="form-group">
							<label for="username">Username:</label>
							<input type="text" class="form-control" id="username" placeholder="Enter username" name="username" disabled value="<%=studentBean.getStudentById(userId).getUsername()%>">
						</div>						
						<div class="form-group">
							<label for="mail">Mail:</label>
							<input type="email" class="form-control" id="mail" placeholder="Enter mail" name="mail" disabled value="<%=studentBean.getStudentById(userId).getMail()%>">
						</div>
						<div class="form-group">
							<label for="faculty">Faculty:</label>
							 <input type="text" class="form-control" id="username" placeholder="Enter username" name="username" disabled value="<%=studentBean.getStudentById(userId).getFaculty().getName()%>">
						</div>
						<%
							String temp =studentBean.getStudentById(userId).getStudyProgram() == null ? "" : studentBean.getStudentById(userId).getStudyProgram();
						%>
						<div class="form-group">
							<label for="studyProgram">Study Program: </label>
							<input class="form-control" type="text" name="studyProgram" disabled id="studyProgram" value="<%=temp%>"/>
						</div>
						<div class="form-group">
							<label for="facultyYear">Faculty Year:</label>
							<input type="text" class="form-control" id="username" placeholder="Enter username" name="username" disabled value="<%=studentBean.getStudentById(userId).getFacultyYear()%>">
						</div>
						<div class="form-group">
							<label for="description">Description:</label>
							<textarea class="form-control" id="description" name="description" rows="10" disabled cols="30" value="<%=studentBean.getStudentById(userId).getDescription()%>"> </textarea>
						</div>		
					</form>					
				</div>
	
		  </div>
</body>
</html>