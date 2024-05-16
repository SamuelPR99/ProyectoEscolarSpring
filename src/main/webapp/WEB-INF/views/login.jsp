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

		<form id="loginForm">
			<div class="BotonUsuario">
				<label class="usuario" for="username"></label>
				<input placeholder="Usuario..." type="text" id="username" name="username" required>
			</div>
			<div class="BotonContraseña">
				<label for="password"></label>
				<input placeholder="Contraseña..." type="password" id="password" name="password" required>
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