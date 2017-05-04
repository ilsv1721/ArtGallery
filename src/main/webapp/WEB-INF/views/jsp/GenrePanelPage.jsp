<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<script>
	$(function() {

		$('#addGenreForm').validate({
			onkeyup : false,
			onfocusout : false,
			onclick : false,
			rules : {
				genre : {
					required : true,
					minlength : 3
				}
			},
			submitHandler : function(form) {

				$.ajax({
					url : form.action,
					type : form.method,
					data : $(form).serialize(),
					success : function(response) {
						alert(response);
					}
				});
			}
		});

		$('#editGenreForm').validate({
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
				var formData = {}
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

	});
</script>

<script src="<c:url value='/resources/js/ajaxLoadingScript.js' />"></script>

<div class="modalWait"></div>


<div class="w3-container">
	<h1 class="w3-center ilya-smc">Genre Editor</h1>
</div>


<div class="w3-card-4">
	<div class="w3-container w3-cyan w3-center ilya-smc">
		<h2>Add new Genre</h2>
	</div>

	<sf:form action="addGenre" method="post" class="w3-container w3-center"
		id="addGenreForm">
		<p>
			<input type="text" class="w3-input " placeholder="New genre..."
				name="genre" />
		</p>
		<p>
			<input type="submit" value="Add Genre" class="w3-button" />
		</p>
	</sf:form>
</div>


<div class="w3-card-4">

	<div class="w3-container w3-cyan w3-center ilya-smc">
		<h2>Edit Genre</h2>
	</div>
	<form action="editGenre" method="post" id="editGenreForm"
		class="w3-container w3-center" name="genreEditDto">
		<p>

			<select class="w3-select w3-border" name="oldValue">
				<c:forEach items="${GenreList}" var="genres">
					<option value="${genres.genre}">${genres.genre}</option>
				</c:forEach>
			</select>
		</p>
		<p>
			<input type="text" class="w3-input " placeholder="Edit genre..."
				name="newValue" />
		</p>
		<p>
			<input type="submit" value="Edit Genre" class="w3-button" />
		</p>
	</form>
</div>

<div class="w3-card-4">

	<div class="w3-container w3-cyan w3-center ilya-smc">
		<h2>Delete Genre</h2>
	</div>
	<sf:form action="deleteGenre" method="post"
		class="w3-container w3-center">
		<p>

			<select class="w3-select w3-border" name="genre">
				<c:forEach items="${GenreList}" var="genres">
					<option value="${genres.genre}">${genres.genre}</option>
				</c:forEach>
			</select>
		</p>
		<p>
			<input type="submit" value="Delete Genre"
				class="w3-button w3-hover-red" />
		</p>
	</sf:form>
</div>
