package com.daw.proyectoescolar.servicios.incidencias;

import java.util.*;
import java.util.Map.Entry;

import com.daw.proyectoescolar.entidades.IncidenciaAlumno;
import com.daw.proyectoescolar.entidades.IncidenciaAplicacion;
import com.daw.proyectoescolar.entidades.IncidenciaProfesor;
import com.daw.proyectoescolar.entidades.Incidencias;
import com.daw.proyectoescolar.entidades.UsuarioBase;
import com.daw.proyectoescolar.repositorio.Colores;
import com.daw.proyectoescolar.repositorio.Constantes;
import com.daw.proyectoescolar.repositorio.IncidenciasRepo;
import com.daw.proyectoescolar.servicios.logs.GestionLogs;

public class GestionIncidencias {
	
	// Atributos
	private final IncidenciasRepo iRepo = new IncidenciasRepo();
	private List<Incidencias> listaIncidencias = iRepo.listarIncidenciasBBDD();

	// Constructores
	public GestionIncidencias() {

	}

	// Getters y Setters
	public List<Incidencias> getListaIncidencias() {
		return listaIncidencias;
	}

	// Metodos

	public void crearIncidenciaAlumno(Scanner sc, UsuarioBase usuario) {

		Incidencias incidenciaAlumno = new IncidenciaAlumno();
		System.out.println("\nIntroduzca la incidencia de alumno: ");
		incidenciaAlumno.setIncidencia(sc.nextLine());
		incidenciaAlumno.setUsuario(usuario);
		iRepo.escribirIncidencia(incidenciaAlumno, usuario.getUsuarioId());
		iRepo.insertarUnicaIncidenciaBBDD(incidenciaAlumno);
		GestionLogs.logOpcionMenu(Constantes.MENU_INCIDENCIAS, "Crear Incidencias de Alumno");

		System.out.println(Colores.ANSI_GREEN + "\nIncidencia de alumno añadida con exito!" + Colores.ANSI_RESET);
	}

	public void crearIncidenciaProfesor(Scanner sc, UsuarioBase usuario) {

		Incidencias incidenciaProfesor = new IncidenciaProfesor();
		System.out.println("\nIntroduzca la incidencia de profesor: ");
		incidenciaProfesor.setIncidencia(sc.nextLine());
		incidenciaProfesor.setUsuario(usuario);
		iRepo.escribirIncidencia(incidenciaProfesor, usuario.getUsuarioId());
		iRepo.insertarUnicaIncidenciaBBDD(incidenciaProfesor);
		GestionLogs.logOpcionMenu(Constantes.MENU_INCIDENCIAS, "Crear Incidencias de Profesor");

		System.out.println(Colores.ANSI_GREEN + "\nIncidencia de profesor añadida con exito!" + Colores.ANSI_RESET);
	}

