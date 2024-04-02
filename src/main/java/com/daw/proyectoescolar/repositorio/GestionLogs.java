package com.daw.proyectoescolar.repositorio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GestionLogs {

	// Constante de la ruta de la carpeta donde se guardan los logs
	private static final String CARPETA_LOGS = "logs/";

	// Metodos para escribir logs
	
	// Metodo para escribir logs de informacion sobre errores
	public static void errorLogs(String mensajeError) {	
		String mensajeLog = "[" + getFechaActual() + "] ERROR: " + mensajeError;
		EscribirLog("error", mensajeLog);
	}

	// Metodo para escribir logs de informacion sobre las opciones de los menus
	public static void logOpcionMenu(String nombreMenu, String opcion) {
		String logMessage = "[" + getFechaActual() + "] MENU '" + nombreMenu + "' OPCION: " + opcion;
		EscribirLog("menu", logMessage);
	}

	// Metodo para escribir logs de informacion sobre las acciones de los usuarios y fecha y hora de la accion realizada
	private static void EscribirLog(String tipoLog, String mensajeLog) {

		String fechaActual = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		String rutaDirectorioLog = CARPETA_LOGS + fechaActual + "/";
		String rutaArchivoLog = rutaDirectorioLog + "log_" + tipoLog + ".log";

		File directorioLog = new File(rutaDirectorioLog);
		if (!directorioLog.exists()) {
			directorioLog.mkdirs();
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivoLog, true))) {
			writer.write(mensajeLog);
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Metodo para obtener la fecha y hora actual
	private static String getFechaActual() {
		return LocalDateTime.now().toString();
	}

}

