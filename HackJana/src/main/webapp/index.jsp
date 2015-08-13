<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
	<title>SpringMVC test</title>
</head>

<body>
	<h3>SpringMVC-Tiles-Integration</h3>

	<spring:url value="page1.htm" var="pg1" htmlEscape="true" />
	<a href="${pg1}">Page 1 (with the template content)</a><br>
	
	<spring:url value="page2.htm" var="pg2" htmlEscape="true" />
	<a href="${pg2}">Page 2 (with a custom content)</a>

</body>
</html>
