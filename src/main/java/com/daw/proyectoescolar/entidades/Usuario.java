package com.daw.proyectoescolar.entidades;

import java.util.List;
import java.util.Scanner;

public interface Usuario {
	
	/**
	 * Interfaz que define los metodos que deben ser implementados por cualquier tipo de usuario.
	 */
	
	public abstract String getNombre();
	public abstract String getContrasena();
	public abstract String getTipoUsuario();

	// Implementa verMenu en los hijos del UsuarioBase
	public abstract void verMenu(Scanner sc, List<UsuarioBase> usuarios, List<Alumno> alumnos, UsuarioBase usuario);

	// Implementa incidenciasMenu en los hijos del UsuarioBase
	public abstract void incidenciasMenu(Scanner sc, UsuarioBase usuario);

}
