<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-toggleable-md navbar-light bg-faded"
	style="background-color: #grey;">

	<button class="navbar-toggler navbar-toggler-right" type="button"
		data-toggle="collapse" data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">

			<li class="nav-item"><a class="nav-link" style="color: black;"
				href="<c:url value="/"/>">Home</a></li>


			<li class="nav-item"><a class="nav-link" style="color: black;"
				href="<c:url value="/exhibitions"/>">Exhibitions</a></li>
		</ul>
		<form class="form-inline my-2 my-lg-0"
			action="<c:url value="/search"/>">
			<input class="form-control mr-sm-2" type="text"
				placeholder="Search painting">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		</form>

		<ul class="nav navbar-nav navbar-right">

			<li class="nav-item"><a class="nav-link" style="color: black;"
				href="<c:url value="/register"/>">Register</a></li>

			<li class="nav-item"><a class="nav-link" style="color: black;"
				href="<c:url value="/login"/>">Login</a></li>

		</ul>
	</div>
</nav>