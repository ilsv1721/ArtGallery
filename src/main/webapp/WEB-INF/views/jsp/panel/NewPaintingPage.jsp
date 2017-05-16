<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<script>
	
</script>


<div class="w3-card-4">
	<div class="w3-container w3-cyan">
		<h2 class="w3-center" style="font-variant: small-caps;">New
			Painting</h2>
	</div>

	<sf:form method="POST" modelAttribute="paintingDto"
		class="w3-container" enctype="multipart/form-data">
		<p>
			<label><b>Title</b></label>
			<sf:input class="w3-input w3-border w3-hover-pale-blue" path="title" />
		</p>

		<table style="width: 100%">
			<tr>
				<td><label><b>year</b></label> <sf:input
						class="w3-input w3-border w3-hover-pale-blue"
						path="creationDate.year"/></td>
				<td><label><b>month</b></label> <sf:input
						class="w3-input w3-border w3-hover-pale-blue"
						path="creationDate.month" /></td>
				<td><label><b>day</b></label> <sf:input
						class="w3-input w3-border w3-hover-pale-blue"
						path="creationDate.day" /></td>

			</tr>
		</table>

		<p>
			<label><b>Author (by user email)</b></label>
			<sf:input class="w3-input w3-border w3-hover-pale-blue"
				path="author.email" />
		</p>

		<p>
			<label><b>Description</b></label>
			<sf:textarea class="w3-input w3-border w3-hover-pale-blue"
				path="description" />
		</p>

		<p>
			<label><b>Choose genres</b></label>
			<sf:select multiple="true" path="genresId" items="${genreList}"
				itemLabel="genre" itemValue="id" size="6" class="w3-select w3-border" />
		</p>
		<p>
		<label><b>Choose exhibitions</b></label>
			<sf:select multiple="true" path="exhibitionsId"
				items="${exhibitionList}" itemLabel="title" itemValue="id" size="6"
				class="w3-select w3-border" />
		</p>
		<p>
			<input type="file" name="file" />
		</p>

		<p>
			<button class="w3-btn w3-cyan">Create</button>
		</p>
	</sf:form>
</div>
