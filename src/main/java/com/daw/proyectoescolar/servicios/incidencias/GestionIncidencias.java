package com.daw.proyectoescolar.servicios.incidencias;

import java.util.ArrayList;
import java.util.Scanner;

import com.daw.proyectoescolar.entidades.IncidenciaAlumno;
import com.daw.proyectoescolar.entidades.IncidenciaAplicacion;
import com.daw.proyectoescolar.entidades.IncidenciaProfesor;
import com.daw.proyectoescolar.entidades.Incidencias;
import com.daw.proyectoescolar.repositorio.Colores;
import com.daw.proyectoescolar.repositorio.Constantes;
import com.daw.proyectoescolar.repositorio.IncidenciasRepo;
import com.daw.proyectoescolar.servicios.logs.GestionLogs;

public class GestionIncidencias {
	
	// Atributos
	private IncidenciasRepo iRepo = new IncidenciasRepo();
	private ArrayList<Incidencias> listaIncidencias = iRepo.leerIncidencias(new ArrayList<>());

	// Constructores
	public GestionIncidencias() {

	}

	// Getters y Setters
	public ArrayList<Incidencias> getListaIncidencias() {
		return listaIncidencias;
	}

	public void setListaIncidencias(ArrayList<Incidencias> listaIncidencias) {
		this.listaIncidencias = listaIncidencias;
	}

	// Metodos

	// Metodo que muestra el menu principal de la gestion de incidencias
	public void menuPrincipal(Scanner sc) {

		String opcion;

		System.out.println(Colores.ANSI_PURPLE + "\nBienvenido a la gestion de incidencias de nuestra aplicacion!\n"
				+ Colores.ANSI_RESET);

		do {

			System.out.println("1 - Añadir una incidencia\n" + "2 - Listar incidencias\n" + "3 - Eliminar incidencias\n"
					+ "4 - Salir del menú de incidencias\n" + "Introduce una opción:");

			opcion = sc.nextLine();

			switch (opcion) {

			case "1", "añadir incidencia", "añadir una incidencia":
				GestionLogs.logOpcionMenu(Constantes.MENU_INCIDENCIAS, "Añadir Incidencias");
				anadirIncidencia(sc);
				break;

			case "2", "listar incidencias", "listar":
				GestionLogs.logOpcionMenu(Constantes.MENU_INCIDENCIAS, "Listar Incidencias");
				listado(sc);
				break;

			case "3", "eliminar", "eliminar incidencias":
				GestionLogs.logOpcionMenu(Constantes.MENU_INCIDENCIAS, "Eliminar Incidencias");
				eliminarIncidencias(sc);
				break;

			case "4", "salir":
				GestionLogs.logOpcionMenu(Constantes.MENU_INCIDENCIAS, "Salió de la aplicación");
				System.out.println(Colores.ANSI_PURPLE + "Saliendo de la aplicacion..." + Colores.ANSI_RESET);
				break;

			default:

				System.err.println("Has introducido una opción inválida. Vuelve a intentarlo");
				GestionLogs.errorLogs("Opcion no valida seleccionada en el menu de incidencias. " + opcion
						+ " no es una opcion valida.");

			}

		} while (!opcion.equals("4") && !opcion.contains("salir"));

	}

	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

