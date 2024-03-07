package com.daw.proyectoescolar.paula;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.daw.proyectoescolar.entidades.Alumno;
import com.daw.proyectoescolar.entidades.Profesor;
import com.daw.proyectoescolar.entidades.Tarea;
import com.daw.proyectoescolar.entidades.UsuarioBase;
import com.daw.proyectoescolar.servicios.gestionusuarios.GestionadorUsuarios;
import com.daw.proyectoescolar.servicios.tareas.GestionTemas;



// generar una libreria que se pueda utilizar en toda la aplicacion, una especie de clase que yo voy a ir llamando desde cada uno de mis metodos
//escribir log(""), le pase una cadena de texto y yo le pase el archivo
//estoy saliendo de mi menu alta usuario
//un log guarda la informacion de cada usuario que entra en la pagina
//

class TareasTest {

	protected  ArrayList<UsuarioBase> usuarios;
	protected ArrayList<Alumno> alumnos;
	protected String menuInput="8";
	GestionadorUsuarios gestorUsuarios = new GestionadorUsuarios();
    GestionTemas gestorTemas = new GestionTemas();
	//1
	@Test
	void testTareaBasica() {
		//si pulsas tema 1, te tiene que aparecer el tema con el nombre y la descripcion y sus tareas
	//Si la tarea es basica y del tema 1, tiene que coincidir con la descripcion de dicha tarea
		Tarea tarea = new Tarea("Básica","Tema 1","Descripción de la tarea");
		 assertEquals("Básica",tarea.getTipo());
		 assertEquals("Tarea 1", tarea.getNombre());
	     assertEquals("Descripción de la tarea", tarea.getDescripcion());
	}

	//2
		@Test
		void test2() {
		
		//si entras como profesor te tiene que salir su menu
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
		void test5() {
			//si en el menu de profesor pulsas ver lista de alumnos te tiene q salir la lista de alumnos
			
		}
	//6
		
		@Test
		void test6() {
			//si entras en ver el listado de temas en alumnos te tiene que salir el listado de temas
			
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
	    public void testMostrarRecomendacion() {
	        // Crear una instancia de la clase que contiene el método mostrarRecomendacion
	        Tarea tarea = new Tarea("Tarea de prueba", "Realizar pruebas unitarias");

	        // Llamar al método que se va a probar
	        tarea.mostrarRecomendacion();

	        // Capturar la salida del método
	        String output = outputContent.toString().trim();

	        // Definir la salida esperada
	        String expectedOutput = "Recomendación: Tarea Tarea de prueba\nDescripción: Realizar pruebas unitarias";

	        // Afirmar que la salida esperada es igual a la salida del método
	        Assertions.assertEquals(expectedOutput, output);
	    }
	
	
	//9
		
		@Test
		void test9() {
		
		
		}
		
	//10
		@Test
		void test10() {
		
		
		}
		
	//11
		@Test
		void test11() {
		
		
		}
		
	//12
		
		@Test
		void test12() {
		
		
		}
		
	//13
		@Test
		void test13() {
		
		
		}
		
	//14
		@Test
		void test14() {
		
		
		}
		
	//15
		@Test
		void test15() {
		
		
		}
	}
