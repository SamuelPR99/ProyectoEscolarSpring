package com.daw.proyectoescolar.entidades;

import java.util.ArrayList;

import java.util.Scanner;

import com.daw.proyectoescolar.repositorio.Colores;
import com.daw.proyectoescolar.servicios.recomendador.SistemaRecomendacion;
import com.daw.proyectoescolar.servicios.tareas.Tema;


public class Profesor extends UsuarioBase {
	
	// Atributos

    // Constructores

	public Profesor() {
		
	}
	public Profesor(String nombre, String contraseña) {
        super(nombre, contraseña);
    }
	

    // Getters y setters
	
	// Metodos
		
    @Override
    public String getTipoUsuario() {
        return "Profesor";
    }

    // Menu profesor
	@Override
	public void verMenu(Scanner sc) {
	    SistemaRecomendacion sistema = new SistemaRecomendacion();
	    Tema tema= new Tema();
	    Profesor a = new Profesor();
	    String opcion;

	    do {
	    	
	        System.out.println(Colores.ANSI_YELLOW + "\nSeleccione una opcion:\n"
	                + "1. Ver lista de temas\n"
	        		+ "2. Ver lista de alumnos\n"
	                + "3. Modificar nota de alumno\n"
	                + "4. Ver estadísticas\n"
	                + "5. Agregar nueva tarea\n"
	                + "6. Modificar tarea\n"
	                + "7. Salir del menu" + Colores.ANSI_RESET);

	        opcion = sc.nextLine().toLowerCase();

	        switch (opcion) {
	        
	        	case "1", "ver listado de temas":
	        		tema.mostrarTemas();
	        		break;
	            case "2", "ver lista de alumnos":
                    a.mostrarListaAlumnos();
	            	sistema.verNotasAlumnos();
	                break;

	            case "3", "modificar nota de alumno":
	                sistema.modificarNotaAlumno(sc);
	                break;

	            case "4", "ver estadísticas":
	                sistema.verEstadisticas();
	                break;

	            case "5", "agregar nueva tarea":
	                sistema.agregarNuevaTarea(sc);
	                break;

	            case "6", "modificar tarea":
	                sistema.modificarTarea(sc);
	                break;

	            case "7", "salir del menu":
	                System.out.println(Colores.ANSI_BOLD + "Saliendo del menu de profesor..." + Colores.ANSI_RESET);
	                break;

	            default:
	                System.err.println("Opcion no valida. Por favor, elige una opción valida.");
	                
	        }
	        
	    } while (!opcion.equals("7") && !opcion.equals("salir del menu"));
	    
	}
	
	private void mostrarListaAlumnos(ArrayList<Alumno> alumnos) {
		// TODO Auto-generated method stub
		System.out.println("Lista de alumnos:");
		for (Alumno alumno : alumnos) {
          System.out.println(alumno.getNombre());
      }
		
	}
	private Tema Tema(ArrayList<Tema> tema) {
		// TODO Auto-generated method stub
		System.out.println("Lista de temas:");
	    for (Tema tema : tema) {
	        System.out.println(tema.getTemas());
	    }
	    
	}
	
}