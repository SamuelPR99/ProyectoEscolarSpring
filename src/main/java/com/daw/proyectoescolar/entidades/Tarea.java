
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
	protected boolean estado;
	protected double nota;

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

	public Tarea(int idTarea, String nombre, String descripcion, String tipo, Date fechaInicio, Date fechaExpiracion,
				  Date fechaEntrega, double puntuacion, boolean estado) {
		this.tareaId = idTarea;
		this.tipo = tipo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaExpiracion = fechaExpiracion;
		this.fechaEntrega = fechaEntrega;
		this.puntuacion = puntuacion;
		this.estado = estado;
	}

	// Constructor para obternerTareasBBDD() de la base de datos: Tarea tarea = new Tarea(tareaId, titulo, descripcion, dificultad);
	public Tarea(int tareaId, String nombre, String descripcion, String tipo) {
		this.tareaId = tareaId;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
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

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaExpiracion() {
		return fechaExpiracion;
	}

	public void setFechaExpiracion(Date fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public double getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
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
