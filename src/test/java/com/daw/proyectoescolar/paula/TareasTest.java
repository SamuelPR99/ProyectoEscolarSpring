package com.daw.proyectoescolar.paula;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.daw.proyectoescolar.entidades.Alumno;
import com.daw.proyectoescolar.entidades.Profesor;
import com.daw.proyectoescolar.entidades.Tarea;
import com.daw.proyectoescolar.entidades.Temas;
import com.daw.proyectoescolar.entidades.UsuarioBase;
import com.daw.proyectoescolar.repositorio.ListaDeTemas;
import com.daw.proyectoescolar.repositorio.Tema1;
import com.daw.proyectoescolar.servicios.gestionusuarios.GestionadorUsuarios;
import com.daw.proyectoescolar.servicios.tareas.GestionTemas;

class TareasTest {

	protected ArrayList<UsuarioBase> usuarios;
	protected ArrayList<Alumno> alumnos;
	protected String menuInput = "8";
	GestionadorUsuarios gestorUsuarios = new GestionadorUsuarios();
	GestionTemas gestorTemas = new GestionTemas();
	protected ArrayList<Tarea> tareas;
	Scanner scannerMock;
	protected String validarDNI;

	@BeforeEach
	public void setUp() {

		scannerMock = mock(Scanner.class);

	}

	@Test
	void testTareaBasica() {

	  Tarea tarea = new Tarea("Básica", "Tema 1", "Descripción de la tarea");

	  // Verificaciones
	  assertEquals("Básica", tarea.getTipo());
	  assertEquals("Tema 1", tarea.getNombre());
	  assertEquals("Descripción de la tarea", tarea.getDescripcion());
	  
	}

	@Test
	void testMenuProfesor() {

	  // Crear un profesor
	  Profesor profesor = new Profesor();

	  // Simular la entrada del usuario para salir del menú
	  when(scannerMock.nextLine()).thenReturn("8");

	  // Probar el menú del profesor
	  profesor.verMenu(scannerMock, usuarios, alumnos);

	  // Verificaciones
	  Mockito.verify(scannerMock, times(2)).nextLine(); // Se llama dos veces para salir del menú
	  Mockito.verify(profesor).verMenu(scannerMock, usuarios, alumnos);
	}

	@Test
	void testMenuAlumno() {

	  // Crear un alumno
	  Alumno alumno = new Alumno();

	  // Simular la entrada del usuario para salir del menú
	  when(scannerMock.nextLine()).thenReturn("6");

	  // Probar el menú del alumno
	  alumno.verMenu(scannerMock, usuarios, alumnos);

	  // Verificaciones
	  Mockito.verify(scannerMock, times(2)).nextLine(); // Se llama dos veces para salir del menú
	  Mockito.verify(alumno).verMenu(scannerMock, usuarios, alumnos);
	}

	@Test
	void testValidarDNI() {

	  // Casos de prueba válidos
	  GestionadorUsuarios validarD = new GestionadorUsuarios();
	  assertTrue(validarD.validarDNI("12345678Z"));
	  assertTrue(validarD.validarDNI("98765432X"));

	  // Casos de prueba inválidos
	  assertFalse(validarD.validarDNI("12345678A")); // Letra incorrecta
	  assertFalse(validarD.validarDNI("12345A678")); // Longitud incorrecta
	  assertFalse(validarD.validarDNI("12345678")); // Longitud incorrecta
	  assertFalse(validarD.validarDNI("ABCDEFGHI"));// Longitud incorrecta y sin letra
	  assertFalse(validarD.validarDNI("")); // Cadena vacía

	}

