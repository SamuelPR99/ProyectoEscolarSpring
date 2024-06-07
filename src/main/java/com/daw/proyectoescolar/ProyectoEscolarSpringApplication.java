package com.daw.proyectoescolar;

import com.daw.proyectoescolar.servicios.usuarios.GestionUsuarios;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoEscolarSpringApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProyectoEscolarSpringApplication.class, args);
		new GestionUsuarios().inicializarBBDD();
	}
}
