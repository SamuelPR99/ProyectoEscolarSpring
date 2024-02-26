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

        tareas.add(new Tarea("Avanzada", "Tarea 1: Simulación de Monte Carlo ", "Implementar un algoritmo de simulación de Monte Carlo para estimar el valor de π."));
        tareas.add(new Tarea("Intermedia", "Tarea 2: Procesos Estocásticos", "Analizar la convergencia de una cadena de Markov mediante el cálculo de matrices de transición."));
        tareas.add(new Tarea("Basica", "Tarea 3: Introducción a la estadística", "Repasar los conceptos básicos de estadística descriptiva."));
        tareas.add(new Tarea("Avanzada", "Tarea 1: Congruencias y Teorema Chino del Resto", "Resolver un sistema de congruencias utilizando el Teorema Chino del Resto."));
        tareas.add(new Tarea("Intermedia", "Tarea 2: Teorema de Fermat y Último Teorema de Fermat", "Investigar sobre los enunciados y pruebas del Teorema de Fermat y el Último Teorema de Fermat."));
        tareas.add(new Tarea("Basica", "Tarea 3: Operaciones básicas con números enteros", "Practicar sumas, restas, multiplicaciones y divisiones de números enteros."));
        tareas.add(new Tarea("Avanzada", "Tarea 1: Espacios de Sobolev", "Estudiar la teoría de espacios de Sobolev y su relación con problemas de valores en la frontera."));
        tareas.add(new Tarea("Intermedia", "Tarea 2: Operadores Lineales y Teorema del Punto Fijo", "Demostrar el Teorema del Punto Fijo de Banach para operadores lineales en espacios de Banach."));
        tareas.add(new Tarea("Basica", "Tarea 3: Conceptos básicos de álgebra", "Repasar conceptos fundamentales de álgebra como ecuaciones lineales y factorización."));
        // Agregar mas tareas

        return tareas;
	}
	
}
    