	@Test
	void testConstructorListaDeTemas() {

	  // Crear una lista de temas
	  ArrayList<Temas> listaDeTemas = new ArrayList<>();
	  listaDeTemas.add(new Tema1("Matemáticas", "Este tema cubre varios conceptos matemáticos.", "Contenido de tarea 1"));
	  listaDeTemas.add(new Tema1("Física", "Este tema cubre varios conceptos de física.", "Contenido de tarea 2"));

	  // Crear una lista de temas a partir de la lista anterior
	  ListaDeTemas lista = new ListaDeTemas(listaDeTemas);

	  // Verificaciones
	  assertNotNull(lista.getListaDeTemas());
	  assertEquals(listaDeTemas.size(), lista.getListaDeTemas().size());
	  assertEquals(listaDeTemas.get(0).getNombre(), lista.getListaDeTemas().get(0).getNombre());
	  assertEquals(listaDeTemas.get(1).getDescripcion(), lista.getListaDeTemas().get(1).getDescripcion());
	  assertEquals(listaDeTemas.get(1).getContenidoTarea(), lista.getListaDeTemas().get(1).getContenidoTarea());
	}

	@Test
	void testTemasIniciados() {
		ListaDeTemas listaDeTemas = new ListaDeTemas();
		listaDeTemas.temasIniciados();

		ArrayList<Temas> temas = listaDeTemas.getListaDeTemas();

		// Verificar que la lista de temas no sea nula
		assertEquals(12, temas.size()); // Comprueba si se han añadido las 12 tareas

		// Verificar algunas tareas específicas
		assertEquals("Teoría de la Probabilidad Estocástica", temas.get(0).getNombre());
		assertEquals(
				"Basica: Tarea 1: Simulación de Monte Carlo 🡺 Implementar un algoritmo de simulación de Monte Carlo para estimar el valor de π.",
				temas.get(0).getContenidoTarea());

		assertEquals("Teoría de Números Avanzada", temas.get(3).getNombre());
		assertEquals(
				"Intermedia: Tarea 2: Congruencia de Euler y Criptografía RSA 🡺 Investigar y demostrar la congruencia de Euler y su aplicación en criptografía RSA.",
				temas.get(3).getContenidoTarea());

		assertEquals("Análisis Funcional", temas.get(6).getNombre());
		assertEquals(
				"Avanzada: Tarea 3: Teorema de Representación de Riesz en Espacios de Hilbert 🡺 Investigar y demostrar el teorema de representación de Riesz sobre espacios de Hilbert.",
				temas.get(6).getContenidoTarea());

		assertEquals("Topología Algebraica", temas.get(9).getNombre());
		assertEquals(
				"Intermedia: Tarea 2:Teorema de la Invariancia de la Dimensión y Propiedades Topológicas 🡺 Investigar y aplicar el teorema de la invariancia de la dimensión para demostrar propiedades topológicas de variedades.",
				temas.get(9).getContenidoTarea());

		assertEquals("Teoría de la Aproximación y Funciones Especiales", temas.get(11).getNombre());
		assertEquals(
				"AvanzadaTarea 3: Conceptos básicos de álgebra 🡺 Repasar conceptos fundamentales de álgebra como ecuaciones lineales y factorización.",
				temas.get(11).getContenidoTarea());
	}

	@Test
	void testVerMenu() {
		String input = menuInput;
		// Simula la entrada del usuario
		ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
		System.setIn(inContent);

		// Crea instancias de las clases necesarias

	}

	@Test
	void testMostrarTareaValida() {
		new ArrayList<>();

		assertEquals("Tarea 1", "Hacer algo");
		assertEquals("Tarea 2", "Hacer otra cosa");

		// Seleccionamos la tarea número 1
		Tarea tareaSeleccionada = new Tarea();
		tareaSeleccionada.mostrarTareas(scannerMock);

		// Verificamos que la tarea seleccionada sea la tarea número 1
		assertEquals("Tarea 1", tareaSeleccionada.getNombre());
		assertEquals("Hacer algo", tareaSeleccionada.getDescripcion());
	}

	@Test
	void testMostrarTareaInvalida() {
		ArrayList<Tarea> tareas = new ArrayList<>();
		assertEquals("Tarea 1", "Hacer algo");
		assertEquals("Tarea 2", "Hacer otra cosa");

		// Verificamos que la tarea seleccionada sea nula
		assertEquals(null, tareas);
	}

