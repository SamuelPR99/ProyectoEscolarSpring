package com.daw.proyectoescolar.servicios.recomendador;

import java.util.ArrayList;
import java.util.Scanner;

import com.daw.proyectoescolar.entidades.Alumno;
import com.daw.proyectoescolar.entidades.Tarea;
import com.daw.proyectoescolar.entidades.UsuarioBase;
import com.daw.proyectoescolar.repositorio.Colores;
import com.daw.proyectoescolar.repositorio.ListadoUsuarios;

public class SistemaRecomendacion {
	
	// Atributos
	
	protected ArrayList<UsuarioBase> usuarios;
    protected ArrayList<Tarea> listaDeTareas;

    // Constructores

    public SistemaRecomendacion() {
    	this.usuarios = ListadoUsuarios.obtenerUsuarios();
        this.listaDeTareas = Tarea.obtenerTodasLasTareas();
    }
    
    public SistemaRecomendacion(ArrayList<UsuarioBase> usuarios) {
        this.usuarios = usuarios;
        
     // Inicialización del ArrayList de tareas
        listaDeTareas = Tarea.obtenerTodasLasTareas();
    }
    
    // Metodos
    
    // Consultar la tarea pendiente del alumno
    public void consultarTareasPendientes(Alumno alumno) {
        ArrayList<Tarea> tareasAsignadas = alumno.getTareasAsignadas();

        if (tareasAsignadas.isEmpty()) {
            System.out.println(Colores.ANSI_GREEN + "No tienes tareas pendientes." + Colores.ANSI_RESET);
        } else {
            System.out.println("Tareas Pendientes:");
            for (Tarea tarea : tareasAsignadas) {
                System.out.println("Tipo: " + tarea.getTipo());
            }
        }
    }

    // Marcar la tarea actual del alumno como completada
    public void marcarTareaCompletada(Alumno alumno, Scanner sc) {
        ArrayList<Tarea> tareasAsignadas = alumno.getTareasAsignadas();

        if (tareasAsignadas.isEmpty()) {
            System.out.println(Colores.ANSI_GREEN + "No tienes tareas pendientes para entregar." + Colores.ANSI_RESET);
        } else {
            System.out.println("Tareas Pendientes:");
            for (int i = 0; i < tareasAsignadas.size(); i++) {
                System.out.println((i + 1) + ". Tipo: " + tareasAsignadas.get(i).getTipo());
            }

            System.out.print("Seleccione el número de la tarea que va a entregar: ");
            int numeroTarea = sc.nextInt();
            sc.nextLine(); // Si no pongo esto, el scanner no lee bien el siguiente string
            
            if (numeroTarea >= 1 && numeroTarea <= tareasAsignadas.size()) {
                Tarea tareaEntregada = tareasAsignadas.remove(numeroTarea - 1);
                System.out.println(Colores.ANSI_GREEN + "Tarea \"" + tareaEntregada.getTipo() + "\" entregada correctamente." + Colores.ANSI_RESET);
            } else {
                System.err.println("Numero de tarea no válido.");
            }
        }
    }

    // Ver las notas de todos los alumnos
    public void verNotasAlumnos() {

		System.out.println("Notas de los alumnos:");
		ArrayList<Alumno> alumnos = obtenerAlumnos(usuarios);

		if (alumnos.isEmpty()) {
			System.err.println("No hay alumnos para mostrar notas.");
			return;
		}

		for (Alumno alumno : alumnos) {
			System.out.println(alumno.getNombre() + " - Nota: " + alumno.getNota());
		}
    	
		}
    

