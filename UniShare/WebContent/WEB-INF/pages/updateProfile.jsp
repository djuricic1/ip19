<%@page import="dto.Faculty"%>
<%@page import="beans.FacultyBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:useBean id="facultyBean" class="beans.FacultyBean"/>
<jsp:useBean id="studentBean" class="beans.StudentBean" scope="session"/>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update profile</title>
</head>
<body>
	<h1>UniShare</h1>
		<h2>Registration</h2>
		<form method="POST" action="Controller?action=registration">
			Name <br/> <input type="text" name="name" id="name" value="<%=studentBean.getUser().getName()%>"> <br/>
			Surname <br/> <input type="text" name="surname" id="surname"/> <br/>
			Username <br /> <input type="text" name="username" id="username" /> <br /> 
			Password <br /> <input type="password" name="password" id="password" /> <br />
			Mail <br/> <input type="text" name="mail" id="mail"> </br>			
			Faculty <br/><select name="faculty">
			<%
				for(Faculty faculty : facultyBean.getAllFaculties()) {
					out.println("<option>" + faculty.getName() + "</option>");
				}
			%>
			</select>
			Study Program <br/> <input type="text" name="studyProgram" id="studyProgram"/> <br/>
			Faculty Year <br/> <select name="facultyYear">
				<option value="first"> 1st Year </option>
				<option value="second"> 2nd Year </option>
				<option value="third"> 3rd Year </option>
				<option value="fourth"> 4th Year </option>
			</select>
			Profile Picture <br/> <input type="image" name="profilePic" id="profilePic"> <br/>
			Description <br/> <textarea name="description" rows="10" cols="30"> </textarea>
			
			
			
			
			
			<input type="submit" value="Sign Up" name="submit" /> <br />
			
		</form>
</body>
</html>