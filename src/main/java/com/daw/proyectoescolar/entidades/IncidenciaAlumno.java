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

	public IncidenciaAlumno(int incidenciaId, String incidencia, String fechaIncidencia, UsuarioBase usuario) {
		super(incidenciaId, incidencia, fechaIncidencia, usuario);
	}

	public IncidenciaAlumno(String incidencia, String fechaIncidencia, UsuarioBase usuario) {
		super(incidencia, fechaIncidencia, usuario);
	}

	public IncidenciaAlumno(String incidencia, UsuarioBase usuario) {
		super(incidencia, usuario);
	}
	
	// Metodos
	@Override
	public String getTipoIncidencia() {
		return "Alumno";
	}

	@Override
	public String toString() { // Metodo que devuelve un mensaje con el tipo de incidencia y la incidencia en si
		return Colores.ANSI_CYAN + "Incidencia de " + getTipoIncidencia() + ": " + getIncidencia() + " - " + fechaIncidencia + Colores.ANSI_RESET;
	}
	
}
