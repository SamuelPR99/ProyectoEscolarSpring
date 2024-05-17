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
    <h1 class="tituloheader">IES Murcia</h1>
    <nav>
        <a href="#">Inicio</a> |
        <a href="#">Perfil</a> |
        <a href="login">Cerrar sesión</a>
    </nav>
</header>
<main>
    <h1>Bienvenid@, ${usuario.nombre}</h1>
    <p>Nota actual: ${usuario.nota}</p>
    <h2>Tareas Asignadas</h2>
    <c:choose>
        <c:when test="${tareasAsignadas.size() == 0}">
            <p>No hay tareas disponibles</p>
        </c:when>
        <c:otherwise>
            <c:forEach var="tarea" items="${tareasAsignadas}">
                <div class="tarea">
                    <div onclick="toggleDetails(this)">
                        <h3>${tarea.nombre}</h3>
                    </div>
                    <div style="display: none;">
                        <p>Descripcion: ${tarea.descripcion}</p>
                        <p>Dificultad: ${tarea.tipo}</p>
                        <p>Fecha de inicio: ${tarea.fechaInicio}</p>
                        <p>Fecha de expiracion:
                            <c:choose>
                                <c:when test="${tarea.fechaExpiracion lt fechaActual}">
                                    <span style="color: red;">${tarea.fechaExpiracion} (Expirada)</span>
                                </c:when>
                                <c:otherwise>
                                    ${tarea.fechaExpiracion}
                                </c:otherwise>
                            </c:choose>
                        </p>
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
                        <form action="entregarTarea" method="post">
                            <input type="hidden" name="idTarea" value="${tarea.tareaId}">
                            <input type="hidden" name="idAlumno" value="${usuario.usuarioId}">
                            <button type="submit">Entregar Tarea</button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>

    <h2>Tareas Entregadas</h2>
    <c:choose>
        <c:when test="${tareasEntregadas.size() == 0}">
            <p>No hay tareas entregadas</p>
        </c:when>
        <c:otherwise>
            <c:forEach var="tarea" items="${tareasEntregadas}">
                <div class="tarea">
                    <div onclick="toggleDetails(this)">
                        <h3>${tarea.nombre}</h3>
                    </div>
                    <div style="display: none;">
                        <p>Descripcion: ${tarea.descripcion}</p>
                        <p>Dificultad: ${tarea.tipo}</p>
                        <p>Fecha de inicio: ${tarea.fechaInicio}</p>
                        <p>Fecha de expiracion:
                            <c:choose>
                                <c:when test="${tarea.fechaExpiracion lt fechaActual}">
                                    <span style="color: red;">${tarea.fechaExpiracion} (Expirada)</span>
                                </c:when>
                                <c:otherwise>
                                    ${tarea.fechaExpiracion}
                                </c:otherwise>
                            </c:choose>
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
        </c:otherwise>
    </c:choose>

    <!-- JavaScript para mostrar y ocultar los detalles de las tareas -->
    <script>
        function toggleDetails(element) {
            var details = element.nextElementSibling;
            if (details.style.maxHeight === "0px" || details.style.maxHeight === "") {
                details.style.display = "block"; // Asegura que el elemento se muestre antes de iniciar la transición
                setTimeout(function() { // Introduce un breve retraso para asegurar que la transición se aplique
                    details.style.opacity = "1";
                    details.style.maxHeight = "500px"; // Ajusta este valor según tu contenido
                    details.style.visibility = "visible"; // Asegura que el elemento sea visible durante la transición
                }, 10); // Un retraso mínimo es suficiente para permitir que el navegador aplique el display: block
            } else {
                details.style.opacity = "0";
                details.style.maxHeight = "0";
                details.style.visibility = "hidden"; // Inicia el proceso de hacer el elemento no visible
                setTimeout(function() {
                    if (details.style.opacity === "0") { // Verifica nuevamente para evitar ocultarlo si el usuario hizo clic nuevamente durante la transición
                        details.style.display = "none";
                    }
                }, 500); // La duración de este temporizador debe coincidir con la duración de la transición CSS
            }
        }
    </script>

    <!-- Otra forma de hacerlo, sin necesidad de temporizadores -->
    <script>
        document.addEventListener("DOMContentLoaded", function() { // Espera a que el DOM esté completamente cargado
            var detalles = document.querySelectorAll('.tarea > div:nth-child(2)'); // Selecciona todos los elementos que se desplegarán
            detalles.forEach(function(detalle) { // Itera sobre cada elemento
                detalle.style.maxHeight = "0px"; // Inicializa max-height a 0
            });
        });
    </script>

</body>
</html>
