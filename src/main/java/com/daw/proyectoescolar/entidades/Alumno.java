package com.daw.proyectoescolar.entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.daw.proyectoescolar.repositorio.Colores;
import com.daw.proyectoescolar.repositorio.Constantes;
import com.daw.proyectoescolar.servicios.logs.GestionLogs;
import com.daw.proyectoescolar.servicios.temas.GestionTemas;
import com.daw.proyectoescolar.servicios.usuarios.GestionUsuarios;
import com.daw.proyectoescolar.servicios.incidencias.GestionIncidencias;

public class Alumno extends UsuarioBase {

	// Atributos
	protected double nota;
	protected List<Tarea> tareasAsignadas = new ArrayList<>();

	// Constructores
	public Alumno() {

	}

	public Alumno(String nombre, String contrasena, double nota) {
		super(nombre, contrasena);
		this.nota = nota;
	}

	public Alumno(String nombre, String contrasena, String dni) {
		super(nombre, contrasena, dni);
	}

	public Alumno(String nombre, String contrasena, String dni, double nota) {
		super(nombre, contrasena, dni);
		this.nota = nota;
	}

	public Alumno(int usuarioId, String nombre, String contrasena, String dni, double nota) {
		super(usuarioId, nombre, contrasena, dni);
		this.nota = nota;
	}

	public Alumno(int usuarioId, String nombre, String contrasena, String dni) {
		super(usuarioId, nombre, contrasena, dni);
	}

	// Getters y setters
	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public List<Tarea> getTareasAsignadas() {
		return tareasAsignadas;
	}

	public void setTareasAsignadas(List<Tarea> tareasAsignadas) {
		this.tareasAsignadas = tareasAsignadas;

		// Metodos

	}

	public void agregarTarea(Tarea tarea) {
		tareasAsignadas.add(tarea);
	}

	public void eliminarTarea(Tarea tarea) {
		tareasAsignadas.remove(tarea);
	}

	@Override
	public String getTipoUsuario() {
		return Constantes.ALUMNO;
	}

	@Override
	public void incidenciasMenu(Scanner sc, UsuarioBase usuario) {
		GestionIncidencias gestionIncidencias = new GestionIncidencias();

		String opcion;
		String opcion2;

		do {

			System.out.println(Colores.ANSI_YELLOW + Colores.ANSI_UNDERLINE + "\nSeleccione una opcion:\n"
					+ Colores.ANSI_RESET + Colores.ANSI_YELLOW + "1. Ver incidencias\n" + "2. Crear incidencia\n"
					+ "3. Salir del menu" + Colores.ANSI_RESET);

			opcion = sc.nextLine().toLowerCase();

			switch (opcion) {

				case "1", "ver incidencias":
					GestionLogs.logOpcionMenu(Constantes.MENU_ALUMNOS, "Ver incidencias");
					gestionIncidencias.verIncidenciasAlumno(usuario.getUsuarioId(), this.nombre);
					gestionIncidencias.verIncidenciasAplicacion(usuario.getUsuarioId(), this.nombre);
					break;

				case "2", "crear incidencia":
					GestionLogs.logOpcionMenu(Constantes.MENU_ALUMNOS, "Crear incidencia");
				System.out.println(Colores.ANSI_PURPLE + Colores.ANSI_UNDERLINE + "Elige el tipo de incidencia que quieres crear: \n"
						+ Colores.ANSI_RESET + "\n1 - Incidencia de Alumno\n2 - Incidencia de Aplicacion");
				
				opcion2 = sc.nextLine().toLowerCase();
				
				if(opcion2.equals("1") || opcion2.equals("incidencia de alumno")) {
				gestionIncidencias.crearIncidenciaAlumno(sc, this);
				} else if(opcion2.equals("2") || opcion2.equals("incidencia de aplicacion")) {
				gestionIncidencias.crearIncidenciaAplicacion(sc, this);
				} else {
					System.err.println("Por favor, introduce una opción válida");
				}
				break;

				case "3", "salir del menu", "salir", "salir del":
					GestionLogs.logOpcionMenu(Constantes.MENU_ALUMNOS, "Salir del menu");
					System.out.println(Colores.ANSI_BOLD + "Saliendo del menu de incidencias..." + Colores.ANSI_RESET);
					break;

				default:
					System.err.println("Opcion no valida. Por favor, elige una opcion valida.");
					GestionLogs.errorLogs("Opcion no valida en el menu de incidencias de alumno. " + opcion + " no es una opcion valida.");
			}

		} while (!opcion.equals("3") && !opcion.contains("salir"));
	}

	@Override
	public void verMenu(Scanner sc, List<UsuarioBase> usuarios, List<Alumno> alumnos, UsuarioBase usuario) {

		GestionUsuarios gestor = new GestionUsuarios();
		GestionTemas gestorTemas = new GestionTemas();

		String opcion;

		do {

			System.out.println(Colores.ANSI_YELLOW + Colores.ANSI_UNDERLINE + "\nSeleccione una opcion:\n"
					+ Colores.ANSI_RESET + Colores.ANSI_YELLOW + "1. Ver nota\n" + "2. Recomendar tarea\n"
					+ "3. Entregar tarea\n" + "4. Cambiar contraseña\n" + "5. Menu Incidencias\n" +  "6. Salir del menu" + Colores.ANSI_RESET);

			opcion = sc.nextLine().toLowerCase();

			switch (opcion) {

			case "1", "ver nota":
				GestionLogs.logOpcionMenu(Constantes.MENU_ALUMNOS, "Ver nota");
				System.out.println("Nota actual: " + getNota());
				break;

			case "2", "recomendar tarea":
				GestionLogs.logOpcionMenu(Constantes.MENU_ALUMNOS, "Recomendar tarea");
				gestorTemas.recomendarTareaYMostrar(this);
				break;

			case "3", "entregar tarea":
				GestionLogs.logOpcionMenu(Constantes.MENU_ALUMNOS, "Entregar tarea bbdd");
				gestorTemas.marcarTareaCompletada(sc, this.usuarioId);
				break;

			case "4", "cambiar contraseña":
				GestionLogs.logOpcionMenu(Constantes.MENU_ALUMNOS, "Cambiar contraseña");
				gestor.cambiarContrasena(sc, this);
				break;

			case "5", "menu incidencias":
				GestionLogs.logOpcionMenu(Constantes.MENU_ALUMNOS, "Menu incidencias");
				incidenciasMenu(sc, usuario);
				break;

			case "6", "salir del menu", "salir", "salir del":
				GestionLogs.logOpcionMenu(Constantes.MENU_ALUMNOS, "Salir del menu");
				System.out.println(Colores.ANSI_BOLD + "Saliendo del menu de alumno..." + Colores.ANSI_RESET);
				break;

			default:
				System.err.println("Opcion no valida. Por favor, elige una opcion valida.");
				GestionLogs.errorLogs("Opcion no valida en el menu de alumno. " + opcion + " no es una opcion valida.");
			}

		} while (!opcion.equals("6") && !opcion.contains("salir"));
	}

}