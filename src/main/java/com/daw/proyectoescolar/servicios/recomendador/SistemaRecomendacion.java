package com.daw.proyectoescolar.servicios.recomendador;

import java.util.ArrayList; 
import java.util.Scanner;

import com.daw.proyectoescolar.entidades.Alumno;
import com.daw.proyectoescolar.entidades.Profesor;
import com.daw.proyectoescolar.entidades.Tarea;
import com.daw.proyectoescolar.entidades.Usuario;
import com.daw.proyectoescolar.entidades.UsuarioBase;
import com.daw.proyectoescolar.repositorio.Colores;

public class SistemaRecomendacion {
	
	// Atributos
	
	private ArrayList<UsuarioBase> usuarios;
    private ArrayList<Tarea> listaDeTareas = new ArrayList<>();

    // Constructores

    public SistemaRecomendacion() {
    	
    	// Inicialización del ArrayList de usuarios
        usuarios = new ArrayList<UsuarioBase>();

        // Agregar algunos datos de ejemplo
        
        // Profesores
        usuarios.add(new Profesor("Guillamon", "pass1"));
        usuarios.add(new Profesor("Lidia", "pass2"));
        usuarios.add(new Profesor("David", "pass3"));
        usuarios.add(new Profesor("Paco", "pass4"));

        // Alumnos
        usuarios.add(new Alumno("Samuel", "123", 9.0));
        usuarios.add(new Alumno("Hugo", "123", 5.0));
        usuarios.add(new Alumno("Enrique", "123", 7.5));
        usuarios.add(new Alumno("Carlos", "123", 3.0));
        
    }
    
    // Metodos
    
    public Usuario login(Scanner sc) {
        System.out.print("Introduzca su usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Introduzca su contraseña: ");
        String contraseña = sc.nextLine();

        for (Usuario u : usuarios) {
            if (u.getNombre().equals(usuario) && u.getContraseña().equals(contraseña)) {
                return u;
            }
        }

        return null;
    }

 // Método para el menú de un alumno
    public void menuAlumno(Usuario usuario, Scanner sc) {
        if (usuario instanceof Alumno) {
            Alumno alumno = (Alumno) usuario;
            boolean salir = false;

            while (!salir) {
                System.out.println(Colores.ANSI_YELLOW + "\nSeleccione una opción:\n"
                		+ "1. Ver nota\n"
                		+ "2. Recomendar tarea\n"
                		+ "3. Consultar tareas pendientes\n"
                		+ "4. Entregar tarea\n"
                		+ "5. Salir del menu" + Colores.ANSI_RESET);

                String opcion = sc.nextLine().toLowerCase();
           
                switch (opcion) {
                    case "1", "ver nota":
                        mostrarNota(alumno);
                        break;

                    case "2", "recomendar tarea":
                        alumno.recomendarTarea();
                        break;

                    case "3", "consultar tareas pendientes":
                        consultarTareasPendientes(alumno);
                        break;

                    case "4", "entregar tarea":
                        marcarTareaCompletada(alumno, sc);
                        break;

                    case "5", "salir del menu":
                        salir = true;
                        System.out.println(Colores.ANSI_BOLD + "Saliendo del menú de alumno..." + Colores.ANSI_RESET);
                        break;

                    default:
                        System.err.println("Opción no válida. Por favor, elige una opción válida.");
                }
            }
        } else {
            System.err.println("Error: El usuario no es un alumno.");
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
            System.out.println(Colores.ANSI_GREEN + "No tienes tareas pendientes." + Colores.ANSI_RESET);
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
            System.out.println(Colores.ANSI_GREEN + "No tienes tareas pendientes para entregar." + Colores.ANSI_RESET);
        } else {
            System.out.println("Tareas Pendientes:");
            for (int i = 0; i < tareasPendientes.size(); i++) {
                System.out.println((i + 1) + ". Tipo: " + tareasPendientes.get(i).getTipo());
            }

            System.out.print("Seleccione el número de la tarea que va a entregar: ");
            int numeroTarea = sc.nextInt();

            if (numeroTarea >= 1 && numeroTarea <= tareasPendientes.size()) {
                Tarea tareaEntregada = tareasPendientes.remove(numeroTarea - 1);
                System.out.println(Colores.ANSI_GREEN + "Tarea \"" + tareaEntregada.getTipo() + "\" entregada correctamente." + Colores.ANSI_RESET);
            } else {
                System.err.println("Número de tarea no válido.");
            }
        }
    }

