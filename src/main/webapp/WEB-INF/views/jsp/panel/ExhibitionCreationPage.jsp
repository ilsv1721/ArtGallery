<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<script>
	$(document).ready(function() {

		$('#exhibAn').validate({
			onkeyup : false,
			onfocusout : false,
			onclick : false,
			rules : {
				title : {
					required : true,
					minlength : 2
				},
				description : {
					required : true
				}
			},
			submitHandler : function(form) {
				form.submit();
			}
		});
	});
</script>

<div class="w3-card-4">
	<div class="w3-container w3-cyan">
		<h2 class="w3-center" style="font-variant: small-caps;">Announce
			new exhibition</h2>
	</div>

	<sf:form method="POST" modelAttribute="exhibAnounceDTO"
		class="w3-container" enctype="multipart/form-data" id="exhibAn">
		<p>
			<label><b>Title</b></label> <input
				class="w3-input w3-border w3-hover-pale-blue" name="title" />
		</p>


		<table style="width: 100%">
			<tr>
				<td><label><b>Start year</b></label> <input
					class="w3-input w3-border w3-hover-pale-blue" name="startDate.year"
					type="number" /></td>
				<td><label><b>Start month</b></label> <input
					class="w3-input w3-border w3-hover-pale-blue"
					name="startDate.month" type="number" /></td>
				<td><label><b>Start day</b></label> <input
					class="w3-input w3-border w3-hover-pale-blue" name="startDate.day"
					type="number" /></td>

			</tr>
		</table>


		<table style="width: 100%">
			<tr>
				<td><label><b>End year</b></label> <input
					class="w3-input w3-border w3-hover-pale-blue" name="endDate.year"
					type="number" /></td>
				<td><label><b>End month</b></label> <input
					class="w3-input w3-border w3-hover-pale-blue" name="endDate.month"
					type="number" /></td>
				<td><label><b>End day</b></label> <input
					class="w3-input w3-border w3-hover-pale-blue" name="endDate.day"
					type="number" /></td>

			</tr>
		</table>

		<p>
			<label><b>Description</b></label>
			<sf:textarea class="w3-input w3-border w3-hover-pale-blue"
				path="description"></sf:textarea>
		</p>
		<p>
			<button class="w3-btn w3-cyan">Announce</button>
		</p>
	</sf:form>
</div>

