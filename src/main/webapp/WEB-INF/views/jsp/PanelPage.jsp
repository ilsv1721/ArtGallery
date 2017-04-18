<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<security:authorize access="hasRole('ROLE_MANAGER')">
	<a href="<c:url value="panel/exhibcreator"/>" class="w3-button">New
		exhibition</a>
</security:authorize>