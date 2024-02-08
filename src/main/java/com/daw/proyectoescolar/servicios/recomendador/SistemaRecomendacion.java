package com.daw.proyectoescolar.servicios.recomendador;

import java.util.ArrayList;
import java.util.Scanner;

import com.daw.proyectoescolar.repositorio.Colores;

public class SistemaRecomendacion {
	
	// Atributos
	
    private ArrayList<Profesor> profesores;
    private ArrayList<Alumno> alumnos;
    private ArrayList<Tarea> listaDeTareas = new ArrayList<>();

    // Metodos

    public SistemaRecomendacion() {
    	
        // Inicializar las listas de profesores y alumnos
        profesores = new ArrayList<Profesor>();
        alumnos = new ArrayList<Alumno>();
        
        // Agregar algunos datos de ejemplo
        
        // Profesores
        profesores.add(new Profesor("Guillamon", "pass1"));
        profesores.add(new Profesor("Lidia", "pass2"));
        profesores.add(new Profesor("David", "pass3"));
        profesores.add(new Profesor("Paco", "pass4"));

        // Alumnos
        alumnos.add(new Alumno("Samuel", "123", 9.0));
        alumnos.add(new Alumno("Hugo", "123", 5.0));
        alumnos.add(new Alumno("Enrique", "123", 7.5));
        alumnos.add(new Alumno("Carlos", "123", 3.0));
        
    }

    public Usuario login(Scanner sc) {
        System.out.print("Introduzca su usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Introduzca su contraseña: ");
        String contraseña = sc.nextLine();

        for (Profesor profesor : profesores) {
            if (profesor.getNombre().equals(usuario) && profesor.getContraseña().equals(contraseña)) {
                return profesor;
            }
        }

        for (Alumno alumno : alumnos) {
            if (alumno.getNombre().equals(usuario) && alumno.getContraseña().equals(contraseña)) {
                return alumno;
            }
        }

        return null;
    }

