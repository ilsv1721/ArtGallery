<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<script>
	$(function() {

		$('#userEmailRoleForm').validate(
				{
					onkeyup : false,
					onfocusout : false,
					onclick : false,
					rules : {
						email : {
							required : true,
							email : true,
							remote : {
								type : 'GET',
								url : '../../rest/validateExistEmail'
							}
						}
					},
					submitHandler : function(form) {
						$('#hiddenArea').fadeOut();
						$(".dels").remove();
						var mail = $('#email').val();
						$.ajax({
							url : form.action,
							type : form.method,
							data : {
								userEmail : mail
							},
							success : function(response) {
								$.each(response, function(i, item) {
									$("#curroles").append(
											'<div class="dels">' + item.role
													+ ' </div>');
								});
								$('#userEmail').val(mail);
								$('#hiddenArea').fadeIn();
							}
						});

					}
				});

		$('#changeUserRolesForm').validate({
			onkeyup : false,
			onfocusout : false,
			onclick : false,
			rules : {
				newRoles : {
					required : true
				}
			},
			submitHandler : function(form) {
				var formData = {};
				$.each(form, function(i, v) {
					var input = $(v);
					formData[input.attr("name")] = input.val();
				});

				$.ajax({
					url : form.action,
					type : form.method,
					headers : {
						"${_csrf.headerName}" : "${_csrf.token}"
					},
					contentType : "application/json",
					data : JSON.stringify(formData),
					success : function(response) {
						$('#hiddenArea').fadeOut();
					}
				});

			}
		});

	});
</script>

<script src="<c:url value='/resources/js/ajaxLoadingScript.js' />"></script>

<div class="modalWait"></div>


<div class="w3-container">
	<h1 class="w3-center ilya-smc">User Role Editor</h1>
</div>

<div class="w3-container w3-card-4">

	<form action="rolesofuser" method="get" id="userEmailRoleForm">
		<p>
			<input type="email" name="email" id="email" class="w3-input" />
		</p>
		<p>
			<input type="submit" value="Get user info"
				class="w3-btn w3-blue-grey" />
		</p>
	</form>
</div>
<p></p>
<div class="w3-container w3-card-4" id="hiddenArea"
	style="display: none;">
	<form method="post" id="changeUserRolesForm">
		<p>
			<label><b>User email: </b></label>
		</p>
		<p>
			<input id="userEmail" name="userEmail" type="text"
				readonly="readonly" />
		</p>
		<p id="curroles">
			<b>Current roles :</b>
		</p>

		<p>
			<label><b>Choose new roles: </b></label>
		</p>
		<p>
			<select multiple required size="3" class="w3-select w3-border"
				name="newRoles" id="newRoles">
				<c:forEach items="${allRoles}" var="role">
					<option value="${role.id}">${role.role}</option>
				</c:forEach>
			</select>
		</p>
		<p>
			<input type="submit" value="Change" class="w3-btn w3-blue-grey" />
		</p>
	</form>
</div>