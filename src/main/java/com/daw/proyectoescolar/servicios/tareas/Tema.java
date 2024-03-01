package com.daw.proyectoescolar.servicios.tareas;

import java.util.ArrayList;
import java.util.Scanner;

public class Tema {
	
	// Atributos
 
	protected String nombre;
	protected String descripcion;
	protected Tarea tarea;
	protected String tareaTemas;
	protected ArrayList<Tema> temas;
	protected ArrayList<Tarea> tareas;
	
	// Constructores
	
	public Tema() {
		
	}
	public Tema(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		
	}
	
	public Tema(String nombre, String descripcion, Tarea tarea, String tareaTemas) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tarea = tarea;
		this.tareaTemas=tareaTemas;
	}
	
	
	// Getters y setters
	
	public String getTareaTemas() {
		return tareaTemas;
	}
	public void setTareaTemas(String tareaTemas) {
		this.tareaTemas = tareaTemas;
	}
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
	
	public ArrayList<Tarea> getTareas() {
        return tareas;
    }
	
    public void setTareas(ArrayList<Tarea> tareas) {
        this.tareas = tareas;
    }
	
	// Metodos
	
	public void mostrarTemas(Scanner sc) {
		
		// inicializar array de abajo
		ArrayList<Tema> temas = obtenerTemas();
		
		int i = 1;
		
		// Mostrar los temas
		for (Tema t : temas) {
			System.out.println(i + ". " + t.getNombre());
			i++;
		}
		
		// Seleccionar tema
		System.out.print("\nSelecciona el numero del tema: ");
		int opcion = sc.nextInt();
	    sc.nextLine(); // Si no pongo esto, el scanner no lee bien el siguiente string y se buguea 3 veces el menu

	    // Mostrar tareas del tema seleccionado
	    temas.get(opcion - 1).mostrarTarea();
	    
	    /*
	    Tema temaSeleccionado = temas.get(opcion - 1);
	    for (Tarea tarea : temaSeleccionado.getTareas()) {
	        tarea.mostrarTareas(sc);
	    }
	    */
		
	}
	
	// Muestra tarea y sus atributos	
    public void mostrarTarea() {
    	
        System.out.println("\nNombre del tema: " + this.getNombre()
        + "\nDescripción: " + this.getDescripcion() + "\n" 
		+ "\nTarea:\n" + this.getTarea().getNombre() 
		+ "\nDescripción de la tarea: " + this.getTarea().getDescripcion());
        
    }
	
	// Array de temas con sus tareas
	public static ArrayList<Tema> obtenerTemas() {
		
		ArrayList<Tema> temas = new ArrayList<Tema>();
        ArrayList<Tarea> tareas = Tarea.obtenerTodasLasTareas();
                
        Tema tema = new Tema();
        tema.setNombre("Teoría de la Probabilidad Estocástica");
        tema.setDescripcion("Estudio de la probabilidad en un espacio de probabilidad");
        tema.setTarea(tareas.get(0)); // Tarea 1 
        
        temas.add(tema);
        
        tema = new Tema();
        tema.setNombre("Teoría de Números Avanzada");
        tema.setDescripcion("Estudio de los números enteros y sus propiedades");
        tema.setTarea(tareas.get(1)); // Tarea 2
        temas.add(tema);
        
        tema = new Tema();
        tema.setNombre("Análisis Funcional");
        tema.setDescripcion("Estudio de espacios vectoriales normados y sus propiedades");
        tema.setTarea(tareas.get(2)); // Tarea 3
        temas.add(tema);
        
        tema = new Tema();
        tema.setNombre("Topología Algebraica");
        tema.setDescripcion("Estudio de la topología y sus propiedades");
        tema.setTarea(tareas.get(3)); // Tarea 4
        temas.add(tema);
        
        tema = new Tema();
        tema.setNombre("Teoría de Representación de Grupos");
        tema.setDescripcion("Estudio de la teoría de grupos y sus propiedades");
        tema.setTarea(tareas.get(4)); // Tarea 5
        temas.add(tema);
        
        tema = new Tema();
        tema.setNombre("Teoría de la Aproximación y Funciones Especiales");
        tema.setDescripcion("Estudio de la aproximación de funciones y sus propiedades");
        tema.setTarea(tareas.get(5)); // Tarea 6
        temas.add(tema);
        
        return temas;
    }
		
}