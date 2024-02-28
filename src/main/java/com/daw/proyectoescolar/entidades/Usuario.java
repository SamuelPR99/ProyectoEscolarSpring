package com.daw.proyectoescolar.entidades;

import java.util.ArrayList;
import java.util.Scanner;

public interface Usuario {
	
	/**
	 * 
	 * Interfaz que define los metodos que deben ser implementados por cualquier tipo de usuario.
	 * 
	 */
	
	public abstract String getNombre();
	public abstract String getContraseña();
	public abstract String getTipoUsuario();

	// Implementa verMenu en los hijos del UsuarioBase
	public abstract void verMenu(Scanner sc, ArrayList<UsuarioBase> usuarios, ArrayList<Alumno> alumnos);
	
}
