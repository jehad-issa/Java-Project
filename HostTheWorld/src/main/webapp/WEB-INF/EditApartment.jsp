<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script src='https://api.mapbox.com/mapbox-gl-js/v2.7.0/mapbox-gl.js'></script>
<link href='https://api.mapbox.com/mapbox-gl-js/v2.7.0/mapbox-gl.css' rel='stylesheet' />
<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Apartment</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" /> 
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<style>
    #map {
      height: 38vh;
      width: 27vw;
      margin-top: 20px;
    }
	label {
	color: white;
	margin-top: 20px !important;
	}
	input, textarea {
	opacity: 0.5;
	color: white;
	}
</style>
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
            <form id="logoutForm" method="POST" action="/logout">
		        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		        <button type="submit" class="btn btn-warning btn-sm"><i class="fa fa-sign-out"></i> Logout!</button>
      		</form>
          </div>
        </div>
      </nav>
	<div class="container mt-2 ">
	      <div class="row justify-content-center">
	      	<h4 class="mt-2" style="color: darkorange;">Add an apartment:</h4>
	        <div class="col-10 p-3">
	        	
				<form:form action="/apartment/${apartment.id}" method="post" modelAttribute="apartment">
					<input type="hidden" name="_method" value="put">
					<div class="row">
						<div class="col-md-5">
							<div class="form-group">
								<label>Numbers of rooms </label>
								<form:input path="numberOfRooms" class="form-control" />
								<form:errors path="numberOfRooms" class="text-danger" />
							</div>
							<div class="form-group">
								<label>Country </label>
								<form:input path="country" class="form-control" />
								<form:errors path="country" class="text-danger" />
							</div>
							<div class="form-group">
								<label>City </label>
								<form:input path="city" class="form-control" />
								<form:errors path="city" class="text-danger" />
							</div>
							<div class="form-group">
								<label>Price per Day</label>
								<form:input path="price" class="form-control" />
								<form:errors path="price" class="text-danger" />
							</div>
							<div class="form-group">
								<label>Description</label>
								<form:textarea path="description" class="form-control" row="7"/>
								<form:errors path="description" class="text-danger" />
							</div>
							<div class="form-group">
								<label>Apartment image URL</label>
								<form:input path="imgUrl" class="form-control" type = "url" />
								<form:errors path="imgUrl" class="text-danger" />
							</div>
						</div>
						<div class="col-md-5 ms-4">
							<div class="form-group">
								<label>latitude</label>
								<form:input path="latitude" class="form-control" id="lat"/>
								<form:errors path="latitude" class="text-danger" />
							</div>
							<div class="form-group">
								<label>longitude</label>
								<form:input path="longitude" class="form-control" id="lng"/>
								<form:errors path="longitude" class="text-danger" />
							</div>
							
							<div id='map' onmouseover="changelocation()"></div>
							
							<button type="submit" class="btn btn-warning mt-2 float-end">Submit</button>
						</div>
					</div>
					
				</form:form>
				</div>
			</div>
		</div>
		
		<script defer>
		 mapboxgl.accessToken = 'pk.eyJ1Ijoib2RheXFzcmF3aSIsImEiOiJjbDFoMDVhNWgxMGFqM2dydGszOW01dzRiIn0.Xa8rZ9uoFidKzddMjOj4Iw';

		  setupMap([35.204269, 31.9522])


		function setupMap(center) {
		  const map = new mapboxgl.Map({
		    container: "map",
		    style: "mapbox://styles/mapbox/streets-v11",
		    center: center,
		    zoom: 15
		  })

		  const nav = new mapboxgl.NavigationControl()
		  map.addControl(nav)

		  const marker = new mapboxgl.Marker({
		    draggable: true
		    }).setLngLat([35.204269, 31.9522])
		    .addTo(map);
		    marker.on('drag', () => {
		        const lngLat = marker.getLngLat();
		        document.getElementById("lng").value = lngLat.lng;
		        document.getElementById("lat").value = lngLat.lat;
		      });

		}

		</script>
</body>
</html>