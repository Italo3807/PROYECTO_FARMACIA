<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="model.Cliente"%>
<%@ page import="model.Empleado"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>VidaFarma</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="icon" href="./imagenes/icon.png" type="image/x-icon">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="./css/style.css">
</head>
<header>
<%
	// Verificar si el atributo "nombre" existe en la sesión
	Empleado user = (Empleado) session.getAttribute("user");
	
	if (user == null) {
	%>
	<jsp:forward page="/Login.jsp" />
	<%
	}
	%>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="ClienteListar.jsp" style="color: lightgreen;">VIDAFARMA</a>
			</div>

			<ul class="nav navbar-nav navbar-right">
				<li><a href="#" style="color: lightgreen;"><span class="glyphicon glyphicon-user"></span>
						<%=user.getNombre()%></a></li>
				<li><a href="/Farmacia/CerrarSessionServlet" style="color: lightgreen;"><span
						class="glyphicon glyphicon-log-in"></span>Cerrar Sesión</a></li>
			</ul>
		</div>
	</nav>


	<div class="container">
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>

			<!-- Wrapper for slides -->
			<div class="carousel-inner">
				<div class="item active">
					<img src="./imagenes/Banner1.jpeg" alt="Imagen 1"
						style="width: 100%; height: 300px;">
				</div>

				<div class="item">
					<img src="./imagenes/Banner1.jpeg" alt="Imagen 2"
						style="width: 100%; height: 300px">
				</div>

				<div class="item">
					<img src="./imagenes/Banner1.jpeg" alt="Imagen 3"
						style="width: 100%; height: 300px">
				</div>
			</div>

			<!-- Left and right controls -->
			<a class="left carousel-control" href="#myCarousel" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#myCarousel"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>
	</div>
	<ul class="nav nav-tabs nav-justified" role="tablist">
		<li ><a href="/Farmacia/ClienteListar.jsp">Clientes</a></li>
		<li><a href="/Farmacia/ProductoListar.jsp">Productos</a></li>
		<li ><a href="/Farmacia/Venta.jsp">Venta</a></li>
		<li><a href="/Farmacia/Reporte.jsp">Reporte</a></li>
			</ul>
</header>
	
</html>