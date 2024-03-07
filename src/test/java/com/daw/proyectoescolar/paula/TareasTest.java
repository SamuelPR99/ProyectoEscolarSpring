package com.daw.proyectoescolar.paula;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.daw.proyectoescolar.entidades.Alumno;
import com.daw.proyectoescolar.entidades.Profesor;
import com.daw.proyectoescolar.entidades.Tarea;
import com.daw.proyectoescolar.entidades.Temas;
import com.daw.proyectoescolar.entidades.UsuarioBase;
import com.daw.proyectoescolar.repositorio.ListaDeTemas;
import com.daw.proyectoescolar.repositorio.Tema1;
import com.daw.proyectoescolar.servicios.gestionusuarios.GestionadorUsuarios;
import com.daw.proyectoescolar.servicios.tareas.GestionTemas;
import com.daw.proyectoescolar.servicios.tareas.Tema;


class TareasTest {

	protected  ArrayList<UsuarioBase> usuarios;
	protected ArrayList<Alumno> alumnos;
	protected String menuInput="8";
	GestionadorUsuarios gestorUsuarios = new GestionadorUsuarios();
    GestionTemas gestorTemas = new GestionTemas();
    protected ArrayList<Tarea> tareas;
	//1
	@Test
	void testTareaBasica() {
	
		Tarea tarea = new Tarea("Básica","Tema 1","Descripción de la tarea");
		 assertEquals("Básica",tarea.getTipo());
		 assertEquals("Tarea 1", tarea.getNombre());
	     assertEquals("Descripción de la tarea", tarea.getDescripcion());
	}

	//2
		@Test
		void test2() {
		
		Scanner sc= new Scanner(System.in);
		Profesor profesor= new Profesor();
		profesor.verMenu(sc, usuarios, alumnos);
		Assertions.assertThat(true);
		
		//esto no se q hace la vd :(
		}
	
	
	//3
		@Test
		void test3() {
			//si entras como alumno te tiene que salir su menu
			Scanner sc= new Scanner(System.in);
			Alumno alumno= new Alumno();
			alumno.verMenu(sc, usuarios, alumnos);
			Assertions.assertThat(true);
			
		}
	//4
		@Test
		void test4() {
			//si pones 1 o Tema 1, te lleva al tema 
			
		}
	//5
		
		@Test
	    public void testConstructorListaDeTemas() {
	        ArrayList<Temas> listaDeTemas = new ArrayList<>();
	        listaDeTemas.add(new Tema("Matemáticas", "Este tema cubre varios conceptos matemáticos.", null, "Contenido de tarea 1"));
	        listaDeTemas.add(new Tema("Física", "Este tema cubre varios conceptos de física.", null, "Contenido de tarea 2"));

	        ListaDeTemas lista = new ListaDeTemas(listaDeTemas);

	        // Verificar que la lista de temas no sea nula
	        assertNotNull(lista.getListaDeTemas());
	        
	        // Verificar que la lista de temas se establece correctamente
	        assertEquals(listaDeTemas.size(), lista.getListaDeTemas().size());
	        assertEquals(listaDeTemas.get(0).getNombre(), lista.getListaDeTemas().get(0).getNombre());
	        assertEquals(listaDeTemas.get(1).getDescripcion(), lista.getListaDeTemas().get(1).getDescripcion());
	        assertEquals(listaDeTemas.get(1).getContenidoTarea(), lista.getListaDeTemas().get(1).getContenidoTarea());
	    }
	
	//6
		@Test
	    public void testTemasIniciados() {
	        ListaDeTemas listaDeTemas = new ListaDeTemas();
	        listaDeTemas.temasIniciados();

	        ArrayList<Temas> temas = listaDeTemas.getListaDeTemas();

	        // Verificar que la lista de temas no sea nula
	        assertEquals(12, temas.size()); // Comprueba si se han añadido las 12 tareas

	        // Verificar algunas tareas específicas
	        assertEquals("Teoría de la Probabilidad Estocástica", temas.get(0).getNombre());
	        assertEquals("Basica: Tarea 1: Simulación de Monte Carlo 🡺 Implementar un algoritmo de simulación de Monte Carlo para estimar el valor de π.", temas.get(0).getContenidoTarea());

	        assertEquals("Teoría de Números Avanzada", temas.get(3).getNombre());
	        assertEquals("Intermedia: Tarea 2: Congruencia de Euler y Criptografía RSA 🡺 Investigar y demostrar la congruencia de Euler y su aplicación en criptografía RSA.", temas.get(3).getContenidoTarea());

	        assertEquals("Análisis Funcional", temas.get(6).getNombre());
	        assertEquals("Avanzada: Tarea 3: Teorema de Representación de Riesz en Espacios de Hilbert 🡺 Investigar y demostrar el teorema de representación de Riesz sobre espacios de Hilbert.", temas.get(6).getContenidoTarea());

	        assertEquals("Topología Algebraica", temas.get(9).getNombre());
	        assertEquals("Intermedia: Tarea 2:Teorema de la Invariancia de la Dimensión y Propiedades Topológicas 🡺 Investigar y aplicar el teorema de la invariancia de la dimensión para demostrar propiedades topológicas de variedades.", temas.get(9).getContenidoTarea());

	        assertEquals("Teoría de la Aproximación y Funciones Especiales", temas.get(11).getNombre());
	        assertEquals("AvanzadaTarea 3: Conceptos básicos de álgebra 🡺 Repasar conceptos fundamentales de álgebra como ecuaciones lineales y factorización.", temas.get(11).getContenidoTarea());
	    }
		
