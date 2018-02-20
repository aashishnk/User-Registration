<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration</title>

	<style>
		.error {color:red}
	</style>
	
</head>
<body>
	<h1><font color="blue">New User Registration Page</font></h1>
	
	
	<form:form action="RegisterUser" modelAttribute="user" method="POST">
	
		First Name : <form:input path="firstName" />
		<form:errors path="firstName" cssClass="error" />
		<br><br>
		Last Name : <form:input path="lastName" />
		<form:errors path="lastName" cssClass="error" />
		<br><br>
		Email-id : <form:input path="emailId" />
		<form:errors path="emailId" cssClass="error" />
		<br><br>
		Password: <form:input type="password" path="password" />
		<form:errors path="password" cssClass="error" />
		<br><br>
		Contact Number: <form:input path="contactNumber" />
		<form:errors path="contactNumber" cssClass="error" />
		<br><br>
		
		<input type="submit" value="REGISTER" />
	
	</form:form>
</body>
</html>