<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
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
    <h1>Bienvenid@, ${usuario.nombre}</h1>
    <p>Nota actual: ${usuario.nota}</p>
    <h2>Tareas Asignadas</h2>
    <c:forEach var="tarea" items="${tareasAsignadas}">
        <div class="tarea">
            <div onclick="toggleDetails(this)">
                <h3>Nombre de la tarea: ${tarea.nombre}</h3>
            </div>
            <div style="display: none;">
                <p>Descripcion: ${tarea.descripcion}</p>
                <p>Dificultad: ${tarea.tipo}</p>
                <p>Fecha de inicio: ${tarea.fechaInicio}</p>
                <p>Fecha de expiracion: ${tarea.fechaExpiracion}</p>
                <p>Fecha de entrega: ${tarea.fechaEntrega}</p>
                <p>Estado:
                    <c:choose>
                        <c:when test="${tarea.estado}">
                            Entregada
                        </c:when>
                        <c:otherwise>
                            No entregada
                        </c:otherwise>
                    </c:choose>
                </p>
                <p>Puntuación: ${tarea.puntuacion} /
                    <c:choose>
                        <c:when test="${tarea.tipo == 'Avanzada'}">
                            3 puntos
                        </c:when>
                        <c:when test="${tarea.tipo == 'Intermedia'}">
                            2 puntos
                        </c:when>
                        <c:when test="${tarea.tipo == 'Basica'}">
                            1 punto
                        </c:when>
                    </c:choose>
                </p>
            </div>
        </div>
    </c:forEach>
    
    <a href="login">Cerrar sesión</a>

    <script>
        function toggleDetails(element) { // Funcion para mostrar/ocultar detalles de una tarea
            var details = element.nextElementSibling; // Elemento siguiente al que se ha hecho click
            if (details.style.display === "none") { // Si esta oculto, se muestra
                details.style.display = "block"; // Se muestra
            } else {
                details.style.display = "none"; // Se oculta
            }
        }
    </script>
</main>
<footer>
    <p>&copy; 2024 IES Murcia. Samuel, Paula, Hugo.</p>
</footer>
</body>
</html>