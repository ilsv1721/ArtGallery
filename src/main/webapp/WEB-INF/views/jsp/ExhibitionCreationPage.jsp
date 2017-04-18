<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div class="w3-card-4">
	<div class="w3-container w3-cyan">
		<h2 style="font-variant: small-caps;">Announce new exhibition</h2>
	</div>

	<sf:form method="POST" modelAttribute="exhibAnounceDTO"
		class="w3-container" enctype="multipart/form-data">
		<p>
			<label><b>Title</b></label>
			<sf:input class="w3-input w3-border w3-hover-pale-blue" path="title" />
		</p>

		<p>
			<label><b>Start date</b></label>
			<sf:input class="w3-input w3-border w3-hover-pale-blue" path="starts" />
		</p>
		<p>
			<label><b>End date</b></label>
			<sf:input class="w3-input w3-border w3-hover-pale-blue" path="ends" />
		</p>

		<p>
			<label><b>By (user email)</b></label>
			<sf:input class="w3-input w3-border w3-hover-pale-blue"
				path="emailAnouncer" />
		</p>

		<p>
			<label><b>Description</b></label>
			<sf:input class="w3-input w3-border w3-hover-pale-blue"
				path="description" />
		</p>

		<p>
			<label><b>Media</b></label> Pic: <input type="file"
				name="profilePicture" accept="image/jpeg,image/png,image/gif" />
		</p>
		<p>
			<label><b>Media</b></label> Pic: <input type="file"
				name="profilePicture" accept="image/jpeg,image/png,image/gif" />
		</p>
		<p>
			<label><b>Media</b></label> Pic: <input type="file"
				name="profilePicture" accept="image/jpeg,image/png,image/gif" />
		</p>
		<p>
			<label><b>Media</b></label> Pic: <input type="file"
				name="profilePicture" accept="image/jpeg,image/png,image/gif" />
		</p>

		<p>
			<button class="w3-btn w3-cyan">Announce</button>
		</p>
	</sf:form>
</div>

