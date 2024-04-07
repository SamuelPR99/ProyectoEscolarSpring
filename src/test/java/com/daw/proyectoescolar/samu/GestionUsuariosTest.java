package com.daw.proyectoescolar.samu;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.daw.proyectoescolar.entidades.Alumno;
import com.daw.proyectoescolar.entidades.Profesor;
import com.daw.proyectoescolar.entidades.Tarea;
import com.daw.proyectoescolar.entidades.UsuarioBase;
import com.daw.proyectoescolar.servicios.gestionusuarios.GestionadorUsuarios;

class GestionUsuariosTest {
	
	private GestionadorUsuarios gestion;
    private ArrayList<UsuarioBase> usuarios;
    private ArrayList<Alumno> alumnos;
    private ArrayList<Tarea> listaDeTareas;

    @BeforeEach
    public void setUp() {
        gestion = new GestionadorUsuarios();
        usuarios = gestion.usuarios();
        alumnos = gestion.obtenerAlumnos(usuarios);
        listaDeTareas = Tarea.obtenerTodasLasTareas();
    }
    
    @Test
	void testCrearAlumno() { //MOD_001
		
        Alumno usuario = new Alumno("Pepe", "pass1", "12345678A", 0.0);
        usuarios.add(usuario);
        
        assertEquals("Pepe", usuario.getNombre());
        assertEquals("pass1", usuario.getContraseña());
        assertEquals("12345678A", usuario.getDni());
        assertEquals(0.0, usuario.getNota());
    	        
	}
    
    @Test
	void testCrearProfesor() { //MOD_002
		
        Profesor usuario = new Profesor("Paquito", "pass1", "12345678A");
        usuarios.add(usuario);
        
        assertEquals("Paquito", usuario.getNombre());
        assertEquals("pass1", usuario.getContraseña());
        assertEquals("12345678A", usuario.getDni());
        
	}

    @Test
    void testLoginUsuarioRegistrado() { //MOD_003
    	
        UsuarioBase usuario = gestion.login("Guillamon", "pass1", usuarios);
        
        assertNotNull(usuario);
        assertEquals("Profesor", usuario.getTipoUsuario());
        
    }

    @Test
    void testLoginUsuarioNoRegistrado() { //MOD_004
    	
        UsuarioBase usuario = gestion.login("test-usuario", "test-contraseña", usuarios);
        
        assertNull(usuario);
        
    }
    
