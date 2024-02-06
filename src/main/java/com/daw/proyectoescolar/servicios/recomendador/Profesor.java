package com.daw.proyectoescolar.servicios.recomendador;

public class Profesor extends Usuario {
	
	// Atributos

    // Constructores

    public Profesor(String nombre, String contraseña) {
        super(nombre, contraseña);
    }

    // Getters y setters

    @Override
    public String getTipoUsuario() {
        return "profesor";
    }
}