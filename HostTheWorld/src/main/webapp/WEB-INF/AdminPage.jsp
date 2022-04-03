<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin page</title>
<link rel="stylesheet" type="text/css" href="/css/VisitorHome.css">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" /> 
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
    <!-- nav bar -->
    <nav class="navbar navbar-expand-lg navbar-light" style="background-color:#d6a268;">
        <div class="container-fluid">
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
            
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            	  <li class="nav-item">
            	<img alt="logo.png" src="/img/logo.png" style="height: 50px; width: 140px;">
                  </li>
                <li class="nav-item">
                    <a class="nav-link" href="/home"><i class="fa fa-home" > Visitor Home</i></a>
                 </li>
            </ul>
            <form class="d-flex me-3" method="POST" action="/search">
			  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
              <input name="search" class="form-control me-2" type="text" placeholder="Search by country">
              <button class="btn btn-warning btn-sm" type="submit"><i class="fa fa-search" ></i> Search</button>
            </form>
            <form id="logoutForm" method="POST" action="/logout">
		        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		        <button type="submit" class="btn btn-warning btn-sm"><i class="fa fa-sign-out"></i> Logout!</button>
      		</form>
          </div>
        </div>
      </nav>
      <!-- body -->
      <section class="section1">
      <div class="container">
      	<h4 class="my-3" style="color: darkorange;">Hi <c:out value="${currentUser.name}" />, welcome to the admin page</h4>
                <div class="row justify-content-center">
			<div class="col-md-8">
				<table class="table border" style="color: white;">
					<thead>
						<tr>
							<th scope="col">ID</th>
							<th scope="col">User Name</th>
							<th scope="col">Email</th>
							<th scope="col">Country</th>
							<th scope="col">ROLE</th>
							<th scope="col">Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${users}">
							<tr>
								<td><c:out value="${user.id}"/></td>
								<td>
									<c:choose>
										<c:when test="${user.roles.get(0).name.equals('ROLE_HOST')}">
									    	<a href="/host/details/${user.id}" style="text-decoration: none;"><c:out value="${user.name}"/></a>
										</c:when>
										<c:otherwise>
											<c:out value="${user.name}"/>
										</c:otherwise>
									</c:choose>
								</td>
								<td><c:out value="${user.email}"/></td>
								<td><c:out value="${user.country}"/></td>
								<td>
							      <c:choose>
							         <c:when test = "${user.roles.get(0).name.equals('ROLE_ADMIN')}">
							            Admin
							         </c:when>
							     	 <c:when test = "${user.roles.get(0).name.equals('ROLE_HOST')}">
							            Host
							         </c:when>
							         <c:when test = "${user.roles.get(0).name.equals('ROLE_VISITOR')}">
							            Visitor
							         </c:when>								        
							      </c:choose>
								</td>
								<td class="d-flex">
									<form action="/users/${user.id}" method="post">
										<input type="hidden" name="_method" value="delete">
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
										<h5>
											<button type="submit" class="btn btn-danger btn-sm me-2">Delete</button>
										</h5>
									</form>
									<c:choose>
										<c:when test="${user.roles.get(0).name.equals('ROLE_ADMIN')}">
											<a href="/makehost/${user.id}" class=" btn btn-success btn-sm" style="height: 32px;">Make Host</a>
										</c:when>
										<c:when test="${user.roles.get(0).name.equals('ROLE_HOST')}">
											<a href="/user/${user.id}" class=" btn btn-success btn-sm" style="height: 32px;">Make Admin</a>
										</c:when>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
      </div>
    </section>
</body>
</body>
</html>