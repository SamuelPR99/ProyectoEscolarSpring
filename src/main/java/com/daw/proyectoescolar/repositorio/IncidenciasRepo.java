package com.daw.proyectoescolar.repositorio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.daw.proyectoescolar.entidades.IncidenciaAlumno;
import com.daw.proyectoescolar.entidades.IncidenciaAplicacion;
import com.daw.proyectoescolar.entidades.IncidenciaProfesor;
import com.daw.proyectoescolar.entidades.Incidencias;
import com.daw.proyectoescolar.logs.GestionLogs;

public class IncidenciasRepo {

	public ArrayList<Incidencias> leerIncidencias(ArrayList<Incidencias> listaIncidencias) {

		try (BufferedReader br = new BufferedReader(new FileReader(Constantes.RUTA_INCIDENCIAS))) {

			String linea;

			while ((linea = br.readLine()) != null) {

				String[] datos = linea.split(";"); // Separar los datos por punto y coma
				String tipoIncidencia = datos[0]; // Tipo de incidencia
				String descripcionIncidencia = datos[1]; // Detalles de la incidencia

				switch (tipoIncidencia) {

				case "Alumno":
					listaIncidencias.add(new IncidenciaAlumno(descripcionIncidencia));
					break;

				case "Profesor":
					listaIncidencias.add(new IncidenciaProfesor(descripcionIncidencia));
					break;

				case "Aplicacion":
					listaIncidencias.add(new IncidenciaAplicacion(descripcionIncidencia));
					break;

				default:
					System.err.println("Incidencia no encontrada");
					GestionLogs.errorLogs("Incidencia no encontrada en el archivo de incidencias." + tipoIncidencia
							+ " no es una incidencia valida.");
					break;
				}
			}

		} catch (IOException e) {
			System.err.println("Error al leer el archivo: " + e.getMessage());
			GestionLogs.errorLogs(
					"Error al leer el archivo: " + e.getMessage() + " No se han cargado los usuarios por defecto.");
		}

		return listaIncidencias;

	}

	public void escribirIncidencia(Incidencias incidencia) {

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			File file = new File(Constantes.RUTA_INCIDENCIAS);

			fw = new FileWriter(file, true);

			bw = new BufferedWriter(fw);
			bw.write(incidencia.getTipoIncidencia() + ";" + incidencia.getIncidencia() + "\n");
			bw.flush();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}