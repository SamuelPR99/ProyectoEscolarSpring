package com.daw.proyectoescolar.repositorio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.daw.proyectoescolar.entidades.Tarea;
import com.daw.proyectoescolar.entidades.Temas;
import com.daw.proyectoescolar.servicios.logs.GestionLogs;

public class TemasRepo {

	// Constructores
	public TemasRepo() {

	}

	// Metodos

	// Lee el archivo de temas y crea un array de temas
	public ArrayList<Temas> archivoTemas() {

		ArrayList<Temas> temas = new ArrayList<>();

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

	public ArrayList<Tarea> archivoTareas() {

		ArrayList<Tarea> tareas = new ArrayList<>();

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
		ArrayList<Tarea> tareasArray = new TemasRepo().archivoTareas();

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
	public HashMap<Integer, ArrayList<Temas>> hashTemas() {

		HashMap<Integer, ArrayList<Temas>> temas = new HashMap<>();
		TemasRepo repo = new TemasRepo();
		ArrayList<Temas> temasArray = repo.archivoTemas(); // Array de temas

		// Crea un HashMap con los temas
		int i = 1;
		for (Temas tema : temasArray) {
			ArrayList<Temas> tareas = new ArrayList<>();
			tareas.add(tema); // Cada tema tiene una lista de tareas
			temas.put(i, tareas); // Se añade al HashMap de temas
			i++;
		}

		return temas;
	}

	// Insertar los temas en la bbdd

	public void insertarTemasYTareasDeArchivoTemas() {

		ArrayList<Temas> temas = archivoTemas();

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
}
