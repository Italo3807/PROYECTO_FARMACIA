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
	String filtro = (String) request.getAttribute("filtro");
	
	%>
	<%
	ArrayList<Cliente> listaCliente = (ArrayList<Cliente>) request.getAttribute("listaClientes");
	if (listaCliente == null) {
		pageContext.forward("ClienteMantenedorDB");
	}
	if (filtro == null) {
		filtro="";
	}
	%>


	<div class="container">
		<div class="panel panel-success mx-auto">
			<div class="panel-heading">Mantenimiento Principal de clientes</div>
			<div class="panel-body">
				<div class="row" style="margin: 6px;">
					<div class="col-lg-6">
						<form action="ClienteMantenedorDB" method="GET">
							<div class="input-group">
								<input type="text" name="filtro" class="form-control"
									placeholder="DNI o Nombre Cliente" aria-describedby="basic-addon2" value="<%=filtro%>">
								<span class="input-group-btn">
									<button class="btn btn-default" type="submit" name="action"
										value="FIND">
										<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
										Buscar
									</button>
								</span> <span class="input-group-btn">
									<button class="btn btn-default" type="submit" name="action"
										value="NEW">
										<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
										Nuevo
									</button>
								</span><span class="input-group-btn">
									<button class="btn btn-default" type="submit" name="action"
										value="LISTARTODOS">
										<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
										Todos
									</button>
								</span>
							</div>
						</form>
					</div>
				</div>
				<div class="table-responsive text-center" style="margin: 20px;">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>Dni</th>
								<th>Nombre Completo</th>
								<th>Fecha Nacimiento</th>
								<th>Correo</th>
								<th>Celular</th>
								<th>Acciones</th>
							</tr>
						</thead>
						<tbody>
							<%
							if(listaCliente!=null){
							for (int i = 0; i < listaCliente.size(); i++) {
								Cliente cli = listaCliente.get(i);
							%>
							<tr>
								<td><%=cli.getDniCliente()%></td>
								<td><%=cli.getNombre() + " " + cli.getApePaterno() + " " + cli.getApeMaterno()%></td>
								<td><%=cli.getFechaNacimiento()%></td>
								<td><%=cli.getCorreo()%></td>
								<td><%=cli.getCelular()%></td>
								<td>
									<div class="btn-group" role="group">
										<form action="ClienteMantenedorDB" method="GET">
											<input type="hidden" name="id"
												value="<%=cli.getIdCliente()%>"> <input
												type="hidden" name="action" value="EDIT">
											<button type="submit" class="btn btn-primary" title="Editar">
												<span class="glyphicon glyphicon-edit"></span>
											</button>
										</form>
										<form action="ClienteMantenedorDB" method="GET">
											<input type="hidden" name="id"
												value="<%=cli.getIdCliente()%>"> <input
												type="hidden" name="action" value="REMOVE">
											<button type="submit" class="btn btn-danger" title="Eliminar">
												<span class="glyphicon glyphicon-remove"></span>
											</button>
										</form>
									</div>
								</td>
							</tr>
							<%
							}}
							%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

</body>
</html>