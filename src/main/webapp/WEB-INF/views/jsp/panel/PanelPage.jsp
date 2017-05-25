<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<p>
	<security:authorize access="isAuthenticated()">
    Authenticated as <b><security:authentication
				property="principal.username" />
	</security:authorize>
	</b>

</p>

<security:authorize access="hasRole('ROLE_USER')">
	<label><b>User panel:</b></label>
	<div class="w3-panel w3-border w3-padding">
		<a href="<c:url context ="/art/panel" value="/userpanel"/>"
			class="w3-button w3-blue">Edit user info</a>
	</div>
</security:authorize>

<p>
	<security:authorize access="hasRole('ROLE_MANAGER')">
		<label><b>Manager panel:</b></label>
		<div class="w3-panel w3-border w3-padding">
			<p>
				<label><b>Exhibition panel:</b></label>
			</p>
			<a href="<c:url context ="/art/panel" value="/exhibcreator"/>"
				class="w3-button w3-blue">New exhibition</a> <a
				href="<c:url context ="/art/panel" value="/chooser?lookfor=exhib"/>"
				class="w3-button w3-blue">Edit exhibition</a>
			<p>
				<label><b>News panel:</b></label>
			</p>
			<a href="<c:url context ="/art/panel" value="/newscreate"/>"
				class="w3-button w3-blue">New News</a> <a
				href="<c:url context ="/art/panel" value="/chooser?lookfor=news"/>"
				class="w3-button w3-blue">Edit News</a>
			<p>
				<label><b>Paint panel:</b></label>
			</p>
			<a href="<c:url context ="/art/panel" value="/paintingnew"/>"
				class="w3-button w3-blue">New paint</a> <a
				href="<c:url context ="/art/panel" value="/paintingedit"/>"
				class="w3-button w3-blue">Edit paint</a>

			<p>
				<label><b>Genre panel:</b></label>
			</p>
			<a href="<c:url context ="/art/panel" value="/genre"/>"
				class="w3-button w3-blue">Genre panel</a>
		</div>

	</security:authorize>
</p>
<p>
	<security:authorize access="hasRole('ROLE_ADMIN')">

		<label><b>Admin panel:</b></label>
		<div class="w3-panel w3-border w3-padding">
			<p>
				<a href="<c:url context ="/art/panel" value="/admin/role"/>"
					class="w3-button w3-blue">Role panel</a>
			</p>
			<p>
				<a href="<c:url context ="/art/panel" value="/admin/roleeditor"/>"
					class="w3-button w3-blue">Edit user role</a>
			</p>


		</div>



	</security:authorize>
</p>