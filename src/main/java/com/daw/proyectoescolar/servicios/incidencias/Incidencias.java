package com.daw.proyectoescolar.servicios.incidencias;

public class Incidencias {

	int idIncidencia;
	String descripcion;
	TipoIncidencia tipoIncidencia;
	
	public Incidencias() {
		
		
	}
	
	public Incidencias(int idIncidencia, String descripcion, TipoIncidencia tipoIncidencia) {
		super();
		this.idIncidencia = idIncidencia;
		this.descripcion = descripcion;
		this.tipoIncidencia = tipoIncidencia;
	}

	/**
	 * @return the idIncidencia
	 */
	public int getIdIncidencia() {
		return idIncidencia;
	}

	/**
	 * @param idIncidencia the idIncidencia to set
	 */
	public void setIdIncidencia(int idIncidencia) {
		this.idIncidencia = idIncidencia;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the tipoIncidencia
	 */
	public TipoIncidencia getTipoIncidencia() {
		return tipoIncidencia;
	}

	/**
	 * @param tipoIncidencia the tipoIncidencia to set
	 */
	public void setTipoIncidencia(TipoIncidencia tipoIncidencia) {
		this.tipoIncidencia = tipoIncidencia;
	}
	
	
	
}
