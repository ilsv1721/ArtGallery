<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<script src="<c:url value='/resources/js/ajaxLoadingScript.js' />"></script>

<script>
	$(function() {

		$('#cahngeForm').validate({
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
						url : '../rest/validateUniqueEmail',
					}
				}
			},
			submitHandler : function(form) {
				form.submit();
			}
		});

		$('#cahngePassForm').validate({
			onkeyup : false,
			onfocusout : false,
			onclick : false,
			rules : {
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
				var formData = {};
			
				$.ajax({
					url : form.action,
					type : form.method,
					headers : {
						'${_csrf.headerName}' : '${_csrf.token}',
					},
     				data : {
     					userId: '${user.id}',
     					password: function() {
							return $('#password').val();
						}
     				},
					success : function(response) {
						alert(response);
					}
				});
			}
		});

	});
</script>

<div class="modalWait"></div>


<div class="w3-container">
	<h1 class="w3-center ilya-smc">User info editor</h1>
</div>

<div class="w3-card-4">
	<div class="w3-container w3-cyan w3-center ilya-smc">
		<h2>Change your info</h2>
	</div>

	<sf:form method="post" class="w3-container" id="cahngeForm"
		modelAttribute="user">

		<p>
			<label for="Id"> <b>Id:</b></label>
			<sf:input class="w3-input" path="id" readonly="true" />
		</p>
		<p>
			<label for="firstName"> <b>First name:</b></label>
			<sf:input class="w3-input " path="firstName" />
		</p>
		<p>
			<label for="lastName"> <b>Last name:</b></label>
			<sf:input class="w3-input " path="lastName" />
		</p>
		<p>
			<input type="submit" value="Change" class="w3-btn w3-blue-grey">
		</p>
	</sf:form>
</div>

<div class="w3-card-4">
	<div class="w3-container w3-cyan w3-center ilya-smc">
		<h2>Change password</h2>
	</div>

	<form method="post" class="w3-container" id="cahngePassForm"
		action="<c:url value="userpanel/editpass"/>">
		<p>
			<label for="Id"> <b>Id:</b></label> <input class="w3-input" readonly
				value="${user.id}" name="userId" />
		</p>
		<p>
			<label for="email"> <b>New pass:</b></label> <input type="password"
				class="w3-input " name="password" id="password" />
		</p>
		<p>
			<label for="email"> <b>Repeat new pass:</b></label> <input
				type="password" class="w3-input" name="password_confirm" />
		</p>
		<p>
			<input type="submit" value="Change" class="w3-btn w3-blue-grey">
		</p>
	</form>
</div>
