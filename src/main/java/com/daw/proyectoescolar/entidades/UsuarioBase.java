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
	    protected String contrasena;
	    protected String dni;
	    
	    // Constructores
	    
	    protected UsuarioBase() {
		}

	    protected UsuarioBase(String nombre, String contrasena) {
	        this.nombre = nombre;
	        this.contrasena = contrasena;
	    }
	    
		protected UsuarioBase(String nombreUsuario, String contrasena, String dni) {
			this.nombre = nombreUsuario;
			this.contrasena = contrasena;
			this.dni = dni;
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
		public String getContrasena() {
			return contrasena;
		}

		public void setContrasena(String contrasena) {
			this.contrasena = contrasena;
		}
		
		public String getDni() {
			return dni;
		}
		
		public void setDni(String dni) {
			this.dni = dni;
		}

		// Metodos
}

