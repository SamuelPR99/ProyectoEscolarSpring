package com.daw.proyectoescolar.entidades;

import java.util.ArrayList;
import java.util.Scanner;

public class Tarea {
    
    // Atributos
    protected String tipo;
    protected String nombre;
    protected String descripcion;
    

    // Constructores
    public Tarea() {

    }
    public Tarea(String tipo) {
        this.tipo = tipo;
    }

    public Tarea(String tipo, String nombre, String descripcion) {
        this.tipo = tipo;
    	this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
    // Metodos
    public void mostrarRecomendacion() {
        System.out.println("Recomendación: Tarea " + tipo + "\nDescripción: " + descripcion);
    }
    
    // Mostrar tareas
    public void mostrarTareas(Scanner sc) {
        ArrayList<Tarea> tareas = obtenerTodasLasTareas();
        
        System.out.println("Selecciona el numero de la tarea");
        int opcion = sc.nextInt();
        
        for(Tarea t : tareas) {
            if (opcion <= 12 && opcion >= 1) {
                System.out.println("Nombre de la tarea: " + t.getNombre());
                System.out.println("Descripcion: " + t.getDescripcion());

            } else {
                System.err.println("Error del eleccion");
            }
            
        }
    }
    	
    // Obtener array de tareas
    public static ArrayList<Tarea> obtenerTodasLasTareas() {
    	
        ArrayList<Tarea> tareas = new ArrayList<Tarea>();

        // TEMA 1
        tareas.add(new Tarea("Basica", "Tarea 1: Simulación de Monte Carlo ", 
        		"Implementar un algoritmo de simulación de Monte Carlo para estimar el valor de π."));
        tareas.add(new Tarea("Intermedia", "Tarea 2: Análisis de Convergencia de Series Infinitas", 
        		"Analizar la convergencia de una serie infinita utilizando técnicas avanzadas de cálculo de límites y criterios de convergencia."));
        tareas.add(new Tarea("Avanzada", "Tarea 3: Demostración del Teorema Central del Límite", 
        		"Investigar y demostrar el teorema central del límite utilizando herramientas avanzadas de probabilidad y análisis matemático."));
      
        // TEMA 2
        tareas.add(new Tarea("Basica", "Tarea 1: Cálculo de Números Primos con la Criba de Eratóstenes", 
        		" Implementar un algoritmo para calcular los primeros N números primos utilizando la criba de Eratóstenes."));
        tareas.add(new Tarea("Intermedia", "Tarea 2: Congruencia de Euler y Criptografía RSA", 
        		"Investigar y demostrar la congruencia de Euler y su aplicación en criptografía RSA."));
        tareas.add(new Tarea("Avanzada", "Tarea 3: Prueba de Primalidad con Test de Miller-Rabin", 
        		" Estudiar y desarrollar una prueba de primalidad utilizando el test de primalidad de Miller-Rabin."));
      
        // TEMA 3
        tareas.add(new Tarea("Basica", "Tarea 1: Método de la Potencia para Autovalores y Autovectores", 
        		"Implementar un algoritmo para encontrar los autovalores y autovectores de una matriz utilizando el método de la potencia."));
        tareas.add(new Tarea("Intermedia", "Tarea 2: Teorema del Punto Fijo de Banach y Ecuaciones Funcionales", 
        		"Estudiar y aplicar el teorema del punto fijo de Banach para resolver ecuaciones funcionales."));
        tareas.add(new Tarea("Avanzada", "Tarea 3: Teorema de Representación de Riesz en Espacios de Hilbert", 
        		"Investigar y demostrar el teorema de representación de Riesz sobre espacios de Hilbert."));
      
        // TEMA 4
        tareas.add(new Tarea("Basica", "Tarea 1: Cálculo del Grupo Fundamental con la Presentación de Van Kampen", 
        		" Implementar un algoritmo para calcular el grupo fundamental de un espacio topológico utilizando la presentación de Van Kampen."));
        tareas.add(new Tarea("Intermedia", "Tarea 2:Teorema de la Invariancia de la Dimensión y Propiedades Topológicas", 
        		"Investigar y aplicar el teorema de la invariancia de la dimensión para demostrar propiedades topológicas de variedades."));
        tareas.add(new Tarea("Avanzada", "Tarea 3: Teorema de la Dualidad de Poincaré en Complejos Simpliciales", 
        		"Estudiar y demostrar el teorema de la dualidad de Poincaré para complejos simpliciales."));
       
        // TEMA 5
        tareas.add(new Tarea("Basica", "Tarea 1: Encontrar Representaciones Irreducibles con Caracteres", 
        		"Implementar un algoritmo para encontrar las representaciones irreducibles de un grupo finito utilizando caracteres."));
        tareas.add(new Tarea("Intermedia", "Tarea 2: Teorema de Maschke y Descomposición de Representaciones", 
        		"Investigar y aplicar el teorema de Maschke para descomponer una representación en suma directa de representaciones irreducibles."));
        tareas.add(new Tarea("Avanzada", "Tarea 3: Teorema de Peter-Weyl y Completitud de Funciones de Clase", 
        		"Estudiar y demostrar el teorema de Peter-Weyl sobre la completitud de las funciones de clase para grupos compactos."));
      
        // TEMA6
        tareas.add(new Tarea("Basica", "Tarea 1: Espacios de Sobolev", 
        		"Estudiar la teoría de espacios de Sobolev y su relación con problemas de valores en la frontera."));
        tareas.add(new Tarea("Intermedia", "Tarea 2: Operadores Lineales y Teorema del Punto Fijo", 
        		"Demostrar el Teorema del Punto Fijo de Banach para operadores lineales en espacios de Banach."));
        tareas.add(new Tarea("Avanzada", "Tarea 3: Conceptos básicos de álgebra", 
        		"Repasar conceptos fundamentales de álgebra como ecuaciones lineales y factorización."));

        return tareas;
	}
	
}
    
