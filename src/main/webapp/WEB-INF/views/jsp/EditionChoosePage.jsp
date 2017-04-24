<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>



	<div class="w3-container">
		<h2 class="w3-center" style="font-variant: small-caps;">Choose an
			entity to edit</h2>

		<input class="w3-input w3-border w3-padding" type="text"
			placeholder="Search for titles.." id="myInput" onkeyup="search()">

		<table class="w3-table-all" id="titlesTable">
			<c:forEach items="${srcList}" var="srcs">
				<tr>
					<td><a
						href='<c:url context ="/art/panel/${srcType}" value="/${srcs.formatted}"/>'><c:out
								value="${srcs.raw}" /></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<script>
		function search() {
			var input, filter, table, tr, td, i;
			input = document.getElementById("myInput");
			filter = input.value.toUpperCase();
			table = document.getElementById("titlesTable");
			tr = table.getElementsByTagName("tr");
			for (i = 0; i < tr.length; i++) {
				td = tr[i].getElementsByTagName("td")[0];
				if (td) {
					if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
						tr[i].style.display = "";
					} else {
						tr[i].style.display = "none";
					}
				}
			}
		}
	</script>

</body>