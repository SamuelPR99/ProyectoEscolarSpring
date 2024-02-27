package com.daw.proyectoescolar.servicios.incidencias;

import com.daw.proyectoescolar.repositorio.Colores;

public class IncidenciaAplicacion extends Incidencias{

	public IncidenciaAplicacion() {
		
	}
	
	public IncidenciaAplicacion(String incidencia) {
		super(incidencia);
	}

	@Override
	public String toString() {
		return Colores.ANSI_CYAN + "Incidencias de Aplicación: " + Colores.ANSI_YELLOW + incidencia + Colores.ANSI_RESET;
	}

	
	
}
