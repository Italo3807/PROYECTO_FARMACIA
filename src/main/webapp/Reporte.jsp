<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.ArrayList"%>

<%@ page import="dao.*"%>
<%@ page import="model.*"%>
<%@ page import="java.util.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="./css/style.css">
</head>
<body>
<%@include file="Header.jsp" %>
	
	<div class="container">
		<div class="panel panel-success mx-auto">
			<div class="panel-heading">Reportes</div>
			<div class="panel-body">
				<canvas id="myChart"></canvas>
				<canvas id="myChartEmpleados"></canvas>
				<script>
					$(document)
							.ready(
									function() {
										$
												.ajax({
													url : "ReportesServlet",
													method : "GET",
													dataType : "json",
													success : function(data) {
														var reporteProductos = data.reporteProductos;
														var ctx = document
																.getElementById(
																		'myChart')
																.getContext(
																		'2d');
														var myChart = new Chart(
																ctx,
																{
																	type : 'bar',
																	data : {
																		labels : reporteProductos.tags,
																		datasets : [ {
																			label : 'Ventas mensuales Productos',
																			data : reporteProductos.valores,
																			backgroundColor : 'rgba(75, 192, 192, 0.2)',
																			borderColor : 'rgba(75, 192, 192, 1)',
																			borderWidth : 1
																		} ]
																	},
																	options : {
																		scales : {
																			y : {
																				beginAtZero : true
																			}
																		}
																	}
																});

														var reporteEmpleados = data.reporteEmpleados;
														var ctx = document
																.getElementById(
																		'myChartEmpleados')
																.getContext(
																		'2d');
														var myChart = new Chart(
																ctx,
																{
																	type : 'bar',
																	data : {
																		labels : reporteEmpleados.tags,
																		datasets : [ {
																			label : 'Ventas mensuales Empleados',
																			data : reporteEmpleados.valores,
																			backgroundColor : 'rgba(75, 192, 192, 0.2)',
																			borderColor : 'rgba(75, 192, 192, 1)',
																			borderWidth : 1
																		} ]
																	},
																	options : {
																		scales : {
																			y : {
																				beginAtZero : true
																			}
																		}
																	}
																});
													}
												});
									});
				</script>
			</div>
		</div>
	</div>






</body>
</html>