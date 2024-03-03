package com.daw.proyectoescolar.repositorio;

import com.daw.proyectoescolar.entidades.Temas;

import java.util.ArrayList;

public class ListaDeTemas {
	
	protected ArrayList<Temas>ListaDeTemas;
	
	public ListaDeTemas() {
		super();
	}

	public ListaDeTemas(ArrayList<Temas> listaDeTemas) {
		super();
		ListaDeTemas = listaDeTemas;
	}

	public ArrayList<Temas> getListaDeTemas() {
		return ListaDeTemas;
	}

	public void setListaDeTemas(ArrayList<Temas> listaDeTemas) {
		ListaDeTemas = listaDeTemas;
	}
	
	
	
	public void temasIniciados() {
		
		ListaDeTemas = new ArrayList<Temas>();
		
		
			
		Temas tema11 = new Tema1();
		tema11.setNombre("Teoría de la Probabilidad Estocástica");
		tema11.setDescripcion("Estudio de la probabilidad en un espacio de probabilidad");
		tema11.setContenidoTarea("Basica: " + " Tarea 1: Simulación de Monte Carlo 🡺" 
		+ " Implementar un algoritmo de simulación de Monte Carlo para estimar el valor de π.");
		
		ListaDeTemas.add(tema11);
			
			
		Temas tema12 = new Tema1();
		tema12.setNombre("Teoría de la Probabilidad Estocástica");
		tema12.setDescripcion("Estudio de la probabilidad en un espacio de probabilidad");
		tema12.setContenidoTarea("Intermedia: "+ " Tarea 2: Análisis de Convergencia de Series Infinitas 🡺" 
		+ " Analizar la convergencia de una serie infinita utilizando técnicas avanzadas de cálculo de límites y criterios de convergencia.");
			
		ListaDeTemas.add(tema12);
			
		Temas tema13 = new Tema1();
		tema13.setNombre("Teoría de la Probabilidad Estocástica");
		tema13.setDescripcion("Estudio de la probabilidad en un espacio de probabilidad");
		tema13.setContenidoTarea("Avanzada: " + " Tarea 3: Demostración del Teorema Central del Límite 🡺" 
		+ " Investigar y demostrar el teorema central del límite utilizando herramientas avanzadas de probabilidad y análisis matemático.");
		
		ListaDeTemas.add(tema13);
		
		
		Temas tema21 = new Tema2();
		tema21.setNombre("Teoría de Números Avanzada");
		tema21.setDescripcion("Estudio de los números enteros y sus propiedades");
		tema21.setContenidoTarea("Basica: " + "Tarea 1: Cálculo de Números Primos con la Criba de Eratóstenes 🡺" 
		+ " Implementar un algoritmo para calcular los primeros N números primos utilizando la criba de Eratóstenes.");
		
		ListaDeTemas.add(tema21);
		
		
		Temas tema22 = new Tema2();
		tema22.setNombre("Teoría de Números Avanzada");
		tema22.setDescripcion("Estudio de los números enteros y sus propiedades");
		tema22.setContenidoTarea("Intermedia: " + "Tarea 2: Congruencia de Euler y Criptografía RSA 🡺" 
		+ " Investigar y demostrar la congruencia de Euler y su aplicación en criptografía RSA.");
		
		ListaDeTemas.add(tema22);
		
		Temas tema23 = new Tema2();
		tema23.setNombre("Teoría de Números Avanzada");
		tema23.setDescripcion("Estudio de los números enteros y sus propiedades");
		tema23.setContenidoTarea("Avanzada: " + "Tarea 3: Prueba de Primalidad con Test de Miller-Rabin 🡺" 
		+ " Estudiar y desarrollar una prueba de primalidad utilizando el test de primalidad de Miller-Rabin.");
		
		ListaDeTemas.add(tema23);
		
		
		Temas tema31 = new Tema3();
		tema31.setNombre("Análisis Funcional");
		tema31.setDescripcion("Estudio de espacios vectoriales normados y sus propiedades");
		tema31.setContenidoTarea("Basica: " +"Tarea 1: Método de la Potencia para Autovalores y Autovectores 🡺"
		+ " Implementar un algoritmo para encontrar los autovalores y autovectores de una matriz utilizando el método de la potencia.");
		
		ListaDeTemas.add(tema31);
		
		
		Temas tema32 = new Tema3();
		tema32.setNombre("Análisis Funcional");
		tema32.setDescripcion("Estudio de espacios vectoriales normados y sus propiedades");
		tema32.setContenidoTarea("Intermedia: " + "Tarea 2: Teorema del Punto Fijo de Banach y Ecuaciones Funcionales 🡺" 
		+ " Estudiar y aplicar el teorema del punto fijo de Banach para resolver ecuaciones funcionales.");
		
		ListaDeTemas.add(tema32);
		
		Temas tema33 = new Tema3();
		tema33.setNombre("Análisis Funcional");
		tema33.setDescripcion("Estudio de espacios vectoriales normados y sus propiedades");
		tema33.setContenidoTarea("Avanzada: " + "Tarea 3: Teorema de Representación de Riesz en Espacios de Hilbert 🡺" 
		+ " Investigar y demostrar el teorema de representación de Riesz sobre espacios de Hilbert.");
		
		ListaDeTemas.add(tema33);
		
		Temas tema41 = new Tema4();
		tema41.setNombre("Topología Algebraica");
		tema41.setDescripcion("Estudio de la topología y sus propiedades");
		tema41.setContenidoTarea("Basica: " + "Tarea 1: Cálculo del Grupo Fundamental con la Presentación de Van Kampen 🡺" 
		+ " Implementar un algoritmo para calcular el grupo fundamental de un espacio topológico utilizando la presentación de Van Kampen.");
		
		ListaDeTemas.add(tema41);
		
		
		Temas tema42 = new Tema4();
		tema42.setNombre("Topología Algebraica");
		tema42.setDescripcion("Estudio de la topología y sus propiedades");
		tema42.setContenidoTarea("Intermedia: " + "Tarea 2:Teorema de la Invariancia de la Dimensión y Propiedades Topológicas 🡺" 
		+ " Investigar y aplicar el teorema de la invariancia de la dimensión para demostrar propiedades topológicas de variedades.");
		
		ListaDeTemas.add(tema42);
		
		Temas tema43 = new Tema4();
		tema43.setNombre("Topología Algebraica");
		tema43.setDescripcion("Estudio de la topología y sus propiedades");
		tema43.setContenidoTarea("Avanzada: " + "Tarea 3: Teorema de la Dualidad de Poincaré en Complejos Simpliciales 🡺" 
		+ " Estudiar y demostrar el teorema de la dualidad de Poincaré para complejos simpliciales.");
		
		ListaDeTemas.add(tema43);
		
		Temas tema51 = new Tema5();
		tema51.setNombre("Teoría de Representación de Grupos");
		tema51.setDescripcion("Estudio de la teoría de grupos y sus propiedades");
		tema51.setContenidoTarea("Basica: "+ "Tarea 1: Encontrar Representaciones Irreducibles con Caracteres 🡺"
		+ " Implementar un algoritmo para encontrar las representaciones irreducibles de un grupo finito utilizando caracteres.");
		
		ListaDeTemas.add(tema51);
		
		
		Temas tema52 = new Tema5();
		tema52.setNombre("Teoría de Representación de Grupos");
		tema52.setDescripcion("Estudio de la teoría de grupos y sus propiedades");
		tema52.setContenidoTarea("Intermedia: "+  "Tarea 2: Teorema de Maschke y Descomposición de Representaciones 🡺" 
		+ " Investigar y aplicar el teorema de Maschke para descomponer una representación en suma directa de representaciones irreducibles.");
		
		ListaDeTemas.add(tema52);
		
		Temas tema53 = new Tema5();
		tema53.setNombre("Teoría de Representación de Grupos");
		tema53.setDescripcion("Estudio de la teoría de grupos y sus propiedades");
		tema53.setContenidoTarea("Avanzada: " + "Tarea 3: Teorema de Peter-Weyl y Completitud de Funciones de Clase 🡺" 
		+ " Estudiar y demostrar el teorema de Peter-Weyl sobre la completitud de las funciones de clase para grupos compactos.");
		
		ListaDeTemas.add(tema53);
		
		Temas tema61 = new Tema6();
		tema61.setNombre("Teoría de la Aproximación y Funciones Especiales");
		tema61.setDescripcion("Estudio de la aproximación de funciones y sus propiedades");
		tema61.setContenidoTarea("Basica: " + "Tarea 1: Espacios de Sobolev 🡺" 
		+ " Estudiar la teoría de espacios de Sobolev y su relación con problemas de valores en la frontera.");
		
		ListaDeTemas.add(tema61);
		
		
		Temas tema62 = new Tema6();
		tema62.setNombre("Teoría de la Aproximación y Funciones Especiales");
		tema62.setDescripcion("Estudio de la aproximación de funciones y sus propiedades");
		tema62.setContenidoTarea("Intermedia: " + "Tarea 2: Operadores Lineales y Teorema del Punto Fijo 🡺" 
		+ " Demostrar el Teorema del Punto Fijo de Banach para operadores lineales en espacios de Banach.");
		
		ListaDeTemas.add(tema62);
		Temas tema63 = new Tema6();
		tema63.setNombre("Teoría de la Aproximación y Funciones Especiales");
		tema63.setDescripcion("Estudio de la aproximación de funciones y sus propiedades");
		tema63.setContenidoTarea("Avanzada" + "Tarea 3: Conceptos básicos de álgebra 🡺" 
		+ " Repasar conceptos fundamentales de álgebra como ecuaciones lineales y factorización.");
		
		ListaDeTemas.add(tema63);
	}
	
}
