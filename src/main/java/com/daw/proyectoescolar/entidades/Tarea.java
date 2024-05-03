
package com.daw.proyectoescolar.entidades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import com.daw.proyectoescolar.repositorio.Constantes;
import com.daw.proyectoescolar.repositorio.GestionLogs;

public class Tarea {
    
    // Atributos
    protected String tipo;
    protected String nombre;
    protected String descripcion;
    

    // Constructores
    public Tarea() {

    }
    public Tarea(String tipo) {
        this.tipo = tipo;
    }

    public Tarea(String tipo, String nombre, String descripcion) {
        this.tipo = tipo;
    	this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
    // Metodos
    public void mostrarRecomendacion() {
        System.out.println("Recomendación: Tarea " + tipo + "\nDescripción: " + descripcion);
    }
    
    // Mostrar tareas
    public void mostrarTareas(Scanner sc) {
        ArrayList<Tarea> tareas = archivoTareas();
        
        System.out.println("Selecciona el numero de la tarea");
        int opcion = sc.nextInt();
        
        for(Tarea t : tareas) {
            if (opcion <= 12 && opcion >= 1) {
                System.out.println("Nombre de la tarea: " + t.getNombre());
                System.out.println("Descripcion: " + t.getDescripcion());

            } else {
                System.err.println("Error del eleccion");
            }
            
        }
    }
    	
    // Leer archivo de tareas
	public ArrayList<Tarea> archivoTareas() {

		ArrayList<Tarea> tareas = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(
				new FileReader(Constantes.RUTA_TAREAS))) {

			String linea;

			while ((linea = br.readLine()) != null) {

				String[] datos = linea.split(";"); // Separar los datos por punto y coma
				tareas.add(new Tarea(datos[0], datos[1], datos[2]));
				
			}

		} catch (Exception e) {
			System.err.println("Error al leer el archivo: " + e.getMessage());
			GestionLogs.errorLogs("Error al leer el archivo: " + e.getMessage());
		}
		
		return tareas;
		
	}

}
	

    
