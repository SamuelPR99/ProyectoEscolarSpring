<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registro</title>
</head>
<body>
	<h1>Registro</h1>
	<form action="registro" method="post">
		<label>Nombre:</label> <input type="text" name="nombre" required><br>
		<label>DNI:</label> <input type="text" name="dni" required><br>
		<label>Tipo de usuario</label> <select name="tipo">
			<option value="Profesor">profesor</option>
			<option value="Alumno">alumno</option>
			
		</select><br>
		
		<label>Contraseña:</label> <input type="password" name="contrasena" required>
		<!-- boton sumbit -->
		<input type="submit" value="Registrarse">

		<br>
		
	</form>
	<a href="login">Iniciar sesión</a>

</body>
</html>