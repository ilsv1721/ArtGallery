<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="w3-container w3-center">

	<h1 class="exhibtitle">${paint.title}</h1>
	<h2 class="exhibtitle">by ${paint.author.firstName}
		${paint.author.lastName}</h2>
	<p>(email: ${paint.author.email})</p>

	<div class="w3-content w3-display-container">
		<img src="<c:url value="/${paint.path}" context="/art/servimg"/>"
			class="w3-image" />
	</div>
	<p>
	<div>
		Genres:
		<c:forEach items="${genres}" var="genres">
			<span>${genres.genre}</span>
		</c:forEach>
	</div>
	</p>
	<table style="width: 100%;">

		<tr>
		</tr>


		<tr>
			<td style="text-align: center; padding: 20px;">Creation date:
				${paint.creationDate.day}.${paint.creationDate.month}.${paint.creationDate.year}

			</td>
		</tr>


		<tr>
			<td>
				<h2 class="exhibtitle">Description</h2> <br>${paint.description}</td>
		</tr>


	</table>
	</p>
</div>