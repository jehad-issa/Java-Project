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
<style>
    form input[type="text"] {
    text-transform: lowercase;
}
</style>
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
            <div class="col-md-6 me-5 p-4 mt-4">
            <h3>New Host</h3>
                <form:form action="/host/registration" method="POST" modelAttribute="host">

                    
                    <form:input path="name" type="text" class="form-control mt-4" placeholder="Your Name" />
					<form:errors path="name" style="color:red;"/>
					

                    <form:input path="email" type="text" class="form-control mt-4" placeholder="Email" />
					<form:errors path="email" style="color:red;"/>
					
					<form:input path="country" type="text" class="form-control mt-4" placeholder="Country" />
					<form:errors path="country" style="color:red;"/>

					<form:input path="city" type="text" class="form-control mt-4" placeholder="City" />
					<form:errors path="city" style="color:red;"/>

                    <form:input path="password" type="password" class="form-control mt-4" placeholder="Password" />
                    <form:errors path="password" style="color:red;"/>

                    <form:input path="passwordConfirmation" type="password" class="form-control mt-4" placeholder="Confirm Passsword"/>
					<form:errors path="passwordConfirmation" style="color:red;"/>
					
                    <div class="mt-4">
					<button type="submit" class="btn btn-success">Register</button>
					</div>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>