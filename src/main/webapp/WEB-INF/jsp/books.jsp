<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>

<html>
<head>
	<title>Home</title>
	
	<script>
    function handleSearch(){
    	alert("1");
        document.bookmainform.action="searchBook";
        document.bookmainform.submit();
     }
</script>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <br><br>
<h1>
	Books Catalog  
</h1>
<form:form class="form-horizontal"  name="bookmainform" method="post" action="deleteBook" modelAttribute="bookOps">
<form:input class="form-control" type="text" placeholder="Search Book By ID" aria-label="Search" path="searchBookId"  />
<br/>
<form:input class="form-control" type="text" placeholder="Search Book By Title" aria-label="Search" path="searchBookTitle"  />
<br/>
<table class="table table-bordered table-striped table-hover table-condensed">
   <thead>
    <tr>
    	<th>CHK</th>
        <th> Id </th>
        <th> Title</th>
        <th> Price</th>
        <th> Volume</th>
        <th>Publish Date</th>
    </tr>
  </thead>
  <tbody>
   <c:forEach items="${lstBooks}" var="book">
        <tr>
          <td><form:checkbox path="delBookIds" value="${book.bookId}" /></td>
          <td><c:out value="${book.bookId}" /></td>
          <td><c:out value="${book.title}" /></td>
          <td><c:out value="${book.price}" /></td>
          <td><c:out value="${book.volume}" /></td>
          <td><c:out value="${book.publishDate}" /></td>
        </tr>
      </c:forEach>
   </tbody>
</table>
<div class="form-actions">
<center>
    <button type="Button" class="btn"><a href="addbookform" target="_top">Add Book</a></button>
     <input type="submit" value="Delete" name="Delete"/>
      <button type="Button" class="btn" onclick="javascript:handleSearch();">Search Book</button>
    </center>
</div>

</form:form>
</body>
</html>
