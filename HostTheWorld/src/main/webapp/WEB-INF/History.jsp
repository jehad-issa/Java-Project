<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Host main page</title>
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
                    <a class="nav-link" href="/"><i class="fa fa-home" > Home</i></a>
                  </li>
              <li class="nav-item">
                <a class="nav-link " aria-current="page" href="/favorites"><i class="fa fa-thumbs-up" > Favorites</i> </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/history"><i class="fa fa-history" >  History</i></a>
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
      	<h4 class="my-3" style="color: darkorange;">Hi <c:out value="${currentUser.name}" />, here are hosts you have reviewed:</h4>
        <div class="row">
        
        	<c:forEach var="host" items="${reviewed}">
	          <div class="col">
	            <div class="card mb-3" style="width: 18rem; background: lightgray;">
	                <img class="card-img-top" src="${host.imgUrl}" alt="Card image cap" style = "height: 260px;">
	                <div class="card-body">
	                    <div class="d-flex justify-content-between">
	                        <h5 class="card-title"><c:out value="${host.name}"></c:out></h5>
	                        <c:if test="${!host.fans.contains(currentUser)}">
	                        	<a href="/like/${host.id}"><span id = heart><i class="fa fa-heart-o" aria-hidden="false" ></i></span></a>
	                        </c:if>
	                       	<c:if test="${host.fans.contains(currentUser)}">
	                        	<a href="/unlike/${host.id}"><span id = heart><i class="fa fa-heart" aria-hidden="false" ></i></span></a>
	                        </c:if>
	                     </div>
						<p><c:out value="${host.city}"/> - <c:out value="${host.country}"/></p>
						<p><c:out value="${host.name}"/> has <c:out value="${host.hostApartments.size()}"/> apartments</p>

			                  
		               	<a href="/host/details/${host.id}" class="btn btn-primary">Host Details</a>
	                </div>
	              </div>
	          </div>
          </c:forEach>
        </div>
      </div>
    </section>
</body>
</body>
</html>