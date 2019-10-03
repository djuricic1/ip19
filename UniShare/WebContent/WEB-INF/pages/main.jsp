<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
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
  		

		<title>UniShare Login</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	
  		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
 		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
		<script src="js/script.js"></script>
		<script src="js/main.js"></script>
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel = "stylesheet" type = "text/css" href = "css/style.css" />
</head>
<body style="background-color:#f7f7f7">
		<input type="hidden" id="custId" name="custId" value="<%=studentBean.getStudent().getId()%>">
		
       
		<nav class="navbar sticky-top navbar-expand-sm navbar-custom">
            
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
				

        <div class="container-fluid" style="margin-top:30px">
            <div class="row">
                <div class="col-sm-3 px-4" style="maxheight:400px; overflow-y:auto"> 
                	<h6 class="px-4">Connected users</h6>         
                    <ul class="list-group px-4" style="margin-top: 5px;" >
                    <%                    	
                        for(Student student : studentBean.getAllStudentConnected()) { 
                    %>             
                    	         
                        <li class="list-group-item" style="border-left: 0px; border-right: 0px; border-bottom:1px dotted; border-top:0px; margin-top:2px;" > 
                        	<img src=<%=student.getImage()==null || "".equals(student.getImage()) ? "https://image.flaticon.com/icons/svg/17/17004.svg" : "/UniShare" + student.getImage() %> 
                        		style="width:25px;height:27px;" class="rounded-circle" alt="Cinque Terre">
                        	<a href="Controller?action=viewProfile&userId=<%=student.getId()%>" class="connected-users" style="font-size:16px;"> <%=student.getName()%> <%=student.getSurname()%> </a>
                        </li>
                   	
                   	<%  
                   	}
                    %>
                    </ul>
                </div>
                <div class="col-sm-5" style="padding-left: 60px; padding-right: 60px;">
                	
                	<div class="row">
                		
                		<form id="new-post-form" style="width:100%" action="Controller?action=post">
                			<div class="form-group" style="margin-bottom:2px;" >
                				<label for="post"  style="background-color:#dfe3ee; width:100%; margin-bottom:0px; font-size:16px;">&nbsp; Write something:</label>
                				<textarea class="form-control" id="post" rows="4"  style="overflow:auto;resize:none"></textarea>
                			</div>
                			<div class="form-group" style="margin-bottom:2px;">
								<label for="postLink"  style="background-color:#dfe3ee; width:100%; margin-bottom:0px; font-size:16px;">Link:</label>
								<input type="text" class="form-control" id="postLink" name="postLink">
							</div>
							<div id="postNtf" align="center" class="text-danger" style="font-size: 12px; display:none">
													     
							</div>
                			<button id="post-submit" type="submit" class="btn" style="background-color:#45668e; color:white; width:120px;">Create post</button>
                		</form>
                		
                	</div>

                    <div class="row" id="postContainer"  >	
                        <%
                        	SimpleDateFormat dt = new SimpleDateFormat("hh:mm:ss dd-mm-yyyy"); 
                        	
                        	
                            for(Post post : studentBean.getAllPosts()) {
                        %>   	<div id=<%=post.getId()%> class="container-fluid post-style px-3 py-3 border border-light rounded" style="width:100%; margin-top:20px;">
		                            <div class="row">
		                            <%
		                            	int uid = post.getStudentId();
		                            %>
		                            	&nbsp; &nbsp;<img src=<%=studentBean.getStudentById(post.getStudentId()).getImage()==null || "".equals(studentBean.getStudentById(post.getStudentId()).getImage()) ? "https://image.flaticon.com/icons/svg/17/17004.svg" : "/UniShare" + studentBean.getStudentById(post.getStudentId()).getImage() %> 
		                        		style="width:30px;height:27px;" class="rounded-circle" alt="Cinque Terre"> &nbsp;
		                            	<a href="Controller?action=viewProfile&userId=<%=uid%>" class="connected-users">
		                            	<h5> <%= studentBean.getStudentById(post.getStudentId()).getUsername()%> </h5>
		                            	</a>
	                            	</div>
	                            	<h6> <%=dt.format(new Date(post.getDatePosted().getTime()))%></h6>
	                            	<p class="form-control" style="background-color:white; border:0px;" > <%=post.getDescription() %> </p>
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
									<div class="row" style="margin-top:5px">
										<div class="col-sm-3 px-0" style="float:right;" >
	                  						<button type="submit" class="btn post-style text-right" style="width:100%;" onclick="addRate(0, <%=post.getId()%>)">
												<img src="assets/img/appImg/like.png" style="width:30px; height:31px;">	
											</button>											
	                  					</div>
										<div class="col-sm-3 justify-content-center align-self-center px-0" >
	                  						<h6 id="post-like-<%=post.getId()%>"><%=post.getNumberOfLikes()%></h6> 
	                  					</div>
	                  					<div class="col-sm-3 px-0" style="float:right;">
	                  						<button type="submit" class="btn post-style text-right" style="width:100%;" onclick="addRate(1, <%=post.getId()%>)">
	                  							<img src="assets/img/appImg/dislike.png" style="width:30px; height:35px;">
	                  						</button>
	                  					</div>
	                  					<div class="col-sm-3 justify-content-center align-self-center px-0">
	                  						<h6 id="post-dislike-<%=post.getId()%>"><%=post.getNumberOfDislikes()%></h6> 		
	                  					</div>										
									</div>
	                  			
                            	</div>
                         <%  } 
                         %>
                    </div>

                </div>
				
				<div class="col-sm-4 px-5" style="padding-left: 60px; padding-right: 60px;" >
					    
					<div class="row" style="widht:80%">
					<h6>File upload</h6>  
							<form id="new-file-form" style="width:100%" action="Controller?action=addFile" method="POST" enctype="multipart/form-data">
								<div class="form-group" style="margin-bottom:5px;" >
									<label for="fileDescription" style="background-color:#dfe3ee; width:100%; margin-bottom:0px; font-size:16px;">&nbsp;Add description:</label>
									<textarea class="form-control" style="resize: none;" id="fileDescription" name="fileDescription" rows="3" ></textarea>
								</div>
								<div class="form-group">								
									<input type="file" id="file" name="file" >
								</div>
								<%
										String ntf = session.getAttribute("addFileNtf") != null ? session.getAttribute("addFileNtf").toString(): "Succesful" ; 
								%>  
								<div id="addFileNtf" align="center" class="text-danger" style="font-size: 12px; display:<%="Succesful".equals(ntf) ? "none" : "block"%>">
								          <p><%=ntf%></p>
								</div>
								
								<button id="file-submit" type="submit" class="btn" style="background-color:#45668e; color:white; width:120px;">Add file</button>
							</form>
					</div>				
					
					<div class="row" id="fileContainer" style="max-height: 400px; overflow-y: auto; margin-top:5px;">
	
						<%
							for(File file : studentBean.getAllFiles()) {
						%>
							<div class="container-fluid post-style px-3 py-3 border border-light rounded" style="margin-top:20px">
							  <div class="row">
							  <%
							  	int uid = file.getStudentId();
							  %>
								&nbsp; &nbsp;<img src=<%=studentBean.getStudentById(file.getStudentId()).getImage()==null || "".equals(studentBean.getStudentById(file.getStudentId()).getImage()) ? "https://image.flaticon.com/icons/svg/17/17004.svg" : "/UniShare" + studentBean.getStudentById(file.getStudentId()).getImage() %> 
		                        		style="width:30px;height:27px;" class="rounded-circle" alt="Cinque Terre"> &nbsp;
								<a href="Controller?action=viewProfile&userId=<%=uid%>" class="connected-users">
									<h5><%=studentBean.getStudentById(file.getStudentId()).getUsername()%></h5> 
								</a>
								</div>
								<p><%=file.getDescription()%></p>
								<a href="<%=request.getContextPath()%><%=file.getPath()%>">Download file</a>
							</div>
						<%	
							}
						%>
					</div>
					<hr>
					<div class="row" id="blogContainer" style="margin-top:30px;">
						<h6>Blog creation</h6>
						<form id="new-blog-form" style="width:100%" action="Controller?action=addBlog">
								<div class="form-group" style="margin-bottom:2px;">
									<label for="blogTitle" style="background-color:#dfe3ee; width:100%; margin-bottom:0px; font-size:16px;">Title:</label>
									<input type="text" class="form-control" id="title" name="title">
								</div>
								<div class="form-group" style="margin-bottom:2px;" >
									<label for="blogText" style="background-color:#dfe3ee; width:100%; margin-bottom:0px; font-size:16px;">Text:</label>
									<textarea style="resize: none;" class="form-control" id="blogDescription" name="blogDescription" rows="4" ></textarea>
								</div>	
								<%
										ntf = session.getAttribute("addBlogNtf") != null ? session.getAttribute("addBlogNtf").toString(): "Succesful" ; 
								%>  
								<div id="addBlogNtf" align="center" class="text-danger" style="font-size: 12px; display:<%="Succesful".equals(ntf) ? "none" : "block"%>">
								          <p><%=ntf%></p>
								</div>						
								<button id="blog-submit" type="submit" class="btn" style="background-color:#45668e; color:white; width:120px;">Add blog</button>
							</form>
					</div>

					<div class="row" id="blogsContainer" style="margin-top:30px;" >
						<%
							for(Blog blog : studentBean.getAllBlogs()) {
						%>	
							<div id="blog-id-<%=blog.getId()%>" class="container-fluid post-style px-3 py-3 border border-light rounded" style="margin-top:20px">
								 <div class="media">
								    <div class="media-left">
								     <img src=<%=studentBean.getStudentById(blog.getStudentId()).getImage()==null || "".equals(studentBean.getStudentById(blog.getStudentId()).getImage()) ? "https://image.flaticon.com/icons/svg/17/17004.svg" : "/UniShare" + studentBean.getStudentById(blog.getStudentId()).getImage() %> 
		                        		style="width:30px;height:27px;" class="media-object" alt="Cinque Terre"> &nbsp;
								     
								    </div>
								   	 <%
									  	int uid = blog.getStudentId();
									  %>
								    <div class="media-body">
								    	<a href="Controller?action=viewProfile&userId=<%=uid%>" class="connected-users">
								  	  		<h5 class="media-heading"> <%=studentBean.getStudentById(blog.getStudentId()).getUsername()%></h5>
								  	  	</a>
								        <h6><%=blog.getTitle()%></h6>
								        <p><%=blog.getContent()%></p>
									    <% for(Comment comment : blog.getComments()) {%>
								   		 	<div class="media">
												<div class="media-left">
												<%
												  	 uid = comment.getStudentId();
												  %>
												 <img src=<%=studentBean.getStudentById(comment.getStudentId()).getImage()==null || "".equals(studentBean.getStudentById(comment.getStudentId()).getImage()) ? "https://image.flaticon.com/icons/svg/17/17004.svg" : "/UniShare" + studentBean.getStudentById(comment.getStudentId()).getImage() %> 
		                        					style="width:30px;height:27px;" class="media-object" alt="Cinque Terre"> &nbsp;
												</div>
												<div class="media-body">
													<a href="Controller?action=viewProfile&userId=<%=uid%>" class="connected-users">
														<h6 class="media-heading"><%=studentBean.getStudentById(comment.getStudentId()).getUsername()%></h6>
													</a>
													<p><%=comment.getContent()%> </p>
												</div>
											</div>
										<%} %>
									</div>
								 </div>
								 <form id="blog-id" action="Controller?action=addComment" method="POST">
								  	
								  	<div class="row">
										&nbsp;&nbsp;&nbsp;<img src=<%=studentBean.getStudent().getImage()==null || "".equals(studentBean.getStudent().getImage()) ? "https://image.flaticon.com/icons/svg/17/17004.svg" : "/UniShare" + studentBean.getStudent().getImage() %> 
		                        		style="width:30px;height:27px;" alt="Cinque Terre"> &nbsp;
								  		<input type="text" class="form-control" id="blogComment" name="blogComment" style="width:70%;">
								  	</div>								
								  	<button id="blog-comment-submit" type="submit" class="btn" style="background-color:#45668e; color:white; width:150px; margin-top:5px;" >Add comment</button>
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