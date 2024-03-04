package com.daw.proyectoescolar.hugo;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import com.daw.proyectoescolar.servicios.incidencias.IncidenciaAlumno;
import com.daw.proyectoescolar.servicios.incidencias.IncidenciaProfesor;
import com.daw.proyectoescolar.servicios.incidencias.IncidenciaAplicacion;
import com.daw.proyectoescolar.servicios.incidencias.GestionDeIncidencias;
import com.daw.proyectoescolar.servicios.incidencias.Incidencias;

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
		
		fail("Zamudio no sabe programar");
		
	}
	
	@Test
	void testComprobarIncidenciaProfesor() {
		
		Incidencias incidenciaProfesor = new IncidenciaProfesor();
		
		incidenciaProfesor.setIncidencia(sc.nextLine());
		
		assert(incidenciaProfesor.getIncidencia().equals(incidenciaProfesor));
		
		fail("Zamudio no sabe programar");
		
	}
	
	@Test
	void testComprobarIncidenciaAplicacion() {
		
		Incidencias incidenciaAplicacion = new IncidenciaAplicacion();
		
		incidenciaAplicacion.setIncidencia(sc.nextLine());
		
		assert(incidenciaAplicacion.getIncidencia().equals(incidenciaAplicacion));
		
		fail("Zamudio no sabe programar");
		
	}
	
}
