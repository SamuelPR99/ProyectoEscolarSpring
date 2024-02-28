package com.daw.proyectoescolar.servicios.incidencias;

import java.util.ArrayList;
import java.util.Scanner;

import com.daw.proyectoescolar.entidades.UsuarioBase;
import com.daw.proyectoescolar.repositorio.Colores;

public class GestionDeIncidencias {

	static Scanner sc = new Scanner(System.in);
	ArrayList<Incidencias> listaIncidencias = new ArrayList<Incidencias>();
	
	public GestionDeIncidencias() {
		
		// CONSTRUCTOR VACÍO \\
		
	}
	
	public void menuPrincipal() {
		
	// MENÚ PRINCIPAL DE LA GESTIÓN DE INCIDENCIAS \\
		
		String opcion;
		
		System.out.println("\nBienvenido a la gestión de incidencias de nuestra aplicación!!!!!!!!!!!!!!!!!!!!!!!!!\n");
		
		do {
			
			System.out.println("1 - Añadir una incidencia\n2 - Listar incidencias\n3 - Eliminar incidencias\n4 - Salir del menú de incidencias\n");
			System.out.println("Introduce una opción:");
			opcion = sc.nextLine();
			
			switch(opcion) {
			
			case "1", "añadir incidencia", "añadir una incidencia":
				añadirIncidencia();
			break;
			
			case "2", "listar incidencias", "listar":
				Listado();
			break;
			
			case "3", "eliminar", "eliminar incidencias":
				eliminarIncidencias();
			break;
			
			case "4", "salir":
				System.out.println(Colores.ANSI_RED + "Saliendo de la aplicación..." + Colores.ANSI_RESET);
			break;
			}
			
		} while (!opcion.equals("4") && !opcion.equals("salir"));
	
	}

	
/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	
	public ArrayList<Incidencias> añadirIncidencia() {
		
		/*Este método hace que el usuario pueda añadir todas las incidencias del tipo que desee hasta que introduzca
 		la opción de "Volver" que lo retornará hasta el menú principal*/
	
	
	
	String opcion;
	boolean volver = false;
	
	// Submenú para añadir las incidencias que el usuario desee \\
	
	do {
	
		System.out.println("¿Qué tipo de incidencia quieres añadir?");
		System.out.println("\n1 - Incidencia de alumno\n2 - Incidencia de profesor\n3 - Incidencia de aplicación\n4 - Volver");
		opcion = sc.nextLine().toLowerCase();
		
		switch (opcion) {
		
        case "1", "incidencia de alumno":
        	
            Incidencias incidenciaAlumno = new IncidenciaAlumno();
            System.out.println("Introduzca la incidencia de alumno: ");
            incidenciaAlumno.setIncidencia(sc.nextLine());
            listaIncidencias.add(incidenciaAlumno);
            
            System.out.println(Colores.ANSI_GREEN + "Incidencia de alumno añadida con éxito!" + Colores.ANSI_RESET);
            
            break;
            
        case "2" , "incidencia de profesor":
        	
            Incidencias incidenciaProfesor = new IncidenciaProfesor();
            System.out.println("Introduzca la incidencia de profesor: ");
            incidenciaProfesor.setIncidencia(sc.nextLine());
            listaIncidencias.add(incidenciaProfesor);
            
            System.out.println(Colores.ANSI_GREEN + "Incidencia de profesor añadida con éxito!" + Colores.ANSI_RESET);
            
            break;
            
        case "3", "incidencia de aplicación":
        	
            Incidencias incidenciaAplicacion = new IncidenciaAplicacion();
            System.out.println("Introduzca la incidencia aplicación: ");
            incidenciaAplicacion.setIncidencia(sc.nextLine());
            listaIncidencias.add(incidenciaAplicacion);
            
            System.out.println(Colores.ANSI_GREEN + "Incidencia de aplicación añadida con éxito!" + Colores.ANSI_RESET);
            
            break;
            
        case "4", "volver":
        	
        	volver = true;
        
            break;
            
        default:
        	
        	System.out.println(Colores.ANSI_RED + "Has introducido una opción inválida." + Colores.ANSI_RESET);

		}
		
		System.out.println("\n");
			
} while (!opcion.equals("4") && !opcion.equals("volver"));
		
		
		return listaIncidencias;
		
	}
	
	
/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	
	public void Listado() {
		
		
		/* Este método se encarga de clasificar las incidencias que se añaden al ArrayList por los tres tipos de incidencias que hay y luego imprimirlas las imprime*/
		
		
		boolean volver = false;
		String opcion;
		
		do {
			
			System.out.println("Elige qué lista de incidencias quieres desplegar\n");
			
			System.out.println("\n1 - Incidencias de Alumnos\n2 - Incidencias de Profesores\n3 - Incidencias de Aplicación\n4 - Incidencias sin filtrar por tipo"
					+ "\n5 - Volver");
			
			opcion = sc.nextLine().toLowerCase();
			
			if (opcion.equals("1") || opcion.equals("incidencias de alumnos") || opcion.equals("incidencias de alumno")) {
				
				verIncidenciaAlumno();
				
			} else if (opcion.equals("2") || opcion.equals("incidencias de profesores") || opcion.equals("incidencias de profesor")) {
				
				verIncidenciaProfesor();
				
			} else if (opcion.equals("3") || opcion.equals("incidencias de aplicacion") || opcion.equals("incidencias de aplicación")) {
				
				verIncidenciaAplicacion();
				
			} else if (opcion.equals("4") || opcion.equals("incidencias sin filtrar") || opcion.equals("incidencias sin filtrar por tipo")) {
				
				verIncidenciasGenerales();
				
			} else if (opcion.equals("5") || opcion.equals("volver")) {
				
				volver = true;
				
			} else {
				
				System.out.println(Colores.ANSI_RED + "Has introducido una opción inválida" + Colores.ANSI_RESET);
				
			}
			
		}while(!volver);
		
	}
	
	
/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	
   public void verIncidenciaAlumno() { // Método encargado de imprimir las incidencias de tipo Alumno. \\
        
        if(listaIncidencias.isEmpty()) {
        	System.out.println(Colores.ANSI_RED + "Lo siento. No hay incidencias de alumnos registradas." + Colores.ANSI_RESET);
    } else {
    	for(Incidencias incidencia : listaIncidencias) {
    		if(incidencia instanceof IncidenciaAlumno) {
	            System.out.println(incidencia);
    		}
    	}
    }
}
	    
