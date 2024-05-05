package com.daw.proyectoescolar.servicios.logs;

import java.time.LocalDateTime;

import com.daw.proyectoescolar.repositorio.logsRepo;

public class GestionLogs {
		
	// Constructores
	private GestionLogs() {
		
	}

	// Metodos para escribir logs
	
	// Metodo para escribir logs de informacion sobre errores
	public static void errorLogs(String mensajeError) {	
		String mensajeLog = "[" + getFechaActual() + "] ERROR: " + mensajeError;
		logsRepo.escribirLog("error", mensajeLog);
	}

	// Metodo para escribir logs de informacion sobre las opciones de los menus
	public static void logOpcionMenu(String nombreMenu, String opcion) {
		String mensajeLog = "[" + getFechaActual() + "] MENU '" + nombreMenu + "' OPCION: " + opcion;
		logsRepo.escribirLog("menu", mensajeLog);
	}

	// Metodo para obtener la fecha y hora actual
	private static String getFechaActual() {
		return LocalDateTime.now().toString();
	}

}

