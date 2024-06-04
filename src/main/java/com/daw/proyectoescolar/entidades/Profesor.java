package com.daw.proyectoescolar.entidades;

import java.util.List;
import java.util.Scanner;

import com.daw.proyectoescolar.repositorio.Colores;
import com.daw.proyectoescolar.repositorio.Constantes;
import com.daw.proyectoescolar.servicios.incidencias.GestionIncidencias;
import com.daw.proyectoescolar.servicios.logs.GestionLogs;
import com.daw.proyectoescolar.servicios.temas.GestionTemas;
import com.daw.proyectoescolar.servicios.usuarios.GestionUsuarios;

public class Profesor extends UsuarioBase {

	// Atributos

	// Constructores

	public Profesor() {

	}

	public Profesor(String nombre, String contrasena) {
		super(nombre, contrasena);
	}

	public Profesor(String nombre, String contrasena, String dni) {
		super(nombre, contrasena, dni);
	}

	public Profesor(int usuarioId, String nombre, String contrasena, String dni) {
		super(usuarioId, nombre, contrasena, dni);
	}

	// Getters y setters

	// Metodos

	@Override
	public String getTipoUsuario() {
		return Constantes.PROFESOR;
	}

	@Override
	public void incidenciasMenu(Scanner sc, UsuarioBase usuario) {

		GestionIncidencias gestor = new GestionIncidencias();
		String opcion;
		String opcion2;

		do {

			System.out.println(Colores.ANSI_YELLOW + Colores.ANSI_UNDERLINE + "\nSeleccione una opcion:\n" + Colores.ANSI_RESET + Colores.ANSI_YELLOW
					+ "1. Mostrar incidencias\n"
					+ "2. Crear incidencia\n"
					+ "3. Salir del menu" + Colores.ANSI_RESET);

			opcion = sc.nextLine().toLowerCase();

			switch (opcion) {

				case "1", "mostrar incidencias":
					GestionLogs.logOpcionMenu(Constantes.MENU_INCIDENCIAS, "Mostrar incidencias");
					gestor.verIncidenciasProfesor(usuario.getUsuarioId(), usuario.getNombre());
					gestor.verIncidenciasAplicacion(usuario.getUsuarioId(), usuario.getNombre());
					break;

				case "2", "crear incidencia":
					GestionLogs.logOpcionMenu(Constantes.MENU_INCIDENCIAS, "Crear incidencia");
					System.out.println(Colores.ANSI_PURPLE + Colores.ANSI_UNDERLINE + "Elige el tipo de incidencia que quieres crear: \n"
							+ Colores.ANSI_RESET + "\n1 - Incidencia de Profesor\n2 - Incidencia de Aplicacion");
					
					opcion2 = sc.nextLine().toLowerCase();
					
					if(opcion2.equals("1") || opcion2.equals("incidencia de profesor")) {
					gestor.crearIncidenciaProfesor(sc, this);
					} else if(opcion2.equals("2") || opcion2.equals("incidencia de aplicacion")) {
					gestor.crearIncidenciaAplicacion(sc, this);
					} else {
						System.err.println("Por favor, introduce una opción válida");
					}
					break;

				case "3", "salir del menu":
					GestionLogs.logOpcionMenu(Constantes.MENU_INCIDENCIAS, "Salir del menu");
					System.out.println(Colores.ANSI_BOLD + "Saliendo del menu de incidencias..." + Colores.ANSI_RESET);
					break;

				default:
					System.err.println("Opcion no valida. Por favor, elige una opcion valida.");
					GestionLogs.errorLogs("Opcion no valida en el menu de incidencias. " + opcion + " no es una opcion valida.");

			}

		} while (!opcion.equals("3") && !opcion.contains("salir"));
	}

	// Menu profesor
	@Override
	public void verMenu(Scanner sc, List<UsuarioBase> usuarios, List<Alumno> alumnos, UsuarioBase usuario) {

		GestionUsuarios gestor = new GestionUsuarios();
		GestionTemas temitas = new GestionTemas();

		String opcion;

		do {

			System.out.println(Colores.ANSI_YELLOW + Colores.ANSI_UNDERLINE + "\nSeleccione una opcion:\n"
					+ Colores.ANSI_RESET + Colores.ANSI_YELLOW + "1. Ver lista de temas\n" + "2. Ver lista de alumnos\n"
					+ "3. Modificar nota de alumno\n" + "4. Ver estadisticas\n" + "5. Agregar nueva tarea\n"
					+ "6. Cambiar contraseña\n" + "7. Menu incidencias\n" +"8. Salir del menu" + Colores.ANSI_RESET);

			opcion = sc.nextLine().toLowerCase();

			switch (opcion) {

			case "1", "ver listado de temas":
				GestionLogs.logOpcionMenu(Constantes.MENU_PROFESORES, "Ver listado de temas");
				temitas.menuTemas(sc);
				break;

			case "2", "ver lista de alumnos":
				GestionLogs.logOpcionMenu(Constantes.MENU_PROFESORES, "Ver lista de alumnos");
				gestor.verNotasAlumnos(usuarios);
				break;

			case "3", "modificar nota de alumno":
				GestionLogs.logOpcionMenu(Constantes.MENU_PROFESORES, "Modificar nota de alumno");
				gestor.modificarNotaAlumno(sc, usuarios);
				break;

			case "4", "ver estadisticas":
				GestionLogs.logOpcionMenu(Constantes.MENU_PROFESORES, "Ver estadisticas");
				gestor.verEstadisticas(usuarios);
				break;

			case "5", "agregar nueva tarea":
				GestionLogs.logOpcionMenu(Constantes.MENU_PROFESORES, "Agregar nueva tarea");
				temitas.asignarTarea(sc, this.usuarioId);
				break;

			case "6", "cambiar contraseña":
				GestionLogs.logOpcionMenu(Constantes.MENU_PROFESORES, "Cambiar contraseña");
				gestor.cambiarContrasena(sc, this);
				break;

			case "7", "menu incidencias":
				GestionLogs.logOpcionMenu(Constantes.MENU_PROFESORES, "Menu incidencias");
				incidenciasMenu(sc, usuario);
				break;
	
			case "8", "salir del menu":
				GestionLogs.logOpcionMenu(Constantes.MENU_PROFESORES, "Salir del menu");
				System.out.println(Colores.ANSI_BOLD + "Saliendo del menu de profesor..." + Colores.ANSI_RESET);
		        break;

			default:
				System.err.println("Opcion no valida. Por favor, elige una opcion valida.");
				GestionLogs.errorLogs("Opcion no valida en el menu de profesor. " + opcion + " no es una opcion valida.");

			}

		} while (!opcion.equals("8") && !opcion.contains("salir"));

	}

}