    public void menuAlumno(Alumno alumno, Scanner sc) {
        boolean salir = false;

        while (!salir) {
            System.out.println("\nSeleccione una opción:\n1. Ver nota\n2. Recomendar tarea\n3. Consultar tareas pendientes\n4. Entregar tarea\n5. Salir del menú");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    mostrarNota(alumno);
                    break;

                case 2:
                    alumno.recomendarTarea();
                    break;

                case 3:
                    consultarTareasPendientes(alumno);
                    break;

                case 4:
                    marcarTareaCompletada(alumno, sc);
                    break;

                case 5:
                    salir = true;
                    System.out.println("Saliendo del menú de alumno...");
                    break;

                default:
                    System.err.println("Opción no válida. Por favor, elige una opción válida.");
            }
        }
    }

    // Método para mostrar la nota de un alumno
    private void mostrarNota(Alumno alumno) {
        System.out.println("Nota de " + alumno.getNombre() + ": " + alumno.getNota());
    }

    // Método para consultar tareas pendientes de un alumno
    private void consultarTareasPendientes(Alumno alumno) {
        ArrayList<Tarea> tareasPendientes = alumno.getTareasPendientes();

        if (tareasPendientes.isEmpty()) {
            System.out.println("No tienes tareas pendientes.");
        } else {
            System.out.println("Tareas Pendientes:");
            for (Tarea tarea : tareasPendientes) {
                System.out.println("Tipo: " + tarea.getTipo());
            }
        }
    }
    
 // Método para marcar una tarea como completada
    private void marcarTareaCompletada(Alumno alumno, Scanner sc) {
        ArrayList<Tarea> tareasPendientes = alumno.getTareasPendientes();

        if (tareasPendientes.isEmpty()) {
            System.out.println("No tienes tareas pendientes para entregar.");
        } else {
            System.out.println("Tareas Pendientes:");
            for (int i = 0; i < tareasPendientes.size(); i++) {
                System.out.println((i + 1) + ". Tipo: " + tareasPendientes.get(i).getTipo());
            }

            System.out.print("Seleccione el número de la tarea que va a entregar: ");
            int numeroTarea = sc.nextInt();

            if (numeroTarea >= 1 && numeroTarea <= tareasPendientes.size()) {
                Tarea tareaEntregada = tareasPendientes.remove(numeroTarea - 1);
                System.out.println("Tarea \"" + tareaEntregada.getTipo() + "\" entregada correctamente.");
            } else {
                System.err.println("Número de tarea no válido.");
            }
        }
    }

    public void menuProfesor(Profesor profesor, Scanner sc) {
        boolean salir = false;

        while (!salir) {
            System.out.println("\nSeleccione una opción:\n1. Ver notas de alumnos\n2. Modificar nota de alumno\n3. Ver estadísticas\n4. Agregar nueva tarea\n5. Modificar tarea\n6. Salir del menú");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    verNotasAlumnos(alumnos);
                    break;

                case 2:
                    modificarNotaAlumno(alumnos, sc);
                    break;

                case 3:
                    verEstadisticas(alumnos);
                    break;

                case 4:
                    agregarNuevaTarea(sc);
                    break;

                case 5:
                    modificarTarea(sc);
                    break;

                case 6:
                    salir = true;
                    System.out.println("Saliendo del menú de profesor...");
                    break;

                default:
                    System.err.println("Opción no válida. Por favor, elige una opción válida.");
            }
        }
    }

    // Método para ver las notas de todos los alumnos
    private void verNotasAlumnos(ArrayList<Alumno> alumnos) {
        System.out.println("Notas de los alumnos:");

        for (Alumno alumno : alumnos) {
            System.out.println(alumno.getNombre() + ": " + alumno.getNota());
        }
    }

 // Método para modificar la nota de un alumno
    private void modificarNotaAlumno(ArrayList<Alumno> alumnos, Scanner sc) {
        System.out.print("Introduzca el nombre del alumno: ");
        String nombreAlumno = sc.nextLine();

        // Buscar al alumno por nombre
        Alumno alumnoEncontrado = null;
        for (Alumno alumno : alumnos) {
            if (alumno.getNombre().equals(nombreAlumno)) {
                alumnoEncontrado = alumno;
                break;
            }
        }

        if (alumnoEncontrado != null) {
            System.out.print("Introduzca la nueva nota para " + nombreAlumno + ": ");
            double nuevaNota = sc.nextDouble();
            alumnoEncontrado.setNota(nuevaNota);
            System.out.println("Nota modificada correctamente para " + nombreAlumno);
        } else {
            System.err.println("Alumno no encontrado");
        }
    }

        // Método para ver estadísticas de todos los alumnos
        private void verEstadisticas(ArrayList<Alumno> alumnos) {
            System.out.println("Estadísticas de los alumnos:");

            if (alumnos.isEmpty()) {
                System.out.println(Colores.ANSI_RED + "No hay alumnos para mostrar estadísticas." + Colores.ANSI_RESET);
                return;
            }

            double sumaNotas = 0;
            double notaMaxima = Double.MIN_VALUE;
            double notaMinima = Double.MAX_VALUE;

            for (Alumno alumno : alumnos) {
                double nota = alumno.getNota();
                sumaNotas += nota;

                if (nota > notaMaxima) {
                    notaMaxima = nota;
                }

                if (nota < notaMinima) {
                    notaMinima = nota;
                }
            }

            double promedio = sumaNotas / alumnos.size();

            System.out.println("Promedio de notas: " + promedio);
            System.out.println(Colores.ANSI_GREEN + "Nota más alta: " + notaMaxima + Colores.ANSI_RESET);
            System.out.println("Nota más baja: " + notaMinima);
        }

     // Método para agregar una nueva tarea
        private void agregarNuevaTarea(Scanner sc) {
            System.out.print("Introduzca el tipo de la nueva tarea: ");
            String tipoTarea = sc.nextLine();

            Tarea nuevaTarea = new Tarea(tipoTarea);
            listaDeTareas.add(nuevaTarea);

            System.out.println("Nueva tarea \"" + tipoTarea + "\" agregada correctamente.");
        }

        private void modificarTarea(Scanner sc) {
            if (listaDeTareas.isEmpty()) {
                System.out.println("No hay tareas para modificar.");
                return;
            }

            System.out.println("Lista de tareas:");
            for (int i = 0; i < listaDeTareas.size(); i++) {
                System.out.println((i + 1) + ". " + listaDeTareas.get(i).getTipo());
            }

            System.out.print("Introduzca el número de la tarea a modificar: ");
            int numeroTarea = sc.nextInt();

            // Asumiendo que la numeración de tareas comienza desde 1
            if (numeroTarea >= 1 && numeroTarea <= listaDeTareas.size()) {
                sc.nextLine(); 

                System.out.print("Introduzca el nuevo tipo de tarea: ");
                String nuevoTipo = sc.nextLine();

                // Modificar la tarea existente
                Tarea tareaModificada = new Tarea(nuevoTipo);
                listaDeTareas.set(numeroTarea - 1, tareaModificada);

                System.out.println("Tarea modificada correctamente.");
            } else {
                System.err.println("Número de tarea no válido.");
            }
        }
}
