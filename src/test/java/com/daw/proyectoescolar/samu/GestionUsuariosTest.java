package com.daw.proyectoescolar.samu;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.daw.proyectoescolar.entidades.Alumno;
import com.daw.proyectoescolar.entidades.UsuarioBase;
import com.daw.proyectoescolar.servicios.gestionusuarios.GestionadorUsuarios;

class GestionUsuariosTest {
	
	
	@BeforeEach
	void setUp() throws Exception {
		
		ArrayList<UsuarioBase> usuarios = new ArrayList<UsuarioBase>();
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		
	}

	@Test
	void CrearUsuarioTest() {
		
		UsuarioBase usuario = new Alumno();
		usuario.setNombre("Samuel");
		usuario.setContraseña("1234");
		
		assertEquals("Samuel", usuario.getNombre());
		assertEquals("1234", usuario.getContraseña());
		
	}
	
	@Test
	void loginUsuarioTest() {
		
		GestionadorUsuarios gestion = new GestionadorUsuarios();
		assertNotNull(gestion.login("Samuel", "1234", new ArrayList<UsuarioBase>()));
		
	}

}
