package com.daw.proyectoescolar.repositorio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GestionLogs {

	private static final String CARPETA_LOGS = "logs/";

	public static void errorLogs(String mensajeError) {	
		String mensajeLog = "[" + getFechaActual() + "] ERROR: " + mensajeError;
		EscribirLog("error", mensajeLog);
	}

	public static void logOpcionMenu(String nombreMenu, String opcion) {
		String logMessage = "[" + getFechaActual() + "] MENU '" + nombreMenu + "' OPCION: " + opcion;
		EscribirLog("menu", logMessage);
	}

	private static void EscribirLog(String tipoLog, String mensajeLog) {

		String fechaActual = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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

	private static String getFechaActual() {
		return LocalDateTime.now().toString();
	}

}

