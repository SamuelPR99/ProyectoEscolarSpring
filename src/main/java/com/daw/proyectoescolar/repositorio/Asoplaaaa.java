package com.daw.proyectoescolar.repositorio;

import java.util.ArrayList;
import java.util.Scanner;

import com.daw.proyectoescolar.entidades.Temas;



public class Asoplaaaa {
	private static Scanner sc;
	private static ListaDeTemas gestion;
	
	public static void main(String[] args) {
		gestion = new ListaDeTemas();
		gestion.temasIniciados();
		
		sc = new Scanner(System.in);
		System.out.println("Que tipo de tema quieres: ");
		String eleccion = sc.nextLine();
		Temas tema = null;
		
		switch(eleccion){
		
		case "tema uno": 
			tema = new Tema1();
			recorroArray(tema);
				break;
					
		case "tema dos": 
			tema = new Tema2();
			recorroArray(tema);
			break;
			
		case "tema tres": 
			tema = new Tema3();
			recorroArray(tema);
			break;
			
		case "tema cuatro": 
			tema = new Tema4();
			recorroArray(tema);
			break;
			
		case "tema cinco": 
			tema = new Tema5();
			recorroArray(tema);
			break;
			
		case "tema seis": 
			tema = new Tema6();
			recorroArray(tema);
			break;
			
				}
	}
		
		
		public static void recorroArray(Temas tema) {
			
			ArrayList<Temas> ListaDeTemas = gestion.getListaDeTemas();
			
			for(int i =0;i<ListaDeTemas.size();i++) {
				Temas c = ListaDeTemas.get(i);
				
				if (c.getClass() == tema.getClass()) {  //mirar bien esto que es importante
					System.out.println("\nNombre: "+c.getNombre()+"\nDescripcion: "+c.getDescripcion()+"\nContenido: "+c.getContenidoTarea()+"\n");
				}
				
			}

	}

}
