<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Log in</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="container-sm mt-3">
        <div class="row justify-content-center">
            <div class="col-md-5" style="text-align: center;">
            	<h3>Login</h3>
            	<p>
	            	<c:if test="${logoutMessage != null}">
				        <c:out value="${logoutMessage}"></c:out>
				    </c:if>
			    </p>
				<form method="POST" action="/login">
				
			        <input name="username" type="email" class="form-control mt-4" placeholder="Email" />
			        
                    <input name="password" type="password" class="form-control mt-4" placeholder="Password" />
			        <c:if test="${errorMessage != null}">
        				<span style="color: red;"><c:out value="${errorMessage}"></c:out></span> 
   			 	    </c:if>
			        <div class="mt-4">
			        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<button type="submit" class="btn btn-success">Log in</button>
					</div>
			    </form>
            </div>
        </div>
    </div>
</body>
</html>