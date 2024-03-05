package com.daw.proyectoescolar.hugo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import com.daw.proyectoescolar.servicios.incidencias.*;

class IncidenciasTest {

	static Scanner sc = new Scanner(System.in);
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

	@Test
	void testComprobarIncidenciaAlumno() {
		
		Incidencias incidenciaAlumno = new IncidenciaAlumno();
		
		incidenciaAlumno.setIncidencia(sc.nextLine());
		
		assert(incidenciaAlumno.getIncidencia().equals(incidenciaAlumno));
		
		fail("ERROR");
		
	}
	
	@Test
	void testComprobarIncidenciaProfesor() {
		
		Incidencias incidenciaProfesor = new IncidenciaProfesor();
		
		incidenciaProfesor.setIncidencia(sc.nextLine());
		
		assert(incidenciaProfesor.getIncidencia().equals(incidenciaProfesor));
		
		fail("ERROR");
		
	}
	
	@Test
	void testComprobarIncidenciaAplicacion() {
		
		Incidencias incidenciaAplicacion = new IncidenciaAplicacion();
		
		incidenciaAplicacion.setIncidencia(sc.nextLine());
		
		assert(incidenciaAplicacion.getIncidencia().equals(incidenciaAplicacion));
		
		fail("ERROR");
		
	}
	
}
