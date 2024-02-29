package com.daw.proyectoescolar.servicios.incidencias;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in); 
		
		new GestionDeIncidencias().menuPrincipal(sc); //Llamada al método "menuPrincipal" de la clase "GestionDeIncidencias"

		sc.close();
	}

}
