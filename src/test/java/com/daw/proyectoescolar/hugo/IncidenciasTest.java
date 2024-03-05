package com.daw.proyectoescolar.hugo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import com.daw.proyectoescolar.servicios.incidencias.*;

import java.util.ArrayList;

class IncidenciasTest {

	private ArrayList<Incidencias> listaIncidencias;
	private GestionDeIncidencias gestionDeIncidencias;
	
	@BeforeEach
	public void setUp() {
		
		gestionDeIncidencias = new GestionDeIncidencias();
		listaIncidencias = new ArrayList<Incidencias>();
		
		Incidencias inciAlumno1 = new IncidenciaAlumno("Samu me ha pegao");
		Incidencias inciAlumno2 = new IncidenciaAlumno("Samu me ha pegao otra ve");
		listaIncidencias.add(inciAlumno1);
		listaIncidencias.add(inciAlumno2);
		
		Incidencias inciProfesor1 = new IncidenciaProfesor("Guillamón me tiene manía");
		Incidencias inciProfesor2 = new IncidenciaProfesor("Guillamón me tiene manía, el retorno");
		listaIncidencias.add(inciProfesor1);
		listaIncidencias.add(inciProfesor2);
		
		Incidencias inciApp1 = new IncidenciaAplicacion("La aplicación me crasheó intentando iniciar sesión");
		Incidencias inciApp2 = new IncidenciaAplicacion("La aplicación me crasheó intentando borrar un usuario");
		listaIncidencias.add(inciApp1);
		listaIncidencias.add(inciApp2);
		
		
		
	}

	@Test
	void ComprobarTamañoArrayList() {
		
		// He añadido 6 incidencias al ArrayList desde el BeforeEach, por lo que tendría 5 posiciones.
		
		assertEquals(5, listaIncidencias.size());
		
	}
	
	@Test
	void ComprobarAñadirIncidencia() {
		
		Incidencias inciAlumnoPrueba = new IncidenciaAlumno("Esto es un test.");
		listaIncidencias.add(inciAlumnoPrueba);
		
		assertEquals(6, listaIncidencias.size());
		
	}
	
	@Test
	void testListarIncidenciaAlumno() {
		
		for(Incidencias incidencia : listaIncidencias) {
			if(incidencia.getTipoIncidencia().equalsIgnoreCase("Alumno")) {
			
			
				
			}
			
		} 
		
		
		
	}
	
	@Test
	void testComprobarIncidenciaAlumno() {
		
		
		
	}
	
	@Test
	void testComprobarIncidenciaProfesor() {
		
		Incidencias incidenciaProfesor = new IncidenciaProfesor();
		
		incidenciaProfesor.setIncidencia();
		
		assert(incidenciaProfesor.getIncidencia().equals(incidenciaProfesor));
		
		fail("ERROR");
		
	}
	
	@Test
	void testComprobarIncidenciaAplicacion() {
		
		Incidencias incidenciaAplicacion = new IncidenciaAplicacion();
		
		incidenciaAplicacion.setIncidencia();
		
		assert(incidenciaAplicacion.getIncidencia().equals(incidenciaAplicacion));
		
		fail("ERROR");
		
	}
	
}
