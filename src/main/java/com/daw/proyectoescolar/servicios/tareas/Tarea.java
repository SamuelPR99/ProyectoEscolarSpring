package com.daw.proyectoescolar.servicios.tareas;

public class Tarea {

	//ATRIBUTOS
	protected String nombre;
	protected String descripcion;
	
	//CONSTRUCTORES
	public Tarea(String nombre, String descripcion) {
		this.nombre=nombre;
		this.descripcion=descripcion;
		
		
	}
	//METODOS

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
}
