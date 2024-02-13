package com.daw.proyectoescolar.servicios.tareas;

public class Alumno {

	//ATRIBUTOS
	protected String usuario;
	protected String contraseña;
	
	//CONSTRUCTORES
	public Alumno() {
	
	}

	public Alumno(String usuario, String contraseña) {
		this.usuario=usuario;
		this.contraseña=contraseña;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	
}
