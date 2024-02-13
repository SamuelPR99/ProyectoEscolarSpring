package com.daw.proyectoescolar.servicios.tareas;

import java.util.ArrayList;

public class Tema {

	protected String nombre;
	protected ArrayList<Tarea>tareas;
	
	public Tema() {
		
	}

	public Tema(String nombre) {
		this.nombre=nombre;
		tareas= new ArrayList<>();
	}

	public String getNombre() {
		return nombre;
	}

	public ArrayList<Tarea> getTareas() {
		return tareas;
	}

	public void agregarTarea(Tarea tarea) {
		tareas.add(tarea);
	}
	
	
	
}
