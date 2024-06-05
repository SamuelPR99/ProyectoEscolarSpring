<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<!DOCTYPE html>
<html>
<head>
    <title>IES Murcia - Profesor</title>
    <link rel="stylesheet" href="../CSS/profesorStyle.css">
    <link rel="stylesheet" href="../CSS/styles.css">
</head>
<body>
<header>
    <h1>IES Murcia</h1>
    <nav>
        <a href="#">Inicio</a> |
        <a href="#">Perfil</a> |
        <a href="login">Cerrar sesión</a>
    </nav>
</header>
<main>
    <!-- Sección para ver los temas -->
    <section class="collapsible-section">
        <h2 class="collapsible-header">Temas</h2>
        <div class="collapsible-content">
            <table class="tablaTemas">
                <c:forEach var="tema" items="${temas}">
                    <tr>
                        <td colspan="3">
                            <strong>Tema: </strong>${tema.numeroTema} - ${tema.nombre}
                        </td>
                        <td>
                            <table>
                                <c:forEach var="tarea" items="${tema.listaTareas}">
                                    <tr>
                                        <td>${tarea.nombre}</td>
                                        <td>${tarea.tipo}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </section>

    <!-- Sección para asignar tarea -->
    <section class="collapsible-section">
        <h2 class="collapsible-header">Asignar Tarea</h2>
        <div class="collapsible-content">
            <form class="asignarTarea" id="asignarTareaForm" action="asignarTarea" method="post">
                <label for="idTarea"><strong>Tarea:</strong></label>
                <select id="idTarea" name="idTarea">
                    <c:forEach var="tema" items="${temas}">
                        <c:forEach var="tarea" items="${tema.listaTareas}">
                            <option value="${tarea.tareaId}">${tarea.nombre}</option>
                        </c:forEach>
                    </c:forEach>
                </select>
                <br>
                <label for="fechaExpiracion"><strong>Fecha de Expiración:</strong></label>
                <input type="date" id="fechaExpiracion" name="fechaExpiracion" required>

                <input type="submit" value="Asignar tarea">
            </form>
        </div>
    </section>

    <section class="collapsible-section">
        <h2 class="collapsible-header">Lista de Alumnos</h2>
        <div class="collapsible-content">
            <table>
                <tr>
                    <th>Nombre</th>
                    <th>Nota</th>
                    <th>Nota Media de las tareas</th>
                    <th>Cambiar nota</th>
                </tr>
                <c:forEach var="entry" items="${notaMediaPorAlumno}">
                    <tr>
                        <td>${entry.key.nombre}</td>
                        <td>${entry.key.nota}</td>
                        <td><fmt:formatNumber value="${entry.value}" type="number" maxFractionDigits="2" minFractionDigits="1"/></td>
                        <td>
                            <form action="cambiarNota" method="post">
                                <input type="hidden" name="idAlumno" value="${entry.key.usuarioId}">
                                <input type="number" name="nota" min="0" max="10" step="0.1" required>
                                <input type="submit" value="✅">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </section>

    <!-- Sección para ver las estadísticas de los alumnos -->
    <section class="collapsible-section">
        <h2 class="collapsible-header">Estadísticas de los Alumnos</h2>
        <div class="collapsible-content">
            <div id="estadisticas">
                <canvas id="graficoNotas"></canvas>
            </div>
        </div>
    </section>
</main>

<script>
    // Convertir los datos de JSTL a JavaScript
    var datosAlumnos = [
        <c:forEach var="alumno" items="${alumnos}" varStatus="status">
        { nombre: "${alumno.nombre}", nota: ${alumno.nota}}<c:if test="${!status.last}">,</c:if>
        </c:forEach>
    ];

    // Convertir los datos de JSTL a JavaScript
    var datosTareasEntregadasATiempoPorAlumno = {
        <c:forEach var="entry" items="${tareasEntregadasATiempoPorAlumno}" varStatus="status">
        "${entry.key.nombre}": ${entry.value}<c:if test="${!status.last}">,</c:if>
        </c:forEach>
    };

    // Convertir los datos de JSTL a JavaScript
    var datosNotaMediaPorAlumno = {
        <c:forEach var="entry" items="${notaMediaPorAlumno}" varStatus="status">
        "${entry.key.nombre}": ${entry.value}<c:if test="${!status.last}">,</c:if>
        </c:forEach>
    };
</script>

<script src="../JS/profesor.js"></script>

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
