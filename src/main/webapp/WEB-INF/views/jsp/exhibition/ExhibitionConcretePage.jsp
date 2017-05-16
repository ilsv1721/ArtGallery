<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	$(function() {
		var slideIndex = 1;

	});

	showDivs(slideIndex);

	function plusDivs(n) {
		showDivs(slideIndex += n);
	}

	function showDivs(n) {
		var i;
		var x = document.getElementsByClassName("mySlides");
		if (n > x.length) {
			slideIndex = 1
		}
		if (n < 1) {
			slideIndex = x.length
		}
		for (i = 0; i < x.length; i++) {
			x[i].style.display = "none";
		}
		x[slideIndex - 1].style.display = "block";
	}
</script>

<div class="w3-container w3-center">

	<h1 class="exhibtitle">${exhibition.title}</h1>
	<h2 class="exhibtitle">by ${exhibition.user.firstName}
		${exhibition.user.lastName}</h2>
	<p>(email: ${exhibition.user.email})</p>
	<c:if test="${not empty exhibition.paints}">
		<div class="w3-content w3-display-container"
			style="height: 80%; width: 100%;">
			<c:forEach items="${exhibition.paints}" var="mediaImage">
				<img
					src="<c:url value="/${mediaImage.path}" context="/art/servimg"/>"
					class="mySlides w3-image" />
			</c:forEach>
			<button class="w3-button w3-display-left w3-black"
				onclick="plusDivs(-1)">&#10094;</button>
			<button class="w3-button w3-display-right w3-black"
				onclick="plusDivs(1)">&#10095;</button>
		</div>
	</c:if>

	<script>
		var slideIndex = 1;
		showDivs(slideIndex);
	</script>

	<p>
	<table style="width: 100%;">

		<tr>
		</tr>


		<tr>
			<td style="text-align: center; padding: 20px;">Start Date:
				${exhibition.startDate.day}.${exhibition.startDate.month}.${exhibition.startDate.year}
				<br> End Date:
				${exhibition.endDate.day}.${exhibition.endDate.month}.${exhibition.endDate.year}
			</td>
		</tr>


		<tr>
			<td>
				<h2 class="exhibtitle">Description</h2> <br>${exhibition.description}</td>
		</tr>


	</table>
	</p>
</div>