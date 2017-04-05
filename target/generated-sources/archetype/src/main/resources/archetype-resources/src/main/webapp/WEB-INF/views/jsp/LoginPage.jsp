#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Log in the system</title>
</head>
<body>
	<c:out value="${symbol_dollar}{error}" />
	<sf:form action="login" method="POST">
		<table>
			<tr>
				<td>Username:</td>
				<td><input type="text" name="Email" value=""></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="Password" value=""></td>
			</tr>
			<tr>
				<td>Remember me:</td>
				<td><input type="checkbox" name="Remember-me" id="remember_me"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="submit"
					value="Login" /></td>
			</tr>
		</table>
	</sf:form>
</body>
</html>