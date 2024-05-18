package com.daw.proyectoescolar.repositorio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.daw.proyectoescolar.entidades.Tarea;
import com.daw.proyectoescolar.entidades.Temas;
import com.daw.proyectoescolar.servicios.logs.GestionLogs;

public class TemasRepo {

	// Constructores
	public TemasRepo() {

	}

	// Metodos

	// Lee el archivo de temas y crea un array de temas
	public List<Temas> archivoTemas() {

		List<Temas> temas = new ArrayList<>();

		try {
			FileReader fr = new FileReader(Constantes.RUTA_TEMAS);
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();

			HashMap<Integer, ArrayList<Tarea>> hashTareas = hashTareas(); // HashMap que obtiene las tareas de
																			// archivoTareas()
			int temaActual = 1;

			while (linea != null) {

				String[] partes = linea.split(";");
				int numeroTema = Integer.parseInt(partes[0]);
				String titulo = partes[1];
				String descripcion = partes[2];

				ArrayList<Tarea> tareasArray = new ArrayList<>();

				for (int i = 0; i < 3; i++) { // Cada tema tiene 3 tareas
					tareasArray.addAll(hashTareas.get(temaActual));
					temaActual++;
				}

				Temas tema = new Temas(numeroTema, titulo, descripcion, tareasArray);
				temas.add(tema);

				linea = br.readLine();
			}

			br.close();

		} catch (IOException e) {
			System.out.println(Constantes.ERROR_LEER_ARCHIVO);
			GestionLogs.errorLogs(Constantes.ERROR_LEER_ARCHIVO + e.getMessage());
		}

		return temas;
	}

	public List<Tarea> archivoTareas() {

		List<Tarea> tareas = new ArrayList<>();

		try {

			FileReader fr = new FileReader(Constantes.RUTA_TAREAS);
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();

			while (linea != null) {
				String[] partes = linea.split(";");
				String tipo = partes[0];
				String nombre = partes[1];
				String descripcion = partes[2];

				Tarea tarea = new Tarea(tipo, nombre, descripcion);
				tareas.add(tarea);
				linea = br.readLine();

			}

			br.close();

		} catch (IOException e) {
			System.out.println("Error al leer el archivo");
			GestionLogs.errorLogs(Constantes.ERROR_LEER_ARCHIVO + e.getMessage());
		}

		return tareas;
	}

	// Crea un HashMap de tareas a partir de archivoTareas()
	public HashMap<Integer, ArrayList<Tarea>> hashTareas() {

		HashMap<Integer, ArrayList<Tarea>> tareas = new HashMap<>();
		List<Tarea> tareasArray = new TemasRepo().archivoTareas();

		int i = 1;
		for (Tarea tarea : tareasArray) {
			ArrayList<Tarea> tareasList = new ArrayList<>();
			tareasList.add(tarea);
			tareas.put(i, tareasList);
			i++;
		}

		return tareas;
	}

	// Crea un HashMap con los temas
	public HashMap<Integer, List<Temas>> hashTemas() {

		HashMap<Integer, List<Temas>> temas = new HashMap<>();
		TemasRepo repo = new TemasRepo();
		List<Temas> temasArray = repo.archivoTemas(); // Array de temas

		// Crea un HashMap con los temas
		int i = 1;
		for (Temas tema : temasArray) {
			List<Temas> tareas = new ArrayList<>();
			tareas.add(tema); // Cada tema tiene una lista de tareas
			temas.put(i, tareas); // Se añade al HashMap de temas
			i++;
		}

		return temas;
	}

	// Insertar los temas en la bbdd