	//7
		

	//testVerMenuProfesor
	 @Test
	    public void testVerMenu() {
	        String input = menuInput;
	        // Simula la entrada del usuario
	        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
	        System.setIn(inContent);
	        

	        // Crea instancias de las clases necesarias
	        
	 }
	 
		//8
	 	@Test
	    public void testMostrarTareaValida() {
	        ArrayList<Tarea> tareas = new ArrayList<>();
	        assertEquals("Tarea 1", "Hacer algo");
	        assertEquals("Tarea 2", "Hacer otra cosa");
	        
	        // Seleccionamos la tarea número 1
	        Tarea tareaSeleccionada = mostrarTarea.(tareas, 1);
	       // Tarea tareaseleccionada = mostrarTarea.mostradorTarea(tareas ,1);
	        // Verificamos que la tarea seleccionada sea la tarea número 1
	        assertEquals("Tarea 1", tareaSeleccionada.getNombre());
	        assertEquals("Hacer algo", tareaSeleccionada.getDescripcion());
	    }
	
	 

	
	//9 
	 @Test
	    public void testMostrarTareaInvalida() {
	        ArrayList<Tarea> tareas = new ArrayList<>();
	        assertEquals("Tarea 1", "Hacer algo");
	        assertEquals("Tarea 2", "Hacer otra cosa");
	        
	        
	        // Verificamos que la tarea seleccionada sea nula
	        assertEquals(null, tareas);
	    }
	
	

	//10
	 @Test
	    public void testObtenerTodasLasTareas() {
	        ArrayList<Tarea> tareas = Tarea.obtenerTodasLasTareas();
	        
	        // Verificar que la lista de tareas no sea nula
	        assertNotNull(tareas);
	        
	        // Verificar que la cantidad de tareas es correcta
	        assertEquals(21, tareas.size());
	        
	        // Verificar algunas tareas específicas
	        assertEquals("Basica", tareas.get(0).getDificultad());
	        assertEquals("Tarea 1: Simulación de Monte Carlo", tareas.get(0).getTitulo());
	        assertEquals("Implementar un algoritmo de simulación de Monte Carlo para estimar el valor de π.", tareas.get(0).getDescripcion());

	        assertEquals("Intermedia", tareas.get(7).getDificultad());
	        assertEquals("Tarea 3: Teorema de Representación de Riesz en Espacios de Hilbert", tareas.get(11).getTitulo());
	        assertEquals("Investigar y demostrar el teorema de representación de Riesz sobre espacios de Hilbert.", tareas.get(11).getDescripcion());

	        assertEquals("Avanzada", tareas.get(20).getDificultad());
	        assertEquals("Tarea 3: Conceptos básicos de álgebra", tareas.get(20).getTitulo());
	        assertEquals("Repasar conceptos fundamentales de álgebra como ecuaciones lineales y factorización.", tareas.get(20).getDescripcion());
	    }
	 
	//11
	 @Test
	    public void testConstructorTemas() {
	        String nombre = "Matemáticas";
	        String descripcion = "Este tema cubre varios conceptos matemáticos.";
	        String contenidoTarea = "Implementar un algoritmo de simulación de Monte Carlo para estimar el valor de π.";

	        Temas tema = new Tema1(nombre, descripcion, contenidoTarea);

	        // Verificar que los valores se establecen correctamente
	        assertEquals(nombre, tema.getNombre());
	        assertEquals(descripcion, tema.getDescripcion());
	        assertEquals(contenidoTarea, tema.getContenidoTarea());
	    }
	
		
	//12
	 @Test
	    public void testGetSetNombre() {
	        Temas tema = new Tema1();
	        String nombre = "Matemáticas";
	        
	        tema.setNombre(nombre);
	        
	        // Verificar si el método getNombre retorna el nombre correctamente
	        assertEquals(nombre, tema.getNombre());
	    }
		
	//13
	 @Test
	    public void testGetSetDescripcion() {
	        Temas tema = new Tema1();
	        String descripcion = "Este tema cubre varios conceptos matemáticos.";
	        
	        tema.setDescripcion(descripcion);
	        
	        // Verificar si el método getDescripcion retorna la descripcion correctamente
	        assertEquals(descripcion, tema.getDescripcion());
	    }
		
	//14
	 @Test
	    public void testGetSetContenidoTarea() {
	        Temas tema = new Tema1();
	        String contenidoTarea = "Implementar un algoritmo de simulación de Monte Carlo para estimar el valor de π.";
	        
	        tema.setContenidoTarea(contenidoTarea);
	        
	        // Verificar si el método getContenidoTarea retorna el contenidoTarea correctamente
	        assertEquals(contenidoTarea, tema.getContenidoTarea());
	    }
		
	//15
		@Test
		void test15() {
		
		
		}
	}
