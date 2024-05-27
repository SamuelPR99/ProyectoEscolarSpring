<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>IES Murcia - Profesor</title>
	<link rel="stylesheet" href="../CSS/loginStyle.css">
	<link rel="stylesheet" href="../CSS/styles.css">
</head>

<body>
	<header>
		<h1>IES Murcia</h1>
	</header>
		<form id="loginForm" action="login" method="post">
			<div>
				<label class="usuario" for="nombre"></label>
				<input placeholder="Usuario..." type="text" id="nombre" name="nombre" required>
			</div>
			<div>
				<label for="contrasena"></label>
				<input placeholder="Contraseña..." type="password" id="contrasena" name="contrasena" required>
			</div>
			<div class="Submit">
				<button type="submit">Login</button>
			</div>
			<h5>${mensaje}</h5>
			<a href="registro" target="_blank"><h4>¿No estas registrado?</h4></a>
		</form>
	<footer>
		<p>&copy; 2024 IES Murcia. Todos los derechos reservados.</p>
		<p>
			<a href="#">Política de Privacidad</a> |
			<a href="#">Términos de Servicio</a>
		</p>
	</footer>
</body>
</html>