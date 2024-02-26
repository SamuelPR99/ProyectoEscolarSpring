package com.daw.proyectoescolar.entidades;

import java.util.ArrayList;
import java.util.Scanner;

public class Tema {
	
	// Atributos
 
	protected String nombre;
	protected String descripcion;
	protected Tarea tarea;
	protected ArrayList<Tema> temas;
	
	// Constructores
	
	public Tema() {
		
	}

	public Tema(String nombre, String descripcion) {
		this.nombre=nombre;
		this.descripcion=descripcion;
	}

	public Tema(String nombre, String descripcion, Tarea tarea) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tarea = tarea;
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
	public Tarea getTarea() {
		return tarea;
	}
	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}
	
	// Metodos
	public void mostrarTemas(Scanner sc) {
		
		ArrayList<Tema> temas = obtenerTemas();
		
		int i = 1;
		
		for (Tema t : temas) {
			System.out.println(i + ". " + t.getNombre());
			i++;
		}
		
		System.out.print("Selecciona el numero del tema: ");
		int opcion = sc.nextInt();
	    sc.nextLine(); // // Si no pongo esto, el scanner no lee bien el siguiente string

		temas.get(opcion - 1).mostrarTarea();
		
	}
		
		
    public void mostrarTarea() {
    	
        System.out.println("Nombre del tema: " + this.getNombre() 
    + "\nDescripción: " + this.getDescripcion() 
    + "\nTarea: " + this.getTarea().getNombre() 
    + "\nDescripción de la tarea: " + this.getTarea().getDescripcion());
        
    }
		
	
	public static ArrayList<Tema> obtenerTemas() {
		
		ArrayList<Tema> temas = new ArrayList<Tema>();
        ArrayList<Tarea> tareas = Tarea.obtenerTodasLasTareas();
        
        Tema tema = new Tema();
        tema.setNombre("Teoría de la Probabilidad Estocástica");
        tema.setDescripcion("Estudio de la probabilidad en un espacio de probabilidad");
        tema.setTarea(tareas.get(0));
        temas.add(tema);
        
        tema = new Tema();
        tema.setNombre("Teoría de Números Avanzada");
        tema.setDescripcion("Estudio de los números enteros y sus propiedades");
        tema.setTarea(tareas.get(1));
        temas.add(tema);
        
        tema = new Tema();
        tema.setNombre("Análisis Funcional");
        tema.setDescripcion("Estudio de espacios vectoriales normados y sus propiedades");
        tema.setTarea(tareas.get(2));
        temas.add(tema);
        
        tema = new Tema();
        tema.setNombre("Topología Algebraica");
        tema.setDescripcion("Estudio de la topología y sus propiedades");
        tema.setTarea(tareas.get(3));
        temas.add(tema);
        
        tema = new Tema();
        tema.setNombre("Teoría de Representación de Grupos");
        tema.setDescripcion("Estudio de la teoría de grupos y sus propiedades");
        tema.setTarea(tareas.get(4));
        temas.add(tema);
        
        tema = new Tema();
        tema.setNombre("Teoría de la Aproximación y Funciones Especiales");
        tema.setDescripcion("Estudio de la aproximación de funciones y sus propiedades");
        tema.setTarea(tareas.get(5));
        temas.add(tema);
        
        return temas;
    }
			
}

	

