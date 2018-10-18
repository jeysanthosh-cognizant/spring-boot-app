<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body  class="container">
<header class="navbar navbar-inverse navbar-fixed-top">
     <div class="navbar-header"> 
          <button type="button" 
        class="navbar-toggle" data-toggle="collapse" 
        data-target="#myNavbar">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span> 
        </button>
    </div>
   <!-- <div class="col-md-4">
    <iframe src="navigation.html" width="100%" height="55x" style="border:none"></iframe> 
    </div>-->
    <div class="collapse navbar-collapse" id="myNavbar">
                 <ul class="nav navbar-nav">
                 <sec:authorize access="!hasAuthority('ROLE_LIBRARIAN') && !hasAuthority('ROLE_PRINCIPAL')">
               <li ><a href="registerForm" target="_top">User Registeration</a></li>
               </sec:authorize>
   <!--                 <li sec:authorize access="!hasRole('ROLE_LIBRARIAN') and !hasRole('ROLE_PRINCIPLE')"><a href="login" target="_top">Login</a></li> 
                 <li sec:authorize access="hasRole('ROLE_PRINCIPLE') || hasRole('ROLE_LIBRARIAN')"><a href="books" target="_top">BOOK</a></li>
                  <li sec:authorize access="hasRole('ROLE_PRINCIPLE') ||  hasRole('ROLE_LIBRARIAN')"><a href="logout" target="_top">logout</a></li>
                  <li sec:authorize access="hasRole('ROLE_PRINCIPLE')"><a href="subjects" target="_top">Subjects</a></li>-->
                  <sec:authorize access="hasAuthority('ROLE_PRINCIPAL') || hasAuthority('ROLE_LIBRARIAN')" >
                 <li ><a href="books" target="_top">BOOK</a></li>
                 </sec:authorize>
                  <sec:authorize access="hasAuthority('ROLE_PRINCIPAL')" >
                  <li><a href="subjects" target="_top">Subjects</a></li>
                  </sec:authorize>
                  <li><a href="logout" target="_top">logout</a></li> 
                </ul>
        </div>
</header>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
	  <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
</body>
</html>