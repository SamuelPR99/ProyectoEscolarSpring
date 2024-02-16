package com.daw.proyectoescolar.servicios.tareas;

import java.util.ArrayList;
import java.util.Scanner;

public class Profesor {

		static Scanner sc= new Scanner(System.in);
		//ATRIBUTOS
		protected String contraseña;
		protected String usuario;
		protected ArrayList<Tema> temas;
		protected ArrayList<Tarea> tareas;
		
		
		//CONSTRUCTORES
		public Profesor() {
			
		}
		public Profesor(String usuario, String contraseña) {
			this.contraseña=contraseña;
			this.usuario=usuario;
		}
		
		
		//METODOS
		public String getContraseña() {
			return contraseña;
		}
		public void setContraseña(String contraseña) {
			this.contraseña = contraseña;
		}
	
		public String getUsuario() {
			return usuario;
		}
		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}
	
		public void mostrarMenu() {
			Scanner sc= new Scanner(System.in);
			int opcion;
			
			do {
				System.out.println("Bienvenido Profesor");
				System.out.println("1. Ver Listado de Alumnos");
				System.out.println("2. Ver Listado de Temas");
				System.out.println("3. Salir");
				opcion=sc.nextInt();
			
				switch(opcion) {
				case 1:
					System.out.println("Lista alumnos");
					break;
				case 2:
					System.out.println("Lista temas y tareas");
					new Tema().mostrarTemas();
					break;
				case 3:
					System.out.println("Salir");
					break;
					
				default:
					System.out.println("Opción no válida");
				}
			}while(opcion!=3);
			
			sc.close();
		}
		public void seleccionarTema() {
			System.out.println("Introduce el número del tema");
			int opcion=sc.nextInt();
			for(Tema t : temas) {
				if(opcion<=6 && opcion>=1) {
			
				}else {
					System.out.println("Error del elección");
				}
			}
		}
		
		public void prueba() {
			temas = new ArrayList<Tema>();
			
			Tema t1 = new Tema(" 1: Teoría de la Probabilidad Estocástica");
			t1.agregarTarea(new Tarea("Tarea 1: Simulación de Monte Carlo ", "Implementar un algoritmo de simulación de Monte Carlo para estimar el valor de π."));
			t1.agregarTarea(new Tarea("Tarea 2:Procesos Estocásticos", "Analizar la convergencia de una cadena de Markov mediante el cálculo de matrices de transición."));
			System.out.println(" ");
			temas.add(t1);
			
			Tema t2= new Tema(" 2: Teoría de Números Avanzada");
			t2.agregarTarea(new Tarea("Tarea 1: Congruencias y Teorema Chino del Resto", "Resolver un sistema de congruencias utilizando el Teorema Chino del Resto."));
			t2.agregarTarea(new Tarea("Tarea 2: Teorema de Fermat y Último Teorema de Fermat", "Investigar sobre los enunciados y pruebas del Teorema de Fermat y el Último Teorema de Fermat."));
			System.out.println(" ");
			temas.add(t2);
			
			Tema t3= new Tema(" 3: Análisis Funcional");
			t3.agregarTarea(new Tarea("Tarea 1: Espacios de Sobolev", "Estudiar la teoría de espacios de Sobolev y su relación con problemas de valores en la frontera."));
			t3.agregarTarea(new Tarea("Tarea 2: Operadores Lineales y Teorema del Punto Fijo", " Demostrar el Teorema del Punto Fijo de Banach para operadores lineales en espacios de Banach."));
			System.out.println(" ");
			temas.add(t3);
			
			Tema t4= new Tema(" 4: Topología Algebraica");
			t4.agregarTarea(new Tarea("Tarea 1: Homología Singular", "Calcular la homología singular de un espacio topológico dado."));
			t4.agregarTarea(new Tarea("Tarea 2: Teorema de los Cuatro Colores"," Investigar sobre la demostración del Teorema de los Cuatro Colores utilizando conceptos de topología algebraica."));
			System.out.println(" ");
			temas.add(t4);
			
			Tema t5= new Tema(" 5: Teoría de Representación de Grupos");
			t5.agregarTarea(new Tarea("Tarea 1: Representaciones de Grupos de Lie","Estudiar las representaciones de grupos de Lie y su aplicación en física teórica."));
			t5.agregarTarea(new Tarea("Tarea 2: Caracteres de Representaciones","Calcular los caracteres de representaciones de grupos finitos y aplicarlos para resolver problemas de estructura de grupos."));
			
			temas.add(t5);
			
			Tema t6= new Tema(" 6: Teoría de la Aproximación y Funciones Especiales");
			t6.agregarTarea(new Tarea("Tarea 1: Polinomios de Chebyshev y Minimización de Error", "Utilizar polinomios de Chebyshev para minimizar el error de aproximación de una función."));
			t6.agregarTarea(new Tarea("Tarea 2: Funciones Hipergeométricas","Investigar sobre las propiedades y aplicaciones de funciones hipergeométricas en análisis matemático y física teórica."));
			temas.add(t6);
			
			for(Tema tema : temas) {
				System.out.println(" ");
				System.out.println("Tema"+ tema.getNombre());
				System.out.println("Tareas: ");
				for(Tarea  tarea : tema.getTareas()) {
					System.out.println(tarea.getNombre()+ "." + " Descripción: "+ tarea.getDescripcion());
					
				}
			}
			
		}
}