    // Método para el menú de un profesor
    public void menuProfesor(Usuario usuario, Scanner sc) {
        if (usuario instanceof Profesor) {
            Profesor profesor = (Profesor) usuario;
            boolean salir = false;

            while (!salir) {
                System.out.println(Colores.ANSI_YELLOW + "\nSeleccione una opción:\n"
                		+ "1. Ver notas de alumnos\n"
                		+ "2. Modificar nota de alumno\n"
                		+ "3. Ver estadísticas\n"
                		+ "4. Agregar nueva tarea\n"
                		+ "5. Modificar tarea\n"
                		+ "6. Salir del menu" + Colores.ANSI_RESET);

                String opcion = sc.nextLine().toLowerCase();
                
                switch (opcion) {
                    case "1", "ver notas de alumnos":
                        verNotasAlumnos();
                        break;

                    case "2", "modificar nota de alumno":
                        modificarNotaAlumno(sc);
                        break;

                    case "3", "ver estadísticas":
                        verEstadisticas();
                        break;

                    case "4", "agregar nueva tarea":
                        agregarNuevaTarea(sc);
                        break;

                    case "5", "modificar tarea":
                        modificarTarea(sc);
                        break;

                    case "6", "salir del menu":
                        salir = true;
                        System.out.println(Colores.ANSI_BOLD +"Saliendo del menú de profesor..." + Colores.ANSI_RESET);
                        break;

                    default:
                        System.err.println("Opción no válida. Por favor, elige una opción válida.");
                }
            }
        } else {
            System.err.println("Error: El usuario no es un profesor.");
        }
    }

    // Metodo para ver las notas de todos los alumnos
    private void verNotasAlumnos() {
        System.out.println("Notas de los alumnos:");

        for (Usuario usuario : usuarios) {
            if (usuario instanceof Alumno) {
                Alumno alumno = (Alumno) usuario;
                System.out.println(alumno.getNombre() + ": " + alumno.getNota());
            }
        }
    }

    // Metodo para modificar la nota de un alumno
    private void modificarNotaAlumno(Scanner sc) {
        System.out.print("Introduzca el nombre del alumno: ");
        String nombreAlumno = sc.nextLine();

        // Buscar al alumno por nombre
        Alumno alumnoEncontrado = null;
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Alumno && usuario.getNombre().equals(nombreAlumno)) {
                alumnoEncontrado = (Alumno) usuario;
                break;
            }
        }

       try {
            System.out.print("Introduzca la nueva nota para " + nombreAlumno + ": ");
            double nuevaNota = sc.nextDouble();
            alumnoEncontrado.setNota(nuevaNota);
            System.out.println(Colores.ANSI_GREEN + "Nota modificada correctamente para " + nombreAlumno + Colores.ANSI_RESET);
        } 
       
       catch (NullPointerException e) {
            System.err.println("Alumno no encontrado");
        }
    }

    // Metodo para ver estadísticas de todos los alumnos
    private void verEstadisticas() {
        System.out.println("Estadísticas de los alumnos:");

        ArrayList<Alumno> alumnos = obtenerAlumnos();

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

        System.out.println(Colores.ANSI_PURPLE + "Promedio de notas: " + promedio + Colores.ANSI_RESET);
        System.out.println(Colores.ANSI_GREEN + "Nota más alta: " + notaMaxima + Colores.ANSI_RESET);
        System.out.println(Colores.ANSI_RED + "Nota más baja: " + notaMinima + Colores.ANSI_RESET);
    }
    
    // Metodo para obtener la lista de alumnos
    private ArrayList<Alumno> obtenerAlumnos() {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Alumno) {
                alumnos.add((Alumno) usuario);
            }
        }
        return alumnos;
    }

    // Metodo para agregar una nueva tarea
    public void agregarNuevaTarea(Scanner sc) {
        System.out.print("Introduzca el tipo de la nueva tarea: ");
        String tipoTarea = sc.nextLine();

        Tarea nuevaTarea = new Tarea(tipoTarea);
        listaDeTareas.add(nuevaTarea);

        System.out.println(Colores.ANSI_GREEN + "Nueva tarea \"" + tipoTarea + "\" agregada correctamente." + Colores.ANSI_RESET);
    }

    // Metodo para modificar una tarea existente
    private void modificarTarea(Scanner sc) {
        if (listaDeTareas.isEmpty()) {
            System.out.println(Colores.ANSI_RED + "No hay tareas para modificar." + Colores.ANSI_RESET);
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

            System.out.println(Colores.ANSI_GREEN + "Tarea modificada correctamente." + Colores.ANSI_RESET);
        } else {
            System.err.println("Número de tarea no válido.");
        }
        
    }
    
    // Metodo que va a ejecutar el Main
    public void EjecutarRecomendadorTareas(Scanner sc) {
    	
    	System.out.println(Colores.ANSI_UNDERLINE + Colores.ANSI_YELLOW_BACKGROUND + Colores.ANSI_BOLD + Colores.ANSI_CYAN + 
    			"Bienvenido al sistema de recomendación de tareas" + Colores.ANSI_RESET);

        Usuario usuario = login(sc);

       try {
            System.out.println("\nBienvenido " + Colores.ANSI_BOLD + usuario.getTipoUsuario() + Colores.ANSI_RESET);

            if (usuario instanceof Alumno) {
                menuAlumno((Alumno) usuario, sc);
            } else if (usuario instanceof Profesor) {
                menuProfesor((Profesor) usuario, sc);
            }
        } 
       
       catch (NullPointerException e) {
            System.err.println("Usuario o contraseña incorrectos");
        }
        
    }
        
}
