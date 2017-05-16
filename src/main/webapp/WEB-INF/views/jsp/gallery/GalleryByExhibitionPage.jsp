<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<script>
	$(document).ready(function() {
		$("#exibitionChooser").change(function() {
			$('#paintingArea').fadeOut();
			var selectValue = $(this).val();
			$.ajax({
				type : "GET",
				url : "../rest/paintingsByExhibition",
				data : {
					"exhibitionId" : selectValue
				},
				success : function(result) {
					$(".deletable").remove();
					$.each(result, function(i, item) {
						$("#paintingArea").append("<div class=\"w3-margin-top w3-center w3-third deletable\"\">"
						        +'<a href="../paintings/'+item.id+'" target="_blank">'
								+"<img src='/art/servimg/"
								+item.path
								+"' class= 'w3-image w3-hover-opacity'"
								+ "style=\"width: 430px;height:180px;\"  //>"
								+"</a>"
								+'<div class="desc" style=" padding: 15px; text-align: center;">'
								+ item.title + '<br> by ' + item.author.firstName + ' ' +item.author.lastName
								+'</div>'
								+"</div>");
						
					});
					$('#paintingArea').fadeIn();
				}
			});
		});
	});
</script>

<script src="<c:url value='/resources/js/ajaxLoadingScript.js' />"></script>

<div class="modalWait"></div>


<div class="w3-card-4">
	<div class="w3-container w3-blue w3-center ilya-smc">
		<p>

			<select class="w3-select w3-border" id="exibitionChooser">
				<option value="" disabled selected>Choose one of the past exhibitions</option>
						<c:forEach items="${exhibitions}" var="exhibs">
						<option value="${exhibs.id}">${exhibs.title}</option>
				</c:forEach>
			</select>
		</p>
	</div>
</div>
<p>
<div class="w3-row-padding w3-margin-top w3-center" id="paintingArea">

</div>
</p>