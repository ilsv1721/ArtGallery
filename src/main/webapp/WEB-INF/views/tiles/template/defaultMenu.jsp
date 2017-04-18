<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<script>
	function myFunction() {
		var x = document.getElementById("demo");
		if (x.className.indexOf("w3-show") == -1) {
			x.className += " w3-show";
		} else {
			x.className = x.className.replace(" w3-show", "");
		}
	}
</script>

<div class="w3-bar w3-border w3-light-grey ">
	<a href="<c:url value="/"/>" class="w3-bar-item w3-button">Home</a> <a
		href="<c:url value="/exhibitions"/>" class="w3-bar-item w3-button">Exhibitions</a>

	<a href="<c:url value="/news"/>" class="w3-bar-item w3-button">News</a>


	<!-- 
	<div class="w3-dropdown-click w3-right">

		<button class="w3-button " onclick="myFunction()">Login</button>

		<div id="demo" class="w3-dropdown-content w3-bar-block w3-card-2"
			style="right: 0;">
			<sf:form action="login" method="post">
				<table style="border: medium;">
					<tr>
						<td>Username:</td>
						<td><input type="text" name="username" value=""></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type="password" name="password" value=""></td>
					</tr>
					<tr>
						<td>Remember me:</td>
						<td><input type="checkbox" name="remember-me"
							id="remember_me"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" name="submit"
							value="Login" /></td>
					</tr>
				</table>
			</sf:form>
		</div>
	</div> -->

	<security:authorize access="!isAuthenticated()">
		<a href="<c:url value="/login"/>"
			class="w3-bar-item w3-button w3-right">Login</a>
	</security:authorize>
	
	<security:authorize access="isAuthenticated()">
		
		<div class="w3-dropdown-click w3-right">

		<button class="w3-button " onclick="myFunction()">Account</button>

		<div id="demo" class="w3-dropdown-content w3-bar-block w3-card-2 w3-light-grey"
			style="right: 0; z-index: 1000;">
			<a href="<c:url value="/panel"/>"
		class="w3-bar-item w3-button w3-right">Panel</a> 
		<a href="<c:url value="/logout"/>"
		class="w3-bar-item w3-button w3-right">Logout</a> 
		</div>
	</div>
		
	</security:authorize>

	<a href="<c:url value="/register"/>"
		class="w3-bar-item w3-button w3-right">Register</a> <a href="#"
		class="w3-bar-item w3-button w3-light-blue w3-right">Go</a> <input
		type="text" class="w3-bar-item w3-input w3-right"
		placeholder="Search..">

</div>


