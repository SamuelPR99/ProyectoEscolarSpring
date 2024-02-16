package com.daw.proyectoescolar.entidades;

import java.util.ArrayList;

public class Tarea extends Tema{
	
	// Atributos
	
    protected String tipo;
    protected String nombre;
    protected String descripcion;
    protected ArrayList<Tarea>tareas;

    // Constructores
    
    public Tarea(String tipo) {
        this.tipo = tipo;
      
    }

    public Tarea(String nombre, String descripcion) {
       
        this.nombre= nombre;
        this.descripcion= descripcion;
        tareas= new ArrayList<>();
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
        System.out.println("Recomendación: Tarea " + tipo);
    }
    public void mostrarTareas() {
    	tareas = new ArrayList<Tarea>();
		Tarea t1= new Tarea("Tarea 1: Simulación de Monte Carlo ", "Implementar un algoritmo de simulación de Monte Carlo para estimar el valor de π.");
		Tarea t2= new Tarea("Tarea 2:Procesos Estocásticos", "Analizar la convergencia de una cadena de Markov mediante el cálculo de matrices de transición.");
		Tarea t3= new Tarea("Tarea 1: Congruencias y Teorema Chino del Resto", "Resolver un sistema de congruencias utilizando el Teorema Chino del Resto.");
		Tarea t4= new Tarea("Tarea 2: Teorema de Fermat y Último Teorema de Fermat", "Investigar sobre los enunciados y pruebas del Teorema de Fermat y el Último Teorema de Fermat.");
		Tarea t5 = new Tarea("Tarea 1: Espacios de Sobolev", "Estudiar la teoría de espacios de Sobolev y su relación con problemas de valores en la frontera.");
		Tarea t6= new Tarea("Tarea 2: Operadores Lineales y Teorema del Punto Fijo", " Demostrar el Teorema del Punto Fijo de Banach para operadores lineales en espacios de Banach.");
		Tarea t7= new Tarea("Tarea 1: Homología Singular", "Calcular la homología singular de un espacio topológico dado.");
		Tarea t8= new Tarea("Tarea 2: Teorema de los Cuatro Colores"," Investigar sobre la demostración del Teorema de los Cuatro Colores utilizando conceptos de topología algebraica.");
		Tarea t9= new Tarea("Tarea 1: Representaciones de Grupos de Lie","Estudiar las representaciones de grupos de Lie y su aplicación en física teórica.");
		Tarea t10= new Tarea("Tarea 2: Caracteres de Representaciones","Calcular los caracteres de representaciones de grupos finitos y aplicarlos para resolver problemas de estructura de grupos.");
		Tarea t11= new Tarea("Tarea 1: Polinomios de Chebyshev y Minimización de Error", "Utilizar polinomios de Chebyshev para minimizar el error de aproximación de una función.");
		Tarea t12 = new Tarea("Tarea 2: Funciones Hipergeométricas","Investigar sobre las propiedades y aplicaciones de funciones hipergeométricas en análisis matemático y física teórica.");
		
		tareas.add(t1);
		tareas.add(t2);
		tareas.add(t3);
		tareas.add(t4);
		tareas.add(t5);
		tareas.add(t6);
		tareas.add(t7);
		tareas.add(t8);
		tareas.add(t9);
		tareas.add(t10);
		tareas.add(t11);
		tareas.add(t12);
		
    }
}