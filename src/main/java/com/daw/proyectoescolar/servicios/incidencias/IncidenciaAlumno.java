package com.daw.proyectoescolar.servicios.incidencias;

import com.daw.proyectoescolar.repositorio.Colores;

public class IncidenciaAlumno extends Incidencias{

	public IncidenciaAlumno() {
		
		// CONSTRUCTOR VACÍO \\
		
	}

	public IncidenciaAlumno(String incidencia) { // Constructor con llamada al constructor de la superclase "Incidencias" \\
		super(incidencia);
	}
	
	@Override
	public String getTipoIncidencia() {
		return "Alumno";
	}

	@Override
	public String toString() { // Método "toString" que imprime cada incidencia que se va añadiendo al ArrayList. \\
		
		return Colores.ANSI_CYAN + "Incidencias de Alumnos: " + Colores.ANSI_YELLOW + incidencia + Colores.ANSI_RESET;
		
	}
	
}
