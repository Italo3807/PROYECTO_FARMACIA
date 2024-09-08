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
	String action = (String) request.getParameter("action");
	%>

	<div class="container">
		<div class="panel panel-success mx-auto">
			<div class="panel-heading">Formulario de Venta</div>
			<div class="panel-body">

				<%
				ClienteImpl cliImpl = new ClienteImpl();
				ProductoImpl proImpl = new ProductoImpl();
				ArrayList<Cliente> listaCliente = cliImpl.obtenerTodosLosClientes("");
				ArrayList<Producto> listaProductos = proImpl.obtenerTodosLosProductos("");
				%>
				<form action="VentaServlet" method="get">
					<div class="table-responsive text-center" style="margin: 20px;">
						<button type="submit" class="btn btn-primary" name="action"
							value="GRABARVENTA">Registrar venta</button>
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>Cliente:</th>
									<th>Vendedor:</th>
									<th>Fecha</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><select class="form-select" name="cliente">
											<%
											for (int i = 0; i < listaCliente.size(); i++) {
											%><option value="<%=listaCliente.get(i).getIdCliente()%>"><%=listaCliente.get(i).getNombre() + " " + listaCliente.get(i).getApePaterno() + " "
		+ listaCliente.get(i).getApeMaterno()%></option>
											<%
											}
											%>
									</select></td>
									<td><%=user.getNombre()%></td>
									<td><input type="date" name="fecha"
										value="<%=java.time.LocalDate.now()%>"></td>
								</tr>
								<tr>

								</tr>
							</tbody>
						</table>
						<button type="button" class="btn btn-primary" data-toggle="modal"
							data-target="#miModal">Añadir Productos</button>
					</div>
					<%
					String mensaje = (String) request.getAttribute("mensaje");
					if (mensaje != null && mensaje.substring(0, 2).equals("No")) {
					%>
					<div class="alert alert-danger">
						<strong>Error!</strong><%=mensaje%>
					</div>
					<%
					} else if (mensaje != null) {
					%>
					<div class="alert alert-success">
						<strong>Éxito! </strong><%=mensaje%>
					</div>
					<%
					}
					%>
				</form>
				<%
				ArrayList<DetalleVenta> listaVenta = (ArrayList<DetalleVenta>) session.getAttribute("DetalleVenta");
				%>


				<div class="table-responsive text-center" style="margin: 20px;">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>Número</th>
								<th>Producto</th>
								<th>Presentacion</th>
								<th>Precio</th>
								<th>Cantidad</th>
								<th>Importe</th>
							</tr>
						</thead>
						<tbody>
							<%
							double importe = 0;
							double importeTotal = 0;
							double precio = 0.0;
							int cantidad = 0;
							for (int i = 0; i < listaVenta.size(); i++) {
								cantidad = listaVenta.get(i).getCantidad();
								precio = listaVenta.get(i).getProducto().getPrecio();
								importe = cantidad * precio;
							%>
							<tr>
								<td><form action="VentaServlet" method="GET">
										<input type="hidden" name="idProducto"
											value="<%=listaVenta.get(i).getProducto().getIdProducto()%>">
										<input type="hidden" name="action" value="REMOVEPRODUCTO">
										<%=i + 1%>
										<button type="submit" class="btn btn-danger" title="Eliminar">
											<span class="glyphicon glyphicon-remove"></span>
										</button>
									</form></td>
								<td><%=listaVenta.get(i).getProducto().getNombre()%></td>
								<td><%=listaVenta.get(i).getProducto().getPresentacion()%></td>
								<td><input type="number" value="<%=precio%>" readonly></td>
								<td><input type="number" name="cantidad"
									value="<%=cantidad%>" readonly></td>
								<td><%=importe%>
							</tr>
							<%
							importeTotal += importe;
							}
							%>
						</tbody>
						<tfoot>
							<tr>
								<th colspan="4"></th>
								<td>Importe Total:</td>
								<td><%=importeTotal%></td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
	</div>






	<!-- Modal -->
	<div class="modal fade" id="miModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title" id="exampleModalLabel">Añadir
						Productos</h1>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Cerrar">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="VentaServlet" method="get">
					<div class="modal-body">

						<div class="form-group">
							<label for="idProducto">Seleccione una Producto:</label> <select
								class="form-control" name="idProducto">
								<%
								for (int i = 0; i < listaProductos.size(); i++) {
								%><option value="<%=listaProductos.get(i).getIdProducto()%>">
									<%=listaProductos.get(i).getNombre() + "   " + listaProductos.get(i).getPresentacion() + " --  S/."
		+ listaProductos.get(i).getPrecio()%></option>
								<%
								}
								%>
							</select>
						</div>
						<div class="form-group">
							<label for="cantidad">Cantidad:</label> <input type="number"
								class="form-control" id="cantidad" name="cantidad" min="1"
								value="1">
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Cerrar</button>
						<button type="submit" class="btn btn-primary"
							data-bs-dismiss="modal" name="action" value="ADDPRODUCTO">Añadir</button>
					</div>
				</form>
			</div>
		</div>
	</div>




</body>
</html>