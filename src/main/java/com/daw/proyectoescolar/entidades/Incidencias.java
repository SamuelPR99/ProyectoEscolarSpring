package com.daw.proyectoescolar.entidades;

public abstract class Incidencias {

	// Atributos
	protected String incidencia;
	
	// Constructores
	public Incidencias() {
		
	}
	
	// Getters y Setters
	public Incidencias(String incidencia) {
		this.incidencia = incidencia;
	}

	public String getIncidencia() {
		return incidencia;
	}

	public void setIncidencia(String incidencia) {
		this.incidencia = incidencia;
	}

	// Metodos
	
	@Override
	public String toString() {
		return "Incidencias [incidencia=" + incidencia + "]";
	}
	
	public abstract String getTipoIncidencia();
	
}
