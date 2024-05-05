package com.daw.proyectoescolar.repositorio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class logsRepo {
	
	// Constructores
	private logsRepo() {

	}

	/* 
	 * Metodo para escribir logs de informacion sobre las acciones de los usuarios 
	 * y fecha y hora de la accion realizada
	 */
	public static void escribirLog(String tipoLog, String mensajeLog) {

		String fechaActual = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		String rutaDirectorioLog = Constantes.CARPETA_LOGS + fechaActual + "/";
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

}
