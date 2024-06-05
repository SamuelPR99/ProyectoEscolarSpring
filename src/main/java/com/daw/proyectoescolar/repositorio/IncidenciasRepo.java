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

	// Leer incidencias del archivo
	public List<Incidencias> leerIncidencias() {

		List<Incidencias> listaIncidencias = new ArrayList<>();
		String linea;

		try (BufferedReader br = new BufferedReader(new FileReader(Constantes.RUTA_INCIDENCIAS))) {

			while ((linea = br.readLine()) != null) {
				String[] datos = linea.split(";");
				UsuarioBase usuario = gestionUsuarios.getUsuarioPorId(Integer.parseInt(datos[3]));
                switch (datos[0]) {
                    case "Alumno" -> {
                        Incidencias incidencia = new IncidenciaAlumno(datos[1], datos[2], usuario);
                        listaIncidencias.add(incidencia);
                    }
                    case "Profesor" -> {
                        Incidencias incidencia = new IncidenciaProfesor(datos[1], datos[2], usuario);
                        listaIncidencias.add(incidencia);
                    }
                    case "Aplicacion" -> {
                        Incidencias incidencia = new IncidenciaAplicacion(datos[1], datos[2], usuario);
                        listaIncidencias.add(incidencia);
                    }
                }
			}

		} catch (IOException e) {
			System.err.println("Error al leer el archivo: " + e.getMessage());
			GestionLogs.errorLogs(
					"Error al leer el archivo: " + e.getMessage() + " No se han cargado las incidencias por defecto.");
		}

		return listaIncidencias;
	}

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
			System.err.println("Error al escribir en el archivo: " + e.getMessage());
			GestionLogs.errorLogs(
					"Error al escribir en el archivo: " + e.getMessage() + " No se han guardado las incidencias.");
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
			System.out.println("Error al comprobar los datos de la tabla incidencia: " + e.getMessage());
			GestionLogs.errorLogs("Error al comprobar los datos de la tabla incidencia: " + e.getMessage());
		} finally {
			conexionBBDD.cerrarConexion(conexion);
		}

		return false;
	}

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
				ps.setInt(4, incidencia.getUsuario().getUsuarioId());
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

	public void insertarUnicaIncidenciaBBDD(Incidencias unicaIncidencia) {

		ConexionBBDD conexionBBDD = new ConexionBBDD();
		Connection conexion = conexionBBDD.conectar();
		String sql = "INSERT INTO incidencia (tipo, incidencia, fecha, usuario_id) VALUES (?, ?, NOW(), ?)";

		try {
			
			PreparedStatement ps = conexion.prepareStatement(sql);
			
				ps.setString(1, unicaIncidencia.getTipoIncidencia());
				ps.setString(2, unicaIncidencia.getIncidencia());
				ps.setInt(3, unicaIncidencia.getUsuario().getUsuarioId());
				ps.executeUpdate();

		} catch (SQLException e) {
			System.err.println("Error al leer el archivo: " + e.getMessage());
			GestionLogs.errorLogs(
					"Error al leer el archivo: " + e.getMessage() + " No se han cargado los usuarios por defecto.");
		} finally {
			conexionBBDD.cerrarConexion(conexion);
		}
		
	}

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
			System.out.println("Error al borrar la incidencia con id: " + incidenciaId + " " + e.getMessage());
			GestionLogs.errorLogs("Error al borrar la incidencia con id: " + incidenciaId + " " + e.getMessage());
		} finally {
			conexionBBDD.cerrarConexion(conexion);
		}
		
	}

	public List<Incidencias> listarIncidenciasBBDD() {

		List<Incidencias> incidencias = new ArrayList<>();
		GestionUsuarios gestionUsuarios = new GestionUsuarios();

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

				UsuarioBase usuario = gestionUsuarios.getUsuarioPorId(usuario_id);

				switch (tipoIncidencia) {
					case Constantes.INCI_PROFESOR:
						incidencias.add(new IncidenciaProfesor(incidencia_id, descripcionIncidencia, fechaIncidencia, usuario));
						break;

					case Constantes.INCI_ALUMNO:
						incidencias.add(new IncidenciaAlumno(incidencia_id, descripcionIncidencia, fechaIncidencia, usuario));
						break;

					case Constantes.INCI_APLICACION:
						incidencias.add(new IncidenciaAplicacion(incidencia_id, descripcionIncidencia, fechaIncidencia, usuario));
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

	public List<Incidencias> buscadorDeIncidenciasBBDD(String busqueda) {

		ConexionBBDD conexionBBDD = new ConexionBBDD();
		Connection conexion = conexionBBDD.conectar();

		String sql = "SELECT * FROM incidencia WHERE incidencia LIKE ?";

		List<Incidencias> incidenciasList = new ArrayList<>();

		try {

			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, "%" + busqueda + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int incidenciaId = rs.getInt("incidencia_id");
				String tipoIncidencia = rs.getString("tipo");
				String descripcionIncidencia = rs.getString("incidencia");
				int usuario_id = rs.getInt("usuario_id");
				String fechaIncidencia = rs.getString("fecha");

				UsuarioBase usuario = gestionUsuarios.getUsuarioPorId(usuario_id);

				switch (tipoIncidencia) {
					case Constantes.INCI_PROFESOR:
						incidenciasList.add(new IncidenciaProfesor(incidenciaId, descripcionIncidencia, fechaIncidencia, usuario));
						break;
					case Constantes.INCI_ALUMNO:
						incidenciasList.add(new IncidenciaAlumno(incidenciaId, descripcionIncidencia, fechaIncidencia, usuario));
						break;
					case Constantes.INCI_APLICACION:
						incidenciasList.add(new IncidenciaAplicacion(incidenciaId, descripcionIncidencia, fechaIncidencia, usuario));
						break;
					default:
						System.err.println("Tipo de incidencia desconocido: " + tipoIncidencia);
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

		return incidenciasList;
	}

	public Map<UsuarioBase, List<Incidencias>> hashMapUsuariosIncidencias() {

		ConexionBBDD conexionBBDD = new ConexionBBDD();
		Connection conexion = conexionBBDD.conectar();

		Map<UsuarioBase, List<Incidencias>> hashMapUsuariosIncidencias = new HashMap<>();

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

				UsuarioBase usuario = gestionUsuarios.getUsuarioPorId(usuario_id);

				if (!hashMapUsuariosIncidencias.containsKey(usuario)) {
					hashMapUsuariosIncidencias.put(usuario, new ArrayList<>());
				}

				switch (tipoIncidencia) {
					case Constantes.INCI_PROFESOR:
						hashMapUsuariosIncidencias.get(usuario).add(new IncidenciaProfesor(incidencia_id, descripcionIncidencia, fechaIncidencia, usuario));
						break;
					case Constantes.INCI_ALUMNO:
						hashMapUsuariosIncidencias.get(usuario).add(new IncidenciaAlumno(incidencia_id, descripcionIncidencia, fechaIncidencia, usuario));
						break;
					case Constantes.INCI_APLICACION:
						hashMapUsuariosIncidencias.get(usuario).add(new IncidenciaAplicacion(incidencia_id, descripcionIncidencia, fechaIncidencia, usuario));
						break;
					default:
						System.err.println("Tipo de incidencia desconocido: " + tipoIncidencia);
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

		return hashMapUsuariosIncidencias;
	}
	
}