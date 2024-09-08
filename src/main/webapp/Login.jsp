<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
  <!DOCTYPE html>
  <html>

  <head>
    <meta charset="ISO-8859-1">
    <title>Vida Farma</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="icon" href="./imagenes/icon.png" type="image/x-icon">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="./css/style.css">
  </head>

  <body>
    <section class="contenedor-principal">
      <div class="imgderecha">

      </div>
      <div class="login-form">
        <div class="card-login">
				<form name="frmAcceso" method="post" action="LoginServlet">
					<h1>VIDAFARMA</h1>
					<div class="form-group">
						<label for="correo"> Correo </label> <input type="email"
							class="form-control input-lg" name="correo" placeholder="Correo">
					</div>
					<div class="form-group">
						<label for="pass"> Contraseña </label> <input type="password"
							class="form-control input-lg" name="pass" placeholder="Password">
					</div>
					<button type="submit" class="btn btn-success">Success</button>
				</form>
			</div>
      </div>
    </section>
  </body>

  </html>