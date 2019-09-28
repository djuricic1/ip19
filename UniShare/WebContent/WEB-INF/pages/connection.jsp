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
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  <script src="js/connection.js"></script>
<title>Insert title here</title>
</head>
<body>

<br>
<input type="hidden" id="studentId" name="studentId" value="<%=studentBean.getStudent().getId()%>">
<div class="container-fluid">
  <h2>Connections</h2>
  <br>
  <!-- Nav pills -->
  <ul class="nav nav-pills" role="tablist">
    <li class="nav-item">
      <a class="nav-link active" data-toggle="pill" href="#home">All students</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" data-toggle="pill" href="#request">Connection request</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" data-toggle="pill" href="#connected">Connected students</a>
    </li>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content">
    
    <div id="home" class="container-fluid tab-pane active"><br>
		<h6>All students</h6>
      		<%
			  	List<Student> connectedStudents = studentBean.getAllStudentConnected(); 
				List<Integer> studentRequests = studentBean.getRequestsSent(studentBean.getStudent().getId());

			  %>
      		<%for(Student student : studentBean.getAllByFacultyId(studentBean.getStudent().getFaculty().getId())){%>
      			<div class="container-fluid" style="margin-top:10px;" id="home-<%=student.getId()%>">
      				<div class="row">
	      				<div class="col-sm-6">
	      					<h6><%=student.getUsername()%></h6>	      				
	      				</div>
	      				
		      			<% if(studentRequests.contains(student.getId())) {%>
		      				<div class="col-sm-6" style="margin-top:5px">
		      					<button type="button" id="btn-del-<%=student.getId()%>" class="btn btn-primary"  disabled> Request sent </button>
		      				</div>
	
	      				<%} else if(connectedStudents.contains(student)) {%>
	      						<div class="col-sm-6" style="margin-top:5px">
		      					<button type="button" class="btn btn-primary"> Connected </button>
		      				</div>
	      				<%} else {%> 
			
	      						<div class="col-sm-6" style="margin-top:5px">
		      					<button type="button" id="btn-sr-<%=student.getId()%>" class="btn btn-primary" onclick="requestConnection(<%=student.getId()%>)"> Add connection </button>
		      				</div>
	      				<%} %>
	      			</div>
	      	</div>
      		<%} %>
    </div>	
    
    
    <div id="request" class="container-fluid tab-pane fade"><br>
      <h3>Requests</h3>
      
      	<% for(Student student : studentBean.getAllConnectionRequests()) { %>
      		
      		<div class="container-fluid" style="margin-top:10px;" id="request-<%=student.getId()%>">
      			<div class="row" style="margin-top:5px">
	      			<div class="col-sm-6" id="req-div-<%=student.getId()%>">
	      				<h6><%=student.getUsername()%></h6>	      				
	      			</div>
	      			<div class="col-sm-3">
	      				<button type="button" onclick="acceptConnectionRequest(<%=student.getId()%>, 1)" class="btn btn-primary">Accept</button>
	      			</div>
	      			<div class="col-sm-3">
	      				<button type="button" onclick="acceptConnectionRequest(<%=student.getId()%>, 0)" class="btn btn-primary">Delete</button>
	      			</div>
	      		</div>
	      	</div>
      		
  		<%} %>
    </div>
    
    <div id="connected" class="container tab-pane fade"><br>
      <h3>Connected</h3>
      	<% for(Student student : studentBean.getAllStudentConnected()) { %>
      		
      		<div class="container-fluid" style="margin-top:10px;" id="connected-<%=student.getId()%>">
      			<div class="row">
	      			<div class="col-sm-6">
	      				<h6><%=student.getUsername()%></h6>	      				
	      			</div>
	      			<div class="col-sm-6" style="margin-top:5px">
	      				<button type="button" class="btn btn-primary" onclick="deleteConnection(<%=student.getId()%>,<%=studentBean.getStudent().getId()%> )">Delete connection</button>
	      			</div>
	      		</div>
	      	</div>
      		
  		<%} %>
    </div>
  </div>
</div>


</body>
</html>