<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="model.Cliente"%>
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
	Cliente cli = (Cliente) request.getAttribute("cliente");
	
	String action = (String)request.getAttribute("action");
	%>
	
	<div class="container">
		<div class="panel panel-success mx-auto">
			<div class="panel-heading"><%=titulo%></div>
			<div class="panel-body">
				<form action="ClienteMantenedorDB" method="POST">
					<%if (action.equals("EDIT")) {%><input type="hidden" name="id"value="<%=cli.getIdCliente()%>"><%}%>
					<div class="form-group">
						<label for="dni">DNI:</label> <input type="text"
							class="form-control" id="dni" name="dni"
							placeholder="Ingresa tu DNI (solo números)" pattern="[0-9]{8}"
							maxlength="8" required value = "<%=cli.getDniCliente()%>"> 
					</div>
					<div class="form-group">
						<label for="nombre">Nombre:</label> <input type="text"
							class="form-control" id="nombre" name="nombre"
							placeholder="Ingresa el nombre" value = "<%=cli.getNombre()%>" >
					</div>
					<div class="form-group">
						<label for="apePaterno">Apellido Paterno:</label> <input
							type="text" class="form-control" id="apePaterno"
							name="apePaterno" placeholder="Ingresa el apellido paterno" 
							value = "<%=cli.getApePaterno()%>">
					</div>
					<div class="form-group">
						<label for="apeMaterno">Apellido Materno:</label> <input
							type="text" class="form-control" id="apeMaterno"
							name="apeMaterno" placeholder="Ingresa el apellido materno"
							 value = "<%=cli.getApeMaterno()%>">
					</div>

					<div class="form-group">
						<label for="fechaNacimiento">Fecha de Nacimiento:</label> <input
							type="date" class="form-control" id="fechaNacimiento"
							name="fechaNacimiento" required 
							value = "<%=cli.getFechaNacimiento()%>">
					</div>

					<div class="form-group">
						<label for="email">Correo Electrónico:</label> <input type="email"
							class="form-control" id="email" name="email"
							placeholder="Ingresa tu correo electrónico" value = "<%=cli.getCorreo()%>">
					</div>
					<div class="form-group">
						<label for="celular">Celular:</label> <input type="tel"
							class="form-control" id="celular" name="celular"
							placeholder="Ingresa tu número de celular" value = "<%=cli.getCelular()%>">
					</div>
					<button type="submit" class="btn btn-primary" name="action"
										value="<%=action%>">Guardar</button>
				</form>
			</div>
		</div>
	</div>

</body>
</html>