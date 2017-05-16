<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="w3-content w3-display-container"
	style="height: 80%; width: 100%;">

	<div class="w3-display-container w3-center" style="height: 100%;">
		<div class="w3-container w3-center ilya-smc">
			<h2>Ooops...Resource was not found.</h2>
		</div>
		<p>
			<img src=' <c:url context="/art/img" value="/NotFound.jpeg"/>'
				class="w3-image">
		</p>
	</div>
</div>