<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin pannel</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="container-sm mt-3">
        <div class="row justify-content-center">
            <div class="col-md-8" style="text-align: center;">
   				<h1>Welcome Admin <c:out value="${currentUser.name}"></c:out></h1>
    
			    <form id="logoutForm" method="POST" action="/logout">
			        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			        <input type="submit" value="Logout!" />
			    </form>
            </div>
        </div>
        <div class="row justify-content-center">
			<div class="col-md-8">
				<table class="table table-hover border">
					<thead>
						<tr>
							<th scope="col">ID</th>
							<th scope="col">User Name</th>
							<th scope="col">Email</th>
							<th scope="col">Country</th>
							<th scope="col">ROLE</th>
							<th scope="col">Delete</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${users}">
							<tr>
								<td><c:out value="${user.id}"/></td>
								<td><c:out value="${user.name}"/></td>
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
								<td>
									<form action="/users/${user.id}" method="post">
										<input type="hidden" name="_method" value="delete">
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
										<h5>
											<button type="submit" class="btn btn-danger btn-sm">Delete</button>
										</h5>
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
    </div>
</body>
</html>