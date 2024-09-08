<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="model.Producto"%>
<%@ page import="model.Empleado"%>

<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="./css/style.css">
</head>
<body>
<%@include file="Header.jsp" %>
	<%
	String titulo = (String) request.getAttribute("titulo");
	Producto prod = (Producto) request.getAttribute("producto");

	String action = (String) request.getAttribute("action");
	%>
	
	<div class="container">
		<div class="panel panel-success mx-auto">
			<div class="panel-heading"><%=titulo%></div>
			<div class="panel-body">
				<form action="ProductoMantenedorDB" method="POST">
					<%
					if (action.equals("EDIT")) {
					%><input type="hidden" name="id"
						value="<%=prod.getIdProducto()%>">
					<%
					}
					%>
					<div class="form-group">
						<label for="nombre">Nombre:</label> <input type="text"
							class="form-control" id="nombre" name = "nombre"
							placeholder="Ingrese el nombre del producto" value="<%=prod.getNombre()%>">
					</div>
					<div class="form-group">
						<label for="descripcion">Descripción:</label> <input type="text"
							class="form-control" id="descripcion" name = "descripcion"
							placeholder="Ingrese la descripción del producto" value="<%=prod.getDescripcion()%>">
					</div>
					<div class="form-group">
						<label for="marca">Marca:</label> <input type="text"
							class="form-control" id="marca" name = "marca"
							placeholder="Ingrese la marca del producto" value="<%=prod.getMarca()%>">
					</div>
					<div class="form-group">
						<label for="presentacion">Presentación:</label> <input type="text"
							class="form-control" id="presentacion" name = "presentacion"
							placeholder="Ingrese la presentación del producto" value="<%=prod.getPresentacion()%>">
					</div>
					<div class="form-group">
						<label for="stock">Stock:</label> <input type="number"
							class="form-control" id="stock" name = "stock"
							placeholder="Ingrese el stock del producto" value="<%=prod.getStock()%>">
					</div>
					<div class="form-group">
						<label for="precio">Precio:</label> <input type="number"
							step="0.01" class="form-control" id="precio" name = "precio"
							placeholder="Ingrese el precio del producto" value="<%=prod.getPrecio()%>">
					</div>
					<button type="submit" class="btn btn-primary" name="action"
										value="<%=action%>">Guardar</button>
				</form>
			</div>
		</div>
	</div>

</body>
</html>