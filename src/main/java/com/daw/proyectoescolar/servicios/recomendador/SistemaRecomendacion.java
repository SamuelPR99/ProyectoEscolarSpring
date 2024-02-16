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
	
	protected ArrayList<UsuarioBase> usuarios;
    protected ArrayList<Tarea> listaDeTareas = new ArrayList<>();

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

    // Método para mostrar la nota de un alumno
    public void mostrarNota(Alumno alumno) {
        System.out.println("Nota de " + alumno.getNombre() + ": " + alumno.getNota());
    }
    
    // Método para consultar tareas pendientes de un alumno
    public void consultarTareasPendientes(Alumno alumno) {
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
    public void marcarTareaCompletada(Alumno alumno, Scanner sc) {
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

    // Metodo para ver las notas de todos los alumnos
    public void verNotasAlumnos() {
        System.out.println("Notas de los alumnos:");

        for (Usuario usuario : usuarios) {
            if (usuario instanceof Alumno) {
                Alumno alumno = (Alumno) usuario;
                System.out.println(alumno.getNombre() + ": " + alumno.getNota());
            }
        }
    }

    // Metodo para modificar la nota de un alumno
    public void modificarNotaAlumno(Scanner sc) {
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
    public void verEstadisticas() {
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
    public ArrayList<Alumno> obtenerAlumnos() {
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
    public void modificarTarea(Scanner sc) {
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
    
   
        
}
