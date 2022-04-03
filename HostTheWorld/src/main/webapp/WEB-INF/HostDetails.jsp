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
              <input name="search" class="form-control me-2" type="search" placeholder="Search by country" aria-label="Search">
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
      <div class="container mt-4">
        <div class="row">
          <div class="col-md-3" style="color: white;">
			<h3 style="color: darkorange;"><c:out value="${host.name}"/></h3>
			<p class="h5">Rate: <c:out value="${rank}"/> <span class="me-5"></span> <i class="fa fa-thumbs-up" > <c:out value="${host.fans.size()}"/></i></p>
			<p><c:out value="${host.city}"/> - <c:out value="${host.country}"/></p>
			<p>Email: <c:out value="${host.email}"/></p>
			<p>Call on: <c:out value="${host.phoneNumber}"/></p>
			
          </div>
          <div class="col-md-4 me-5" style="color: white;">
          	<h5> All Reviews:</h5>
          	<div style="overflow: auto; height: 200px;">
		         <c:forEach var="review" items="${host.hostReviews}"> 
					<h6 class="d-flex">
						By <c:out value="${review.reviewer.name}"/> 

						<span class="h6 ms-4">rate: <c:out value="${review.hostRank}"/></span>
						<span class="ms-3">
							<c:if test="${review.reviewer == currentUser}">
								<form action="/reviews/${review.id}/${host.id}" method="post">
									<input type="hidden" name="_method" value="delete">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
									<h5>
										<button type="submit" class="btn btn-danger btn-sm" style="height: 25px;">Delete</button>
									</h5>
								</form>
							</c:if>
						</span>
					</h6>
					<p class="mx-3 "><c:out value="${review.hostReview}"/></p>
		         </c:forEach>
	         </div>
          </div>
          <div class="col-md-3" style="color: white;">
			<h5>Add Review</h5>
            <form:form action="/reviews/new/${host.id}" method="POST" modelAttribute="review">

                  
			<form:errors path="hostReview" style="color:red;" />
			<form:textarea class="form-control" path="hostReview" style="opacity:0.7;" />
			
			<form:label path="hostRank" class="form-label mt-4 h5">Rate:</form:label>
			<form:input path="hostRank" type="range" class="form-range" min="0" max="10" step="1"/>
			
            <div class="mt-4 d-flex">
				<button type="submit" class="btn btn-primary btn-sm">Add review</button>
				<c:if test="${!host.fans.contains(currentUser)}">
					<a href="/like/${host.id}" class="btn btn-primary btn-sm ms-2">like</a>
				</c:if>
				<c:if test="${host.fans.contains(currentUser)}">
					<a href="/unlike/${host.id}" class="btn btn-primary btn-sm ms-2">Unlike</a>
				</c:if>
			</div>
            </form:form>
          </div>
        </div>
        <div class="row mt-5">
        	<h5 style="color: white">All Apartments that <c:out value="${host.name}"/> have:</h5>
        	<c:forEach var="apt" items="${host.hostApartments}">
	          <div class="col mt-3">
	            <div class="card mb-3" style="width: 18rem; background: lightgray;">
	                <img class="card-img-top" src="${apt.imgUrl}" alt="Card image cap" style="height: 260px;">
	                <div class="card-body">
	                    <div class="d-flex justify-content-between">
	                        <h5 class="card-title"><c:out value="${apt.numberOfRooms}" /> rooms $<c:out value="${apt.price}" /> per day</h5>
                     	</div>
                     	<p><c:out value="${host.city}"/> - <c:out value="${host.country}"/></p>
                     	<p class="mt-2">
                     		location details: <br>
							<span>Long: <c:out value="${apt.longitude}"/></span><br>
							<span>Lat: <c:out value="${apt.latitude}"/></span>
						</p>
										
						<p class="card-text"><c:out value="${apt.description}"/></p>
						 
						<c:if test="${currentUser.hostApartments.contains(apt)}">
							<a href="/apartment/${apt.id}/edit" class="btn btn-primary">Edit</a>
	                	</c:if>
	                	<c:if test="${!currentUser.hostApartments.contains(apt)}">
	                		<c:if test="${apt.visitors.size() == 0 }">
								<a href="/book/${apt.id}/${host.id}" class="btn btn-primary">Book</a>
							</c:if>
							<c:if test="${apt.visitors.size() > 0 && apt.visitors.contains(currentUser) }">
								<a href="/unbook/${apt.id}/${host.id}" class="btn btn-primary">UnBook</a>
							</c:if>
							<c:if test="${apt.visitors.size() > 0 && !apt.visitors.contains(currentUser) }">
								<a href="/unbook/${apt.id}/${host.id}" class="btn btn-primary disable">Booked</a>
							</c:if>							
	                	</c:if>
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