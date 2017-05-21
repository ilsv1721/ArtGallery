<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<script>
	$(function() {

		$('#regForm').validate({
			onkeyup : false,
			onfocusout : false,
			onclick : false,
			rules : {
				firstName : {
					required : true,
					minlength : 2
				},
				lastName : {
					required : true,
					minlength : 2
				},
				email : {
					required : true,
					email : true,
					remote : {
						type : 'GET',
						url : 'rest/validateUniqueEmail',
					}
				},
				password : {
					required : true,
					minlength : 5
				},
				password_confirm : {
					required : true,
					minlength : 5,
					equalTo : "#password"
				}

			},
			submitHandler : function(form) {
				form.submit();

			}
		});
	});
</script>

<div class="w3-card-4">

	<div class="w3-container w3-cyan ilya-smc w3-center">
		<h2>register</h2>
	</div>

	<sf:form method="POST" modelAttribute="userDetailsDto"
		class="w3-container" id="regForm">
		
		<p>
			<label class="ilay-smc">First Name:</label>
		</p>
		<p>
			<sf:input name="firstName" path="firstName"
				class="w3-input w3-border w3-round" />
		</p>
		<p>
			<label class="ilay-smc">Last name:</label>
		</p>
		<p>
			<sf:input name="lastName" path="lastName"
				class="w3-input w3-border w3-round" />
		</p>
		<p>
			<label class="ilay-smc">Email:</label>
		</p>
		<p>
			<sf:input name="email" path="email" type="email" id="email"
				class="w3-input w3-border w3-round" />
		</p>
		<p>

			<label class="ilay-smc">Password:</label>
			<sf:input name="password" path="password" type="password"
				class="w3-input w3-border w3-round" />
		</p>
		<p>

			<label class="ilay-smc">Repeat password:</label> <input
				id="password_confirm" type="password"
				class="w3-input w3-border w3-round" />
		</p>
		<p>
			<input type="submit" value="Register" class="w3-btn w3-blue-grey">
		</p>
	</sf:form>

	<div class="w3-card-4">

		<div class="w3-container w3-cyan w3-center ilya-smc">
			<h2>Delete role</h2>
		</div>
		<form action="deleteRole" method="post" class="w3-container w3-center"
			id="deleteForm">
			<p>

				<select class="w3-select w3-border" name="role">
					<option value=" " disabled selected>Choose role</option>
					<c:forEach items="${RoleList}" var="roles">
						<option value="${roles.role}">${roles.role}</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<input type="submit" value="Delete Role"
					class="w3-button w3-hover-red" />
			</p>
		</form>
	</div>

</div>