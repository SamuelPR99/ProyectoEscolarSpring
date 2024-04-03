package com.daw.proyectoescolar.servicios.tareas;
 
import java.util.ArrayList;
import java.util.Scanner;
 
import com.daw.proyectoescolar.entidades.Temas;
import com.daw.proyectoescolar.repositorio.Colores;
import com.daw.proyectoescolar.repositorio.ListaDeTemas;
import com.daw.proyectoescolar.repositorio.Tema1;
import com.daw.proyectoescolar.repositorio.Tema2;
import com.daw.proyectoescolar.repositorio.Tema3;
import com.daw.proyectoescolar.repositorio.Tema4;
import com.daw.proyectoescolar.repositorio.Tema5;
import com.daw.proyectoescolar.repositorio.Tema6;
 
public class GestionTemas {
	
	protected static ListaDeTemas gestion;
	
	public void menuTemas (Scanner sc) {
		
		gestion = new ListaDeTemas();
		gestion.temasIniciados();
		Temas tema = null;
		System.out.println(Colores.ANSI_PURPLE + Colores.ANSI_UNDERLINE + "\n¿Que tema quieres?:\n" + Colores.ANSI_RESET + Colores.ANSI_PURPLE
		+ "Tema 1\n" 
		+ "Tema 2\n" 
		+ "Tema 3\n" 
		+ "Tema 4\n" 
		+ "Tema 5\n" 
		+ "Tema 6\n" + Colores.ANSI_RESET);
		String eleccion = sc.nextLine().toLowerCase();
		switch(eleccion){
		case "1", "tema 1": 
			tema = new Tema1();
			recorroArray(tema);
				break;
		case "2", "tema 2": 
			tema = new Tema2();
			recorroArray(tema);
			break;
		case "3", "tema 3": 
			tema = new Tema3();
			recorroArray(tema);
			break;
		case "4", "tema 4": 
			tema = new Tema4();
			recorroArray(tema);
			break;
		case "5", "tema 5": 
			tema = new Tema5();
			recorroArray(tema);
			break;
		case "6", "tema 6": 
			tema = new Tema6();
			recorroArray(tema);
			break;
				}
	}
	
	private static void recorroArray(Temas tema) {
		
		ArrayList<Temas> ListaDeTemas = gestion.getListaDeTemas();
		for(int i =0;i<ListaDeTemas.size();i++) {
			Temas c = ListaDeTemas.get(i);
			if (c.getClass() == tema.getClass()) {  //mirar mal esto que es importante
				System.out.println("\nNombre: "+ c.getNombre() 
				+ "\nDescripcion: " + c.getDescripcion() 
				+ "\nContenido: "+ c.getContenidoTarea() + "\n");
			}
		}
 
	}
}