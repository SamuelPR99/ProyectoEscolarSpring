<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Log</title>
	<link rel="stylesheet" href="../CSS/loginStyle.css">
</head>

<body>
<div class="prin">
	<div class="todo">
		<div class="logo" style="overflow: hidden;"></div>
		<div>
			<h1 class="mensaje">¡Bienvenido!</h1>
		</div>

		<form id="loginForm" action="login" method="post">
			<div class="BotonUsuario">
				<label class="usuario" for="nombre"></label>
				<input placeholder="Usuario..." type="text" id="nombre" name="nombre" required>
			</div>
			<div class="BotonContraseña">
				<label for="contrasena"></label>
				<input placeholder="Contraseña..." type="password" id="contrasena" name="contrasena" required>
			</div>
			<div class="Submit">
				<button type="submit">Login</button>
			</div>
			<a href="registro" target="_blank"><h5>¿No estas registrado?</h5></a>
		</form>
	</div>
</div>
</body>

<footer>
	<div class="fot">
		<div class="cards">

			<div class="card">
				<h4>Sobre nosotros</h4>
			</div>

			<div class="card">
				<h4>Recomendaciones</h4>
			</div>

			<div class="card">
				<h4>Preguntas y respuestas</h4>
			</div>
		</div>
	</div>
</footer>

</html>