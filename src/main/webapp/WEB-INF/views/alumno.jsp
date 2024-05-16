<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>IES Murcia - Alumno</title>
    <link rel="stylesheet" href="../CSS/alumnoStyle.css">
</head>
<body>
<header>
    <h1>IES Murcia</h1>
    <nav>
        <a href="#">Inicio</a> |
        <a href="#">Perfil</a> |
        <a href="#">Contacto</a>
    </nav>
</header>
<main>
    <h1>Bienvenido, ${usuario.nombre}</h1>
    <p>Nota actual: ${usuario.nota}</p>
    <h2>Tareas Asignadas</h2>
    <c:forEach var="tarea" items="${usuario.tareas}">
        <div class="tarea">
            <h3>${tarea.nombre}</h3>
            <p>${tarea.descripcion}</p>
            <p>Fecha de entrega: ${tarea.fechaEntrega}</p>
        </div>
    </c:forEach>
    <h2>Calificaciones</h2>
    <a href="login">Cerrar sesión</a>
</main>
<footer>
    <p>&copy; 2024 IES Murcia. Samuel, Paula, Hugo.</p>
</footer>
</body>
</html>