package com.daw.proyectoescolar.servicios.incidencias;

public abstract class Incidencias {

	protected String incidencia;
	
	public Incidencias() {
		
	}
	
	public Incidencias(String incidencia) {
		this.incidencia = incidencia;
	}

	/**
	 * @return the incidencia
	 */
	public String getIncidencia() {
		return incidencia;
	}

	/**
	 * @param incidencia the incidencia to set
	 */
	public void setIncidencia(String incidencia) {
		this.incidencia = incidencia;
	}

	@Override
	public String toString() {
		return "Incidencias [incidencia=" + incidencia + "]";
	}
	
	
	
}