    // Modificar la nota de un alumno
    public void modificarNotaAlumno(Scanner sc) {

		System.out.println("Lista de alumnos:");
		ArrayList<Alumno> alumnos = obtenerAlumnos(usuarios);

		if (alumnos.isEmpty()) {
			System.err.println("No hay alumnos para modificar notas.");
			return;
		}

		for (int i = 0; i < alumnos.size(); i++) {
			System.out.println((i + 1) + ". " + alumnos.get(i).getNombre());
		}

		System.out.print("Introduzca el numero del alumno a modificar: ");
		int numeroAlumno = sc.nextInt();
		sc.nextLine(); // Si no pongo esto, el scanner no lee bien el siguiente string

		if (numeroAlumno >= 1 && numeroAlumno <= alumnos.size()) {
			System.out.print("Introduzca la nueva nota: ");
			double nuevaNota = sc.nextDouble();
			sc.nextLine(); // Si no pongo esto, el scanner no lee bien el siguiente string

			alumnos.get(numeroAlumno - 1).setNota(nuevaNota);
			System.out.println(Colores.ANSI_GREEN + "Nota modificada correctamente." + Colores.ANSI_RESET);
		} else {
			System.err.println("Numero de alumno no valido.");
		}
    }
    

    // Ver estadísticas de todos los alumnos
    public void verEstadisticas() {
    	
        System.out.println("Estadísticas de los alumnos:");

        ArrayList<Alumno> alumnos = obtenerAlumnos(usuarios);

        if (alumnos.isEmpty()) {
            System.err.println("No hay alumnos para mostrar estadísticas.");
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
    
    // Crear la lista de alumnos mediante la lista usuarios
    public ArrayList<Alumno> obtenerAlumnos(ArrayList<UsuarioBase> usuarios) {
    
		ArrayList<Alumno> alumnos = new ArrayList<>();

		for (UsuarioBase usuario : usuarios) {
			if (usuario.getTipoUsuario().equals("Alumno")) {
				alumnos.add((Alumno) usuario);
			}
		}

		return alumnos;
	}

    // Agregar una nueva tarea
    public void agregarNuevaTarea(Scanner sc) {
    	
        System.out.print("Introduzca el tipo de la nueva tarea: ");
        String tipoTarea = sc.nextLine();

        Tarea nuevaTarea = new Tarea(tipoTarea);
        listaDeTareas.add(nuevaTarea);

        System.out.println(Colores.ANSI_GREEN + "Nueva tarea \"" + tipoTarea + "\" agregada correctamente." + Colores.ANSI_RESET);
    }

    // Modificar una tarea existente
    public void modificarTarea(Scanner sc) {
    	
        if (listaDeTareas.isEmpty()) {
            System.err.println("No hay tareas para modificar.");
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
            sc.nextLine(); // Si no pongo esto, el scanner no lee bien el siguiente string

            System.out.print("Introduzca el nuevo tipo de tarea: ");
            String nuevoTipo = sc.nextLine();

            // Modificar la tarea existente
            Tarea tareaModificada = new Tarea(nuevoTipo);
            listaDeTareas.set(numeroTarea - 1, tareaModificada);

            System.out.println(Colores.ANSI_GREEN + "Tarea modificada correctamente." + Colores.ANSI_RESET);
        } else {
            System.err.println("Numero de tarea no valido.");
        }
        
    }
    
    // Recomendar tarea a un alumno
	public Tarea recomendarTarea(Alumno alumno) {

		double nota = alumno.getNota();
		String tipoTarea;

		if (nota >= 9.0) {
			tipoTarea = "Avanzada";
		} else if (nota >= 7.0) {
			tipoTarea = "Intermedia";
		} else {
			tipoTarea = "Basica";
		}

		// Buscar la primera tarea del tipo recomendado
		for (Tarea tarea : listaDeTareas) {
			if (tarea.getTipo().equals(tipoTarea)) {
				alumno.agregarTarea(tarea);
				return tarea;
				
			}
		}

		// Si no hay tareas del tipo que se recomienda, busca la primera tarea de cualquier tipo
		if (!listaDeTareas.isEmpty()) {
			Tarea tareaRecomendada = listaDeTareas.get(0);
	        alumno.agregarTarea(tareaRecomendada);
			return listaDeTareas.get(0);
		}

		// Si no hay tareas, pos error
		return null;
	}

	// Metodo para simplificar el menu en una sola opcion
	public void recomendarTareaYMostrar(Alumno alumno) {
		
        Tarea tareaRecomendada = recomendarTarea(alumno);
        tareaRecomendada.mostrarRecomendacion();
        
    }
	  
}