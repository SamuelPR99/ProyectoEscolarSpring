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

	public IncidenciaAplicacion(int incidenciaId, String incidencia, String fechaIncidencia, UsuarioBase usuario) {
		super(incidenciaId, incidencia, fechaIncidencia, usuario);
	}

	public IncidenciaAplicacion(String incidencia, String fechaIncidencia, UsuarioBase usuario) {
		super(incidencia, fechaIncidencia, usuario);
	}

	public IncidenciaAplicacion(String incidencia, UsuarioBase usuario) {
		super(incidencia, usuario);
	}

	// Metodos
	@Override
	public String getTipoIncidencia() {
		return "Aplicacion";
	}
	
	@Override
	public String toString() { // Metodo que devuelve un mensaje con el tipo de incidencia y la incidencia en si
		return Colores.ANSI_CYAN + "Incidencias de Aplicación: " + Colores.ANSI_YELLOW + incidencia + Colores.ANSI_GREEN + 
				" Nombre del usuario: " + usuario.getNombre() + Colores.ANSI_RESET;
	}

}
