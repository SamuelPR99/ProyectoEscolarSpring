package com.daw.proyectoescolar.entidades;

import com.daw.proyectoescolar.repositorio.Colores;

public class IncidenciaAplicacion extends Incidencias {

	// Constructores
	public IncidenciaAplicacion() {
				
	}
	
	public IncidenciaAplicacion(String incidencia) { // Constructor con llamada al constructor de la superclase "Incidencias"
		super(incidencia);
	}
	
	public IncidenciaAplicacion(String incidencia, String fechaIncidencia) { 
		super(incidencia, fechaIncidencia);
	}
	
	public IncidenciaAplicacion(int incidenciaId, String incidencia, String fechaIncidencia) {																					// "Incidencias"
		super(incidenciaId, incidencia, fechaIncidencia);
	}

	public IncidenciaAplicacion(int incidenciaId, String incidencia, String fechaIncidencia, int usuarioId) {
		super(incidenciaId, incidencia, fechaIncidencia, usuarioId);
	}

	public IncidenciaAplicacion(String incidencia, String fechaIncidencia, int usuarioId) {
		super(incidencia, fechaIncidencia, usuarioId);
	}

	// Metodos
	@Override
	public String getTipoIncidencia() {
		return "Aplicacion";
	}
	
	@Override
	public String toString() { // Metodo "toString" que imprime cada incidencia que se va añadiendo al ArrayList.
		return Colores.ANSI_CYAN + "Incidencias de Aplicación: " + Colores.ANSI_YELLOW + incidencia + Colores.ANSI_RESET;
	}

}
