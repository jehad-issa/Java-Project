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
<link rel="stylesheet" type="text/css" href="/css/welcome.css">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container-sm mt-2">
        <div class="row justify-content-center">
            <div class="col-md-6 p-3 mt-2" style="background: rgba(76, 175, 80, 0.3); box-shadow: 0.2px 0.2px 0.2px green;">
		       	<div class="row justify-content-center">
		       		<div class="col-md-4 me-3">
		       			<img alt="logo.png" src="/img/logo.png" style="height: 80px; width: 170px;">
		       		</div>
		   
		       		<div class="col-md-7">
		       			<h3  class="mt-4" style="text-align: center;"> <em>Join as Host</em></h3>
		       		</div>
        		</div>
                <form:form action="/host/registration" method="POST" modelAttribute="host">

                    
                    <form:input path="name" type="text" class="form-control mt-4" placeholder="Your Name" />
					<form:errors path="name" style="color:red;"/>
					

                    <form:input path="email" type="text" class="form-control mt-4" placeholder="Email" />
					<form:errors path="email" style="color:red;"/>
					
					<form:input path="country" type="text" class="form-control mt-4" placeholder="Country" />
					<form:errors path="country" style="color:red;"/>

					<form:input path="city" type="text" class="form-control mt-4" placeholder="City" />
					<form:errors path="city" style="color:red;"/>
					
					<form:input path="phoneNumber" type="text" class="form-control mt-4" placeholder="Please Enter your phone Number" />
					<form:errors path="phoneNumber" style="color:red;"/>
					
					<form:input path="imgUrl" type="url" class="form-control mt-4" placeholder="please enter profile image URL" />
					<form:errors path="imgUrl" style="color:red;"/>					

                    <form:input path="password" type="password" class="form-control mt-4" placeholder="Password" />
                    <form:errors path="password" style="color:red;"/>

                    <form:input path="passwordConfirmation" type="password" class="form-control mt-4" placeholder="Confirm Passsword"/>
					<form:errors path="passwordConfirmation" style="color:red;"/>
					
                    <div class="mt-4">
					<button type="submit" class="btn" style="background-color: #842029; color: white;">Register</button>
					</div>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>