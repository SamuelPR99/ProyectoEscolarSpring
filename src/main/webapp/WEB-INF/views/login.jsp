<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Log</title>
<style>
body {
	font-family: Arial, sans-serif;
	text-align: center;
	margin-top: 30px;
	background-color: #000000;
	background-image: url(fotos/fondoLog2.jpg);
	background-size: cover;
	background-position: center;
	--color2: rgb(235, 220, 11, 0.6);
	z-index: 3;
}

h1 {
	font-family: fantasy; /* Cambiar la fuente */
	font-weight: bold; /* A�adir negrita */
	font-style: italic; /* A�adir cursiva */
	background: linear-gradient(10deg, rgb(110, 110, 110),
		rgb(255, 255, 255), rgb(105, 105, 105)); /* Fondo blanco y negro */
	-webkit-background-clip: text;
	background-clip: text;
	-webkit-text-fill-color: transparent;
	margin-top: 60px;
	margin-bottom: -100px;
}

h5 {
	margin-top: 10px;
	cursor: pointer;
	color: #ffffff;
}

form {
	width: 300px;
	height: 190px;
	margin: 0 auto;
	margin-top: 190px;
	box-shadow: 0px 3px 10px 10px rgba(0, 0, 0, 0.2);
	border-radius: 30px;
}

input[type="text"], input[type="password"] {
	width: 90%;
	padding: 10px;
	margin: 9px 0;
	margin-left: 5px;
	margin-right: 5px;
	box-sizing: border-box;
	border-radius: 30px;
	background-color: #e0ded9;
	border-color: #575755;
}

button {
	width: 90%;
	padding: 10px;
	margin-left: 5px;
	margin-right: 5px;
	background-color: #777775;
	color: rgb(0, 0, 0);
	border: none;
	border-radius: 30px;
	cursor: pointer;
}

button:hover {
	background-color: #80da37;
	transition: 0.5s;
	animation: boing 0.5s ease infinite;
	/*Lit nombre de animacion, el tiempo de ejecucion y que se infinito */
}

footer {
	position: fixed; /* Establece la posici�n fija */
	bottom: 0; /* Coloca el footer en la parte inferior de la ventana */
	left: 0; /* Coloca el footer en el borde izquierdo */
	width: 100%; /* Define el ancho del footer */
	background-color: rgba(0, 0, 0, 0.2);
	/* Fondo semi-transparente para el footer */
	padding: 20px; /* A�ade espacio interno al footer */
	z-index: 1000;
	/* Asegura que el footer est� encima de otros elementos */
	text-align: center;
}

.fot {
	display: inline-block;
}

@
keyframes boing { 0% {
	transform: translateY(0); /* Sin desplazamiento vertical al inicio */
}

50


%
{
transform


:


translateY
(


-5px


)
; /* Desplazamiento hacia arriba a la mitad de la animaci�n */


}
100


%
{
transform


:


translateY
(


0


)
; /* Sin desplazamiento vertical al final */


}
}
@
keyframes pulso { 0% {
	transform: scale(1);
	box-shadow: 0 0 0 rgba(0, 0, 0, 0);
	background-color: #ffffff; /* Amarillo al inicio */
}

50


%
{
transform


:


scale
(


1
.1


)
;


box-shadow


:


0


0


20px


rgba
(


0
,
0
,
0
,
0
.5


)
;


background-color


:


#72b336
; /* Naranja a la mitad de la animaci�n */


}
100


%
{
transform


:


scale
(


1


)
;


box-shadow


:


0


0


0


rgba
(


0
,
0
,
0
,
0


)
;


background-color


:


#ffffff
; /* Amarillo al final */


}
}
@
keyframes cambio-forma { 0% {
	width: 150px;
	height: 50px;
	border-radius: 5px;
}

100


%
{
width


:


100px
;


height


:


100px
;


border-radius


:


50
%;


}
}
.BotonUsuario input[type="text"]:hover, .BotonContrase�a input[type="password"]:hover
	{
	transition: all;
	transition: 0.7s;
	transform: translateX(10px)
}

.BotonContrase�a input[type="text"]:focus, .BotonUsuario input[type="password"]:focus
	{
	transition: all;
	transition: 0.5s;
	transform: scaleY(0.9);
}

.card {
	line-height: 10px;
	text-align: justify;
	text-align: center;
	background-color: rgba(255, 255, 255, 0.7);
	border-radius: 20px;
	height: 50px;
	width: 200px;
	box-shadow: 0px 3px 10px 10px rgba(0, 0, 0, 0.2);
	margin: 0 10px;
	display: inline-block;
}

.cards .card:hover {
	transition: 0.5s;
	transform: scale(1.1, 1.1);
	transform: rotateY(10deg);
	filter: brightness(110%);
	cursor: pointer;
	z-index: -1;
	box-shadow: 0px 0px 70px 10px var(--color2);
	background: rgba(230, 190, 12, 0.9);
	animation: pulso 1s ease infinite alternate;
}

.cards:hover>.card:not(:hover) {
	filter: blur(5px);
	transition: 0.5s;
	transform: scale(0.9, 0.9);
}

.todo {
	background-color: rgba(124, 123, 121, 0.2);
	/* Color con opacidad (aqu� se utiliza 0.5 para el 50% de opacidad) */
	float: left;
	margin-top: 230px;
	box-shadow: 0px 3px 10px 10px rgba(0, 0, 0, 0.2);
	height: 900px;
	margin-left: 20px;
	width: 400px;
	height: 50%;
	border-radius: 10px 40px;
	z-index: 2;
}

.prin {
	float: left;
	position: relative;
	margin-left: 725px;
	height: 900px;
	width: 10px;
	border-radius: 10px 40px;
	z-index: 1;
}

.logo {
	background-image: url(fotos/hotelesPaco.jpg);
	z-index: 1001;
	position: fixed;
	bottom: 0;
	right: 0;
	height: 100px;
	width: 200px;
	background-position: center;
	background-size: cover;
	border-radius: 100px;
	border-style: double;
	border-color: rgb(138, 84, 2);
}

.logo:hover {
	animation: cambio-forma 0.5s ease-in-out infinite alternate;
}
</style>
</head>

<body>
	<div class="prin">
		<div class="todo">
			<div class="logo" style="overflow: hidden;"></div>
			<div>
				<h1 class="mensaje">�Bienvenido!</h1>
			</div>

			<form id="loginForm">
				<div class="BotonUsuario">
					<label class="usuario" for="username"></label> <input
						placeholder="Usuario..." type="text" id="username" name="username"
						required>
				</div>
				<div class="BotonContrase�a">
					<label for="password"></label> <input placeholder="Contrase�a..."
						type="password" id="password" name="password" required>
				</div>
				<div class="Submit">
					<button type="submit">Login</button>
				</div>
				<a href="http://127.0.0.1:5500/registroApp.html" target="_blank">
					<h5>�No est�s registrado? pulsa aqu�</h5>
				</a>
			</form>
		</div>
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
		</footer>

	</div>
</body>

</html>