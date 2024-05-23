package com.daw.proyectoescolar.entidades;

import java.util.List;
import java.util.Scanner;

import com.daw.proyectoescolar.repositorio.Colores;

public class IncidenciaAlumno extends Incidencias {

	// Constructores
	public IncidenciaAlumno() {
		super();
	}
	
	public IncidenciaAlumno(String incidencia) {
		super(incidencia);
	}
	
	public IncidenciaAlumno(String incidencia, String fechaIncidencia) {
		super(incidencia, fechaIncidencia);
	}
	
	public IncidenciaAlumno(int incidenciaId, String incidencia, String fechaIncidencia) {
		super(incidenciaId, incidencia, fechaIncidencia);
	}

	public IncidenciaAlumno(int incidenciaId, String incidencia, String fechaIncidencia, int usuarioId) {
		super(incidenciaId, incidencia, fechaIncidencia, usuarioId);
	}

	public IncidenciaAlumno(String incidencia, String fechaIncidencia, int usuarioId) {
		super(incidencia, fechaIncidencia, usuarioId);
	}
	
	// Metodos
	@Override
	public String getTipoIncidencia() {
		return "Alumno";
	}

	@Override
	public String toString() { // Metodo que devuelve un mensaje con el tipo de incidencia y la incidencia en si
		return Colores.ANSI_CYAN + "Incidencias de Alumnos: " + Colores.ANSI_YELLOW + incidencia + Colores.ANSI_GREEN + 
				" Nombre del usuario que asignó la incidencia: " + usuarioId + Colores.ANSI_RESET;
	}
	
}
