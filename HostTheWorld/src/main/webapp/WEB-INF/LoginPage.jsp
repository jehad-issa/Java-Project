<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
<link rel="stylesheet" type="text/css" href="/css/welcome.css">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="container-sm mt-3">
		<div class="row justify-content-center">
			<div class="col-md-6 p-4 me-3"
				style="background: rgba(76, 175, 80, 0.3); box-shadow: 0.2px 0.2px 0.2px green;">
				<div class="row">
					<div class="col-md-4 me-3">
						<img alt="logo.png" src="/img/logo.png"
							style="height: 80px; width: 170px;">
					</div>

					<div class="col-md-7">
						<h4>
							<em>Host the world</em>
						</h4>
						<p>"Live with no excuses and travel with no regrets" - Oscar
							Wilde</p>
					</div>
				</div>
				<div class="row">
					<h4>
						<em>About us:</em>
					</h4>
					<p>The main goal of this project is to make connection between Host and Visitor, to make this connections easy we established a web site that allow the Host and Visitor to register in this web site. The Host can display his Apartment,add new Apartment and edit his Apartment, and other user(Visitor) He can reserve Apartment, add an Apartment to his favorite and make a review for an Apartment.</p>
				</div>
			</div>
		<div class="col-md-3">
			<h3>Login</h3>
			<p>
				<c:if test="${logoutMessage != null}">
					<span style="color: #842029"><c:out value="${logoutMessage}"></c:out></span>
				</c:if>
			</p>
			<form method="POST" action="/login">

				<input name="username" type="email" class="form-control mt-4"
					placeholder="Email" /> <input name="password" type="password"
					class="form-control mt-4" placeholder="Password" />
				<c:if test="${errorMessage != null}">
					<span style="color: red;"><c:out value="${errorMessage}"></c:out></span>
				</c:if>
				<div class="mt-4" style="text-align: end;">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<button type="submit" class="btn btn-success">Log in</button>
				</div>
			</form>
			</div>
		</div>
	</div>
	<div class="row justify-content-center mt-5">
		<div class="col-md-4 p-4 me-5"
			style="background: rgba(76, 175, 80, 0.3); box-shadow: 0.2px 0.2px 0.2px green;">
			<h2 class="mb-2">Visitors</h2>
			<p>join us as visitor to seek for hosting through your journey
				all over the world</p>
			<h4>Genuine reviews you can trust</h4>
			<p>Non-biased reviews from real guests, readers and experts</p>
			<h4>Find alot of apartments all over the world</h4>
			<p>Easy connecting with many hosts around the world with sharing
				feedbacks and reviews</p>
			<a href="/visitor/registration" class="btn"
				style="background-color: #842029; color: white;">Join as Visitor</a>
		</div>
		<div class="col-md-4 p-4 ms-5"
			style="background: rgba(76, 175, 80, 0.3); box-shadow: 0.2 0.2 0.2px green;">
			<h2 class="mb-2">Hosts</h2>
			<p>join us as host to seek many travelers around the world where
				both get benefit by sharing reviews and contact each other easily</p>
			<h4>Search Engine Exposure</h4>
			<p>Marketing on search engines to encourage guests to book your
				property quicker.</p>
			<h4>Free to Join</h4>
			<p>It's absolutely free to sign up - so join our extensive
				network today!</p>
			<a href="/host/registration" class="btn"
				style="background-color: #842029; color: white;">Join as Host</a>
		</div>
	</div>
</body>
</html>