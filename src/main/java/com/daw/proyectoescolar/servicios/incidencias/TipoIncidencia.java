package com.daw.proyectoescolar.servicios.incidencias;

public class TipoIncidencia extends Incidencias{

	boolean incidenciaAlumno;
	boolean incidenciaProfesor;
	boolean incidenciaAplicacion;

	public TipoIncidencia() {
		
	}
	
	public TipoIncidencia(int idIncidencia, String descripcion, TipoIncidencia tipoIncidencia, boolean incidenciaAlumno, boolean incidenciaProfesor, boolean incidenciaAplicacion) {
		super(idIncidencia, descripcion, tipoIncidencia);
		this.incidenciaAlumno = incidenciaAlumno;
		this.incidenciaProfesor = incidenciaProfesor;
		this.incidenciaAplicacion = incidenciaAplicacion;
		
		
		
	}
	
	
}
