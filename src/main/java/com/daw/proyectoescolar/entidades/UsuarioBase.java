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
	    protected String dni;
	    
	    // Constructores
	    
	    public UsuarioBase() {
		}

	    public UsuarioBase(String nombre, String contraseña) {
	        this.nombre = nombre;
	        this.contraseña = contraseña;
	    }
	    
		public UsuarioBase(String nombreUsuario, String contrasena, String dni) {
			this.nombre = nombreUsuario;
			this.contraseña = contrasena;
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
		public String getContraseña() {
			return contraseña;
		}

		public void setContraseña(String contraseña) {
			this.contraseña = contraseña;
		}
		
		public String getDni() {
			return dni;
		}
		
		public void setDni(String dni) {
			this.dni = dni;
		}
		
		// Metodos
		
		 public boolean autenticar(String contraseña) {
			 
			 return this.contraseña.equals(contraseña);
			 
		    }
		
}

