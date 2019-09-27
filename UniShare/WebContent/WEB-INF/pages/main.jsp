<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="dto.Student"%>
<%@ page import="dto.Post"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="dto.File"%>
<%@ page import="dto.Blog"%>
<%@ page import="dto.Comment"%>

<jsp:useBean id="studentBean" class="beans.StudentBean" scope="session"/>


<!DOCTYPE html>
<html>
<head><title>UniShare Main</title>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">  	
  		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

		<title>UniShare Login</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	
  		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
 		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
		<script src="js/script.js"></script>
		<script src="js/main.js"></script>
</head>
<body>
		<input type="hidden" id="custId" name="custId" value="<%=studentBean.getStudent().getId()%>">
		
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
            <a class="navbar-brand" href="/Controller">UniShare</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>

			<div class="collapse navbar-collapse" id="collapsibleNavbar">
			  <ul class="navbar-nav">
				<li class="nav-item">
				  <a class="nav-link" href="Controller?action=toUpdate">Profile</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="Controller?action=connections">Connections</a>
				</li>
				<li class="nav-item">
				  <a class="nav-link" href="Controller?action=logout">Sign Out</a>
				</li>
				
			  </ul>
			</div>  
          
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
                		
                		<form id="new-post-form" style="width:100%" action="Controller?action=post">
                			<div class="form-group" >
                				<label for="post">Write something:</label>
                				<textarea class="form-control" id="post" rows="4" ></textarea>
                			</div>
                			<div class="form-group">
								<label for="postLink">Link:</label>
								<input type="text" class="form-control" id="postLink" name="postLink">
							</div>
                			<button id="post-submit" type="submit" class="btn btn-primary" >Create post</button>
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
								<%
									

								 %>
									
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
				
				<div class="col-sm-3 px-5" >
					
					<div class="row" style="widht:80%">
							<form id="new-file-form" style="width:100%" action="Controller?action=addFile" method="POST" enctype="multipart/form-data">
								<div class="form-group" >
									<label for="fileDescription">Add description:</label>
									<textarea class="form-control" id="fileDescription" name="fileDescription" rows="4" ></textarea>
								</div>
								<div class="form-group">								
									<input type="file" id="file" name="file">
								</div>
							
								<button id="file-submit" type="submit" class="btn btn-primary" >Add file</button>
							</form>
					</div>				
					
					<div class="row" id="fileContainer">
						<%
							for(File file : studentBean.getAllFiles()) {
						%>
							<div class="container-fluid" style="border-style:1px; margin-top:30px">
							
								<h5><%=studentBean.getStudentById(file.getStudentId()).getUsername()%></h5> 
								<p><%=file.getDescription()%></p>
								<a href="<%=request.getContextPath()%><%=file.getPath()%>">Download file</a>
							</div>
						<%	
							}
						%>
					</div>
					
					<div class="row" id="blogContainer" style="margin-top:30px;">
						<form id="new-blog-form" style="width:100%" action="Controller?action=addBlog" method="POST">
								<div class="form-group">
									<label for="blogTitle">Title:</label>
									<input type="text" class="form-control" id="title" name="title">
								</div>
								<div class="form-group" >
									<label for="blogText">Text:</label>
									<textarea class="form-control" id="blogDescription" name="blogDescription" rows="4" ></textarea>
								</div>							
								<button id="blog-submit" type="submit" class="btn btn-primary" >Add blog</button>
							</form>
					</div>

					<div class="row" id="blogsContainer" style="margin-top:30px;">
						<%
							for(Blog blog : studentBean.getAllBlogs()) {
						%>	
							<div class="container-fluid" style="border-style:1px; margin-top:30px">
								 <div class="media">
								    <div class="media-left">
								      <!-- <img src="img_avatar1.png" class="media-object" style="width:60px"> -->
								      <h6><%=studentBean.getStudentById(blog.getStudentId()).getUsername()%></h6>
								    </div>
								    <div class="media-body">
								      <h4 class="media-heading"><%=blog.getTitle()%></h4>
								      <p><%=blog.getContent()%></p>
									  <% for(Comment comment : blog.getComments()) {%>
								   		 	<div class="media">
												<div class="media-left">
													<h6><%=studentBean.getStudentById(comment.getStudentId()).getUsername()%></h6>
												</div>
												<div class="media-body">
													<h4 class="media-heading"></h4>
													<p><%=comment.getContent()%> </p>
												</div>
											</div>
										<%} %>
									</div>
								 </div>
								 <form id="blog-id" action="Controller?action=addComment" method="POST">
								  	<div class="form-group">
								  		<input type="text" class="form-control" id="blogComment" name="blogComment">
								  		<button id="blog-comment-submit" type="submit" class="btn btn-primary" >Add comment</button>								  	
								  	</div>
								  	<input type="hidden" id="blogId" name="blogId" value="<%=blog.getId()%>">
								 </form>

								 </div>
								  <hr>	
						<%			
							}
						%>
					</div>
					
				</div>
				

            </div>

        </div>

		

</body>

</html>