    @Test
    void testRegistro() { //MOD_005
    	
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
    void testBorrarUsuario() { //MOD_006
    	
        UsuarioBase usuarioExistente = gestion.login("Guillamon", "pass1", usuarios);
        
        assertNotNull(usuarioExistente);
        
        gestion.borrarUsuario("Guillamon", usuarios);
        UsuarioBase usuarioBorrado = gestion.login("Guillamon", "pass1", usuarios);
        
        assertNull(usuarioBorrado);
        
    }

    @Test
    void testModificarContraseña() { //MOD_007
    	
        UsuarioBase usuario = new Profesor("Guillamon", "pass1", "12345678Z");
        usuarios.add(usuario);

        gestion.cambiarContraseña("NuevaContraseña", usuario);

        UsuarioBase usuarioModificado = gestion.login("Guillamon", "NuevaContraseña", usuarios);
        
        assertNotNull(usuarioModificado);
        
    }
    
    @Test
    void testCambiarContraseñaUsuarioNoExistente() { //MOD_008
    	
        UsuarioBase usuario = new Profesor("UsuarioConContraseña", "contraseña", "12345678A");
        gestion.cambiarContraseña("NuevaContraseña", usuario);
        UsuarioBase usuarioModificado = gestion.login("UsuarioConContraseña", "NuevaContraseña", usuarios);
        
        assertNull(usuarioModificado);
        
    }
        
    @Test
    void testMostrarUsuarios() { //MOD_009
    	
        gestion.registro("Alumno1", "12345678A", "contraseña1", "alumno", usuarios);
        gestion.registro("Alumno2", "12345678B", "contraseña2", "alumno", usuarios);
        
        assertEquals(4, alumnos.size());
        assertDoesNotThrow(() -> gestion.mostrarUsuarios(usuarios));
        
    }
    
    @Test
    void testVerEstadisticas() { //MOD_010
    	
        gestion.registro("Alumno1", "12345678A", "contraseña1", "alumno", usuarios);
        gestion.registro("Alumno2", "12345678B", "contraseña2", "alumno", usuarios);
        
        assertEquals(4, alumnos.size());
        assertDoesNotThrow(() -> gestion.verEstadisticas(usuarios));
         
    }
    
    @Test
    void testConsultarTareasPendientes() { //MOD_011
    	
        Alumno alumno = new Alumno("Alumno1", "contraseña", "12345678A", 0.0);
        alumno.agregarTarea(new Tarea("Tarea Avanzada"));
        alumno.agregarTarea(new Tarea("Tarea Basica"));
        alumnos.add(alumno);
        
        assertEquals(2, alumno.getTareasAsignadas().size());
        assertDoesNotThrow(() -> gestion.consultarTareasPendientes(alumno));
        
    }
    
    @Test
    void testRecomendarTarea() { //MOD_012
    	
        Alumno alumnoNotaAlta = new Alumno("Carlos", "pass1", "12345678A", 9.5);
        Alumno alumnoNotaMedia = new Alumno("Enrique", "pass2", "12345678B", 7.5);
        Alumno alumnoNotaBaja = new Alumno("Ismael", "pass3", "12345678C", 5.0);

        Tarea tareaRecomendadaAvanzada = gestion.recomendarTarea(alumnoNotaAlta);
        Tarea tareaRecomendadaIntermedia = gestion.recomendarTarea(alumnoNotaMedia);
        Tarea tareaRecomendadaBasica = gestion.recomendarTarea(alumnoNotaBaja);
        
        assertEquals("Tarea 3: Demostración del Teorema Central del Límite", tareaRecomendadaAvanzada.getNombre());
        assertEquals("Tarea 2: Análisis de Convergencia de Series Infinitas", tareaRecomendadaIntermedia.getNombre());
        assertEquals("Tarea 1: Simulación de Monte Carlo ", tareaRecomendadaBasica.getNombre());
        
    }
    
    @Test
	void testAsignarTarea() { //MOD_013

		Alumno alumno = new Alumno("Alumno1", "contraseña", "12345678A", 0.0);
		Tarea tarea = new Tarea("Tarea Basica");
		
        alumno.agregarTarea(tarea);
        alumnos.add(alumno);
        listaDeTareas.add(tarea);
                
		assertEquals(1, alumno.getTareasAsignadas().size());

	}
    
    @Test
    void testMarcarTareaCompletada() { //MOD_014

		Alumno alumno = new Alumno("Alumno1", "contraseña", "12345678A", 0.0);
		Tarea tarea = new Tarea("Tarea Basica");

		alumno.agregarTarea(tarea);
		alumnos.add(alumno);
		listaDeTareas.add(tarea);

		assertEquals(1, alumno.getTareasAsignadas().size());

		gestion.marcarTareaCompletada(alumno, 0);

		assertEquals(0, alumno.getTareasAsignadas().size());
		
    }
    
    @Test
    void testMarcarTareaCompletadaEquivocada() { //MOD_015

        Alumno alumno = new Alumno("Alumno1", "contraseña", "12345678A", 0.0);
        Tarea tarea = new Tarea("Tarea Basica");
        
        alumno.agregarTarea(tarea);
        alumnos.add(alumno);
        listaDeTareas.add(tarea);
        
        assertEquals(1, alumno.getTareasAsignadas().size());
        
        gestion.marcarTareaCompletada(alumno, 1);
        
        assertEquals(1, alumno.getTareasAsignadas().size());
    	        	        
    }
    
}