<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>

<html>
<head>
	<title>Home</title>
	
	<script>
    function handleSearch(){
        document.subjectmainform.action="searchSubject";
        document.subjectmainform.submit();
     }
</script>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <br><br>
<h1>
	Subject Catalog  
</h1>
<form:form class="form-horizontal"  name="subjectmainform" method="post" action="deleteSubject" modelAttribute="subjectOps">
<br/>
<form:input class="form-control" type="text" placeholder="Search By Subject Id" aria-label="Search" path="searchSubjId" />
<br/>
<form:input class="form-control" type="text" placeholder="Search By Duration" aria-label="Search" path="searchDuration" />
<br/>
<table class="table table-bordered table-striped table-hover table-condensed">
   <thead>
    <tr>
    	<th>CHK</th>
        <th> SubjectId </th>
        <th> Title</th>
        <th> Duration (hours)</th>
        <th> Reference Book</th>
    </tr>
  </thead>
  <tbody>
   <c:forEach items="${lstSubjects}" var="subject">
        <tr>
          <td><form:checkbox path="delSubjIds" value="${subject.subjectId}" /></td>
          <td><c:out value="${subject.subjectId}" /></td>
          <td><c:out value="${subject.subtitle}" /></td>
          <td><c:out value="${subject.durationInHours}" /></td>
          <c:forEach items="${subject.references}" var="bookRef">
          <td><c:out value="${bookRef.title}" /></td>
          </c:forEach>
        </tr>
      </c:forEach>
   </tbody>
</table>
<div class="form-actions">
<center>
    <button type="Button" class="btn"><a href="addSubjectform" target="_top">Add Subject</a></button>
     <input type="submit" value="Delete" name="Delete"/>
      <button type="Button" class="btn" onclick="javascript:handleSearch();">Search Subject</button>
    </center>
</div>

</form:form>
</body>
</html>
