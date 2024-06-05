<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>IES Murcia - Administrador</title>
    <link rel="stylesheet" href="../CSS/administradorStyle.css">
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
    <h1>Bienvenid@, ${usuario.nombre}</h1>
    <section>
        <h2>Lista de usuarios</h2>
        <div>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Tipo de usuario</th>
                    <th>DNI</th>
                    <th>Borrar usuario</th>
                </tr>
                <c:forEach var="usuario" items="${usuarios}">
                    <tr>
                        <td>${usuario.usuarioId}</td>
                        <td>${usuario.nombre}</td>
                        <td>${usuario.tipoUsuario}</td>
                        <td>${usuario.dni}</td>
                        <td style="text-align: center">
                            <form action="borrarUsuario" method="post">
                                <input type="hidden" name="usuarioId" value="${usuario.usuarioId}">
                                <input type="submit" value="❌" class="${usuario.tipoUsuario == 'Administrador' ? 'btn-disabled' : ''}" ${usuario.tipoUsuario == 'Administrador' ? 'disabled' : ''}>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </section>

    <section>
        <h2>Incidencias</h2>
        <div>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Usuario</th>
                    <th>Fecha</th>
                    <th>Descripción</th>
                    <th>Resolver incidencia</th>
                </tr>
                <c:forEach var="entry" items="${incidencia}">
                    <c:forEach var="incidencia" items="${entry.value}">
                        <tr>
                            <td>${incidencia.incidenciaId}</td>
                            <td>${entry.key.nombre}</td>
                            <td>${incidencia.fechaIncidencia}</td>
                            <td>${incidencia.incidencia}</td>
                            <td style="text-align: center">
                                <form method="post">
                                    <input type="submit" value="🛠️">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </c:forEach>
            </table>

            <form action="buscarIncidencias" method="post" class="busqueda-incidencias-form">
                <label>
                    <input type="text" name="busqueda" placeholder="Buscar incidencias - Palabra clave" class="busqueda-input">
                </label>
                <input type="submit" value="Buscar" class="busqueda-submit">
            </form>

            <c:if test="${not empty listaBusqueda}">
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Usuario</th>
                        <th>Fecha</th>
                        <th>Descripción</th>
                        <th>Resolver incidencia</th>
                    </tr>
                    <c:forEach var="incidencia" items="${listaBusqueda}">
                        <tr>
                            <td>${incidencia.incidenciaId}</td>
                            <td>${incidencia.usuario.nombre}</td>
                            <td>${incidencia.fechaIncidencia}</td>
                            <td>${incidencia.incidencia}</td>
                            <td style="text-align: center">
                                <form method="post">
                                    <input type="submit" value="🛠️">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
    </section>
</main>
<footer>
    <p>&copy; 2024 IES Murcia. Todos los derechos reservados.</p>
    <p>
        <a href="#">Política de Privacidad</a> |
        <a href="#">Términos de Servicio</a>
    </p>
</footer>
</body>
</html>