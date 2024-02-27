package com.daw.proyectoescolar.entidades;

import java.util.Scanner;

import com.daw.proyectoescolar.repositorio.Colores;
import com.daw.proyectoescolar.servicios.registro.GestorUsuarios;

public class Administrador extends UsuarioBase {
	
	// Atributos
	
	// Constructores
	
	public Administrador() {
		
	}
	
	public Administrador(String nombre, String contraseña) {
        super(nombre, contraseña);
    }
	
	public Administrador(String nombre, String contraseña, String dni) {
		super(nombre, contraseña, dni);
	}
	
	// Getters y setters
	
	//Metodos
	
	// Zamudio
	
	@Override
	public void mostrarInformacion() {
	     // Implementación para mostrar la información de un administrador
	     System.out.println("Nombre de usuario: " + nombre);
	 }

	@Override
	public String getTipoUsuario() {
		
		return "Administrador";
	}

	// Menu administrador
	@Override
	public void verMenu(Scanner sc) {

		// Inicializar objeto que realize los metodos de administrador, etc.
		GestorUsuarios gestor = new GestorUsuarios();
		
	    String opcion;

	    do {
	    	
	    	System.out.println(Colores.ANSI_YELLOW + "\nSeleccione una opcion:\n"
	                + "1. Mostrar usuarios registrados\n"
	                + "2. Crear un usuario de forma manual\n"
	                + "3. Borrar un usuario\n"
	                + "4. lo que sea4\n"
	                + "5. lo que sea5\n"
	                + "6. Salir del menu" + Colores.ANSI_RESET);
	    	
	        opcion = sc.nextLine().toLowerCase();

	        switch (opcion) {
	        
	            case "1", "Mostrar usuarios registrados":
	            	
	            	gestor.mostrarUsuariosRegistrados();
	            
	                break;

	            case "2", "Crear un usuario de forma manual":
	            	
	                gestor.crearUsuario(sc);
	            
	                break;

	            case "3", "Borrar un usuario":
	               
	            	gestor.borrarUsuario(sc);
	            	
	                break;

	            case "4", "lo que sea4":
	            	
	            	
	               
	                break;

	            case "5", "lo que sea5":
	               
	                break;

	            case "6", "salir del menu":
	                System.out.println(Colores.ANSI_BOLD + "Saliendo del menu de administrador..." + Colores.ANSI_RESET);
	                break;

	            default:
	                System.err.println("Opcion no valida. Por favor, elige una opción valida.");
	                
	        }
	        
	    } while (!opcion.equals("6") && !opcion.equals("salir del menu"));
		
	}  
       
}      
       
       