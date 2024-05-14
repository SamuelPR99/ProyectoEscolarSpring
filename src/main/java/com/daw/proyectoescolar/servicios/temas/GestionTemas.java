package com.daw.proyectoescolar.servicios.temas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.daw.proyectoescolar.entidades.Temas;
import com.daw.proyectoescolar.repositorio.Colores;
import com.daw.proyectoescolar.repositorio.TemasRepo;

public class GestionTemas {
	
	TemasRepo tRepo = new TemasRepo();

	// Constructores
	public GestionTemas() {

	}

	// Metodos

	

	// Muestra los temas
	public void mostrarTemas() {

		HashMap<Integer, ArrayList<Temas>> temas = tRepo.hashTemas();

		for (Integer tema : temas.keySet()) { // Se recorren los temas
			System.out.println(tema + " - " + temas.get(tema).get(0).getNombre()); // Se muestra el nombre de cada tema
		}
		
	}

	// Muestra el menu de temas y tareas y permite seleccionar un tema y ver sus tareas asociadas
	public void menuTemas(Scanner sc) {
				
		System.out.println(Colores.ANSI_UNDERLINE + Colores.ANSI_YELLOW + "Seleccione un tema: " + Colores.ANSI_RESET);
		mostrarTemas();
		int numTema = sc.nextInt();
		sc.nextLine(); // Limpiar buffer
		
		HashMap<Integer, ArrayList<Temas>> temas = tRepo.hashTemas();
		ArrayList<Temas> tema = temas.get(numTema);
		System.out.println(tema.get(0).getListaTareas() + "\n");
		
	}
	
}
