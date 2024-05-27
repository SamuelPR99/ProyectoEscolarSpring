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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.daw.proyectoescolar.entidades.IncidenciaAlumno;
import com.daw.proyectoescolar.entidades.IncidenciaAplicacion;
import com.daw.proyectoescolar.entidades.IncidenciaProfesor;
import com.daw.proyectoescolar.entidades.Incidencias;
import com.daw.proyectoescolar.entidades.UsuarioBase;
import com.daw.proyectoescolar.servicios.logs.GestionLogs;
import com.daw.proyectoescolar.servicios.usuarios.GestionUsuarios;

public class IncidenciasRepo {

	GestionUsuarios gestionUsuarios = new GestionUsuarios();
	
	public List<Incidencias> leerIncidencias() {

		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		String ultimoRegistro = null;

		List<Incidencias> listaIncidencias = new ArrayList<>();

		try {
			archivo = new File(Constantes.RUTA_INCIDENCIAS);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea;
			
			while ((linea = br.readLine()) != null) {
				String[] datos = leerUltimoRegistroFichero(ultimoRegistro).split(";");
				if (datos[0].equals("Alumno")) {
					Incidencias incidencia = new IncidenciaAlumno(datos[1], datos[2], Integer.parseInt(datos[3]));
					listaIncidencias.add(incidencia);
				} else if (datos[0].equals("Profesor")) {
					Incidencias incidencia = new IncidenciaProfesor(datos[1], datos[2], Integer.parseInt(datos[3]));
					listaIncidencias.add(incidencia);
				} else if (datos[0].equals("Aplicacion")) {
					Incidencias incidencia = new IncidenciaAplicacion(datos[1], datos[2], Integer.parseInt(datos[3]));
					listaIncidencias.add(incidencia);
				}
			}
		} catch (IOException e) {
			System.err.println("Error al leer el archivo: " + e.getMessage());
			GestionLogs.errorLogs(
					"Error al leer el archivo: " + e.getMessage() + " No se han cargado los usuarios por defecto.");
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (IOException e2) {
				System.err.println("Error al cerrar el archivo: " + e2.getMessage());
				GestionLogs.errorLogs("Error al cerrar el archivo: " + e2.getMessage()
						+ " No se han cargado los usuarios por defecto.");
			}
		}
		return listaIncidencias;
	}
	
/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	public List<Incidencias> leerIncidenciasParaHashMap() {

		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		List<Incidencias> listaIncidencias = new ArrayList<>();

		try {
			archivo = new File(Constantes.RUTA_INCIDENCIAS);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea;
			
			while ((linea = br.readLine()) != null) {
				String[] datos = linea.split(";");
				if (datos[0].equals("Alumno")) {
					Incidencias incidencia = new IncidenciaAlumno(datos[1], datos[2], Integer.parseInt(datos[3]));
					listaIncidencias.add(incidencia);
				} else if (datos[0].equals("Profesor")) {
					Incidencias incidencia = new IncidenciaProfesor(datos[1], datos[2], Integer.parseInt(datos[3]));
					listaIncidencias.add(incidencia);
				} else if (datos[0].equals("Aplicacion")) {
					Incidencias incidencia = new IncidenciaAplicacion(datos[1], datos[2], Integer.parseInt(datos[3]));
					listaIncidencias.add(incidencia);
				}
			}
		} catch (IOException e) {
			System.err.println("Error al leer el archivo: " + e.getMessage());
			GestionLogs.errorLogs(
					"Error al leer el archivo: " + e.getMessage() + " No se han cargado los usuarios por defecto.");
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (IOException e2) {
				System.err.println("Error al cerrar el archivo: " + e2.getMessage());
				GestionLogs.errorLogs("Error al cerrar el archivo: " + e2.getMessage()
						+ " No se han cargado los usuarios por defecto.");
			}
		}
		
		return listaIncidencias;
		
	}
	
/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	public String leerUltimoRegistroFichero(String texto) {
		
		String ultimoRegistro = null;
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String linea = null;
		
		try {
			
			archivo = new File(Constantes.RUTA_INCIDENCIAS);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
				while ((linea = br.readLine()) != null) {
					
					ultimoRegistro = linea;
					
				}
				
				br.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		return ultimoRegistro;
		
	}
	
/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

	public void escribirIncidencia(Incidencias incidencia, int usuarioId) {
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			File file = new File(Constantes.RUTA_INCIDENCIAS);
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			bw.write(incidencia.getTipoIncidencia() + ";" + incidencia.getIncidencia() + ";" + FechaYHora.fechaActual()
					+ ";" + usuarioId  + "\n");
			bw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

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

/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	public void insertarIncidenciasBBDD() {

		List<Incidencias> incidencias = leerIncidencias();

		ConexionBBDD conexionBBDD = new ConexionBBDD();
		Connection conexion = conexionBBDD.conectar();
		String sql = "INSERT INTO incidencia (tipo, incidencia, fecha, usuario_id) VALUES (?, ?, ?, ?)";
		int filasAfectadas = 0;

		try {
			PreparedStatement ps = conexion.prepareStatement(sql);
			for (Incidencias incidencia : incidencias) {
				ps.setString(1, incidencia.getTipoIncidencia());
				ps.setString(2, incidencia.getIncidencia());
				ps.setString(3, incidencia.getFechaIncidencia());
				ps.setInt(4, incidencia.getUsuarioId());
				filasAfectadas = ps.executeUpdate();

			}

			if (filasAfectadas > 0) {
				System.out.println("Se han añadido " + filasAfectadas + " incidencias");
			} else {
				System.out.println("No se ha añadido ninguna incidencia");
			}
			ps.close();

		} catch (SQLException e) {
			System.err.println("Error al leer el archivo: " + e.getMessage());
			GestionLogs.errorLogs(
					"Error al leer el archivo: " + e.getMessage() + " No se han cargado los usuarios por defecto.");
		} finally {
			conexionBBDD.cerrarConexion(conexion);
		}
	}
	
/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	public void insertarUnicaIncidenciaBBDD(Incidencias unicaIncidencia) {

		ConexionBBDD conexionBBDD = new ConexionBBDD();
		Connection conexion = conexionBBDD.conectar();
		String sql = "INSERT INTO incidencia (tipo, incidencia, fecha, usuario_id) VALUES (?, ?, NOW(), ?)";

		try {
			
			PreparedStatement ps = conexion.prepareStatement(sql);
			
				ps.setString(1, unicaIncidencia.getTipoIncidencia());
				ps.setString(2, unicaIncidencia.getIncidencia());
				ps.setInt(3, unicaIncidencia.getUsuarioId());
				ps.executeUpdate();

		} catch (SQLException e) {
			System.err.println("Error al leer el archivo: " + e.getMessage());
			GestionLogs.errorLogs(
					"Error al leer el archivo: " + e.getMessage() + " No se han cargado los usuarios por defecto.");
		} finally {
			conexionBBDD.cerrarConexion(conexion);
		}
		
	}
	
/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

	
	public void eliminarIncidenciasBBDD(int incidenciaId) {
		
		ConexionBBDD conexionBBDD = new ConexionBBDD();
		Connection conexion = conexionBBDD.conectar();
		
		String sql = "DELETE FROM incidencia WHERE incidencia_id = ?";
		
		try {
			
			PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, incidenciaId);
            
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > -1) {
                System.out.println(Colores.ANSI_GREEN + "Incidencia con ID " + Colores.ANSI_YELLOW + incidenciaId + Colores.ANSI_GREEN + 
                		" eliminada correctamente de la base de datos" + Colores.ANSI_RESET);
            } else {
                System.out.println(Colores.ANSI_RED + "No existe ninguna incidencia con el ID " + Colores.ANSI_YELLOW + incidenciaId + Colores.ANSI_RESET);
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
			GestionLogs.errorLogs("Error al borrar la incidencia con id: " + incidenciaId + " " + e.getMessage());
		} finally {
			conexionBBDD.cerrarConexion(conexion);
		}
		
	}

/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	public List<Incidencias> listarIncidenciasBBDD() {

		List<Incidencias> incidencias = leerIncidencias();

		ConexionBBDD conexionBBDD = new ConexionBBDD();
		Connection conexion = conexionBBDD.conectar();
		String sql = "SELECT * FROM incidencia";

		try {

			PreparedStatement ps = conexion.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int incidencia_id = rs.getInt("incidencia_id");
				String tipoIncidencia = rs.getString("tipo");
				String descripcionIncidencia = rs.getString("incidencia");
				int usuario_id = rs.getInt("usuario_id");
				String fechaIncidencia = rs.getString("fecha");

				switch (tipoIncidencia) {
				case Constantes.INCI_PROFESOR:
					incidencias.add(new IncidenciaProfesor(incidencia_id, descripcionIncidencia, fechaIncidencia, usuario_id));
					break;

				case Constantes.INCI_ALUMNO:
					incidencias.add(new IncidenciaAlumno(incidencia_id, descripcionIncidencia, fechaIncidencia, usuario_id));
					break;

				case Constantes.INCI_APLICACION:
					incidencias.add(new IncidenciaAplicacion(incidencia_id, descripcionIncidencia, fechaIncidencia, usuario_id));
					break;

				default:
					System.err.println("Tipo de incidencia desconocido: " + tipoIncidencia);
					break;
				}

			}

			rs.close();
			ps.close();

		} catch (SQLException e) {
			System.err.println("Error al leer el archivo: " + e.getMessage());
			GestionLogs.errorLogs(
					"Error al leer el archivo: " + e.getMessage() + " No se han cargado los usuarios por defecto.");
		} finally {
			conexionBBDD.cerrarConexion(conexion);
		}

		return incidencias;

	}
	
/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	public void buscadorDeIncidenciasBBDD(int incidenciaId) {

		List<Incidencias> incidencias = new ArrayList<Incidencias>();
		
		Incidencias incidencia = null;
		String tipo = "";
		String descripcionIncidencia = "";
		int usuarioId = 0;
		String fecha = "";
		
		ConexionBBDD conexionBBDD = new ConexionBBDD();
		Connection conexion = conexionBBDD.conectar();
		
		String sql = "SELECT * FROM incidencia WHERE incidencia_id = ?";
		
		try {
			
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, incidenciaId);
			ps.setString(2, tipo);
			ps.setString(3, descripcionIncidencia);
			ps.setString(4, fecha);
			ps.setInt(5, usuarioId);
			
			ResultSet rs = ps.executeQuery();
			
			 if ("alumno".equalsIgnoreCase(tipo)) {
                 incidencia = new IncidenciaAlumno(incidenciaId, descripcionIncidencia, fecha, usuarioId);
             } else if ("profesor".equalsIgnoreCase(tipo)) {
            	 incidencia = new IncidenciaProfesor(incidenciaId, descripcionIncidencia, fecha, usuarioId);
             } else if ("aplicacion".equalsIgnoreCase(tipo)) {
            	 incidencia = new IncidenciaAplicacion(incidenciaId, descripcionIncidencia, fecha, usuarioId);
             }
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			System.err.println("Error al leer el archivo: " + e.getMessage());
			GestionLogs.errorLogs("Error al leer el archivo: " + e.getMessage() + " No se han cargado los usuarios por defecto.");
		} finally {
			conexionBBDD.cerrarConexion(conexion);
		}
		
	}

}