<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<script>
	var titleiddd = '${exhibEditionDTO.id}';
	var cstok = '${_csrf.token}';
	var cshead = '${_csrf.headerName}';

	$(document).ready(function() {$("#deleteButton").click(
										function() {
											var r = confirm("Are you sure you want to delete this Exhibition?");
											if (r == true) {
												$
														.ajax({
															type : "POST",
															beforeSend : function(
																	request) {
																request
																		.setRequestHeader(
																				cshead,
																				cstok);
															},
															url : "../deletion/del",
															data : {
																srcId : titleiddd,
																srcType : "exhib"
															},
															dataType : "text",
															success : function(
																	result) {
																alert(result);
															},
															error : function(e) {
																console
																		.log(e.responseText);
															}
														});
											} else {

											}

										});

					});
</script>


<div class="w3-card-4">
	<div class="w3-container w3-cyan">
		<h2 class="w3-center" style="font-variant: small-caps;">Edit the
			exhibition</h2>
	</div>

	<sf:form method="POST" modelAttribute="exhibEditionDTO"
		class="w3-container" enctype="multipart/form-data">
		<p>
			<label><b>Id: ${exhibEditionDTO.id} </b></label>
			<sf:hidden class="w3-input w3-border w3-hover-pale-blue" path="id"
				readonly="true" id="titleId" />
		</p>

		<p>
			<label><b>Title</b></label>
			<sf:input class="w3-input w3-border w3-hover-pale-blue" path="title" />
		</p>

		<p>
		<table style="width: 100%">
			<tr>
				<td><label><b>Start year</b></label> <sf:input
						class="w3-input w3-border w3-hover-pale-blue"
						path="startDate.year" /></td>
				<td><label><b>Start month</b></label> <sf:input
						class="w3-input w3-border w3-hover-pale-blue"
						path="startDate.month" /></td>
				<td><label><b>Start day</b></label> <sf:input
						class="w3-input w3-border w3-hover-pale-blue" path="startDate.day" /></td>

			</tr>
		</table>

		<p>
		<table style="width: 100%">
			<tr>
				<td><label><b>End year</b></label> <sf:input
						class="w3-input w3-border w3-hover-pale-blue" path="endDate.year" /></td>
				<td><label><b>End month</b></label> <sf:input
						class="w3-input w3-border w3-hover-pale-blue" path="endDate.month" /></td>
				<td><label><b>End day</b></label> <sf:input
						class="w3-input w3-border w3-hover-pale-blue" path="endDate.day" /></td>

			</tr>
		</table>
		<p>
			<label><b>By (user email)</b></label>
			<sf:input class="w3-input w3-border w3-hover-pale-blue"
				path="user.email" />
		</p>

		<p>
			<label><b>Description</b></label>
			<sf:textarea class="w3-input w3-border w3-hover-pale-blue"
				path="description" />
		</p>

		<p>
		<table style="width: 100%">
			<tr>
				<td style="width: 50%">
					<p>
						<button class="w3-btn w3-cyan" style="width: 100%">Change</button>
					</p>
				</td>
				<td style="width: 50%">
					<p>
						<button type=button class="w3-btn w3-red w3-center"
							style="width: 100%" id="deleteButton">Delete</button>
					</p>
				</td>
			<tr>
		</table>
	</sf:form>
</div>

<script>
	
</script>
