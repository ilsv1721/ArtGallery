<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<script>
	$(document).ready(
			function() {
				$("#exibitionChooser").val(" ");
				var paintings;
				$("#exibitionChooser").change(
						function() {
							$("#paintingChooserPanel").fadeOut();
							$("#formArea").fadeOut();
							$(".dels").remove();
							var selectValue = $(this).val();
							$.ajax({
								type : "GET",
								url : "../rest/paintingsByExhibition",
								data : {
									"exhibitionId" : selectValue
								},
								success : function(result) {
									paintings = result;
									$.each(result, function(i, item) {
										$("#paintingSelect").append(
												'<option class="dels" value="'+i+'">'
														+ item.title
														+ '</option>');
									});
									$("#paintingChooserPanel").fadeIn();
								}
							});
						});

				$("#paintingSelect").change(
						function() {
							$("#formArea").fadeOut();
							var selectValue = $(this).val();
							$("#title").val(paintings[selectValue].title);
							$("#id").val(paintings[selectValue].id);
							$("#day").val(
									paintings[selectValue].creationDate.day);
							$("#year").val(
									paintings[selectValue].creationDate.year);
							$("#month").val(
									paintings[selectValue].creationDate.month);
							$("#authorEmail").val(
									paintings[selectValue].authorEmail);
							$("#description").val(
									paintings[selectValue].description);
							$("#authoremail").val(
									paintings[selectValue].author.email);
							$("#paintimage").attr(
									"src",
									'/art/servimg/'
											+ paintings[selectValue].path);
							
							$("#formArea").fadeIn();

						});

				$('#deleteButton').click(function() {
					$.ajax({
						type : "POST",
						url : "paintingdelete",
						headers : {
							"${_csrf.headerName}" : "${_csrf.token}"
						},
						data : {
							"paintId" : function() {
								return $('#id').val();
							},
							"exhibitionId" : function() {
								return $('#exibitionChooser').val();
							}
						},
						success : function(result) {
							$("#formArea").fadeOut();
							$("#paintingChooserPanel").fadeOut();
							$("#exibitionChooser").val(" ");

						}
					});
				});

				$('#paintEditFrom').validate({
					onkeyup : false,
					onfocusout : false,
					onclick : false,
					rules : {
						title : {
							required : true,
							minlength : 2
						},
						description : {
							required : true,
							minlength : 2
						},
						day : {
							required : true,
							number : true
						},
						year : {
							required : true,
							number : true
						},
						month : {
							required : true,
							number : true
						},
						authorEmail : {
							required : true,
							email : true,
							remote : {
								type : 'GET',
								url : '../rest/validateExistEmail',
								data : {
									email : function() {
										return $('#authorEmail').val();
									}
								}
							}
						},
						genresId : {
							required : true
						},
						exhibitionsId : {
							required : true
						}

					},
					submitHandler : function(form) {
						form.submit();
					}
				});

			});
</script>

<script src="<c:url value='/resources/js/ajaxLoadingScript.js' />"></script>

<div class="modalWait"></div>

<p>
<div class="w3-card-4">
	<div class="w3-container w3-cyan w3-center ilya-smc">
		<p>

			<select class="w3-select w3-border" id="exibitionChooser">
				<option value=" " disabled selected>Choose exhibition</option>
				<c:forEach items="${exhibitions}" var="exhibs">
					<option value="${exhibs.id}">${exhibs.title}</option>
				</c:forEach>
			</select>
		</p>
	</div>
</div>
</p>
<p>
<div class="w3-card-4">
	<div class="w3-container w3-cyan w3-center ilya-smc"
		id="paintingChooserPanel" style="display: none;">
		<p>
			<select class="w3-select w3-border" id="paintingSelect">
				<option value="" disabled selected>Choose painting</option>
			</select>
		</p>
	</div>
</div>
</p>

<div class="w3-content w3-display-container w3-center " id="formArea"
	style="height: 80%; width: 100%; display: none;">

	<p>
		<button type="button" class="w3-btn w3-red" id="deleteButton">Delete</button>
	</p>

	<sf:form method="POST" class="w3-container "
		modelAttribute="paintingEditDto" id="paintEditFrom">

		<p>
			<img src="" class="w3-image" id="paintimage">
		</p>

		<p>
			<label class="ilay-smc"><b>Id:</b></label>
		</p>
		<p>
			<sf:input path="id" class="w3-input w3-border w3-round"
				readonly="true" />
		</p>

		<p>
			<label class="ilay-smc"><b>Title</b>:</label>
		</p>

		<p>
			<sf:input path="title" class="w3-input w3-border w3-round" />
		</p>
		<p>
			<label class="ilay-smc"><b>Author email:</b>:</label>
		</p>

		<p>
			<sf:input path="author.email" id="authoremail" class="w3-input w3-border w3-round" />
		</p>

		<p>
			<label class="ilay-smc"><b>Description:</b></label>
		</p>

		<p>
			<sf:textarea path="description" class="w3-input w3-border w3-round" />
		</p>

		<p>
		<table style="width: 100%">
			<tr>
				<td><label><b>Year</b></label> <sf:input
						class="w3-input w3-border w3-hover-pale-blue"
						path="creationDate.year" id="year" /></td>
				<td><label><b>Month</b></label> <sf:input
						class="w3-input w3-border w3-hover-pale-blue"
						path="creationDate.month" id="month" /></td>
				<td><label><b>Day</b></label> <sf:input
						class="w3-input w3-border w3-hover-pale-blue"
						path="creationDate.day" id="day" /></td>

			</tr>
		</table>
		</p>

		<p>
			<label><b>Choose genres</b></label>
			<sf:select multiple="true" path="genresId" items="${genreList}"
				itemLabel="genre" itemValue="id" size="6"
				class="w3-select w3-border" />
		</p>
		<p>
			<label><b>Choose exhibitions</b></label>
			<sf:select multiple="true" path="exhibitionsId"
				items="${exhibitions}" itemLabel="title" itemValue="id" size="6"
				class="w3-select w3-border" />
		</p>

		<p>
			<input type="submit" value="Change" class="w3-btn w3-blue-grey">
		</p>
	</sf:form>

</div>