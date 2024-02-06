package com.daw.proyectoescolar.servicios.recomendador;

public class Usuario {
	
	// Atributos
	
    protected String nombre;
    protected String contraseña;
    
    // Constructores

    public Usuario(String nombre, String contraseña) {
        this.nombre = nombre;
        this.contraseña = contraseña;
    }
    
    // Getters y setters

    public String getTipoUsuario() {
        return "usuario";
    }

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }
}