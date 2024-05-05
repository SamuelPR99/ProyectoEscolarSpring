
package com.daw.proyectoescolar.entidades;

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

	@Override
	public String toString() {
		return nombre + " - " + tipo + "\n" +  descripcion + "\n";
	}

	public void mostrarRecomendacion() {
		System.out.println("Recomendación: Tarea " + tipo + "\nDescripción: " + descripcion);
	}

}
