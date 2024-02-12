package com.daw.proyectoescolar.entidades;

public interface Usuario {
	
	/**
	 * 
	 * Interfaz que define los metodos que deben ser implementados por cualquier tipo de usuario.
	 * 
	 */
	
	public abstract String getNombre();
	public abstract String getContraseña();
	public abstract String getTipoUsuario();
    
}
