<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="w3-container w3-center">
	<h2>${status}</h2>

	<h1>Exhibitions</h1>
	<table>
		<c:forEach items="${exhibsList}" var="exhibition">
			<tr>
				<td><a
					href='<c:url context="/art/exhibitions" value="/${exhibition.url}"/>'><c:out
							value="${exhibition.meta}" /></a></td>
			</tr>
		</c:forEach>
	</table>

	<h1>Paintings</h1>
	<table>
		<c:forEach items="${paintsList}" var="paints">
			<tr>
				<td><a
					href='<c:url context="/art/paintings" value="/${paints.url}"/>'><c:out
							value="${paints.meta}" /></a></td>
			</tr>
		</c:forEach>
	</table>
</div>