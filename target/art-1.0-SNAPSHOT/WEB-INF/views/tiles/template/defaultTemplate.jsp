<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><tiles:getAsString name="title" /></title>

<link href="<c:url value='/resources/css/bootstrap.css' />"
	rel="stylesheet"></link>

<link href="<c:url value='/resources/css/addons.css' />"
	rel="stylesheet"></link>


</head>

<body>

	<section id="sidemenu">
		<tiles:insertAttribute name="menu" />
	</section>

	<section id="site-content"
		style="padding-left: 100px; padding-top: 45px;">
		<tiles:insertAttribute name="body" />
	</section>

	<footer id="footer">
		<tiles:insertAttribute name="footer" />
	</footer>

	<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
	<script
		src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</body>
</html>