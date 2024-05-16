<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Registro Completado</title>
	<link rel="stylesheet" href="../CSS/registroValid.css">
</head>
<body>

<div class="container">
	<div class="circle" onclick="desaparecer()">
		<span class="check">&#10003;</span>
		<div class="message">¡Registro completado, muchas gracias!</div>
	</div>
</div>

<script>
	function desaparecer() {
		var circle = document.querySelector('.circle');
		circle.style.transition = 'transform 1s, opacity 1s';
		circle.style.transform = 'scale(0) rotate(360deg)';
		circle.style.opacity = '0';
	}
	// Esperar a que la animación termine antes de redirigir
	setTimeout(function() {
		window.location.href = 'login'; // Asegúrate de que la ruta 'login' sea correcta según tu configuración de rutas
	}, 3000); // Espera 1 segundo, que es la duración de la animación
</script>

</body>
</html>
