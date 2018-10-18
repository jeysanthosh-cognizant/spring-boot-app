<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>
<html>
<head>
	<title>Add Book</title>
	     <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Contact Me</title>
     <script src="https://code.jquery.com/jquery-3.3.1.slim.js"></script>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <br><br>
<h1>
	Add Book 
</h1>
<form:form class="form-horizontal"  method="POST" action="addBook"
  modelAttribute="book">
  <div class="form-group">
    <form:label path="bookId" class="control-label col-sm-2">BookId</form:label>
    <div class="col-sm-10"> 
    <form:input path="bookId"  class="form-control" />
    </div>
    </div>
     
     <div class="form-group">
    <form:label path="title" class="control-label col-sm-2">Title</form:label>
    <div class="col-sm-10"> 
    <form:input path="title" class="form-control"/>
    </div>
    </div>
    
    <div class="form-group">
       <form:label path="price" class="control-label col-sm-2">Price</form:label>
       <div class="col-sm-10"> 
    <form:input path="price" class="form-control"/>
    </div>
    </div>
    
    <div class="form-group">
       <form:label path="volume" class="control-label col-sm-2">Volume</form:label>
       <div class="col-sm-10"> 
    <form:input path="volume" class="form-control"/>
    </div>
    </div>
    
    <div class="form-group">
       <form:label path="publishDate" class="control-label col-sm-2">Publish Date</form:label>
       <div class="col-sm-10"> 
    <form:input path="publishDate"  class="form-control" />
    </div>
    </div>
     
     <div class="form-group">
     <center>
    <input type="submit" value="Submit" />
    </center>
    </div>
</form:form></body>
</html>
