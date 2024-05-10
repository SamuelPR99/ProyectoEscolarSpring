package com.daw.proyectoescolar.servicios.temas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.daw.proyectoescolar.entidades.Temas;
import com.daw.proyectoescolar.repositorio.Colores;
import com.daw.proyectoescolar.repositorio.TemasRepo;

public class GestionTemas {

	// Constructores
	public GestionTemas() {

	}

	// Metodos

	// Crea un HashMap con los temas
	public HashMap<Integer, ArrayList<Temas>> hashTemas() {

		HashMap<Integer, ArrayList<Temas>> temas = new HashMap<>(); 
		TemasRepo repo = new TemasRepo();
		ArrayList<Temas> temasArray = repo.archivoTemas(); // Array de temas
		
		// Crea un HashMap con los temas
		int i = 1;
		for (Temas tema : temasArray) {
			ArrayList<Temas> tareas = new ArrayList<>();
			tareas.add(tema); // Cada tema tiene una lista de tareas
			temas.put(i, tareas); // Se añade al HashMap de temas 
			i++;
		}

		return temas;
	}

	// Muestra los temas
	public void mostrarTemas() {

		HashMap<Integer, ArrayList<Temas>> temas = hashTemas();

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
		
		HashMap<Integer, ArrayList<Temas>> temas = hashTemas();
		ArrayList<Temas> tema = temas.get(numTema);
		System.out.println(tema.get(0).getListaTareas() + "\n");
		
	}
	
}