	// Metodo encargado de añadir incidencias al ArrayList
	public ArrayList<Incidencias> anadirIncidencia(Scanner sc) {

		String opcion;

		// Submenu para añadir las incidencias que el usuario desee

		do {

			System.out.println("¿Que tipo de incidencia quieres añadir?\n" + "1 - Incidencia de alumno\n"
					+ "2 - Incidencia de profesor\n" + "3 - Incidencia de aplicacion\n" + "4 - Volver");

			opcion = sc.nextLine().toLowerCase();

			switch (opcion) {

			case "1", "incidencia de alumno", "alumno":

				Incidencias incidenciaAlumno = new IncidenciaAlumno();
				System.out.println("\nIntroduzca la incidencia de alumno: ");
				incidenciaAlumno.setIncidencia(sc.nextLine());
				iRepo.escribirIncidencia(incidenciaAlumno);
				iRepo.leerIncidencias(listaIncidencias);
				iRepo.insertarIncidenciasBBDD(listaIncidencias);
				GestionLogs.logOpcionMenu(Constantes.MENU_INCIDENCIAS, "Añadir Incidencias de Alumno");

				System.out
						.println(Colores.ANSI_GREEN + "\nIncidencia de alumno añadida con exito!" + Colores.ANSI_RESET);

				break;

			case "2", "incidencia de profesor", "profesor":

				Incidencias incidenciaProfesor = new IncidenciaProfesor();
				System.out.println("\nIntroduzca la incidencia de profesor: ");
				incidenciaProfesor.setIncidencia(sc.nextLine());
				iRepo.escribirIncidencia(incidenciaProfesor);
				iRepo.leerIncidencias(listaIncidencias);
				GestionLogs.logOpcionMenu(Constantes.MENU_INCIDENCIAS, "Añadir Incidencias de Profesor");

				System.out.println(
						Colores.ANSI_GREEN + "\nIncidencia de profesor añadida con exito!" + Colores.ANSI_RESET);

				break;

			case "3", "incidencia de aplicacion", "aplicacion":

				Incidencias incidenciaAplicacion = new IncidenciaAplicacion();
				System.out.println("\nIntroduzca la incidencia aplicación: ");
				incidenciaAplicacion.setIncidencia(sc.nextLine());
				iRepo.escribirIncidencia(incidenciaAplicacion);
				iRepo.leerIncidencias(listaIncidencias);
				GestionLogs.logOpcionMenu(Constantes.MENU_INCIDENCIAS, "Añadir Incidencias de Aplicacion");

				System.out.println(
						Colores.ANSI_GREEN + "\nIncidencia de aplicacion añadida con exito!" + Colores.ANSI_RESET);

				break;

			case "4", "volver":
				GestionLogs.logOpcionMenu(Constantes.MENU_INCIDENCIAS, "Volver al menú principal");
				break;

			default:

				System.err.println("Has introducido una opcion invalida.");
				GestionLogs.errorLogs("Opcion no valida seleccionada en el menu de incidencias. " + opcion
						+ " no es una opcion valida.");

			}

			System.out.println("\n");

		} while (!opcion.equals("4") && !opcion.equals("volver"));

		return getListaIncidencias();

	}

	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

	// Metodo encargado de listar las incidencias
	public void listado(Scanner sc) {

		/*
		 * Este metodo se encarga de clasificar las incidencias que se añaden al
		 * ArrayList por los tres tipos de incidencias que hay y luego imprimirlas las
		 * imprime
		 */

		boolean volver = false;
		String opcion;

		do {

			System.out.println("Elige que lista de incidencias quieres desplegar\n" + "1 - Incidencias de Alumnos\n"
					+ "2 - Incidencias de Profesores\n" + "3 - Incidencias de Aplicacion\n"
					+ "4 - Incidencias sin filtrar por tipo\n" + "5 - Volver");

			opcion = sc.nextLine().toLowerCase();

			if (opcion.equals("1") || opcion.equals("incidencias de alumnos")
					|| opcion.equals("incidencias de alumno")) {

				verIncidenciaAlumno();

			} else if (opcion.equals("2") || opcion.equals("incidencias de profesores")
					|| opcion.equals("incidencias de profesor")) {

				verIncidenciaProfesor();

			} else if (opcion.equals("3") || opcion.equals("incidencias de aplicacion")) {

				verIncidenciaAplicacion();

			} else if (opcion.equals("4") || opcion.equals("incidencias sin filtrar")
					|| opcion.equals("incidencias sin filtrar por tipo")) {

				verIncidenciasGenerales();

			} else if (opcion.equals("5") || opcion.equals("volver")) {

				volver = true;

			} else {

				System.err.println("Has introducido una opcion invalida");

			}

		} while (!volver);

	}

	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

	// Metodo encargado de imprimir las incidencias de tipo Alumno
	public void verIncidenciaAlumno() {

		if (getListaIncidencias().isEmpty()) {
			System.err.println("\nLo siento. No hay incidencias de alumnos registradas.\n");
		} else {
			for (Incidencias incidencia : getListaIncidencias()) {
				if (incidencia.getTipoIncidencia().equals("Alumno")) {
					System.out.println(incidencia);
				}
			}
		}
	}

	// Metodo encargado de imprimir las incidencias de tipo Profesor
	public void verIncidenciaProfesor() {

		if (getListaIncidencias().isEmpty()) {
			System.err.println("\nLo siento. No hay incidencias de profesores registradas.\n");
		} else {
			for (Incidencias incidencia : getListaIncidencias()) {
				if (incidencia.getTipoIncidencia().equals("Profesor")) {
					System.out.println(incidencia);
				}
			}
		}
	}

