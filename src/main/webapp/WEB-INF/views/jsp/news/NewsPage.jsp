<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<table class="w3-table w3-bordered w3-striped">
	<c:forEach items="${newsList}" var="news">
		<tr>
			<td style="width: 33%"><div class="w3-left-align">${news.publishedDate}</div></td>
			<td style="width: 33%"><div class="w3-center">${news.title}</div></td>
			<td style="width: 33%"><div class="w3-right-align">${news.author.firstName}
					${news.author.lastName}</div></td>
		</tr>
		<tr>
			<td colspan="3">${news.text}</td>
		</tr>
	</c:forEach>
</table>