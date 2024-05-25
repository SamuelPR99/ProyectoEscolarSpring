<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Registro</title>
	<link rel="stylesheet" href="../CSS/registroStyle.css">
	<link rel="stylesheet" href="../CSS/styles.css">
</head>
<body>
<header>
	<h1>IES Murcia</h1>
</header>
<div>
	<form id="registroForm" action="registro" method="post">
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
</div>

<div class="desplegable" id="desplegable">
	<p>"Por favor no mientas en el tipo de usuario o tu madre morira"</p>
</div>

<script>
	const form = document.getElementById('registroForm');
	const desplegable = document.getElementById('desplegable');

	form.addEventListener('mouseenter', function() {
		desplegable.classList.add('mostrar-desplegable');
	});

	cuerpo.addEventListener('mouseleave', function() {
		desplegable.classList.remove('mostrar-desplegable');
	});
</script>

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