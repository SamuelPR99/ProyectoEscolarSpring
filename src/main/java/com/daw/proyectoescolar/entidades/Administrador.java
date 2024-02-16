package com.daw.proyectoescolar.entidades;

import java.util.Scanner;

public class Administrador extends UsuarioBase {
	
	// Atributos
	
	// Constructores
	
	public Administrador() {
		
	}
	
	public Administrador(String nombre, String contraseña) {
        super(nombre, contraseña);
    }
	
	// Getters y setters
	
	//Metodos

	@Override
	public String getTipoUsuario() {
		
		return "Administrador";
	}

	@Override
	public void verMenu(Scanner sc) {

		
	}

}
