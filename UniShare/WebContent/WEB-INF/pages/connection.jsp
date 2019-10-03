<%@page import="dto.Faculty"%>
<%@page import="dto.Student"%>
<%@page import="beans.FacultyBean"%>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="dto.Student"%>

<jsp:useBean id="facultyBean" class="beans.FacultyBean"/>
<jsp:useBean id="studentBean" class="beans.StudentBean" scope="session"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="css/style.css"> 
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  <script src="js/connection.js"></script>
  
<title>Connections</title>
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
			

	<input type="hidden" id="studentId" name="studentId" value="<%=studentBean.getStudent().getId()%>">
	
	<div class="container-fluid" style="margin-top: 20px;">
	 
	  <ul class="nav nav-pills blue" role="tablist">
	    <li class="nav-item">
	      <a class="nav-link active" data-toggle="pill"  href="#home">All students</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" data-toggle="pill"  href="#request">Connection request</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" data-toggle="pill" href="#connected">Connected students</a>
	    </li>
	  </ul>
	
	  <!-- Tab panes -->
	  <div class="tab-content">
	    
	    <div id="home" class="container-fluid tab-pane active" ><br>
	      		<%
				  	List<Student> connectedStudents = studentBean.getAllStudentConnected(); 
					List<Integer> studentRequests = studentBean.getRequestsSent(studentBean.getStudent().getId());
	
				  %>
	      		<%for(Student student : studentBean.getAllByFacultyId(studentBean.getStudent().getFaculty().getId())){%>
	      			<div class="container-fluid" style="margin-top:10px; border-bottom:1px solid #f7f7f7;" id="home-<%=student.getId()%>">
	      				<div class="row">
		      				<div class="col-sm-6">
		      					<div class="row">
		      						&nbsp;&nbsp;<img src=<%=student.getImage()==null || "".equals(student.getImage()) ? "https://image.flaticon.com/icons/svg/17/17004.svg" : "/UniShare" + student.getImage() %> 
		                        		style="width:30px;height:27px;" alt="Cinque Terre"> &nbsp;
		      						<h6><%=student.getUsername()%></h6>
		      					</div>   		      				
		      				</div>
		      				
			      			<% if(studentRequests.contains(student.getId())) {%>
			      				<div class="col-sm-6" style="margin-top:5px">
			      					<button type="button" id="btn-del-<%=student.getId()%>" class="btn" style="background-color:#45668e; color:white; width:160px; float:right;"  disabled> Request sent </button>
			      				</div>
		
		      				<%} else if(connectedStudents.contains(student)) {%>
		      						<div class="col-sm-6" style="margin-top:5px">
			      					<button type="button" class="btn" style="background-color:#45668e; color:white; width:160px; float:right;" id="btn-connect-<%=student.getId()%>"> Connected </button>
			      				</div>
		      				<%} else {%> 
				
		      						<div class="col-sm-6" style="margin-top:5px">
			      					<button type="button" id="btn-sr-<%=student.getId()%>" class="btn" style="background-color:#45668e; color:white; width:160px; float:right;" onclick="requestConnection(<%=student.getId()%>)"> Add connection </button>
			      				</div>
		      				<%} %>
		      			</div>
		      	</div>
	      		<%} %>
	    </div>	
	    
	    
	    <div id="request" class="container-fluid tab-pane fade"><br>
	      <h3>Requests</h3>
	      
	      	<% for(Student student : studentBean.getAllConnectionRequests()) { %>
	      		
	      		<div class="container-fluid" style="margin-top:10px; border-bottom:1px solid #f7f7f7 " id="request-<%=student.getId()%>">
	      			<div class="row" style="margin-top:5px">
		      			<div class="col-sm-6" id="req-div-<%=student.getId()%>">
		      				<div class="row">
		      						&nbsp;&nbsp;<img src=<%=student.getImage()==null || "".equals(student.getImage()) ? "https://image.flaticon.com/icons/svg/17/17004.svg" : "/UniShare" + student.getImage() %> 
		                        		style="width:30px;height:27px;" alt="Cinque Terre"> &nbsp;
		      						<h6><%=student.getUsername()%></h6>
		      				</div>   				
		      			</div>
		      			<div class="col-sm-3">
		      				<button type="button" onclick="acceptConnectionRequest(<%=student.getId()%>, 1)" class="btn" style="background-color:#45668e; color:white; width:120px; float:right;">Accept</button>
		      			</div>
		      			<div class="col-sm-3">
		      				<button type="button" onclick="acceptConnectionRequest(<%=student.getId()%>, 0)" class="btn" style="background-color:#45668e; color:white; width:120px; float:right;">Delete</button>
		      			</div>
		      		</div>
		      	</div>
	      		
	  		<%} %>
	    </div>
	    
	    <div id="connected" class="container-fluid tab-pane fade"><br>

	      	<% for(Student student : studentBean.getAllStudentConnected()) { %>
	      		
	      		<div class="container-fluid" style="margin-top:10px; border-bottom:1px solid #f7f7f7 " id="connected-<%=student.getId()%>">
	      			<div class="row">
		      			<div class="col-sm-6">
		      				<div class="row">
		      						&nbsp;&nbsp;<img src=<%=student.getImage()==null || "".equals(student.getImage()) ? "https://image.flaticon.com/icons/svg/17/17004.svg" : "/UniShare" + student.getImage() %> 
		                        		style="width:30px;height:27px;" alt="Cinque Terre"> &nbsp;
		      						<h6><%=student.getUsername()%></h6>
		      				</div>   					
		      			</div>
		      			<div class="col-sm-6" style="margin-top:5px">
		      				<button type="button" class="btn" style="background-color:#45668e; color:white; width:180px; float:right;" onclick="deleteConnection(<%=student.getId()%>,<%=studentBean.getStudent().getId()%> )">Delete connection</button>
		      			</div>
		      		</div>
		      	</div>
	      		
	  		<%} %>
	    </div>
	  </div>
	</div>


</body>
</html>