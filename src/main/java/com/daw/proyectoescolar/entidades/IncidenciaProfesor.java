package com.daw.proyectoescolar.entidades;

import com.daw.proyectoescolar.repositorio.Colores;

public class IncidenciaProfesor extends Incidencias {

	// Constructores
	public IncidenciaProfesor() {
			
	}
	
	public IncidenciaProfesor(String incidencia) { // Constructor con llamada al constructor de la superclase "Incidencias"
		super(incidencia);
	}
	
	public IncidenciaProfesor(String incidencia, String fechaIncidencia) {
		super(incidencia, fechaIncidencia);
	}
	
	public IncidenciaProfesor(int incidenciaId, String incidencia, String fechaIncidencia) { 
		super(incidenciaId, incidencia, fechaIncidencia);
	}

	public IncidenciaProfesor(int incidenciaId, String incidencia, String fechaIncidencia, int usuarioId) {
		super(incidenciaId, incidencia, fechaIncidencia, usuarioId);
	}

	public IncidenciaProfesor(String incidencia, String fechaIncidencia, int usuarioId) {
		super(incidencia, fechaIncidencia, usuarioId);
	}
	
	// Metodos
	@Override
	public String getTipoIncidencia() {
		return "Profesor";
	}

	@Override
	public String toString() { // Metodo que devuelve un mensaje con el tipo de incidencia y la incidencia en si
		return Colores.ANSI_CYAN + "Incidencias de Profesores: " + Colores.ANSI_YELLOW + incidencia + Colores.ANSI_GREEN + 
				" Nombre del usuario: " + usuarioId + Colores.ANSI_RESET;
	}
}