<%@page import="dto.Faculty"%>
<%@page import="beans.FacultyBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@page import="beans.StudentBean"%>

<jsp:useBean id="facultyBean" class="beans.FacultyBean"/>
<!DOCTYPE html>
<html>
	<head>
		<title>UniShare</title>
		<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">  	
  		
  		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">	
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
 		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
 		<script src="js/login.js"></script> 
	</head>
	<body>

		<div class="jumbotron text-center" style="margin-bottom:0; background-color:#45668e; color: white;">
		  <h1>UniShare</h1>
		</div>
		
		<div class="container-fluid" style="margin-top:100px">
			
			<div class=row> 
				
				<div class="col-sm-6" align="center">				
							<div class="col-sm-6">
								<h3>Log into UniShare</h3>
								<form class="needs-validation" novalidate action="Controller?action=login" method="POST">
									<div class="form-group">								
										<input type="text" class="form-control" id="username" placeholder="Enter username" name="username" required>
										<div class="invalid-feedback">
								          Please enter username
								        </div>
									</div>
									<div class="form-group">							
										<input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password" required>
										<div class="invalid-feedback">
								          Please enter password.
								        </div>
									</div>
									<%
										String ntf = session.getAttribute("loginNotification") != null ? session.getAttribute("loginNotification").toString(): "" ; 
										if(!ntf.equals("")) {
									%>  
										<div id="loginNtf" align="center" class="text-danger" style="font-size: 12px;">
								          <p><%=ntf%></p>
								        </div>
									
									<%}%>
									<button type="submit" onclick="javascript:hideLoginNtf()"class="btn" style="background-color:#45668e; color:white; width:120px">Submit</button>
								</form>
							</div>				
				
	
				</div>
	
				<div class="col-sm-6" align="center">
				
					<div class="col-sm-8" >
							<h3>Sign Up</h3>
							<form class="needs-validation" novalidate method="POST" action="Controller?action=registration"
								oninput='password2.setCustomValidity(password2.value != password.value ? "Passwords do not match." : "")'>
								<div class="form-group">						
									<input type="text" class="form-control" id="name" placeholder="Enter name" name="name" required>
									<div class="invalid-feedback">
								          Please enter name
								     </div>
								</div>
								<div class="form-group">								
									<input type="text" class="form-control" id="surname" placeholder="Enter surname" name="surname" required>
									<div class="invalid-feedback">
								          Please enter surname
								    </div>
								</div>
								<div class="form-group">								
									<input type="text" class="form-control" id="usernameRegistration" placeholder="Enter username" name="username" required>
									<div class="invalid-feedback">
								          Please enter username
								    </div>
								</div>
								<div class="form-group">								
									<input type="password" class="form-control password" minlength="6" id="password" placeholder="Enter password" name="password" required
										oninput="">
									<div class="invalid-feedback pass-info">
								          Please enter password
								    </div>
								</div>
								<div class="form-group">								
									<input type="password" class="form-control password" id="password2" placeholder="Confirm password" name="password2" required>
									<div class="invalid-feedback">
								       	  Passwords do not match.
								    </div>
								</div>
								<div class="form-group">								
									<input type="email" class="form-control" id="mail" placeholder="Enter mail" name="mail" required>
									<div class="invalid-feedback">
								          Please enter username
								        </div>
								</div>
								<%
									ntf = session.getAttribute("registrationNotification") != null ? session.getAttribute("registrationNotification").toString(): "" ; 
									if(!ntf.equals("")) {
								%>  
									<div id="registrationNtf" align="center" class="text-danger" style="font-size: 12px;">
							          <p><%=ntf%></p>
							        </div>
									
								<%}%>
								<button type="submit" class="btn" style="background-color:#45668e; color:white; width:120px">Submit</button>
							</form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>