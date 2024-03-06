package com.daw.proyectoescolar.paula;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Scanner;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.daw.proyectoescolar.entidades.Alumno;
import com.daw.proyectoescolar.entidades.Profesor;
import com.daw.proyectoescolar.entidades.Tarea;
import com.daw.proyectoescolar.entidades.UsuarioBase;

class TareasTest {

	protected  ArrayList<UsuarioBase> usuarios;
	protected ArrayList<Alumno> alumnos;
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
		@Test
		void test7() {
			//
			
		}
	//8
		@Test
		void test8() {
			//
		
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
