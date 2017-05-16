<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<script>
	$(function() {

		$('#addForm').validate({
			onkeyup : false,
			onfocusout : false,
			onclick : false,
			rules : {
				role : {
					required : true,
					minlength : 3
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
						'${_csrf.headerName}' : '${_csrf.token}',
					},
					contentType : "application/json",
					data : JSON.stringify(formData),
					success : function(response) {
						alert(response);
					}
				});
			}
		});

		$('#editForm').validate({
			onkeyup : false,
			onfocusout : false,
			onclick : false,
			rules : {
				newValue : {
					required : true,
					minlength : 3
				}
			},
			submitHandler : function(form) {
				var formData = {};
				$.each(form, function(i, v) {
					var input = $(v);
					formData[input.attr("name")] = input.val();
				});
				$.ajax({
					headers : {
						'${_csrf.headerName}' : '${_csrf.token}',
					},
					url : form.action,
					type : form.method,
					contentType : "application/json",
					data : JSON.stringify(formData),
					success : function(response) {
						alert(response);
					}
				});
			}
		});

		$('#deleteForm').validate({
			onkeyup : false,
			onfocusout : false,
			onclick : false,
			rules : {
				role : {
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
						'${_csrf.headerName}' : '${_csrf.token}',
					},
					contentType : "application/json",
					data : JSON.stringify(formData),
					success : function(response) {
						alert(response);
					}
				});
			}
		});

	});
</script>

<script src="<c:url value='/resources/js/ajaxLoadingScript.js' />"></script>

<div class="modalWait"></div>


<div class="w3-container">
	<h1 class="w3-center ilya-smc">Roles Editor</h1>
</div>


<div class="w3-card-4">
	<div class="w3-container w3-cyan w3-center ilya-smc">
		<h2>Add new role</h2>
	</div>

	<form action="addRole" method="post" class="w3-container w3-center"
		id="addForm">
		<p>
			<input type="text" class="w3-input " placeholder="New role..."
				name="role" />
		</p>
		<p>
			<input type="submit" value="Add Role" class="w3-button" />
		</p>
	</form>
</div>


<div class="w3-card-4">

	<div class="w3-container w3-cyan w3-center ilya-smc">
		<h2>Edit role</h2>
	</div>
	<form action="editRole" method="post" id="editForm"
		class="w3-container w3-center" name="roleEditDto">
		<p>

			<select class="w3-select w3-border" name="role">
				<option value=" " disabled selected>Choose role</option>
				<c:forEach items="${RoleList}" var="roles">
					<option value="${roles.role}">${roles.role}</option>
				</c:forEach>
			</select>
		</p>
		<p>
			<input type="text" class="w3-input " placeholder="Edit role..."
				name="newValue" />
		</p>
		<p>
			<input type="submit" value="Edit Role" class="w3-button" />
		</p>
	</form>
</div>

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
