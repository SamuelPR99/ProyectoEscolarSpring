package com.daw.proyectoescolar.entidades;

public abstract class Incidencias {
	
	// Atributos
	protected int incidenciaId;
	protected String incidencia;
	protected String fechaIncidencia;
	protected int usuarioId;
	
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

	public Incidencias(int incidenciaId, String incidencia, String fechaIncidencia, int usuarioId) {
		this.incidenciaId = incidenciaId;
		this.incidencia = incidencia;
		this.fechaIncidencia = fechaIncidencia;
		this.usuarioId = usuarioId;
	}

	public Incidencias(String incidencia, String fechaIncidencia, int usuarioId) {
		this.incidencia = incidencia;
		this.fechaIncidencia = fechaIncidencia;
		this.usuarioId = usuarioId;
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

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	// Metodos
	
	@Override
	public String toString() {
		return "Incidencias [incidenciaId=" + incidenciaId + ", incidencia=" + incidencia + ", fechaIncidencia="
				+ fechaIncidencia + usuarioId + "]";
	}
	
	public abstract String getTipoIncidencia();
}