    public void verIncidenciaProfesor() { // Método encargado de imprimir las incidencias de tipo Profesor. \\
    	
	 if(listaIncidencias.isEmpty()) {
        	System.out.println(Colores.ANSI_RED + "Lo siento. No hay incidencias de profesores registradas." + Colores.ANSI_RESET);
        } else {
        	for(Incidencias incidencia : listaIncidencias) {
        		if(incidencia instanceof IncidenciaProfesor) {
    	            System.out.println(incidencia);
        		}
        	}
        }
    }
	    
    public void verIncidenciaAplicacion() { // Método encargado de imprimir las incidencias de tipo Aplicación. \\
    	
	 if(listaIncidencias.isEmpty()) {
        	System.out.println(Colores.ANSI_RED + "Lo siento. No hay incidencias de aplicación registradas." + Colores.ANSI_RESET);
        } else {
        	for(Incidencias incidencia : listaIncidencias) {
        		if(incidencia instanceof IncidenciaAplicacion) {
    	            System.out.println(incidencia);
        		}
        	}
        }
    }
	    
	public void verIncidenciasGenerales() { // Método encargado de imprimir todas las incidencias, sin filtrarlas por su tipo. \\
	    	
	if(listaIncidencias.isEmpty()) {
		System.out.println(Colores.ANSI_RED + "Lo siento. No existe ningún tipo de incidencia registrado" + Colores.ANSI_RESET);
    	} else {
    	for(Incidencias incidencia : listaIncidencias) {
    		System.out.println(incidencia);
    	}
    }
  }
	
    public void eliminarIncidencias() { // Método encargado de eliminar las incidencias registradas. \\
    									//	Se eliminan por tipo. \\
		
	if(listaIncidencias.isEmpty()) {
		System.out.println(Colores.ANSI_RED + "Lo siento. No existe ningún tipo de incidencia registrado" + Colores.ANSI_RESET);
		
		} else {
	
		System.out.println("Introduce qué tipo de incidencia que quieres eliminar:\n ");
		System.out.println("1 - Alumno\n2 - Profesor\n3 - Aplicación");
		String eliminarIncidencia = sc.nextLine().toLowerCase();
			
	for (Incidencias incidencia : listaIncidencias) {
		
		if (eliminarIncidencia.equals("alumno") || eliminarIncidencia.equals("1")) {
			
			if(incidencia instanceof IncidenciaAlumno) {
	        	
	            listaIncidencias.remove(incidencia);
	            System.out.println(Colores.ANSI_GREEN + "\nIncidencia de alumno eliminada correctamente." + Colores.ANSI_RESET);
	                    return;
	        	} 
            
		}
                
        else if (eliminarIncidencia.equals("profesor") || eliminarIncidencia.equals("2")) {
        	
        	if(incidencia instanceof IncidenciaProfesor) {
            	
                listaIncidencias.remove(incidencia);
                System.out.println(Colores.ANSI_GREEN + "\nIncidencia de profesor eliminada correctamente." + Colores.ANSI_RESET);
                        return;
            	} 
            
		}
	
        else if (eliminarIncidencia.equals("aplicacion") || eliminarIncidencia.equals("aplicación")
        		|| eliminarIncidencia.equals("3")) {
        	
        	if(incidencia instanceof IncidenciaAplicacion) {
        	
            listaIncidencias.remove(incidencia);
            System.out.println(Colores.ANSI_GREEN + "\nIncidencia de aplicación eliminada correctamente." + Colores.ANSI_RESET);
                    return;
        	}
          }
		}
      }
	}
    
	    
}
