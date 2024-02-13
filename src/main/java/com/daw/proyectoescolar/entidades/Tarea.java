package com.daw.proyectoescolar.entidades;

public class Tarea {
	
	// Atributos
	
    protected String tipo;

    // Constructores
    
    public Tarea(String tipo) {
        this.tipo = tipo;
    }

    // Getters y setters
    
    public String getTipo() {
        return tipo;
    }
    
    // Metodos

    public void mostrarRecomendacion() {
        System.out.println("Recomendación: Tarea " + tipo);
    }
}