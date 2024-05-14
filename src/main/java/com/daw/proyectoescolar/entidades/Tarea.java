
package com.daw.proyectoescolar.entidades;

import java.sql.Date;

public class Tarea {

	// Atributos
	protected int tareaId;
	protected String tipo;
	protected String nombre;
	protected String descripcion;
	protected int temaId;
	protected Date fechaInicio; 
	protected Date fechaExpiracion; 
	protected Date fechaEntrega; 
	protected double puntuacion;

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
	
	public Tarea(String tipo, String nombre, String descripcion, int temaId) {
		this.tipo = tipo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.temaId = temaId;
	}
	
	public Tarea(int tareaId, String tipo, String nombre, String descripcion, int temaId) {
		this.tareaId = tareaId;
		this.tipo = tipo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.temaId = temaId;
	}
	
	public Tarea(int idTarea, String nombre, String descripcion, String tipo, Date fechaInicio, Date fechaExpiracion,
			Date fechaEntrega, double puntuacion) {
		this.tareaId = idTarea;
		this.tipo = tipo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaExpiracion = fechaExpiracion;
		this.fechaEntrega = fechaEntrega;
		this.puntuacion = puntuacion;
	}
	
	// Getters y setters
	public int getTareaId() {
		return tareaId;
	}

	public void setTareaId(int tareaId) {
		this.tareaId = tareaId;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

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

	public int getTemaId() {
		return temaId;
	}

	public void setTemaId(int temaId) {
		this.temaId = temaId;
	}

	// Metodos

	@Override
	public String toString() {
		return nombre + " - " + tipo + "\n" +  descripcion + "\n";
	}

	public void mostrarRecomendacion() {
		System.out.println("Recomendación: Tarea " + tipo + "\nDescripción: " + descripcion);
	}

}
