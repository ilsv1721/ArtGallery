<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

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
			<label><b>Genre and Role panel:</b></label>
		</p>
		<a href="<c:url context ="/art/panel" value="/genre"/>"
			class="w3-button w3-blue">Genre panel</a> <a
			href="<c:url context ="/art/panel" value="/role"/>" class="w3-button w3-blue">Role
			panel</a>
	</div>

</security:authorize>

<security:authorize access="hasRole('ROLE_ADMIN')">
	<a href="<c:url context ="/art/panel" value="/exhibcreator"/>"
		class="w3-button">Edit user data</a>
</security:authorize>