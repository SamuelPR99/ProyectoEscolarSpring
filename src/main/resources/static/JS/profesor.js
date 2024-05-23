// Desplegar las secciones de los alumnos
document.addEventListener("DOMContentLoaded", function() {
    const sectionHeaders = document.querySelectorAll(".collapsible-header");

    sectionHeaders.forEach(function(header) {
        header.addEventListener("click", function() {
            var section = header.parentElement;
            var content = section.querySelector(".collapsible-content");

            // abrir o cerrar la sección
            section.classList.toggle("open");

            // mostrar u ocultar el contenido
            if (section.classList.contains("open")) {
                content.style.maxHeight = content.scrollHeight + "px";
            } else {
                content.style.maxHeight = "0";
            }
        });
    });

    // Desplegar el modal para asignar una tarea
    document.getElementById('asignarTareaForm').addEventListener('submit', function() {
        alert('La tarea ha sido asignada correctamente.');
    });

    // Obtener los datos de los alumnos y las tareas para generar el gráfico
    $(document).ready(function () {
        var etiquetas = datosAlumnos.map(alumno => alumno.nombre);
        var notas = datosAlumnos.map(alumno => alumno.nota);
        var nombres = Object.keys(datosTareasEntregadasATiempoPorAlumno);
        var tareasEntregadasATiempo = nombres.map(nombre => datosTareasEntregadasATiempoPorAlumno[nombre]);
        var notaMedia = nombres.map(nombre => datosNotaMediaPorAlumno[nombre]); // Obtener las notas medias
        var coloresFondo = notas.map(nota => colorPorNota(nota)); // Asignar un color basado en la nota
        var coloresFondoTareas = tareasEntregadasATiempo.map(() => 'rgba(8,202,0,0.5)'); // Color para las tareas entregadas a tiempo
        var coloresFondoNotaMedia = notaMedia.map(() => 'rgba(255,193,7,0.5)'); // Color para las notas medias
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
                },
                {
                    label: 'Nota media de tareas',
                    data: notaMedia, // Usar las notas medias
                    backgroundColor: coloresFondoNotaMedia, // Usar los colores asignados para las notas medias
                    borderColor: 'rgb(255,193,7)',
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

    // Función para asignar un color basado en la nota
    function colorPorNota(nota) {
        if (nota >= 9) return 'rgba(75, 192, 192, 0.2)'; // Verde claro para notas altas
        else if (nota >= 7) return 'rgba(54, 162, 235, 0.2)'; // Azul para notas medias
        else if (nota >= 5) return 'rgba(255, 206, 86, 0.2)'; // Amarillo para notas suficientes
        else return 'rgba(255, 99, 132, 0.2)'; // Rojo para notas bajas
    }
});
