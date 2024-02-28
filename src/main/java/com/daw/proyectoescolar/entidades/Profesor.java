package com.daw.proyectoescolar.entidades;

import java.util.ArrayList;

import java.util.Scanner;

import com.daw.proyectoescolar.repositorio.Colores;
import com.daw.proyectoescolar.servicios.recomendador.SistemaRecomendacion;
import com.daw.proyectoescolar.servicios.registro.GestorUsuarios;



public class Profesor extends UsuarioBase {
	
	// Atributos

    // Constructores

	public Profesor() {
		
	}
	
	public Profesor(String nombre, String contraseña) {
        super(nombre, contraseña);
    }
	
	public Profesor(String nombre, String contraseña, String dni) {
		super(nombre, contraseña, dni);
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
		
	    GestorUsuarios gestor = new GestorUsuarios();
        ArrayList<UsuarioBase> usuarios = gestor.usuarios;
	    SistemaRecomendacion sistema = new SistemaRecomendacion(usuarios);
	    Tema tema = new Tema();
	    
	    String opcion;

	    do {
	    	
	        System.out.println(Colores.ANSI_YELLOW + "\nSeleccione una opcion:\n"
	                + "1. Ver lista de temas\n"
	        		+ "2. Ver lista de alumnos\n"
	                + "3. Modificar nota de alumno\n"
	                + "4. Ver estadisticas\n"
	                + "5. Agregar nueva tarea\n"
	                + "6. Modificar tarea\n"
	                + "7. Cambiar contraseña\n"
	                + "8. Salir del menu" + Colores.ANSI_RESET);

	        opcion = sc.nextLine().toLowerCase();

	        switch (opcion) {
	        
	        	case "1", "ver listado de temas":
	        		tema.mostrarTemas(sc);
	        		break;
	            case "2", "ver lista de alumnos":
                    //a.mostrarListaAlumnos();
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

	            case "7", "cambiar contraseña":
	            	gestor.cambiarContraseña(sc, this);
                	break;
	                
	            case "8", "salir del menu":
	                System.out.println(Colores.ANSI_BOLD + "Saliendo del menu de profesor..." + Colores.ANSI_RESET);
	                break;

	            default:
	                System.err.println("Opcion no valida. Por favor, elige una opción valida.");
	                
	        }
	        
	    } while (!opcion.equals("8") && !opcion.equals("salir del menu"));
	    
	}
	
	public void mostrarListaAlumnos(ArrayList<Alumno> alumnos) {
		
		System.out.println("Lista de alumnos:");
		for (Alumno alumno : alumnos) {
          System.out.println(alumno.getNombre());
      }
		
	}

}