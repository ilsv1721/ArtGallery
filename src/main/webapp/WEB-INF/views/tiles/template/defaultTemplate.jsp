<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><tiles:getAsString name="title" /></title>

<link href="<c:url value='/resources/css/w3.css' />" rel="stylesheet"></link>

<link href="<c:url value='/resources/css/addons.css' />"
	rel="stylesheet"></link>




</head>

<body>

	<script src="<c:url value='/resources/js/jquery-3.2.1.min.js' />"></script>
	<script src="<c:url value='/resources/js/jquery.validate.min.js' />"></script>
	<script src="<c:url value='/resources/js/jquery.serializeObject.min.js' />"></script>
	

	<section id="sidemenu">
		<tiles:insertAttribute name="menu" />
	</section>

	<section id="site-content"
		style="padding-top: 30px; padding-left: 10%; padding-right: 10%;">
		<tiles:insertAttribute name="body" />
	</section>

	<footer id="footer">
		<tiles:insertAttribute name="footer" />
	</footer>


</body>
</html>