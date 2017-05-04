<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="w3-content w3-display-container"
	style="height: 80%; width: 100%;">

	<div class="w3-display-container mySlides" style="height: 100%;">
		<img
			src=' <c:url context="/art/servimg" value="${paintingDto.path}"/>' class="w3-image">
		<div
			class="w3-center w3-container w3-section w3-large w3-text-white w3-display-middle"
			style="width: 100%">
			<button class="w3-button w3-display-left w3-black"
				onclick="plusDivs(-1)">&#10094;</button>
			<button class="w3-button w3-display-right w3-black"
				onclick="plusDivs(1)">&#10095;</button>
		</div>



	</div>
	</div>