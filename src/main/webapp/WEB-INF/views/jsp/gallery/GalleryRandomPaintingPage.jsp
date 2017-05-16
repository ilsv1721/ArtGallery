<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	$(document).ready(
			function() {
				$('#randButton').click(
						function() {
							$("#mainLayout").fadeOut();
							$.ajax({
								type : "GET",
								url : "../rest/randompainting",
								success : function(result) {

									$("#randomPaintingImg").attr("src",
											'/art/servimg/' + result.path);
									$("#mainLayout").fadeIn();
								}
							});
						});

			});
</script>

<div class="w3-content w3-display-container"
	style="height: 80%; width: 100%;" id="mainLayout">
	<p>
	<div class="w3-display-container w3-center mySlides"
		style="height: 100%;">
		<img id="randomPaintingImg"
			src=" <c:url context="/art/servimg" value="/${paintingDto.path}"/>"
			class="w3-image">
		<div
			class="w3-center w3-container w3-section w3-large w3-text-white w3-display-middle"
			style="width: 100%">
			<button class="w3-button w3-display-right w3-black" id="randButton">&#10095;</button>
		</div>
	</div>
	</p>
</div>