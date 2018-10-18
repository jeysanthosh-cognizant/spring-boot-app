<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <br><br>
<h1>
	Hello User!  
</h1>

<P>  ${pageContext.request.userPrincipal.name} logged in </P>
	  <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />

</body>
</html>
