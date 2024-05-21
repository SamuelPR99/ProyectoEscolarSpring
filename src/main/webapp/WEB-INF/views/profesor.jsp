<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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

    <script>
        document.getElementById('asignarTareaForm').addEventListener('submit', function() {
            alert('La tarea ha sido asignada correctamente.');
        });
    </script>

    <!-- Sección para cambiar la nota al alumno -->
    <section class="collapsible-section">
        <h2 class="collapsible-header">Lista de Alumnos</h2>
        <div class="collapsible-content">
            <table>
                <tr>
                    <th>Nombre</th>
                    <th>Nota</th>
                    <th>Cambiar nota</th>
                </tr>
                <c:forEach var="alumno" items="${alumnos}">
                    <tr>
                        <td>${alumno.nombre}</td>
                        <td>${alumno.nota}</td>
                        <td>
                            <form action="cambiarNota" method="post">
                                <input type="hidden" name="idAlumno" value="${alumno.usuarioId}">
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
    document.addEventListener("DOMContentLoaded", function() {
        const sectionHeaders = document.querySelectorAll(".collapsible-header");

        // Toggle section content visibility
        sectionHeaders.forEach(function(header) {
            header.addEventListener("click", function() {
                var section = header.parentElement;
                var content = section.querySelector(".collapsible-content");

                // Toggle the open class
                section.classList.toggle("open");

                // Adjust max-height for smooth transitions
                if (section.classList.contains("open")) {
                    content.style.maxHeight = content.scrollHeight + "px";
                } else {
                    content.style.maxHeight = "0";
                }
            });
        });
    });
</script>

<script>
    // Convertir los datos de JSTL a JavaScript
    var datosAlumnos = [
        <c:forEach var="alumno" items="${alumnos}" varStatus="status">
        { nombre: "${alumno.nombre}", nota: ${alumno.nota}}<c:if test="${!status.last}">,</c:if>
        </c:forEach>
    ];
</script>

<script>
    // Convertir los datos de JSTL a JavaScript
    var datosTareasEntregadasATiempoPorAlumno = {
        <c:forEach var="entry" items="${tareasEntregadasATiempoPorAlumno}" varStatus="status">
        "${entry.key.nombre}": ${entry.value}<c:if test="${!status.last}">,</c:if>
        </c:forEach>
    };
</script>

<script>
    $(document).ready(function () {
        var etiquetas = datosAlumnos.map(alumno => alumno.nombre);
        var notas = datosAlumnos.map(alumno => alumno.nota);
        var nombres = Object.keys(datosTareasEntregadasATiempoPorAlumno);
        var tareasEntregadasATiempo = nombres.map(nombre => datosTareasEntregadasATiempoPorAlumno[nombre]);
        var coloresFondo = notas.map(nota => colorPorNota(nota)); // Asignar un color basado en la nota
        var coloresFondoTareas = tareasEntregadasATiempo.map(() => 'rgba(8,202,0,0.5)'); // Color para las tareas entregadas a tiempo
        console.log(datosTareasEntregadasATiempoPorAlumno);
        var configuracion = {
            type: 'bar',
            data: {
                labels: etiquetas,
                datasets: [{
                    label: 'Nota del alumno',
                    data: notas,
                    backgroundColor: coloresFondo, // Usar los colores asignados
                    borderColor: 'rgb(0,97,161)',
                    borderWidth: 1
                },
                    {
                        label: 'Tareas entregadas a tiempo',
                        data: tareasEntregadasATiempo,
                        backgroundColor: coloresFondoTareas, // Usar los colores asignados para las tareas
                        borderColor: 'rgb(0,123,255)',
                        borderWidth: 1
                    }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        };

        var ctx = document.getElementById('graficoNotas').getContext('2d');
        var graficoNotas = new Chart(ctx, configuracion);
    });
</script>
</script>

<script>
    function colorPorNota(nota) {
        if (nota >= 9) return 'rgba(75, 192, 192, 0.2)'; // Verde claro para notas altas
        else if (nota >= 7) return 'rgba(54, 162, 235, 0.2)'; // Azul para notas medias
        else if (nota >= 5) return 'rgba(255, 206, 86, 0.2)'; // Amarillo para notas suficientes
        else return 'rgba(255, 99, 132, 0.2)'; // Rojo para notas bajas
    }
</script>


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