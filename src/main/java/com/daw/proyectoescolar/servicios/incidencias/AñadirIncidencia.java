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
		boolean volver = false;
		
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
		
		
		
	}
	
	public ArrayList<Incidencias> obtenerLista(ArrayList<Incidencias> listaIncidencias){
		
		ArrayList<Incidencias> devolverLista = listaIncidencias;
		return listaIncidencias;
	}
	
}
