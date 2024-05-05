package com.daw.proyectoescolar.entidades;

import java.util.ArrayList;
import java.util.Scanner;

import com.daw.proyectoescolar.logs.GestionLogs;
import com.daw.proyectoescolar.repositorio.Colores;
import com.daw.proyectoescolar.repositorio.Constantes;
import com.daw.proyectoescolar.servicios.temas.GestionTemas;
import com.daw.proyectoescolar.servicios.usuarios.GestionUsuarios;

public class Profesor extends UsuarioBase {
	
	// Atributos

    // Constructores

	public Profesor() {
		
	}
	
	public Profesor(String nombre, String contrasena) {
        super(nombre, contrasena);
    }
	
	public Profesor(String nombre, String contrasena, String dni) {
		super(nombre, contrasena, dni);
	}
	
    // Getters y setters
	
	// Metodos
		
    @Override
    public String getTipoUsuario() {
        return Constantes.PROFESOR;
    }
    
    // Menu profesor
	@Override
	public void verMenu(Scanner sc, ArrayList<UsuarioBase> usuarios, ArrayList<Alumno> alumnos) {
		
		GestionUsuarios gestor = new GestionUsuarios();
	    GestionTemas temitas = new GestionTemas();
	    
	    String opcion;

	    do {
	    	
	        System.out.println(Colores.ANSI_YELLOW + Colores.ANSI_UNDERLINE +"\nSeleccione una opcion:\n" + Colores.ANSI_RESET + Colores.ANSI_YELLOW
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
	        		GestionLogs.logOpcionMenu(Constantes.MENU_PROFESORES, "Ver listado de temas");
	        	    temitas.menuTemas(sc);
	        		break;
	        		
	            case "2", "ver lista de alumnos":
	            	GestionLogs.logOpcionMenu(Constantes.MENU_PROFESORES, "Ver lista de alumnos");
	            	gestor.verNotasAlumnos(usuarios);
	                break;

	            case "3", "modificar nota de alumno":
	            	GestionLogs.logOpcionMenu(Constantes.MENU_PROFESORES, "Modificar nota de alumno");
	                gestor.modificarNotaAlumno(sc, usuarios);
	                break;

	            case "4", "ver estadísticas":
	            	GestionLogs.logOpcionMenu(Constantes.MENU_PROFESORES, "Ver estadisticas");
	                gestor.verEstadisticas(usuarios);
	                break;

	            case "5", "agregar nueva tarea":
	            	GestionLogs.logOpcionMenu(Constantes.MENU_PROFESORES, "Agregar nueva tarea");
	                gestor.agregarNuevaTarea(sc);
	                break;

	            case "6", "modificar tarea":
	            	GestionLogs.logOpcionMenu(Constantes.MENU_PROFESORES, "Modificar tarea");
	                gestor.modificarTarea(sc);
	                break;

	            case "7", "cambiar contraseña":
	            	GestionLogs.logOpcionMenu(Constantes.MENU_PROFESORES, "Cambiar contraseña");
	            	gestor.cambiarContrasena(sc, this);
                	break;
                   
	            case "8", "salir del menu":
	            	GestionLogs.logOpcionMenu(Constantes.MENU_PROFESORES, "Salir del menu");
	                System.out.println(Colores.ANSI_BOLD + "Saliendo del menu de profesor..." + Colores.ANSI_RESET);
	                break;

	            default:
	                System.err.println("Opcion no valida. Por favor, elige una opcion valida.");
	                GestionLogs.errorLogs("Opcion no valida en el menu de profesor. " + opcion + " no es una opcion valida.");
	                
	        }
	        
	    } while (!opcion.equals("8") && !opcion.contains("salir"));
	    
	}
	
}