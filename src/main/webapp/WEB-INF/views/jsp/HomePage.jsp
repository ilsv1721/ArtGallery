<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href='<c:url value="/css/utils/utilFormError.css"/>'>
<title>Gallery</title>
</head>
<body>

	<div class="w3-content w3-display-container"
		style="max-height: 70%; width: 100%;">

		<div class="w3-display-container mySlides">
			<img
				src="https://wallpaperscraft.com/image/triangle_light_blurred_88541_1366x768.jpg"
				style="width: 100%">
			<div
				class="w3-display-topleft w3-large w3-container w3-padding-16 w3-black"
				style="font-variant: small-caps;">Find out about our
				exhibitions</div>
		</div>

		<div class="w3-display-container mySlides">
			<img
				src="http://www.mrwallpaper.com/wallpapers/Winter-Lake-1366x768.jpg"
				style="width: 100%">
			<div
				class="w3-display-topmiddle w3-large w3-container w3-padding-16 w3-black style="font-variant:small-caps;">
				Visit our web-gallery</div>
		</div>

		<div
			class="w3-center w3-container w3-section w3-large w3-text-white w3-display-middle"
			style="width: 100%">
			<button class="w3-button w3-display-left w3-black"
				onclick="plusDivs(-1)">&#10094;</button>
			<button class="w3-button w3-display-right w3-black"
				onclick="plusDivs(1)">&#10095;</button>
		</div>



	</div>

	<script>
		var myIndex = 0;
		var times123;
		carousel();

		function plusDivs(n) {
			clearTimeout(times123);
			showDivs(myIndex += n);
		}

		function showDivs(n) {
			var i;
			var x = document.getElementsByClassName("mySlides");
			if (n > x.length) {
				myIndex = 1
			}
			if (n < 1) {
				myIndex = x.length
			}
			for (i = 0; i < x.length; i++) {
				x[i].style.display = "none";
			}
			x[myIndex - 1].style.display = "block";
			times123 = setTimeout(carousel, 10000);
		}

		function carousel() {
			var i;
			var x = document.getElementsByClassName("mySlides");
			for (i = 0; i < x.length; i++) {
				x[i].style.display = "none";
			}
			myIndex++;
			if (myIndex > x.length) {
				myIndex = 1
			}
			x[myIndex - 1].style.display = "block";
			times123 = setTimeout(carousel, 5000);
		}
	</script>

</body>