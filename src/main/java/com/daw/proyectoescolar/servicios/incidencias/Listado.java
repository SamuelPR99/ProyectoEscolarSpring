package com.daw.proyectoescolar.servicios.incidencias;

import java.util.ArrayList;
import java.util.Scanner;

import com.daw.proyectoescolar.repositorio.Colores;

public class Listado {

   
    ArrayList<Incidencias> verIncidencias;
    ArrayList<Incidencias> listaIncidencias = new AñadirIncidencia().listaIncidencias;
    
    public Listado() {
    	
    }
    
    public void menuListado() {
    	
    	verIncidencias = new AñadirIncidencia().añadirIncidencia(listaIncidencias);
    	
    	Scanner sc = new Scanner(System.in);
		boolean volver = false;
		int opcion;
		
		do {
			
			System.out.println("Elige qué lista de incidencias quieres desplegar");
			System.out.println("\n1 - Incidencias de Alumno\n2 - Incidencias de Profesor\n3 - Incidencias de Aplicación\n4 - Volver");
			opcion = sc.nextInt();
			
			if (opcion == 1) {
				verIncidenciaAlumno();
			} else if (opcion == 2) {
				verIncidenciaProfesor();
			} else if (opcion == 3) {
				verIncidenciaAplicacion();
			} else if (opcion == 4){
				volver = true;
			} else {
				System.out.println(Colores.ANSI_RED + "Has introducido una opción inválida" + Colores.ANSI_RESET);
			}
			
		}while(!volver);
    	
    }

    public void verIncidenciaAlumno() {
        
        for (Incidencias incidencia : verIncidencias) {
           if(incidencia instanceof IncidenciaAlumno) {
            System.out.println(incidencia);
           }
        }
    }
    
    public void verIncidenciaProfesor() {
    	for (Incidencias incidencia : verIncidencias) {
    		if(incidencia instanceof IncidenciaProfesor) {
    			System.out.println(incidencia);
    		}
    	}
    }
    
    public void verIncidenciaAplicacion() {
    	for (Incidencias incidencia : verIncidencias) {
    		if(incidencia instanceof IncidenciaAplicacion) {
    			System.out.println(incidencia);
    		}
    	}
    }
    
}