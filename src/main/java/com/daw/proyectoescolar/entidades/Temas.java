package com.daw.proyectoescolar.entidades;

public abstract class Temas {

	// Atributos
	protected String nombre;
	protected String descripcion;
	protected String contenidoTarea;
	protected String Temas;
	
	// Constructores
	public Temas() {
		
	}

	public Temas(String nombre, String descripcion, String contenidoTarea) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.contenidoTarea = contenidoTarea;
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

	public String getContenidoTarea() {
		return contenidoTarea;
	}

	public void setContenidoTarea(String contenidoTarea) {
		this.contenidoTarea = contenidoTarea;
	}
			
}
