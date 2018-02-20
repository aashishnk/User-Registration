<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
	
	<style>
		.error {color:red}
	</style>

</head>
<body>
	
	<h1><font color="blue">User Login Page</font></h1>
	
	
	<form:form action="userLogin" modelAttribute="user" method="POST">
	
		Email-id : <form:input path="emailId" />
		<form:errors path="emailId" cssClass="error" />
		<br><br>
		Password: <form:input type="password" path="password" />
		<form:errors path="password" cssClass="error" />
		<br><br>
		
		<input type="submit" value="Sign In" />
	
	</form:form>
	<br>
	<input type=button value="Sign Up"
	onclick="window.location.href='userRegistration'; return false;" />
	<h4><font color="red">${loginErrorMessage}</font></h4>
	
	

</body>
</html>