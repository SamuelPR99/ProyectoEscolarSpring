package com.daw.proyectoescolar.hugo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.daw.proyectoescolar.entidades.IncidenciaAlumno;
import com.daw.proyectoescolar.entidades.IncidenciaAplicacion;
import com.daw.proyectoescolar.entidades.IncidenciaProfesor;
import com.daw.proyectoescolar.entidades.Incidencias;
import com.daw.proyectoescolar.servicios.incidencias.GestionIncidencias;

class IncidenciasTest {

	/*
	private ArrayList<Incidencias> listaIncidencias;
	private GestionIncidencias gestionIncidencias;
	private Scanner scannerMock;
	private Scanner sc;

	@BeforeEach
	public void setUp() {

		gestionIncidencias = new GestionIncidencias();
		listaIncidencias = new ArrayList<Incidencias>();
		scannerMock = mock(Scanner.class);

		Incidencias inciAlumno1 = new IncidenciaAlumno("Samu me ha pegao");
		Incidencias inciAlumno2 = new IncidenciaAlumno("Samu me ha pegao otra ve");
		listaIncidencias.add(inciAlumno1);
		listaIncidencias.add(inciAlumno2);

		Incidencias inciProfesor1 = new IncidenciaProfesor("Guillamón me tiene manía");
		Incidencias inciProfesor2 = new IncidenciaProfesor("Guillamón me tiene manía, el retorno");
		listaIncidencias.add(inciProfesor1);
		listaIncidencias.add(inciProfesor2);

		Incidencias inciApp1 = new IncidenciaAplicacion("La aplicación me crasheó intentando iniciar sesión");
		Incidencias inciApp2 = new IncidenciaAplicacion("La aplicación me crasheó intentando borrar un usuario");
		listaIncidencias.add(inciApp1);
		listaIncidencias.add(inciApp2);

	}

	@Test
	void testMostrarMenuPrincipal() {

		sc = new Scanner(System.in);

		gestionIncidencias.menuPrincipal(sc);

	}

	@Test
	void testMensajeError() {

		when(scannerMock.nextLine()).thenReturn("7") // Opción inválida dentro del menú principal \\
				.thenReturn("4"); // Salir del menú \\

		gestionIncidencias.menuPrincipal(scannerMock);

	}

	@Test
	void testAñadirIncidenciaAlumno() {

		when(scannerMock.nextLine()).thenReturn("1") // Entra al submenú de Añadir Incidencias \\
				.thenReturn("1") // Elige la opción de añadir una incidencia de alumno \\
				.thenReturn("Incidencia Alumno Test.") // Añade la incidencia de alumno \\
				.thenReturn("4"); // Sale de la aplicación \\

		gestionIncidencias.menuPrincipal(scannerMock);

		assertEquals(1, gestionIncidencias.getListaIncidencias().size());

	}

	@Test
	void testAñadirIncidenciaProfesor() {

		when(scannerMock.nextLine()).thenReturn("1") // Entra al submenú de Añadir Incidencias \\
				.thenReturn("2") // Elige la opción de añadir una incidencia de profesor \\
				.thenReturn("Incidencia Profesor Test.") // Añade la incidencia de profesor \\
				.thenReturn("4"); // Sale de la aplicación \\

		gestionIncidencias.menuPrincipal(scannerMock);

		assertEquals(1, gestionIncidencias.getListaIncidencias().size());

	}

	@Test
	void testAñadirIncidenciaAplicacion() {

		when(scannerMock.nextLine()).thenReturn("1") // Entra al submenú de Añadir Incidencias \\
				.thenReturn("3") // Elige la opción de añadir una incidencia de aplicación \\
				.thenReturn("Incidencia Aplicación Test.") // Añade la incidencia de aplicación \\
				.thenReturn("4"); // Sale de la aplicación \\

		gestionIncidencias.menuPrincipal(scannerMock);

		assertEquals(1, gestionIncidencias.getListaIncidencias().size());

	}

	@Test
	void testListarIncidenciaAlumno() {

		when(scannerMock.nextLine()).thenReturn("1") // Entra al submenú de Añadir Incidencias \\
				.thenReturn("1") // Elige la opción de añadir una incidencia de alumno \\
				.thenReturn("Imprimir Incidencia Alumno Test") // Añade la incidencia de alumno \\
				.thenReturn("4") // Vuelve al menú principal \\
				.thenReturn("2") // Entra al submenú de Listar Incidencias \\
				.thenReturn("1") // Imprime la incidencia de alumno previamente añadida \\
				.thenReturn("5") // Vuelve al menú principal \\
				.thenReturn("4"); // Sale de la aplicación \\

		gestionIncidencias.menuPrincipal(scannerMock);

	}

	@Test
	void testListarIncidenciaProfesor() {

		when(scannerMock.nextLine()).thenReturn("1") // Entra al submenú de Añadir Incidencias \\
				.thenReturn("2") // Elige la opción de añadir una incidencia de profesor \\
				.thenReturn("Imprimir Incidencia Profesor Test") // Añade la incidencia de profesor \\
				.thenReturn("4") // Vuelve al menú principal \\
				.thenReturn("2") // Entra al submenú de Listar Incidencias \\
				.thenReturn("2") // Imprime la incidencia de profesor previamente añadida \\
				.thenReturn("5") // Vuelve al menú principal \\
				.thenReturn("4"); // Sale de la aplicación \\

		gestionIncidencias.menuPrincipal(scannerMock);

	}

	@Test
	void testListarIncidenciaAplicacion() {

		when(scannerMock.nextLine()).thenReturn("1") // Entra al submenú de Añadir Incidencias \\
				.thenReturn("3") // Elige la opción de añadir una incidencia de aplicación \\
				.thenReturn("Imprimir Incidencia Aplicación Test") // Añade la incidencia de aplicación \\
				.thenReturn("4") // Vuelve al menú principal \\
				.thenReturn("2") // Entra al submenú de Listar Incidencias \\
				.thenReturn("3") // Imprime la incidencia de aplicación previamente añadida \\
				.thenReturn("5") // Vuelve al menú principal \\
				.thenReturn("4"); // Sale de la aplicación \\

		gestionIncidencias.menuPrincipal(scannerMock);

	}

	@Test
	void testListarIncidenciasSinFiltrar() {

		when(scannerMock.nextLine())

				// ES NECESARIO AÑADIR UNA INCIDENCIA DE CADA TIPO PARA QUE SE PUEDA VER LA
				// PRUEBA DE QUE IMPRIME TODAS LAS INCIDENCIAS SIN FILTRAR \\

				.thenReturn("1") // Entra al submenú de Añadir Incidencias \\

				.thenReturn("1") // Elige la opción de añadir una incidencia de alumno \\
				.thenReturn("Imprimir Incidencia Alumno Test") // Añade la incidencia de alumno \\

				.thenReturn("2") // Elige la opción de añadir una incidencia de profesor \\
				.thenReturn("Imprimir Incidencia Profesor Test") // Añade la incidencia de profesor \\

				.thenReturn("3") // Elige la opción de añadir una incidencia de aplicación \\
				.thenReturn("Imprimir Incidencia Aplicación Test") // Añade la incidencia de aplicación \\

				.thenReturn("4") // Vuelve al menú principal \\
				.thenReturn("2") // Entra al submenú de Listar Incidencias \\
				.thenReturn("4") // Imprime todas las incidencias añadidas sin filtrarlas por tipo \\
				.thenReturn("5") // Vuelve al menú principal \\
				.thenReturn("4"); // Sale de la aplicación \\

		gestionIncidencias.menuPrincipal(scannerMock);

	}

	@Test
	void testEliminarIncidenciaAlumno() {

		when(scannerMock.nextLine()).thenReturn("1") // Entra al submenú de Añadir Incidencias \\
				.thenReturn("1") // Elige la opción de añadir una incidencia de alumno \\
				.thenReturn("Esto es un test") // Añade la incidencia de alumno \\
				.thenReturn("4") // Vuelve al menú principal \\
				.thenReturn("3") // Entra al submenú de Eliminar Incidencias \\
				.thenReturn("1") // Elige la opción de eliminar la incidencia de alumno previamente añadida \\
				.thenReturn("4"); // Sale del menú \\

		gestionIncidencias.menuPrincipal(scannerMock);

	}

	@Test
	void testEliminarIncidenciaProfesor() {

		when(scannerMock.nextLine()).thenReturn("1") // Entra al submenú de Añadir Incidencias \\
				.thenReturn("2") // Elige la opción de añadir una incidencia de profesor \\
				.thenReturn("Esto es un test") // Añade la incidencia de profesor \\
				.thenReturn("4") // Vuelve al menú principal \\
				.thenReturn("3") // Entra al submenú de Eliminar Incidencias \\
				.thenReturn("2") // Elige la opción de eliminar la incidencia de profesor previamente añadida \\
				.thenReturn("4"); // Sale del menú \\

		gestionIncidencias.menuPrincipal(scannerMock);

	}

	@Test
	void testEliminarIncidenciaAplicacion() {

		when(scannerMock.nextLine()).thenReturn("1") // Entra al submenú de Añadir Incidencias \\
				.thenReturn("3") // Elige la opción de añadir una incidencia de aplicación \\
				.thenReturn("Esto es un test") // Añade la incidencia de aplicación \\
				.thenReturn("4") // Vuelve al menú principal \\
				.thenReturn("3") // Entra al submenú de Eliminar Incidencias \\
				.thenReturn("3") // Elige la opción de eliminar la incidencia de aplicación previamente añadida
									// \\
				.thenReturn("4"); // Sale del menú \\

		gestionIncidencias.menuPrincipal(scannerMock);

	}

	@Test
	void testSalirDelMenu() {

		when(scannerMock.nextLine()).thenReturn("4"); // Sale de la aplicación \\

		gestionIncidencias.menuPrincipal(scannerMock);

	}

	@Test
	void ComprobarTamañoArrayList() {

		// He añadido 6 incidencias al ArrayList desde el BeforeEach, por lo que tendría
		// 5 posiciones.

		assertEquals(5, listaIncidencias.size());

	}

	@Test
	void ComprobarAñadirIncidencia() {

		Incidencias inciAlumnoPrueba = new IncidenciaAlumno("Esto es un test.");
		listaIncidencias.add(inciAlumnoPrueba);

		assertEquals(6, listaIncidencias.size());

	}
*/
}
