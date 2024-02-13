package com.daw.proyectoescolar.entidades;

public class Profesor extends UsuarioBase {
	
	// Atributos

    // Constructores

	public Profesor(String nombre, String contraseña) {
        super(nombre, contraseña);
    }

    // Getters y setters
	
	// Metodos

    @Override
    public String getTipoUsuario() {
        return "profesor";
    }
}