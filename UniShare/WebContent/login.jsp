<%@page import="beans.StudentBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>UniShare Login</title>
		<link href="styles/style.css" type="text/css" rel="stylesheet">
	</head>
	<body>
		<h1>UniShare</h1>
		<h2>Login</h2>
		<form method="POST" action="Controller?action=login">
			Korisni&#269;ko ime<br /> <input type="text" name="username"
				id="username" /><br /> Lozinka <br /> <input type="password"
				name="password" id="password" /><br /> <input type="submit"
				value="Log In" name="submit" /><br />
			<!-- <h3><%=session.getAttribute("notification")!=null?session.getAttribute("notification").toString():""%></h3> 
			<br /> <a href="?action=registration">Kreiraj novi nalog &gt;&gt;&gt;</a>   -->
		</form>
	</body>
</html>