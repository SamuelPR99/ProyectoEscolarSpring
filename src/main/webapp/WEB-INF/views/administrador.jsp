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
                        <td>
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
</main>
<footer>
    <p>&copy; 2024 IES Murcia. Todos los derechos reservados.</p>
    <p>
        <a href="#">Política de Privacidad</a> |
        <a href="#">Términos de Servicio</a> |
        <a href="#">Contacto</a>
    </p>
</footer>
</body>
</html>
