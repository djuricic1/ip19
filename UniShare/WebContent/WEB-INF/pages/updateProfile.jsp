<%@page import="dto.Faculty"%>
<%@page import="beans.FacultyBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
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
		<form method="POST" action="Controller?action=update">
			Name <br/> <input type="text" name="name" id="name" value="<%=studentBean.getStudent().getName()%>"> <br/>
			Surname <br/> <input type="text" name="surname" id="surname" value="<%=studentBean.getStudent().getSurname()%>"/> <br/>
			Username <br /> <input type="text" name="username" id="username" value="<%=studentBean.getStudent().getUsername()%>" /> <br /> 
			Password <br /> <input type="password" name="password" id="password" value="<%=studentBean.getStudent().getPassword()%>" /> <br />
			Mail <br/> <input type="text" name="mail" id="mail" value="<%=studentBean.getStudent().getMail()%>"/> </br>			
			Faculty <br/>
			<select name="faculty">
			<%
				for(Faculty faculty : facultyBean.getAllFaculties()) {
					if(studentBean.getStudent().getFaculty().getId() == faculty.getId())
						out.println("<option selected>" + faculty.getName() + "</option>");
					out.println("<option>" + faculty.getName() + "</option>");
				}
			%>
			</select> <br/>
			<%
				String temp =studentBean.getStudent().getStudyProgram() == null ? "" : studentBean.getStudent().getStudyProgram();
			%>
			Study Program <br/> <input type="text" name="studyProgram" id="studyProgram" value="<%=temp%>"/> <br/>
			Faculty Year <br/> 
			<select name="facultyYear">
				<option value="1"> 1st Year </option>
				<option value="2"> 2nd Year </option>
				<option value="3"> 3rd Year </option>
				<option value="4"> 4th Year </option>
			</select> <br/>
			<!-- Profile Picture <br/> <input type="image" name="profilePic" id="profilePic" width="48" height="48"> <br/> -->
			Description <br/> <textarea name="description" rows="10" cols="30" value="<%=studentBean.getStudent().getDescription()%>"> </textarea> </br>
			
		
			<input type="submit" value="Update" name="update" /> <br />
			
		</form>
</body>
</html>