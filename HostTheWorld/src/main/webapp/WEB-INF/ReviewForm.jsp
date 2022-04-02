<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login or Register</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="container-sm mt-3">
        <div class="row justify-content-center">
            <div class="col-md-6" style="text-align: center;">
            	<h2 style="color: blue;">Host the world</h2>
            </div>
        </div>
    </div>
	<div class="container-sm mt-2">
        <div class="row justify-content-center">
            <div class="col-md-4 mt-4">
            <h5>Add Review</h5>
                <form:form action="/reviews/new/${hostId}" method="POST" modelAttribute="review">

                    
					<form:errors path="hostReview" style="color:red;" />
					<form:textarea class="form-control" path="hostReview"/>
					
					<form:label path="hostRank" class="form-label mt-3 h5">Rate:</form:label>
					<form:input path="hostRank" type="range" class="form-range" min="0" max="10" step="1"/>
					
                    <div class="mt-4">
					<button type="submit" class="btn btn-success">Add review</button>
					</div>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>