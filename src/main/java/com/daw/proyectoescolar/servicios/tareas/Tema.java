package com.daw.proyectoescolar.servicios.tareas;

import java.util.ArrayList;
import java.util.Scanner;

public class Tema  {

	protected String nombre;
	protected ArrayList<Tarea>tareas;
	protected ArrayList<Tema> temas;
	protected Tarea tarea;
	
	public Tema() {
		
	}

	public Tema(String nombre) {
		this.nombre=nombre;
		
		tareas= new ArrayList<>();
	}

	public String getNombre() {
		return nombre;
	}

	public ArrayList<Tarea> getTareas() {
		return tareas;
	}
	
	public void mostrarTemas() {
		Scanner sc= new Scanner(System.in);
		temas = new ArrayList<Tema>();
		
		Tema t1 = new Tema(" 1: Teoría de la Probabilidad Estocástica");
		temas.add(t1);
		
		Tema t2= new Tema(" 2: Teoría de Números Avanzada");
		temas.add(t2);
		
		Tema t3= new Tema(" 3: Análisis Funcional");
		temas.add(t3);
		
		Tema t4= new Tema(" 4: Topología Algebraica");
		temas.add(t4);
		
		Tema t5= new Tema(" 5: Teoría de Representación de Grupos");
		temas.add(t5);
		
		Tema t6= new Tema(" 6: Teoría de la Aproximación y Funciones Especiales");
		temas.add(t6);
	
		System.out.println("Selecciona un tema");
		int opcion=sc.nextInt();
		
		switch(opcion){
			case 1:
				t1.agregarTarea(new Tarea("Tarea 1: Simulación de Monte Carlo ", "Implementar un algoritmo de simulación de Monte Carlo para estimar el valor de π."));
				t1.agregarTarea(new Tarea("Tarea 2:Procesos Estocásticos", "Analizar la convergencia de una cadena de Markov mediante el cálculo de matrices de transición."));
			case 2:
				t2.agregarTarea(new Tarea("Tarea 1: Congruencias y Teorema Chino del Resto", "Resolver un sistema de congruencias utilizando el Teorema Chino del Resto."));
				t2.agregarTarea(new Tarea("Tarea 2: Teorema de Fermat y Último Teorema de Fermat", "Investigar sobre los enunciados y pruebas del Teorema de Fermat y el Último Teorema de Fermat."));
				
			case 3:
				t3.agregarTarea(new Tarea("Tarea 1: Espacios de Sobolev", "Estudiar la teoría de espacios de Sobolev y su relación con problemas de valores en la frontera."));
				t3.agregarTarea(new Tarea("Tarea 2: Operadores Lineales y Teorema del Punto Fijo", " Demostrar el Teorema del Punto Fijo de Banach para operadores lineales en espacios de Banach."));
				
			case 4:
				t4.agregarTarea(new Tarea("Tarea 1: Homología Singular", "Calcular la homología singular de un espacio topológico dado."));
				t4.agregarTarea(new Tarea("Tarea 2: Teorema de los Cuatro Colores"," Investigar sobre la demostración del Teorema de los Cuatro Colores utilizando conceptos de topología algebraica."));
				
			case 5:
				t5.agregarTarea(new Tarea("Tarea 1: Representaciones de Grupos de Lie","Estudiar las representaciones de grupos de Lie y su aplicación en física teórica."));
				t5.agregarTarea(new Tarea("Tarea 2: Caracteres de Representaciones","Calcular los caracteres de representaciones de grupos finitos y aplicarlos para resolver problemas de estructura de grupos."));
			
			case 6:
				t6.agregarTarea(new Tarea("Tarea 1: Polinomios de Chebyshev y Minimización de Error", "Utilizar polinomios de Chebyshev para minimizar el error de aproximación de una función."));
				t6.agregarTarea(new Tarea("Tarea 2: Funciones Hipergeométricas","Investigar sobre las propiedades y aplicaciones de funciones hipergeométricas en análisis matemático y física teórica."));
				
		}
		sc.close();
	
	}

	public void agregarTarea(Tarea tarea) {
		temas = new ArrayList<Tema>();
		tareas.add(tarea);
		
        temas.add(new Tarea("Tarea 1: Simulación de Monte Carlo ", "Implementar un algoritmo de simulación de Monte Carlo para estimar el valor de π."));
		t1.agregarTarea(new Tarea("Tarea 2:Procesos Estocásticos", "Analizar la convergencia de una cadena de Markov mediante el cálculo de matrices de transición."));
    }
	
	/*public void seleccionarTema() {
		Scanner sc= new Scanner(System.in);
		System.out.println("Selecciona un tema");
		int opcion=sc.nextInt();
		
		switch(opcion){
			case 1:
				
				
		}
		sc.close();
		
		}*/
	

	public ArrayList<Tema> getTemas() {
		return temas;
	}

	public void setTemas(ArrayList<Tema> temas) {
		this.temas = temas;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTareas(ArrayList<Tarea> tareas) {
		this.tareas = tareas;
	}
	
	
}