	public void insertarTemasYTareasBBDD() {

		List<Temas> temas = archivoTemas();

		ConexionBBDD conexionBBDD = new ConexionBBDD();
		Connection conexion = conexionBBDD.conectar();

		String sqlInsertTemas = "INSERT INTO tema (numero_tema, titulo, descripcion) VALUES (?, ?, ?)";
		String sqlInsertTareas = "INSERT INTO tarea (titulo, descripcion, dificultad, tema_id) VALUES (?, ?, ?, ?)";
		String sqlSelect = "SELECT tema_id FROM tema WHERE numero_tema = ?";

		try {

			PreparedStatement psInsertTemas = conexion.prepareStatement(sqlInsertTemas);
			PreparedStatement psInsertTareas = conexion.prepareStatement(sqlInsertTareas);

			for (Temas archivoTemas : temas) {
				psInsertTemas.setInt(1, archivoTemas.getNumeroTema());
				psInsertTemas.setString(2, archivoTemas.getNombre());
				psInsertTemas.setString(3, archivoTemas.getDescripcion());

				psInsertTemas.executeUpdate();

				PreparedStatement psSelect = conexion.prepareStatement(sqlSelect);
				psSelect.setInt(1, archivoTemas.getNumeroTema());
				ResultSet rs = psSelect.executeQuery();

				if (rs.next()) {
					int temaId = rs.getInt("tema_id");
					for (Tarea tarea : archivoTemas.getListaTareas()) {
						psInsertTareas.setString(1, tarea.getNombre());
						psInsertTareas.setString(2, tarea.getDescripcion());
						psInsertTareas.setString(3, tarea.getTipo());
						psInsertTareas.setInt(4, temaId);
						psInsertTareas.executeUpdate();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexionBBDD.cerrarConexion(conexion);
		}
	}

	// Comprobar si hay datos en temas con count
	public boolean comprobarDatos() {

		ConexionBBDD conexionBBDD = new ConexionBBDD();
		Connection conexion = conexionBBDD.conectar();

		String sql = "SELECT COUNT(*) FROM tema";

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

	public void asignarTarea(int idTarea, int idAlumno, int idProfesor, Date fechaExpiracion) {

		ConexionBBDD conexionBBDD = new ConexionBBDD();
		Connection conexion = conexionBBDD.conectar();

		String sql = "SELECT * FROM usuario WHERE usuario_id = ?";

		try {

			PreparedStatement ps = conexion.prepareStatement(sql);

			ps.setInt(1, idAlumno);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) { // El alumno existe en la tabla usuario

				sql = "INSERT INTO asignartarea (tarea_id, fecha_inicio, fecha_expiracion, alumno_id, profesor_id, estado) VALUES (?, NOW(), ?, ?, ?, 0)";

				ps = conexion.prepareStatement(sql);

				ps.setInt(1, idTarea);
				ps.setDate(2, fechaExpiracion);
				ps.setInt(3, idAlumno);
				ps.setInt(4, idProfesor);
				ps.executeUpdate();

			} else { // El alumno no existe en la tabla usuario
				System.out.println("El alumno con id " + idAlumno + " no existe en la base de datos.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexionBBDD.cerrarConexion(conexion);
		}
	}

	public List<Tarea> obtenerTareasAlumno(int idAlumno) {

		ConexionBBDD conexionBBDD = new ConexionBBDD();
		Connection conexion = conexionBBDD.conectar();

		List<Tarea> tareas = new ArrayList<>();

		String sql = "SELECT tarea.tarea_id, tarea.titulo, tarea.descripcion, tarea.dificultad, asignartarea.fecha_inicio, asignartarea.fecha_expiracion, asignartarea.fecha_entrega, asignartarea.puntuacion, asignartarea.estado, asignartarea.puntuacion FROM tarea INNER JOIN asignartarea ON tarea.tarea_id = asignartarea.tarea_id WHERE asignartarea.alumno_id = ? AND asignartarea.estado != 1";

		try {

			PreparedStatement ps = conexion.prepareStatement(sql);

			ps.setInt(1, idAlumno);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int idTarea = rs.getInt("tarea_id");
				String titulo = rs.getString("titulo");
				String descripcion = rs.getString("descripcion");
				String dificultad = rs.getString("dificultad");
				Date fechaInicio = rs.getDate("fecha_inicio");
				Date fechaExpiracion = rs.getDate("fecha_expiracion");
				Date fechaEntrega = rs.getDate("fecha_entrega");
				double puntuacion = rs.getDouble("puntuacion");
				boolean estado = rs.getBoolean("estado");

				Tarea tarea = new Tarea(idTarea, titulo, descripcion, dificultad, fechaInicio, fechaExpiracion,
						fechaEntrega, puntuacion, estado);
				tareas.add(tarea);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexionBBDD.cerrarConexion(conexion);
		}

		return tareas;
	}

	public void entregarTarea(int idTarea, int idAlumno) {

		ConexionBBDD conexionBBDD = new ConexionBBDD();
		Connection conexion = conexionBBDD.conectar();

		String sql = "SELECT * FROM asignartarea WHERE tarea_id = ? AND alumno_id = ?";

		try {

			PreparedStatement ps = conexion.prepareStatement(sql);

			ps.setInt(1, idTarea);
			ps.setInt(2, idAlumno);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Date fechaExpiracion = rs.getDate("fecha_expiracion");

				double puntuacion;
				if (FechaYHora.fechaActualCompleta().before(fechaExpiracion)) { // La tarea se entrega a tiempo
					puntuacion = calcularPuntuacion(idTarea);
				} else { // La tarea se entrega tarde, la puntuacion es 0
					puntuacion = 0;
				}

				String sqlUpdate = "UPDATE asignartarea SET fecha_entrega = NOW(), puntuacion = ?, estado = ? WHERE tarea_id = ? AND alumno_id = ?";

				PreparedStatement psUpdate = conexion.prepareStatement(sqlUpdate);

				psUpdate.setDouble(1, puntuacion);
				psUpdate.setInt(2, 1); // La tarea se marca como entregada
				psUpdate.setInt(3, idTarea);
				psUpdate.setInt(4, idAlumno);
				psUpdate.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexionBBDD.cerrarConexion(conexion);
		}
	}

	private double calcularPuntuacion(int idTarea) {

		String dificultad = obtenerDificultadTarea(idTarea);
		double puntuacion = switch (dificultad) {
            case "Basica" -> 1.0;
            case "Intermedia" -> 2.0;
            case "Avanzada" -> 3.0;
            default -> 0.0;
        };

        return puntuacion;
	}

	private String obtenerDificultadTarea(int idTarea) {

		ConexionBBDD conexionBBDD = new ConexionBBDD();
		Connection conexion = conexionBBDD.conectar();

		String dificultad = "";
		String sql = "SELECT dificultad FROM tarea WHERE tarea_id = ?";

		try {

			PreparedStatement ps = conexion.prepareStatement(sql);

			ps.setInt(1, idTarea);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				dificultad = rs.getString("dificultad");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexionBBDD.cerrarConexion(conexion);
		}

		return dificultad;
	}

	public List<Tarea> tareasEntregadas(int idAlumno) {

		ConexionBBDD conexionBBDD = new ConexionBBDD();
		Connection conexion = conexionBBDD.conectar();

		List<Tarea> tareas = new ArrayList<>();

		String sql = "SELECT tarea.tarea_id, tarea.titulo, tarea.descripcion, tarea.dificultad, asignartarea.fecha_inicio, asignartarea.fecha_expiracion, asignartarea.fecha_entrega, asignartarea.puntuacion, asignartarea.estado, asignartarea.puntuacion FROM tarea INNER JOIN asignartarea ON tarea.tarea_id = asignartarea.tarea_id WHERE asignartarea.alumno_id = ? AND asignartarea.estado = 1";

		try {

			PreparedStatement ps = conexion.prepareStatement(sql);

			ps.setInt(1, idAlumno);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int idTarea = rs.getInt("tarea_id");
				String titulo = rs.getString("titulo");
				String descripcion = rs.getString("descripcion");
				String dificultad = rs.getString("dificultad");
				Date fechaInicio = rs.getDate("fecha_inicio");
				Date fechaExpiracion = rs.getDate("fecha_expiracion");
				Date fechaEntrega = rs.getDate("fecha_entrega");
				double puntuacion = rs.getDouble("puntuacion");
				boolean estado = rs.getBoolean("estado");

				Tarea tarea = new Tarea(idTarea, titulo, descripcion, dificultad, fechaInicio, fechaExpiracion,
						fechaEntrega, puntuacion, estado);
				tareas.add(tarea);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexionBBDD.cerrarConexion(conexion);
		}

		return tareas;
	}

	// Obtener temas con tareas de la base de datos
	public List<Temas> obtenerTemasBBDD() {

		List<Temas> temas = new ArrayList<>();

		ConexionBBDD conexionBBDD = new ConexionBBDD();
		Connection conexion = conexionBBDD.conectar();

		String sql = "SELECT * FROM tema";

		try {

			PreparedStatement ps = conexion.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int temaId = rs.getInt("tema_id");
				int numeroTema = rs.getInt("numero_tema");
				String titulo = rs.getString("titulo");
				String descripcion = rs.getString("descripcion");

				List<Tarea> tareas = obtenerTareasBBDD(temaId);

				Temas tema = new Temas(temaId, numeroTema, titulo, descripcion, (ArrayList<Tarea>) tareas);
				temas.add(tema);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexionBBDD.cerrarConexion(conexion);
		}

		return temas;
	}

	// Obtener tareas de la base de datos
	public List<Tarea> obtenerTareasBBDD(int temaId) {

		List<Tarea> tareas = new ArrayList<>();

		ConexionBBDD conexionBBDD = new ConexionBBDD();
		Connection conexion = conexionBBDD.conectar();

		String sql = "SELECT * FROM tarea WHERE tema_id = ?";

		try {

			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, temaId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int tareaId = rs.getInt("tarea_id");
				String titulo = rs.getString("titulo");
				String descripcion = rs.getString("descripcion");
				String dificultad = rs.getString("dificultad");

				Tarea tarea = new Tarea(tareaId, titulo, descripcion, dificultad);
				tareas.add(tarea);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexionBBDD.cerrarConexion(conexion);
		}

		return tareas;
	}



}