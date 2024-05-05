package com.daw.proyectoescolar.entidades;

import java.util.ArrayList;

public class Temas {

	// Atributos
	protected String nombre;
	protected String descripcion;
	protected ArrayList<Tarea> listaTareas;

	// Constructores

	public Temas() {
	}

	public Temas(String nombre, String descripcion, ArrayList<Tarea> listaTareas) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.listaTareas = listaTareas;
	}

	// Getters y Setters
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

	public ArrayList<Tarea> getListaTareas() {
		return listaTareas;
	}

	public void setListaTareas(ArrayList<Tarea> listaTareas) {
		this.listaTareas = listaTareas;
	}

	// Metodos
	@Override
	public String toString() {
		return "Temas [nombre=" + nombre + ", descripcion=" + descripcion + ", listaTareas=" + listaTareas + "]";
	}

}