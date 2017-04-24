<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<h1>Check out our exhibitions!</h1>


	<!-- REDO THIS IS HARDCODED AS FUCK ${requestScope['javax.servlet.forward.request_uri']} -->
	<table>
		<c:forEach items="${exhibitionsList}" var="exhibition">
			<tr>
				<td><a
					href='<c:url context="/art/exhibitions" value="/${exhibition.formattedTitle}"/>'><c:out
							value="${exhibition.title}" /></a></td>
			</tr>
		</c:forEach>
	</table>

</body>