package com.daw.proyectoescolar.servicios.incidencias;

import com.daw.proyectoescolar.repositorio.Colores;

public class IncidenciaAlumno extends Incidencias{

	public IncidenciaAlumno() {
		
	}

	public IncidenciaAlumno(String incidencia) {
		super(incidencia);
	}

	@Override
	public String toString() {
		return Colores.ANSI_CYAN + "Incidencias de Alumnos: " + Colores.ANSI_YELLOW + incidencia + Colores.ANSI_RESET;
	}
	
	
	
}
