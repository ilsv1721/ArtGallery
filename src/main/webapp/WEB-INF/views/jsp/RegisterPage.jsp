<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<sf:form method="POST" modelAttribute="userDetailsDto">
	<br>First Name:
		<sf:input path="firstName" cssErrorClass="error" />
	<br>
	<br>
	Last name: <sf:input path="lastName" />
	<br>
	Email: <sf:input path="email" type="email" />

	<br>
	<br>
	Password: <sf:input path="password" type="password" />
	<br>
	<sf:errors path="*" element="div" cssClass="errors" />
	<input type="submit" value="Register">
</sf:form>
