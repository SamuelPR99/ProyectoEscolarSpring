package com.daw.proyectoescolar.servicios.incidencias;

import com.daw.proyectoescolar.repositorio.Colores;

public class IncidenciaProfesor extends Incidencias{

	public IncidenciaProfesor() {
		
	}
	
	public IncidenciaProfesor(String incidencia) {
		super(incidencia);
	}

	@Override
	public String toString() {
		return Colores.ANSI_CYAN + "Incidencias de Profesores: " + Colores.ANSI_YELLOW + incidencia + Colores.ANSI_RESET;
	}

	
	
}
