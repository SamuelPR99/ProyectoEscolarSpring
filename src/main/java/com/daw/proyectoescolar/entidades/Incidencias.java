package com.daw.proyectoescolar.entidades;

public abstract class Incidencias {

	// Atributos
	protected int incidenciaId;
	protected String incidencia;
	protected String fechaIncidencia;
	
	// Constructores
	public Incidencias() {
		
	}
	
	public Incidencias(String incidencia) {
		this.incidencia = incidencia;
	}
	
	public Incidencias(String incidencia, String fechaIncidencia) {
		this.incidencia = incidencia;
		this.fechaIncidencia = fechaIncidencia;
	}
		
	public Incidencias(int incidenciaId, String incidencia, String fechaIncidencia) {
		this.incidenciaId = incidenciaId;
		this.incidencia = incidencia;
		this.fechaIncidencia = fechaIncidencia;
	}

	// Getters y Setters

	public int getIncidenciaId() {
		return incidenciaId;
	}

	public void setIncidenciaId(int incidenciaId) {
		this.incidenciaId = incidenciaId;
	}

	public String getIncidencia() {
		return incidencia;
	}

	public void setIncidencia(String incidencia) {
		this.incidencia = incidencia;
	}

	public String getFechaIncidencia() {
		return fechaIncidencia;
	}

	public void setFechaIncidencia(String fechaIncidencia) {
		this.fechaIncidencia = fechaIncidencia;
	}

	// Metodos
	
	@Override
	public String toString() {
		return "Incidencias [incidenciaId=" + incidenciaId + ", incidencia=" + incidencia + ", fechaIncidencia="
				+ fechaIncidencia + "]";
	}
	
	public abstract String getTipoIncidencia();
}
