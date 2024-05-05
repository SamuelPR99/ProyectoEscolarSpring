package com.daw.proyectoescolar.servicios.temas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.daw.proyectoescolar.entidades.Tarea;
import com.daw.proyectoescolar.entidades.Temas;
import com.daw.proyectoescolar.repositorio.Colores;
import com.daw.proyectoescolar.repositorio.TemasRepo;

public class GestionTemas {

	// Constructores
	public GestionTemas() {

	}

	// Metodos

	public HashMap<Integer, ArrayList<Temas>> hashTemas() {

		HashMap<Integer, ArrayList<Temas>> temas = new HashMap<>();
		TemasRepo repo = new TemasRepo();
		ArrayList<Temas> temasArray = repo.archivoTemas();
		
		int i = 1;
		for (Temas tema : temasArray) {
			ArrayList<Temas> tareas = new ArrayList<>();
			tareas.add(tema);
			temas.put(i, tareas);
			i++;
		}

		return temas;
	}

	public void mostrarTemas() {

		HashMap<Integer, ArrayList<Temas>> temas = hashTemas();

		for (Integer tema : temas.keySet()) {
			System.out.println(tema + " - " + temas.get(tema).get(0).getNombre());
		}
		
	}

	public void menuTemas(Scanner sc) {
				
		System.out.println(Colores.ANSI_UNDERLINE + Colores.ANSI_YELLOW + "Seleccione un tema: " + Colores.ANSI_RESET);
		mostrarTemas();
		int numTema = sc.nextInt();
		sc.nextLine(); // Limpiar buffer
		
		HashMap<Integer, ArrayList<Temas>> temas = hashTemas();
		ArrayList<Temas> tema = temas.get(numTema);
		System.out.println(tema.get(0).getListaTareas() + "\n");
		
	}
	
	public HashMap<Integer, ArrayList<Tarea>> hashTareas() {

		HashMap<Integer, ArrayList<Tarea>> tareas = new HashMap<>();
		ArrayList<Tarea> tareasArray = new TemasRepo().archivoTareas();

		int i = 1;
		for (Tarea tarea : tareasArray) {
			ArrayList<Tarea> tareasList = new ArrayList<>();
			tareasList.add(tarea);
			tareas.put(i, tareasList);
			i++;
		}

		return tareas;
	}
}
