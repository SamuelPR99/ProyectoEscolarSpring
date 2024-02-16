package com.daw.proyectoescolar.servicios.incidencias;

public class TipoIncidencia extends Incidencias{

	boolean incidenciaAlumno;
	boolean incidenciaProfesor;
	boolean incidenciaAplicacion;

	public TipoIncidencia() {
		
	}
	
	public TipoIncidencia(int idIncidencia, String descripcion, TipoIncidencia tipoIncidencia) {
		super(idIncidencia, descripcion, tipoIncidencia);
	}
	
}
