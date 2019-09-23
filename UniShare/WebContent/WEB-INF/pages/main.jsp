<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="dto.Student"%>

<jsp:useBean id="studentBean" class="beans.StudentBean" scope="session"/>


<!DOCTYPE html>
<html>
<head><title>UniShare Main</title>
<meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">  	
  		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  		<script src="js/script.js"></script>
		<title>UniShare Login</title>
		<link href="styles/style.css" type="text/css" rel="stylesheet">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
 		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
		<input type="hidden" id="custId" name="custId" value="<%=studentBean.getStudent().getId()%>">
		
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
            <a class="navbar-brand" href="#">UniShare</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <!-- <div class="collapse navbar-collapse" id="collapsibleNavbar">
                <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="#">Sign In</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8080/UniShare/registration.jsp">Sign Up</a>
                </li>
                
                </ul>
            </div> 
        --> 
        </nav>

        <div class="container-fluid" style="margin-top:30px">
            <div class="row">
                <div class="col-sm-3">          
                    <ul class="list-group">
                    <%                    	
                        for(Student student : studentBean.getAllStudentConnected()) {
                        	out.println("<li class=\"list-group-item\">" + student.getUsername() + "</li>");
                        }
                    %>
                    </ul>
                </div>
                <div class="col-sm-6">
                	
                	<div class="row">
                		
                		<form action="javascript:addPost();" id="new-post-form" style="width:100%">
                			<div class="form-group" >
                				<label for="post">Write something:</label>
                				<textarea class="form-control" id="post" rows="4" ></textarea>
                			</div>
                			<button type="submit" class="btn btn-primary" >Create post</button>
                		</form>
                		
                	</div>
                
                </div>
            </div>

        </div>

		

</body>
</html>