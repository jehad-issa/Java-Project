<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Appertment</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" /> 
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div style="display: flex; justify-content: space-between; padding: 20px">
<h1>Add a Appartment</h1>
<a href="">back to home</a>
</div>
	<div class="container mt-4 ">
	      <div class="row">
	        <div class="col-6 offset-3 border border-1 border-dark p-3 ">
				<form:form action="/host/new" method="post" modelAttribute="apartment">
					<div class="form-group">
						<label>Numbers of rooms </label>
						<form:input path="numberOfRooms" class="form-control" />
						<form:errors path="numberOfRooms" class="text-danger" />
					</div>
					<div class="form-group">
						<label>Capacity </label>
						<form:input path="hostageCapacity" class="form-control" />
						<form:errors path="hostageCapacity" class="text-danger" />
					</div>
					<div class="form-group">
						<label>Price per month </label>
						<form:input path="price" class="form-control" />
						<form:errors path="price" class="text-danger" />
					</div>
					<div class="form-group">
						<label>Location</label>
						<form:input path="location" class="form-control"/>
						<form:errors path="location" class="text-danger" />
					</div>
					<div class="form-group">
						<label>Description</label>
						<form:textarea path="description" class="form-control" row="7" />
						<form:errors path="description" class="text-danger" />
					</div>

					<input type="submit" value="Submit" class="btn btn-primary mt-2 float-end"  />
					<a href="" style="margin-right:50px;"class="btn btn-primary mt-2 float-end">Cancel</a>
				</form:form>
				</div>
			</div>
		</div>
</body>
</html>