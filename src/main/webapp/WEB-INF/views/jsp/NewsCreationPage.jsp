<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>



<div class="w3-card-4">
	<div class="w3-container w3-cyan">
		<h2 class="w3-center" style="font-variant: small-caps;">Announce
			news</h2>
	</div>

	<sf:form method="POST" modelAttribute="NewsDto" class="w3-container"
		enctype="multipart/form-data">
		<p>
			<label><b>Title</b></label> <input
				class="w3-input w3-border w3-hover-pale-blue" name="title" />
		</p>

		<p>
	
		<label><b>By (user email)</b></label>
		<input class="w3-input w3-border w3-hover-pale-blue" name="keyAuthor" />
		</p>

		<p>
			<label><b>Text</b></label> <input
				class="w3-input w3-border w3-hover-pale-blue" name="text" />
		</p>

		<p>
			<button class="w3-btn w3-cyan">Announce</button>
		</p>
	</sf:form>


</div>
