package com.daw.proyectoescolar.servicios.incidencias;

import java.util.Scanner;

import com.daw.proyectoescolar.repositorio.Colores;

import java.util.ArrayList;

public class Menu {

	public Menu() {
		
		Scanner sc = new Scanner(System.in);
		
		String opcion;
		boolean salir = false;
		
		System.out.println("Bienvenido a la gestión de incidencias de nuestra aplicación!!!!!!!!!!!!!!!!!!!!!!!!!");
		
		do {
			
			System.out.println("1 - Añadir una incidencia\n2 - Listar incidencias\n3 - Eliminar incidencias\n4 - Salir del menú de incidencias");
			System.out.println("Introduce una opción:");
			opcion = sc.nextLine().toLowerCase();
			
			switch(opcion) {
			
			case "1", "añadir incidencia", "añadir una incidencia":
				new AñadirIncidencia();
			break;
			
			case "2", "listar incidencias", "listar":
				new Listado();
			break;
			
			case "3", "eliminar", "eliminar incidencias":
				
			break;
			
			case "4", "salir":
				System.out.println(Colores.ANSI_RED + "Saliendo de la aplicación..." + Colores.ANSI_RESET);
				salir = true;
				System.exit(0);
			break;
			}
			
			
		}while(salir);
		
	}

}
