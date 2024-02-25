package com.daw.proyectoescolar.servicios.incidencias;

import java.util.ArrayList;
import java.util.Scanner;

import com.daw.proyectoescolar.repositorio.Colores;

public class AñadirIncidencia {

	ArrayList<Incidencias> listaIncidencias;
	
	public AñadirIncidencia() {
		
		Scanner sc = new Scanner(System.in);
		
		listaIncidencias = new ArrayList<Incidencias>();
		String opcion;
		boolean salir = false;
		
		do {
		
			System.out.println("¿Qué tipo de incidencia quieres añadir?");
			System.out.println("\n1 - Incidencia de alumno\n2 - Incidencia de profesor\n3 - Incidencia de aplicación\n4 - Salir");
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
                
            case "4", "salir":
            	
                System.out.println("Saliendo de la aplicación...");
                System.exit(0);
                break;
                
            default:
                System.err.println("Has introducido una opción inválida.");
                salir = true;
                break;
			}
			
			System.out.println("\n1 - Incidencia de alumno\n2 - Incidencia de profesor\n3 - Incidencia de aplicación\n4 - Salir");
			opcion = sc.nextLine().toLowerCase();
			

    } while (salir);
		
		
		
		
	}

}
