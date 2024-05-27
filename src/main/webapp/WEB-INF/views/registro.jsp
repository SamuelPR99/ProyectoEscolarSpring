<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>IES Murcia - Profesor</title>
	<link rel="stylesheet" href="../CSS/registroStyle.css">
	<link rel="stylesheet" href="../CSS/styles.css">
</head>

<body>
<header>
	<h1>IES Murcia</h1>
</header>
<form id="regForm" action="registro" method="post">
	<label>Nombre:</label> <input type="text" name="nombre" required><br>
	<label>DNI:</label> <input type="text" name="dni" required><br>
	<label>Tipo de usuario</label> <select name="tipo">
	<option value="Profesor">Profesor</option>
	<option value="Alumno">Alumno</option>
	</select><br>
	<label>Contraseña:</label> <input type="password" name="contrasena" required>
	<input type="submit" value="Registrarse">
	${mensaje}
	<br>
	<a href="login">Iniciar sesión</a>
</form>
<footer>
	<p>&copy; 2024 IES Murcia. Todos los derechos reservados.</p>
	<p>
		<a href="#">Política de Privacidad</a> |
		<a href="#">Términos de Servicio</a> |
		<a href="incidencias">Incidencias</a>
	</p>
</footer>
</body>
</html>