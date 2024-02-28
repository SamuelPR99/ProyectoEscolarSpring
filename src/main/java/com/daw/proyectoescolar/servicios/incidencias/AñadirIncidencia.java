package com.daw.proyectoescolar.servicios.incidencias;

import java.util.ArrayList;
import java.util.Scanner;

import com.daw.proyectoescolar.repositorio.Colores;

public class AñadirIncidencia {
	

	
	//Métodos
	public ArrayList<Incidencias> obtenerIncidencias() {
		
		ArrayList<Incidencias> listaIncidencias = new ArrayList<Incidencias>();
		
		/*Este método hace que el usuario pueda añadir todas las incidencias del tipo que desee hasta que introduzca
 		la opción de "Volver" que lo retornará hasta el menú principal*/
	
	Scanner sc = new Scanner(System.in);
	
	String opcion;
	boolean volver = false;
	
	//Submenú para añadir las incidencias que el usuario desee
	
	do {
	
		System.out.println("¿Qué tipo de incidencia quieres añadir?");
		System.out.println("\n1 - Incidencia de alumno\n2 - Incidencia de profesor\n3 - Incidencia de aplicación\n4 - Volver");
		opcion = sc.nextLine().toLowerCase();
		
		switch (opcion) {
		
        case "1", "incidencia de alumno":
        	
            Incidencias incidenciaAlumno = new IncidenciaAlumno();
            System.out.println("Introduzca la incidencia: ");
            incidenciaAlumno.setIncidencia(sc.nextLine());
            listaIncidencias.add(incidenciaAlumno);
            
            break;
            
        case "2" , "incidencia de profesor":
        	
            Incidencias incidenciaProfesor = new IncidenciaProfesor();
            System.out.println("Introduzca la incidencia: ");
            incidenciaProfesor.setIncidencia(sc.nextLine());
            listaIncidencias.add(incidenciaProfesor);
            
            break;
            
        case "3", "incidencia de aplicación":
        	
            Incidencias incidenciaAplicacion = new IncidenciaAplicacion();
            System.out.println("Introduzca la incidencia: ");
            incidenciaAplicacion.setIncidencia(sc.nextLine());
            listaIncidencias.add(incidenciaAplicacion);
            
            break;
            
        case "4", "volver":
        	
        	volver = true;
        
            break;
            
        default:
        	
        	System.out.println(Colores.ANSI_RED + "Has introducido una opción inválida." + Colores.ANSI_RESET);

		}
			
} while (!opcion.equals("4") && !opcion.equals("volver"));
		
		return listaIncidencias;
		
	}
	
	public void verIncidencias(ArrayList<Incidencias> listaIncidencias) {

		

		if (listaIncidencias.size() > 0) {

			System.out.println("Estas son las incidencias que has añadido: ");

			for (Incidencias incidencia : listaIncidencias) {

				System.out.println(incidencia.getIncidencia() + ": " + incidencia.getIncidencia());

			}

		} else {

			System.out.println("No has añadido ninguna incidencia.");

		}
		
	}
	
}
