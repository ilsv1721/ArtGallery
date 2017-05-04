<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<security:authorize access="hasRole('ROLE_MANAGER')">
	<a href="<c:url context ="/art/panel" value="/exhibcreator"/>"
		class="w3-button">New exhibition</a>

	<a href="<c:url context ="/art/panel" value="/chooser?lookfor=exhib"/>"
		class="w3-button">Edit exhibition</a>

	<a href="<c:url context ="/art/panel" value="/newscreate"/>"
		class="w3-button">New News</a>

	<a href="<c:url context ="/art/panel" value="/chooser?lookfor=news"/>"
		class="w3-button">Edit News</a>

	<a href="<c:url context ="/art/panel" value="/genre"/>"
		class="w3-button">Genre panel</a>


</security:authorize>

<security:authorize access="hasRole('ROLE_ADMIN')">
	<a href="<c:url context ="/art/panel" value="/exhibcreator"/>"
		class="w3-button">Edit user data</a>
</security:authorize>