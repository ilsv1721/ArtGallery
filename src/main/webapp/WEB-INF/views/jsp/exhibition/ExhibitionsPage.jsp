<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	function opentab(evt, tabName) {
		var i, x, tablinks;
		x = document.getElementsByClassName("extab");
		for (i = 0; i < x.length; i++) {
			x[i].style.display = "none";
		}
		tablinks = document.getElementsByClassName("tablink");
		for (i = 0; i < x.length; i++) {
			tablinks[i].className = tablinks[i].className.replace(
					"w3-border-blue", "");
		}
		document.getElementById(tabName).style.display = "block";
		evt.currentTarget.firstElementChild.className += " w3-border-blue";
	};
	</script>

<div class="w3-container w3-center">
	<h2>Check out our exhibitions!</h2>

	<div class="w3-row">
		<a href="javascript:void(0)" onclick="opentab(event, 'Past');">
			<div
				class="w3-third tablink w3-bottombar w3-hover-light-grey w3-padding">Past(click me)</div>
		</a> <a href="javascript:void(0)" onclick="opentab(event, 'Current');">
			<div
				class="w3-third tablink w3-bottombar w3-hover-light-grey w3-padding">Current</div>
		</a> <a href="javascript:void(0)" onclick="opentab(event, 'Future');">
			<div
				class="w3-third tablink w3-bottombar w3-hover-light-grey w3-padding">Future</div>
		</a>
	</div>
	

	<div id="Past" class="w3-container extab" style="display: none">
		<table>
			<c:forEach items="${pastExhibitions}" var="exhibition">
				<tr>
					<td><a
						href='<c:url context="/art/exhibitions" value="/${exhibition.url}"/>'><c:out
								value="${exhibition.meta}" /></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<div id="Current" class="w3-container extab w3-center"
		style="display: none">
		<table>
			<c:forEach items="${currentExhibitions}" var="exhibition">
				<tr>
					<td><a
						href='<c:url context="/art/exhibitions" value="/${exhibition.url}"/>'><c:out
								value="${exhibition.meta}" /></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<div id="Future" class="w3-container extab" style="display: none">
		<table>
			<c:forEach items="${futureExhibitions}" var="exhibition">
				<tr>
					<td><a
						href='<c:url context="/art/exhibitions" value="/${exhibition.url}"/>'><c:out
								value="${exhibition.meta}" /></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>



