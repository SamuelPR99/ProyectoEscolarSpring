<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Incidencias - ${usuario.nombre}</title>
    <link rel="stylesheet" href="../CSS/incidenciasStyle.css">
    <link rel="stylesheet" href="../CSS/styles.css">
</head>
<body>
<header>
    <h1>IES Murcia</h1>
    <nav>
        <a href="<c:choose><c:when test='${usuario.tipoUsuario eq "Administrador"}'>administrador</c:when><c:when test='${usuario.tipoUsuario eq "Alumno"}'>alumno</c:when><c:otherwise>profesor</c:otherwise></c:choose>">Inicio</a> |
        <a href="#">Perfil</a> |
        <a href="login">Cerrar sesión</a>
    </nav>
</header>

<main>
    <h1>Bienvenid@, ${usuario.nombre} - Menu Incidencias</h1>
    <h2>Añadir Incidencia</h2>
    <form action="addIncidencia" method="post">
        <label for="tipoIncidencia"><strong>Tipo de Incidencia:</strong></label>
        <select id="tipoIncidencia" name="tipoIncidencia">
            <c:choose>
                <c:when test="${usuario.tipoUsuario eq 'Alumno'}">
                    <option value="IncidenciaAlumno">Incidencia de Alumno</option>
                    <option value="IncidenciaAplicacion">Incidencia de Aplicación</option>
                </c:when>
                <c:otherwise>
                    <option value="IncidenciaProfesor">Incidencia de Profesor</option>
                    <option value="IncidenciaAplicacion">Incidencia de Aplicación</option>
                </c:otherwise>
            </c:choose>
        </select>
        <label for="descripcionIncidencia"><strong>Descripción de la Incidencia:</strong></label>
        <textarea id="descripcionIncidencia" name="descripcionIncidencia" maxlength="255"></textarea><br>
        <span id="charCount">0 / 255 Caracteres</span>
        <input type="submit" value="Añadir Incidencia">
    </form>

    <h2>Mis Incidencias</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Descripción</th>
            <th>Fecha</th>
        </tr>
        <c:forEach var="incidencia" items="${incidencias}">
            <tr>
                <td>${incidencia.incidenciaId}</td>
                <td>${incidencia.incidencia}</td>
                <td>${incidencia.fechaIncidencia}</td>
            </tr>
        </c:forEach>
    </table>
</main>

<script src="../JS/incidencias.js"></script>

<footer>
    <p>&copy; 2024 IES Murcia. Todos los derechos reservados.</p>
    <p>
        <a href="#">Política de Privacidad</a> |
        <a href="#">Términos de Servicio</a>
    </p>
</footer>
</body>
</html>