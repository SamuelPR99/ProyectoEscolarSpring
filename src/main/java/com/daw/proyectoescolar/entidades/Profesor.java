package com.daw.proyectoescolar.entidades;

import java.util.Scanner;

import com.daw.proyectoescolar.repositorio.Colores;

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

   
	@Override
	public void verMenu(Scanner sc) {
		
		
	}
}