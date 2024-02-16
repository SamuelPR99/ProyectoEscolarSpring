package com.daw.proyectoescolar.entidades;

public abstract class UsuarioBase implements Usuario {
	
	/**
	 * 
	 * Clase abstracta que representa a un usuario generico.
	 * Define los atributos y metodos comunes para todos los tipos de usuarios.
	 * 
	 */

		// Atributos
		
	    protected String nombre;
	    protected String contraseña;
	    
	    // Constructores
	    
	    public UsuarioBase() {
		}

	    public UsuarioBase(String nombre, String contraseña) {
	        this.nombre = nombre;
	        this.contraseña = contraseña;
	    }
		
		// Getters y setters

	    @Override
	    public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		@Override
		public String getContraseña() {
			return contraseña;
		}

		public void setContraseña(String contraseña) {
			this.contraseña = contraseña;
		}
		
		// Metodos
		
		
		
}

