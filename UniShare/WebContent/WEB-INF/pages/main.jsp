<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="dto.Student"%>
<%@ page import="dto.Post"%>
<%@ page import="java.text.SimpleDateFormat" %>
<jsp:useBean id="studentBean" class="beans.StudentBean" scope="session"/>


<!DOCTYPE html>
<html>
<head><title>UniShare Main</title>
<meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">  	
  		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  		<script src="js/script.js"></script>
		<title>UniShare Login</title>
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
                			<div class="form-group">
								<label for="postLink">Link:</label>
								<input type="text" class="form-control" id="postLink" name="postLink">
							</div>
                			<button type="submit" class="btn btn-primary" >Create post</button>
                		</form>
                		
                	</div>

                    <div class="row" id="postContainer">
                        <%
                            for(Post post : studentBean.getAllPosts()) {
                        %>   	<div id=<%=post.getId()%> class="container-fluid"  style="width:100%; margin-top:90px;">
                            	<h5> <%= studentBean.getStudentById(post.getStudentId()).getUsername()%> </h5>
                            	<h6> <%= new Date(post.getDatePosted().getTime()) %></h6>
                            	<textarea class="form-control" rows="4" style="background-color:white;" disabled> <%=post.getDescription() %> </textarea>
                  				<% if(post.getTypeOfPost().equals("1")){ %>
                  				<a href="#"><%=post.getLinkPost()%></a>
                  				<%} else if (post.getTypeOfPost().equals("2")) {%>
                  				<div class="row start" style="margin-top:8px;">
                                    <div class="col-sm-8">
                                  <div class="embed-responsive embed-responsive-4by3">
 									<iframe class="embed-responsive-item" src="<%=post.getLinkPost()%>" allowfullscreen></iframe>
								</div>
                                </div>
                                </div>
								<%} %>
								<div class="row">
									<div class="col-sm-6">
                  						<h6 id="post-like-<%=post.getId()%>">Likes:<%=post.getNumberOfLikes()%></h6> 
                  					</div>
                  					<div class="col-sm-6">
                  						<h6 id="post-dislike-<%=post.getId()%>">Dislikes:<%=post.getNumberOfDislikes()%></h6> 		
                  					</div>
								
									
								</div>
                  				<div class="row" style="margin-top:5px"> 
                  					<div class="col-sm-6">
                  						<button type="submit" class="btn btn-primary" style="width:100%;" onclick="addRate(0, <%=post.getId()%>)">Like</button>
                  					</div>
                  					<div class="col-sm-6">
                  						<button type="submit" class="btn btn-primary" style="width:100%;" onclick="addRate(1, <%=post.getId()%>)">Dislike</button>
                  					</div>
                  				</div>
                            	</div>
                         <%  } 
                         %>
                    </div>

                </div>
            </div>

        </div>

		

</body>
</html>