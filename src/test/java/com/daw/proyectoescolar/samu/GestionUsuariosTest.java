package com.daw.proyectoescolar.samu;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import  org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.daw.proyectoescolar.entidades.Alumno;
import com.daw.proyectoescolar.entidades.Profesor;
import com.daw.proyectoescolar.entidades.UsuarioBase;
import com.daw.proyectoescolar.servicios.gestionusuarios.GestionadorUsuarios;
import com.daw.proyectoescolar.entidades.Tarea;
@SuppressWarnings("unused")


class GestionUsuariosTest {
	
	private GestionadorUsuarios gestion;
    private ArrayList<UsuarioBase> usuarios;
    private ArrayList<Alumno> alumnos;

    @BeforeEach
    public void setUp() {
        gestion = new GestionadorUsuarios();
        usuarios = GestionadorUsuarios.usuarios(new ArrayList<>());
        alumnos = gestion.obtenerAlumnos(usuarios);
    }
    
    @Test
	public void testCrearAlumno() {
		
        Alumno usuario = new Alumno("Pepe", "pass1", "12345678Z", 0.0);
        usuarios.add(usuario);
        
        assertEquals("Pepe", usuario.getNombre());
        assertEquals("pass1", usuario.getContraseña());
        assertEquals("12345678Z", usuario.getDni());
        assertEquals(0.0, usuario.getNota());
    	        
	}
    
    @Test
	public void testCrearProfesor() {
		
        Profesor usuario = new Profesor("Paquito", "pass1", "12345678Z");
        usuarios.add(usuario);
        
        assertEquals("Paquito", usuario.getNombre());
        assertEquals("pass1", usuario.getContraseña());
        assertEquals("12345678Z", usuario.getDni());
        
	}

    @Test
    public void testLoginUsuarioRegistrado() {
    	
        UsuarioBase usuario = gestion.login("Guillamon", "pass1", usuarios);
        
        Assertions.assertNotNull(usuario);
        Assertions.assertEquals("Profesor", usuario.getTipoUsuario());
        
    }

    @Test
    public void testLoginUsuarioNoRegistrado() {
    	
        UsuarioBase usuario = gestion.login("test-usuario", "test-contraseña", usuarios);
        Assertions.assertNull(usuario);
        
    }
    
    @Test
    public void testRegistro() {
    	
        String nombre = "test-usuario";
        String dni = "12345678Z";
        String contraseña = "Jaja@jaja";
        String tipo = "alumno";

        gestion.registro(nombre, dni, contraseña, tipo, usuarios);

        UsuarioBase usuarioRegistrado = gestion.login(nombre, contraseña, usuarios);

        assertNotNull(usuarioRegistrado);
        assertEquals("Alumno", usuarioRegistrado.getTipoUsuario());
    }
    
    @Test
    public void testBorrarUsuario() {
    	
        UsuarioBase usuarioExistente = gestion.login("Guillamon", "pass1", usuarios);
        assertNotNull(usuarioExistente);
        
        gestion.borrarUsuario("Guillamon", usuarios);
        UsuarioBase usuarioBorrado = gestion.login("Guillamon", "pass1", usuarios);
        assertNull(usuarioBorrado);
        
    }
    
    @Test
    public void testModificarContraseña() {
    	
        UsuarioBase usuario = new Profesor("Guillamon", "pass1", "12345678Z");
        usuarios.add(usuario);

        gestion.cambiarContraseña("NuevaContraseña", usuario);

        UsuarioBase usuarioModificado = gestion.login("Guillamon", "NuevaContraseña", usuarios);
        assertNotNull(usuarioModificado);
        
    }
        
    @Test
    public void testMostrarUsuarios() {
    	
        gestion.registro("Usuario1", "12345678A", "contraseña1", "alumno", usuarios);
        gestion.registro("Usuario2", "12345678B", "contraseña2", "alumno", usuarios);
        
        assertDoesNotThrow(() -> gestion.mostrarUsuarios(usuarios));
        
    }
    
    @Test
    public void testVerEstadisticas() {
    	
        gestion.registro("Alumno1", "12345678A", "contraseña1", "alumno", usuarios);
        gestion.registro("Alumno2", "12345678B", "contraseña2", "alumno", usuarios);
        
        assertDoesNotThrow(() -> gestion.verEstadisticas(usuarios));
    }
    
    @Test
    public void testConsultarTareasPendientes() {
    	
        Alumno alumno = new Alumno("Alumno1", "contraseña", "12345678A", 0.0);
        alumno.agregarTarea(new Tarea("Tarea Avanzada"));
        alumno.agregarTarea(new Tarea("Tarea Basica"));
        alumnos.add(alumno);
        
        assertDoesNotThrow(() -> gestion.consultarTareasPendientes(alumno));
    }
    
}
