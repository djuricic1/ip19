<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UniShare Registration</title>
</head>
<body>
	<h1>UniShare</h1>
		<h2>Registration</h2>
		<form method="POST" action="?action=registration">
			Name <br/> <input type="text" name="name" id="name"/> <br/>
			Surname <br/> <input type="text" name="surname" id="surname"/> <br/>
			Username <br /> <input type="text" name="username" id="username" /> <br /> 
			Password <br /> <input type="password" name="password" id="password" /> <br />
			Mail <br/> <input type="text" name="mail" id="mail"> </br>	
			<input type="submit" value="Prijavi me" name="submit" /> <br />
			
		</form>
</body>
</html>