	// Metodo encargado de imprimir las incidencias de tipo Aplicacion
	public void verIncidenciaAplicacion() {

		if (getListaIncidencias().isEmpty()) {
			System.err.println("\nLo siento. No hay incidencias de aplicacion registradas.\n");
		} else {
			for (Incidencias incidencia : getListaIncidencias()) {
				if (incidencia.getTipoIncidencia().equals("Aplicacion")) {
					System.out.println(incidencia);
				}
			}
		}
	}

	/* Metodo encargado de imprimir todas las incidencias, 
	 * sin filtrarlas por su tipo.
	 */
	public void verIncidenciasGenerales() {

		if (getListaIncidencias().isEmpty()) {
			System.err.println("\nLo siento. No existe ningun tipo de incidencia registrado\n");
		} else {
			for (Incidencias incidencia : getListaIncidencias()) {
				System.out.println(incidencia);

			}
		}
	}

	/*
	 * Metodo encargado de eliminar las incidencias registradas. Se eliminan por
	 * tipo de incidencia.
	 */
	public void eliminarIncidencias(Scanner sc) {

		String opcion;

		do {

			System.out.println("¿Que tipo de incidencia quieres eliminar?\n" + "1 - Incidencia de alumno\n"
					+ "2 - Incidencia de profesor\n" + "3 - Incidencia de aplicacion\n" + "4 - Volver");
			opcion = sc.nextLine().toLowerCase();

			switch (opcion) {

			case "1", "incidencia de alumno", "alumno":

				eliminarIncidenciaAlumno();
				GestionLogs.logOpcionMenu(Constantes.MENU_INCIDENCIAS, "Eliminar Incidencias de Alumno");

				break;

			case "2", "incidencia de profesor", "profesor":

				eliminarIncidenciaProfesor();
				GestionLogs.logOpcionMenu(Constantes.MENU_INCIDENCIAS, "Eliminar Incidencias de Profesor");

				break;

			case "3", "incidencia de aplicacion", "aplicacion":

				eliminarIncidenciaAplicacion();
				GestionLogs.logOpcionMenu(Constantes.MENU_INCIDENCIAS, "Eliminar Incidencias de Aplicacion");

				break;

			case "4", "volver":
				GestionLogs.logOpcionMenu(Constantes.MENU_INCIDENCIAS, "Volver al menú principal");
				break;

			default:

				System.err.println("Has introducido una opción invalida.");
				GestionLogs.errorLogs("Opcion no valida seleccionada en el menu de incidencias. " + opcion
						+ " no es una opcion valida.");

			}

		} while (!opcion.equals("4") && !opcion.contains("volver"));

	}

	// Metodo encargado de eliminar las incidencias de tipo Alumno
	public void eliminarIncidenciaAlumno() {

		if (getListaIncidencias().isEmpty()) {
			System.err.println("\nLo siento. No hay incidencias de alumnos registradas.\n");
		} else {
			for (int i = 0; i < getListaIncidencias().size(); i++) {
				if (getListaIncidencias().get(i).getTipoIncidencia().equals("Alumno")) {
					getListaIncidencias().remove(i);
					System.out.println(
							Colores.ANSI_GREEN + "\nIncidencias de alumnos eliminadas con exito!" + Colores.ANSI_RESET);
				}
			}
		}
	}

	// Metodo encargado de eliminar las incidencias de tipo Profesor
	public void eliminarIncidenciaProfesor() {

		if (getListaIncidencias().isEmpty()) {
			System.err.println("\nLo siento. No hay incidencias de profesor registradas.\n");
		} else {
			for (int i = 0; i < getListaIncidencias().size(); i++) {
				if (getListaIncidencias().get(i).getTipoIncidencia().equals("Profesor")) {
					getListaIncidencias().remove(i);
					System.out.println(Colores.ANSI_GREEN + "\nIncidencias de profesores eliminadas con exito!"
							+ Colores.ANSI_RESET);
				}
			}
		}
	}

	// Metodo encargado de eliminar las incidencias de tipo Aplicacion
	public void eliminarIncidenciaAplicacion() {

		if (getListaIncidencias().isEmpty()) {
			System.err.println("\nLo siento. No hay incidencias de aplicacion registradas.\n");
		} else {
			for (int i = 0; i < getListaIncidencias().size(); i++) {
				if (getListaIncidencias().get(i).getTipoIncidencia().equals("Aplicacion")) {
					getListaIncidencias().remove(i);
					System.out.println(Colores.ANSI_GREEN + "\nIncidencias de aplicacion eliminadas con exito!"
							+ Colores.ANSI_RESET);
				}
			}
		}
	}

}
