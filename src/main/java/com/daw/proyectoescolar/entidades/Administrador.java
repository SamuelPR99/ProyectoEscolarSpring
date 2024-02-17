package com.daw.proyectoescolar.entidades;

import java.util.Scanner;

import com.daw.proyectoescolar.repositorio.Colores;

public class Administrador extends UsuarioBase {
	
	// Atributos
	
	// Constructores
	
	public Administrador() {
		
	}
	
	public Administrador(String nombre, String contraseña) {
        super(nombre, contraseña);
    }
	
	// Getters y setters
	
	//Metodos

	@Override
	public String getTipoUsuario() {
		
		return "Administrador";
	}

	// Menu administrador
	@Override
	public void verMenu(Scanner sc) {

	    String opcion;

	    do {
	    	
	    	System.out.println(Colores.ANSI_YELLOW + "\nSeleccione una opcion:\n"
	                + "1. lo que sea\n"
	                + "2. lo que sea2\n"
	                + "3. lo que sea3\n"
	                + "4. lo que sea4\n"
	                + "5. lo que sea5\n"
	                + "6. Salir del menu" + Colores.ANSI_RESET);
	    	
	        opcion = sc.nextLine().toLowerCase();

	        switch (opcion) {
	        
	            case "1", "lo que sea":
	            
	                break;

	            case "2", "lo que sea2":
	               
	                break;

	            case "3", "lo que sea3":
	               
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
       
       