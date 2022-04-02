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
<title>Add Appertment</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" /> 
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<style>
    #map {
      height: 40vh;
      width: 40vw;
    }

</style>
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
						<form:input path="latitude" class="form-control" id="lat"/>
						<form:errors path="latitude" class="text-danger" />
					</div>
					<div class="form-group">
						<label>Location</label>
						<form:input path="longitude" class="form-control" id="lng"/>
						<form:errors path="longitude" class="text-danger" />
					</div>
					
					<div id='map' onmouseover="changelocation()"></div>
				
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