<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	var slideIndex = 1;
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


<h1 class="exhibtitle">${exhibition.title}
	<span>by ${exhibition.announcedBy.firstName}
		${exhibition.announcedBy.lastName}</span>
</h1>

<table style="width: 100%;">

	<tr>
		<td><div class="w3-content w3-display-container"
				style="max-width: 900px;">
				<c:forEach items="${exhibition.exhibitionImages}" var="mediaImage">
					<img
						src="<c:url value="/${mediaImage.path}" context="/art/servimg"/>"
						class="mySlides w3-image" />
				</c:forEach>
				<button class="w3-button w3-display-left w3-black"
					onclick="plusDivs(-1)">&#10094;</button>
				<button class="w3-button w3-display-right w3-black"
					onclick="plusDivs(1)">&#10095;</button>

			</div> <script>
				var slideIndex = 1;
				showDivs(slideIndex);
			</script></td>
	</tr>


	<tr>
		<td style="text-align: center; padding: 20px;">Start Date:
			${exhibition.starts} <br> End Date: ${exhibition.ends}

		</td>
	</tr>


	<tr>
		<td>
			<h2 class="exhibtitle">Description</h2> <br>${exhibition.description}</td>
	</tr>


</table>