	@Test
	void testObtenerTodasLasTareas() {
		ArrayList<Tarea> tareas = Tarea.obtenerTodasLasTareas();

		// Verificar que la lista de tareas no sea nula
		assertNotNull(tareas);

		// Verificar que la cantidad de tareas es correcta
		assertEquals(21, tareas.size());

		// Verificar algunas tareas específicas
		assertEquals("Basica", tareas.get(0).getTipo());
		assertEquals("Tarea 1: Simulación de Monte Carlo", tareas.get(0).getNombre());
		assertEquals("Implementar un algoritmo de simulación de Monte Carlo para estimar el valor de π.",
				tareas.get(0).getDescripcion());

		assertEquals("Intermedia", tareas.get(7).getTipo());
		assertEquals("Tarea 3: Teorema de Representación de Riesz en Espacios de Hilbert", tareas.get(11).getNombre());
		assertEquals("Investigar y demostrar el teorema de representación de Riesz sobre espacios de Hilbert.",
				tareas.get(11).getDescripcion());

		assertEquals("Avanzada", tareas.get(20).getTipo());
		assertEquals("Tarea 3: Conceptos básicos de álgebra", tareas.get(20).getNombre());
		assertEquals("Repasar conceptos fundamentales de álgebra como ecuaciones lineales y factorización.",
				tareas.get(20).getDescripcion());
	}

	@Test
	void testConstructorTemas() {
		String nombre = "Matemáticas";
		String descripcion = "Este tema cubre varios conceptos matemáticos.";
		String contenidoTarea = "Implementar un algoritmo de simulación de Monte Carlo para estimar el valor de π.";

		Temas tema = new Tema1(nombre, descripcion, contenidoTarea);

		// Verificar que los valores se establecen correctamente
		assertEquals(nombre, tema.getNombre());
		assertEquals(descripcion, tema.getDescripcion());
		assertEquals(contenidoTarea, tema.getContenidoTarea());
	}

	@Test
	void testNombreTema() {
		Temas tema = new Tema1();
		String nombre = "Matemáticas";

		tema.setNombre(nombre);

		// Verificar si el método getNombre retorna el nombre correctamente
		assertEquals(nombre, tema.getNombre());
	}

	@Test
	void testDescripcionTema() {
		Temas tema = new Tema1();
		String descripcion = "Este tema cubre varios conceptos matemáticos.";

		tema.setDescripcion(descripcion);

		// Verificar si el método getDescripcion retorna la descripcion correctamente
		assertEquals(descripcion, tema.getDescripcion());
	}

	@Test
	void testContenidoTarea() {
		Temas tema = new Tema1();
		String contenidoTarea = "Implementar un algoritmo de simulación de Monte Carlo para estimar el valor de π.";

		tema.setContenidoTarea(contenidoTarea);

		// Verificar si el método getContenidoTarea retorna el contenidoTarea correctamente
		assertEquals(contenidoTarea, tema.getContenidoTarea());
	}

	@Test
	void testModificarNotaAlumno() {
		
		ArrayList<Alumno> alumnos = new ArrayList<>();
		Alumno alumno1 = new Alumno("Juan", "juan", 10.7);
		Alumno alumno2 = new Alumno("María", "maria", 8.9);
		alumnos.add(alumno1);
		alumnos.add(alumno2);

		// Modificar la nota del alumno 2
		int numeroAlumno = 2;
		when(scannerMock.nextInt()).thenReturn(numeroAlumno);
		when(scannerMock.nextDouble()).thenReturn(9.0);
		
		// Modificar la nota del alumno
		
		GestionadorUsuarios modificarNotaAlumno = new GestionadorUsuarios();
		// (numeroAlumno, nuevaNota, alumnos);
		
		modificarNotaAlumno.modificarNotaAlumno(scannerMock, usuarios);
		
		assertEquals(9.0, alumnos.get(numeroAlumno - 1).getNota(), 0.001);
	}
	
}