	// esto metelo en los menus de incidencias de los usuarios que ya me ha dao un poco de pereza
	public void crearIncidenciaAplicacion(Scanner sc, UsuarioBase usuario) {

		Incidencias incidenciaAplicacion = new IncidenciaAplicacion();
		System.out.println("\nIntroduzca la incidencia de aplicacion: ");
		incidenciaAplicacion.setIncidencia(sc.nextLine());
		incidenciaAplicacion.setUsuario(usuario);
		iRepo.escribirIncidencia(incidenciaAplicacion, usuario.getUsuarioId());
		iRepo.insertarUnicaIncidenciaBBDD(incidenciaAplicacion);
		GestionLogs.logOpcionMenu(Constantes.MENU_INCIDENCIAS, "Crear Incidencias de Aplicacion");

		System.out.println(Colores.ANSI_GREEN + "\nIncidencia de aplicacion añadida con exito!" + Colores.ANSI_RESET);
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

			System.out.println("""
                    Elige que lista de incidencias quieres desplegar
                    1 - Incidencias de Alumnos
                    2 - Incidencias de Profesores
                    3 - Incidencias de Aplicacion
                    4 - Incidencias sin filtrar por tipo
                    5 - Volver""");

			opcion = sc.nextLine().toLowerCase();

            switch (opcion) {
                case "1", "incidencias de alumnos", "incidencias de alumno" -> verIncidenciaAlumno();
                case "2", "incidencias de profesores", "incidencias de profesor" -> verIncidenciaProfesor();
                case "3", "incidencias de aplicacion" -> verIncidenciaAplicacion();
                case "4", "incidencias sin filtrar", "incidencias sin filtrar por tipo" -> verIncidenciasGenerales();
                case "5", "volver" -> volver = true;
                default -> System.err.println("Has introducido una opcion invalida");
            }

		} while (!volver);

	}

	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	public void verIncidenciasAlumno(int usuarioId, String nombre) {

		if (getListaIncidencias().isEmpty()) {
			System.err.println("\nLo siento. No hay incidencias de alumnos registradas.\n");
		} else {
			for (Incidencias incidencia : getListaIncidencias()) {
				if (incidencia.getTipoIncidencia().equals("Alumno") && incidencia.getUsuario().getUsuarioId() == usuarioId) {
					System.out.println(incidencia + " - " + nombre);
				}
			}
		}

	}

	public void verIncidenciasProfesor(int usuarioId, String nombre) {

		if (getListaIncidencias().isEmpty()) {
			System.err.println("\nLo siento. No hay incidencias de profesores registradas.\n");
		} else {
			for (Incidencias incidencia : getListaIncidencias()) {
				if (incidencia.getTipoIncidencia().equals("Profesor") && incidencia.getUsuario().getUsuarioId() == usuarioId) {
					System.out.println(incidencia + " - " + nombre);
				}
			}
		}

	}
	
	public void verIncidenciasAplicacion(int usuarioId, String nombre) {

		if (getListaIncidencias().isEmpty()) {
			System.err.println("\nLo siento. No hay incidencias de profesores registradas.\n");
		} else {
			for (Incidencias incidencia : getListaIncidencias()) {
				if (incidencia.getTipoIncidencia().equals("Aplicacion") && incidencia.getUsuario().getUsuarioId() == usuarioId) {
					System.out.println(incidencia + " - " + nombre);
				}
			}
		}

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

	// Metodo encargado de eliminar incidencias por scanner
	public void eliminarIncidencias(Scanner sc) {
		System.out.println("Introduce el ID de la incidencia a eliminar: ");
        int incidenciaId = sc.nextInt();
        if(incidenciaId <= 0) {
		System.err.println("No existe ningún ID de incidencia por debajo de 1.");
		} else {
			iRepo.eliminarIncidenciasBBDD(incidenciaId);
		}
	}

	// Metodo encargado de imprimir las incidencias para un usuario en concreto en la web
	public Map<UsuarioBase, List<Incidencias>> hashMapUsuariosIncidencias() {
		return iRepo.hashMapUsuariosIncidencias();
	}

	// Metodo encargado de imprimir el HashMap de incidencias de los usuarios
	public void pintarHashIncidencias() {

		for(Entry<UsuarioBase, List<Incidencias>> entry : hashMapUsuariosIncidencias().entrySet()) {
			System.out.println(entry.getKey().getNombre() + " - ID: " + entry.getKey().getUsuarioId());
			for (Incidencias incidencias : entry.getValue()) {
				System.out.println(incidencias);
			}
		}
	}

	// Buscador de incidencias scanner
	public void buscadorDeIncidencias(Scanner sc) {

		System.out.println("Introduce la clave de busqueda de la incidencia a buscar: ");
		String busqueda = sc.nextLine();
		List<Incidencias> listaBusqueda = iRepo.buscadorDeIncidenciasBBDD(busqueda);

		if(listaBusqueda.isEmpty()) {
			System.err.println("Busqueda vacia");
		} else {
			for(Incidencias incidencias : listaBusqueda) {
				System.out.println(incidencias);
			}
		}
	}

	// Buscador de incidencias model and view
	public List<Incidencias> buscadorDeIncidencias(String busqueda) {
        return iRepo.buscadorDeIncidenciasBBDD(busqueda);
	}

	// Eliminar incidencias de la BBDD
	public void eliminarIncidencias(int incidenciaId) {
		iRepo.eliminarIncidenciasBBDD(incidenciaId);
	}

	// Insertar incidencia en la BBDD
	public void insertarIncidencia(Incidencias incidencia) {
		iRepo.insertarUnicaIncidenciaBBDD(incidencia);
		this.listaIncidencias = getListaIncidencias(); // Actualizar la lista de incidencias, aunque creo que no funciona creo
	}

	// Obtener lista de incidencias de un usuario en concreto por su ID
	public List<Incidencias> obtenerIncidenciasUsuario(int usuarioId) {
		List<Incidencias> listaIncidenciasUsuario = new ArrayList<>();
		for(Incidencias incidencia : getListaIncidencias()) {
			if(incidencia.getUsuario().getUsuarioId() == usuarioId) {
				listaIncidenciasUsuario.add(incidencia);
			}
		}
		return listaIncidenciasUsuario;
	}
}