package com.daw.proyectoescolar.entidades;

public abstract class Incidencias {
	
	// Atributos
	protected int incidenciaId;
	protected String incidencia;
	protected String fechaIncidencia;
	protected UsuarioBase usuario;
	
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

	public Incidencias(int incidenciaId, String incidencia, String fechaIncidencia, UsuarioBase usuario) {
		this.incidenciaId = incidenciaId;
		this.incidencia = incidencia;
		this.fechaIncidencia = fechaIncidencia;
		this.usuario = usuario;
	}

	public Incidencias(String incidencia, String fechaIncidencia, UsuarioBase usuario) {
		this.incidencia = incidencia;
		this.fechaIncidencia = fechaIncidencia;
		this.usuario = usuario;
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

	public UsuarioBase getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioBase usuario) {
		this.usuario = usuario;
	}

	// Metodos
	
	@Override
	public String toString() {
		return "Incidencias [incidenciaId=" + incidenciaId + ", incidencia=" + incidencia + ", fechaIncidencia="
				+ fechaIncidencia + usuario.getNombre() + "]";
	}
	
	public abstract String getTipoIncidencia();
}
