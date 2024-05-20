package com.daw.proyectoescolar.repositorio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.daw.proyectoescolar.entidades.IncidenciaAlumno;
import com.daw.proyectoescolar.entidades.IncidenciaAplicacion;
import com.daw.proyectoescolar.entidades.IncidenciaProfesor;
import com.daw.proyectoescolar.entidades.Incidencias;
import com.daw.proyectoescolar.servicios.logs.GestionLogs;

public class IncidenciasRepo {

	public ArrayList<Incidencias> leerIncidencias(ArrayList<Incidencias> listaIncidencias) {

		try (BufferedReader br = new BufferedReader(new FileReader(Constantes.RUTA_INCIDENCIAS))) {

			String linea;

			while ((linea = br.readLine()) != null) {

				String[] datos = linea.split(";"); // Separar los datos por punto y coma
				String tipoIncidencia = datos[0]; // Tipo de incidencia
				String descripcionIncidencia = datos[1]; // Detalles de la incidencia
				String fechaIncidencia = datos[2]; // Fecha de la incidencia

				switch (tipoIncidencia) {

				case "Alumno":
					listaIncidencias.add(new IncidenciaAlumno(descripcionIncidencia, fechaIncidencia));
					break;

				case "Profesor":
					listaIncidencias.add(new IncidenciaProfesor(descripcionIncidencia, fechaIncidencia));
					break;

				case "Aplicacion":
					listaIncidencias.add(new IncidenciaAplicacion(descripcionIncidencia, fechaIncidencia));
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
			bw.write(incidencia.getTipoIncidencia() + ";" + incidencia.getIncidencia() + ";" + FechaYHora.fechaActual()
					+ "\n");
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

	// Comprobar si hay datos en incidencias con count
	public boolean comprobarDatos() {

		ConexionBBDD conexionBBDD = new ConexionBBDD();
		Connection conexion = conexionBBDD.conectar();

		String sql = "SELECT COUNT(*) FROM incidencia";

		try {

			PreparedStatement ps = conexion.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int contador = rs.getInt(1);

				if (contador > 0) {
					return true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexionBBDD.cerrarConexion(conexion);
		}

		return false;
	}

	public List<Incidencias> insertarIncidenciasBBDD(ArrayList<Incidencias> listaIncidencias) {
		
		List<Incidencias> incidencias = leerIncidencias(listaIncidencias);
		
		ConexionBBDD conexionBBDD = new ConexionBBDD();
		Connection conexion = conexionBBDD.conectar();
		
		String sql = "INSERT INTO incidencia (tipo, incidencia, fecha) VALUES (?, ?, ?)";
		
		try  {
		
		PreparedStatement ps = conexion.prepareStatement(sql);
        
		for(Incidencias incidencia : incidencias) {
		
				ps.setString(1, incidencia.getTipoIncidencia());
				ps.setString(2, incidencia.getIncidencia());
				ps.setString(3, incidencia.getFechaIncidencia());
				ps.executeUpdate();
				
		}
				
		 
		 
		} catch (SQLException e) {
			System.err.println("Error al leer el archivo: " + e.getMessage());
			GestionLogs.errorLogs(
					"Error al leer el archivo: " + e.getMessage() + " No se han insertado las incidencias en la base de datos.");
		} finally {
			conexionBBDD.cerrarConexion(conexion);
		}
		
		return incidencias;
		
	}
	
}