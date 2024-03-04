package com.daw.proyectoescolar.samu;

import org.junit.jupiter.api.Test;

import com.daw.proyectoescolar.entidades.Alumno;
import com.daw.proyectoescolar.entidades.UsuarioBase;

class GestionUsuariosTest {

	@Test
	void testCrearUsuario() {
		
		UsuarioBase usuario = new Alumno();
		usuario.setNombre("Pepe");
		usuario.setContraseña("Jaja@jaja");
		
		assert(usuario.getNombre().equals("Pepe"));
		assert(usuario.getContraseña().equals("Jaja@jaja"));
		
	}

}
