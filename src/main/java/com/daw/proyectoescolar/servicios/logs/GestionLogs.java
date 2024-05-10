package com.daw.proyectoescolar.servicios.logs;

import com.daw.proyectoescolar.repositorio.FechaYHora;
import com.daw.proyectoescolar.repositorio.logsRepo;

public class GestionLogs {
		
	// Constructores
	private GestionLogs() {
		
	}

	// Metodos para escribir logs
	
	// Metodo para escribir logs de informacion sobre errores
	public static void errorLogs(String mensajeError) {	
		String mensajeLog = "[" + FechaYHora.fechaActual() + "] ERROR: " + mensajeError;
		logsRepo.escribirLog("error", mensajeLog);
	}

	// Metodo para escribir logs de informacion sobre las opciones de los menus
	public static void logOpcionMenu(String nombreMenu, String opcion) {
		String mensajeLog = "[" + FechaYHora.fechaActual() + "] MENU '" + nombreMenu + "' OPCION: " + opcion;
		logsRepo.escribirLog("menu", mensajeLog);
	}

	
}

