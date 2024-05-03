package com.daw.proyectoescolar.entidades;

import com.daw.proyectoescolar.repositorio.Colores;

public class IncidenciaAplicacion extends Incidencias{

	public IncidenciaAplicacion() {
		
		// CONSTRUCTOR VACÍO \\
		
	}
	
	public IncidenciaAplicacion(String incidencia) { // Constructor con llamada al constructor de la superclase "Incidencias" \\
		super(incidencia);
	}

	@Override
	public String getTipoIncidencia() {
		return "Aplicacion";
	}
	
	@Override
	public String toString() { // Método "toString" que imprime cada incidencia que se va añadiendo al ArrayList. \\
		
		return Colores.ANSI_CYAN + "Incidencias de Aplicación: " + Colores.ANSI_YELLOW + incidencia + Colores.ANSI_RESET;
		
	}

}
