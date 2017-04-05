#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href='<c:url value="/css/utils/utilFormError.css"/>'>
<title>Gallery</title>
</head>
<body>
	<h1>exiit</h1>

	<a href="<c:url value="/lstComms"/>">Last Comments</a>|
	<a href="<c:url value="/users/register"/>">Register</a>|
	<security:authorize access="isAuthenticated()">
		<a href='<c:url value="/logout"/>'>logout</a>|</security:authorize>


</body>