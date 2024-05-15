package com.daw.proyectoescolar.servicios.temas;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.daw.proyectoescolar.entidades.Alumno;
import com.daw.proyectoescolar.entidades.Tarea;
import com.daw.proyectoescolar.entidades.Temas;
import com.daw.proyectoescolar.repositorio.Colores;
import com.daw.proyectoescolar.repositorio.TemasRepo;
import com.daw.proyectoescolar.repositorio.UsuariosRepo;

public class GestionTemas {

	TemasRepo tRepo = new TemasRepo();
	private ArrayList<Tarea> listaDeTareas = new TemasRepo().archivoTareas();

	// Constructores
	public GestionTemas() {

	}

	// Metodos

	// Muestra los temas
	public void mostrarTemas() {

		HashMap<Integer, ArrayList<Temas>> temas = tRepo.hashTemas();

		for (Integer tema : temas.keySet()) { // Se recorren los temas
			System.out.println(tema + " - " + temas.get(tema).get(0).getNombre()); // Se muestra el nombre de cada tema
		}

	}

	// Muestra el menu de temas y tareas y permite seleccionar un tema y ver sus
	// tareas asociadas
	public void menuTemas(Scanner sc) {

		System.out.println(Colores.ANSI_UNDERLINE + Colores.ANSI_YELLOW + "Seleccione un tema: " + Colores.ANSI_RESET);
		mostrarTemas();
		int numTema = sc.nextInt();
		sc.nextLine(); // Limpiar buffer

		HashMap<Integer, ArrayList<Temas>> temas = tRepo.hashTemas();
		ArrayList<Temas> tema = temas.get(numTema);
		System.out.println(tema.get(0).getListaTareas() + "\n");

	}

	// Recomendar una tarea al alumno
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

		// Si no hay tareas del tipo que se recomienda, busca la primera tarea de
		// cualquier tipo
		if (!listaDeTareas.isEmpty()) {
			Tarea tareaRecomendada = listaDeTareas.get(0);
			alumno.agregarTarea(tareaRecomendada);
			return listaDeTareas.get(0);
		}

		return null;
	}

	// Recomendar una tarea al alumno y mostrarla
	public void recomendarTareaYMostrar(Alumno alumno) {

		Tarea tareaRecomendada = recomendarTarea(alumno);
		tareaRecomendada.mostrarRecomendacion();
	}

	// Asignar una tarea a los alumnos desde profesor
	public void asignarTarea(Scanner sc, int idProfesor) {

		UsuariosRepo uRepo = new UsuariosRepo();
		ArrayList<Alumno> alumnos = uRepo.obtenerAlumnos();

		mostrarTemas();

		System.out.println("Por favor, elija un tema:");
		int idTema = sc.nextInt();
		sc.nextLine(); // Limpiar buffer

		// Obtener las tareas del tema elegido
		ArrayList<Tarea> tareasTema = tRepo.archivoTemas().get(idTema - 1).getListaTareas();

		// Mostrar las tareas del tema elegido
		for (int i = 0; i < tareasTema.size(); i++) {
			System.out.println((i + 1) + ". " + tareasTema.get(i).getNombre());
		}

		// Pedir al profesor que elija una tarea
		System.out.println("Por favor, elija una tarea:");
		int idTarea = sc.nextInt();
		sc.nextLine(); // Limpiar buffer

		// Pedir al profesor que introduzca una fecha de expiración
		System.out.println("Por favor, introduzca la fecha de expiración (en formato yyyy-mm-dd):");
		String fechaExpiracionStr = sc.nextLine();
		Date fechaExpiracion = Date.valueOf(fechaExpiracionStr); // Convertir la fecha de String a Date

		// Asignar la tarea a todos los alumnos
		for (Alumno alumno : alumnos) {
			tRepo.asignarTarea(idTarea, alumno.getUsuarioId(), idProfesor, fechaExpiracion);
		}

		System.out.println("Tarea asignada a todos los alumnos con éxito.");
	}

	// Marcar una tarea como completada
	public void marcarTareaCompletada(Scanner sc, int idAlumno) {

		// Obtener las tareas del alumno
		ArrayList<Tarea> tareas = tRepo.obtenerTareasAlumno(idAlumno);

		// Comprobar si el ArrayList de tareas esta vacio
		if (tareas.isEmpty()) {
			System.out.println(Colores.ANSI_BOLD + "No tienes ninguna tarea. 🤷‍♀️" + Colores.ANSI_RESET);
			return;
		}

		// Mostrar las tareas del alumno
		for (int i = 0; i < tareas.size(); i++) {
			System.out.println(tareas.get(i).getTareaId() + ". " + tareas.get(i).getNombre());
		}

		System.out.println("Por favor, elija una tarea:");
		int idTarea = sc.nextInt();
		sc.nextLine(); // Limpiar buffer

		// Marcar la tarea como completada
		tRepo.entregarTarea(idTarea, idAlumno);
		System.out.println(Colores.ANSI_GREEN + "Tarea entregada con exito. 😎" + Colores.ANSI_RESET);
	}

}
