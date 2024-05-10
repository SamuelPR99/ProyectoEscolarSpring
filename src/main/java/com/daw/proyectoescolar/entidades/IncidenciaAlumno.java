package com.daw.proyectoescolar.entidades;

import com.daw.proyectoescolar.repositorio.Colores;

public class IncidenciaAlumno extends Incidencias {

	// Constructores
	public IncidenciaAlumno() {
		super();
	}
	
	public IncidenciaAlumno(String incidencia) {
		super(incidencia);
	}
	
	public IncidenciaAlumno(int incidenciaId, String incidencia, String fechaIncidencia) {
		super(incidenciaId, incidencia, fechaIncidencia);
	}
	
	// Metodos
	@Override
	public String getTipoIncidencia() {
		return "Alumno";
	}

	@Override
	public String toString() { // Metodo que devuelve un mensaje con el tipo de incidencia y la incidencia en si
		return Colores.ANSI_CYAN + "Incidencias de Alumnos: " + Colores.ANSI_YELLOW + incidencia + Colores.ANSI_RESET;
	}
	
}
