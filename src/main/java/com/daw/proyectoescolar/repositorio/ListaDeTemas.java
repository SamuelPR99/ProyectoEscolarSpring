package com.daw.proyectoescolar.repositorio;

import com.daw.proyectoescolar.entidades.Temas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ListaDeTemas {
	
	protected ArrayList<Temas> ListaDeTemas;
	
	
	public ListaDeTemas() {
		super();
	}

	public ListaDeTemas(ArrayList<Temas> listaDeTemas) {
		
		super();
		ListaDeTemas = listaDeTemas;
	}

	public ArrayList<Temas> getListaDeTemas() {
		return ListaDeTemas;
	}

	public void setListaDeTemas(ArrayList<Temas> listaDeTemas) {
		ListaDeTemas = listaDeTemas;
	}
	public void archivoTemas() {
		
		ListaDeTemas = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(
				new FileReader("src/main/java/com/daw/proyectoescolar/repositorio/temas.csv"))) {

			String linea;
			while ((linea = br.readLine()) != null) {

				String[] datos = linea.split(";"); // Separar los datos por punto y coma
				String nombreTema = datos[0]; // nombre del tema
				String descripcionTema = datos[1]; // descripcion del tema 
				String contenidoTema = datos[2]; // Dificultad y tarea
				
				switch(nombreTema) {

				case "Teoría de la Probabilidad Estocástica":
					ListaDeTemas.add(new Tema1(nombreTema, descripcionTema, contenidoTema));
					break;
					
				case "Teoría de Números Avanzada":
					ListaDeTemas.add(new Tema2(nombreTema, descripcionTema, contenidoTema));
					break;
					
				case "Análisis Funcional": 
					ListaDeTemas.add(new Tema3(nombreTema, descripcionTema, contenidoTema));
				
					break;
					
				case "Topología Algebraica":
					ListaDeTemas.add(new Tema4(nombreTema, descripcionTema, contenidoTema));
					break;
					
				case "Teoría de Representación de Grupos": 
					ListaDeTemas.add(new Tema5(nombreTema, descripcionTema, contenidoTema));
					break;
					
				case "Teoría de la Aproximación y Funciones Especiales": 
					ListaDeTemas.add(new Tema6(nombreTema, descripcionTema, contenidoTema));
					break;
					
				default:
					System.err.println("Tema no encontrado");
					GestionLogs.errorLogs("Tema no encontrado");
					break;
					
				}
			}
		} catch (IOException e) {
			System.err.println("Error al leer el archivo: " + e.getMessage());
			GestionLogs.errorLogs(
					"Error al leer el archivo: " + e.getMessage() + " No se han cargado los temas por defecto.");
		} 
		
	}
}