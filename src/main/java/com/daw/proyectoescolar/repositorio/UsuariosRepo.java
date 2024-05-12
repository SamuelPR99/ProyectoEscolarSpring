package com.daw.proyectoescolar.repositorio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.daw.proyectoescolar.entidades.Administrador;
import com.daw.proyectoescolar.entidades.Alumno;
import com.daw.proyectoescolar.entidades.Profesor;
import com.daw.proyectoescolar.entidades.UsuarioBase;
import com.daw.proyectoescolar.servicios.logs.GestionLogs;

public class UsuariosRepo {

	// Constructor
	public UsuariosRepo() {

	}

	// Leer los usuarios del archivo
	public ArrayList<UsuarioBase> usuarios() {

		ArrayList<UsuarioBase> usuariosDefecto = new ArrayList<>();

		String linea = null;

		try (BufferedReader br = new BufferedReader(new FileReader(Constantes.RUTA_USUARIOS))) {

			while ((linea = br.readLine()) != null) {

				String[] datos = linea.split(";"); // Separar los datos por punto y coma
				String tipo = datos[0]; // Tipo de usuario
				String nombre = datos[1]; // Nombre del usuario
				String contrasena = datos[2]; // Contraseña del usuario
				String dni = datos[3]; // DNI del usuario
				double nota = 0.0; // Nota del alumno

				if (tipo.equals(Constantes.ALUMNO)) { // Si el usuario es un alumno, se le asigna una nota
					nota = Double.parseDouble(datos[4]);
				}

				// Crear el usuario dependiendo del tipo
				switch (tipo) {

				case Constantes.PROFESOR:
					usuariosDefecto.add(new Profesor(nombre, contrasena, dni));
					break;

				case Constantes.ALUMNO:
					usuariosDefecto.add(new Alumno(nombre, contrasena, dni, nota));
					break;

				case Constantes.ADMINISTRADOR:
					usuariosDefecto.add(new Administrador(nombre, contrasena, dni));
					break;

				default:
					System.err.println("Tipo de usuario desconocido: " + tipo);
					break;
				}
			}

		} catch (IOException e) {
			System.err.println("Error al leer el archivo: " + e.getMessage());
			GestionLogs.errorLogs("Error al leer el archivo: " + e.getMessage()
					+ " No se han cargado los usuarios por defecto. " + linea);
		}

		return usuariosDefecto;
	}

	// Guardar un usuario en el archivo
	public void registro(UsuarioBase usuario) {

		try (FileWriter fw = new FileWriter(Constantes.RUTA_USUARIOS, true)) { // Si es true: Añadir al final del
																				// archivo

			// Escribir el usuario en el archivo con sus atributos separados por punto y
			// coma, si es alumno se añade la nota por defecto (0.0)
			fw.write(usuario.getTipoUsuario() + ";" + usuario.getNombre() + ";" + usuario.getContrasena() + ";"
					+ usuario.getDni() + (usuario.getTipoUsuario().equals(Constantes.ALUMNO) ? ";0.0" : "") + "\n");
			fw.flush();
			fw.close();

		} catch (IOException e) {
			System.err.println(Constantes.ERROR_ESCRIBIR_ARCHIVO + e.getMessage());
			GestionLogs
					.errorLogs(Constantes.ERROR_ESCRIBIR_ARCHIVO + e.getMessage() + " No se ha guardado el usuario.");
		}

	}

	// Borrar un usuario del archivo
	public void borrarUsuario(String nombre) {

		ArrayList<UsuarioBase> usuarios = usuarios();

		try (FileWriter fw = new FileWriter(Constantes.RUTA_USUARIOS)) {

			for (UsuarioBase usuario : usuarios) {
				if (!usuario.getNombre().equals(nombre)) { // Si el nombre del usuario no coincide, se escribe en el
															// archivo
					fw.write(usuario.getTipoUsuario() + ";" + usuario.getNombre() + ";" + usuario.getContrasena() + ";"
							+ usuario.getDni() + (usuario.getTipoUsuario().equals(Constantes.ALUMNO) ? ";0.0" : "")
							+ "\n");
				}

			}

			fw.flush();
			fw.close();

		} catch (IOException e) {
			System.err.println(Constantes.ERROR_ESCRIBIR_ARCHIVO + e.getMessage());
			GestionLogs.errorLogs(Constantes.ERROR_ESCRIBIR_ARCHIVO + e.getMessage() + " No se ha borrado el usuario.");
		}

	}

	// Cambiar la contraseña en el archivo
	public void cambiarContrasena(String nuevaContrasena, UsuarioBase usuario) {

		ArrayList<UsuarioBase> usuarios = usuarios();

		try (FileWriter fw = new FileWriter(Constantes.RUTA_USUARIOS)) {

			for (UsuarioBase usuarioActual : usuarios) {
				if (usuarioActual.getNombre().equals(usuario.getNombre())) {
					usuarioActual.setContrasena(nuevaContrasena);
				}

				fw.write(usuarioActual.getTipoUsuario() + ";" + usuarioActual.getNombre() + ";"
						+ usuarioActual.getContrasena() + ";" + usuarioActual.getDni()
						+ (usuarioActual.getTipoUsuario().equals(Constantes.ALUMNO) ? ";0.0" : "") + "\n");
			}

			fw.flush();
			fw.close();

		} catch (IOException e) {
			System.err.println(Constantes.ERROR_ESCRIBIR_ARCHIVO + e.getMessage());
			GestionLogs.errorLogs(
					Constantes.ERROR_ESCRIBIR_ARCHIVO + e.getMessage() + " No se ha cambiado la contraseña.");
		}

	}

	// Modificar la nota de un alumno en el archivo
	public void modificarNotaAlumno(double nuevaNota, Alumno alumno) {

		ArrayList<UsuarioBase> usuarios = usuarios();

		try (FileWriter fw = new FileWriter(Constantes.RUTA_USUARIOS)) {

			for (UsuarioBase usuario : usuarios) {
				if (usuario.getNombre().equals(alumno.getNombre())) {
					((Alumno) usuario).setNota(nuevaNota);
				}

				// Cuando se detecta un alumno, se escribe en el archivo con su nota del array
				fw.write(usuario.getTipoUsuario() + ";" + usuario.getNombre() + ";" + usuario.getContrasena() + ";"
						+ usuario.getDni()
						+ (usuario.getTipoUsuario().equals("Alumno") ? ";" + ((Alumno) usuario).getNota() : "") + "\n");
			}

			fw.flush();
			fw.close();

		} catch (IOException e) {
			System.err.println(Constantes.ERROR_ESCRIBIR_ARCHIVO + e.getMessage());
			GestionLogs.errorLogs(Constantes.ERROR_ESCRIBIR_ARCHIVO + e.getMessage());
		}

	}

	/*---------------------------------*/

	// Base de datos

	/*
	 * Script de la base de datos
	 * 
	 * CREATE TABLE `asignartarea` ( `tareaAsignada_id` int(11) NOT NULL
	 * AUTO_INCREMENT, `tarea_id` int(11) NOT NULL, `fecha_inicio` datetime NOT
	 * NULL, `fecha_entrega` datetime NOT NULL, `fecha_expiracion` datetime NOT
	 * NULL, `puntuacion` decimal(10,0) NOT NULL, `usuario_id` int(11) NOT NULL,
	 * PRIMARY KEY (`tareaAsignada_id`), KEY `asignartarea_tarea_FK` (`tarea_id`),
	 * KEY `asignartarea_usuario_FK` (`usuario_id`), CONSTRAINT
	 * `asignartarea_tarea_FK` FOREIGN KEY (`tarea_id`) REFERENCES `tarea`
	 * (`tarea_id`), CONSTRAINT `asignartarea_usuario_FK` FOREIGN KEY (`usuario_id`)
	 * REFERENCES `usuario` (`usuario_id`) )
	 * 
	 * CREATE TABLE `incidencia` ( `incidencia_id` int(11) NOT NULL AUTO_INCREMENT,
	 * `tipo` enum('Aplicacion','Profesor','Alumno') NOT NULL, `incidencia`
	 * varchar(255) NOT NULL, `usuario_id` int(11) NOT NULL, `fecha` datetime
	 * DEFAULT NULL, PRIMARY KEY (`incidencia_id`), KEY `incidencia_usuario_FK`
	 * (`usuario_id`), CONSTRAINT `incidencia_usuario_FK` FOREIGN KEY (`usuario_id`)
	 * REFERENCES `usuario` (`usuario_id`) )
	 * 
	 * CREATE TABLE `nota` ( `usuario_id` int(11) NOT NULL, `nota` decimal(10,0) NOT
	 * NULL, PRIMARY KEY (`usuario_id`), CONSTRAINT `nota_usuario_FK` FOREIGN KEY
	 * (`usuario_id`) REFERENCES `usuario` (`usuario_id`) )
	 * 
	 * CREATE TABLE `tarea` ( `tarea_id` int(11) NOT NULL AUTO_INCREMENT, `titulo`
	 * varchar(255) NOT NULL, `dificultad` varchar(255) NOT NULL, `descripcion`
	 * varchar(255) NOT NULL, `tema_id` int(11) NOT NULL, PRIMARY KEY (`tarea_id`),
	 * KEY `tareas_temas_FK` (`tema_id`), CONSTRAINT `tarea_tema_FK` FOREIGN KEY
	 * (`tema_id`) REFERENCES `tema` (`tema_id`) )
	 * 
	 * CREATE TABLE `tema` ( `tema_id` int(11) NOT NULL, `titulo` varchar(255) NOT
	 * NULL, `descripcion` varchar(255) NOT NULL, PRIMARY KEY (`tema_id`) )
	 * 
	 * CREATE TABLE `usuario` ( `usuario_id` int(11) NOT NULL AUTO_INCREMENT,
	 * `nombre` varchar(150) NOT NULL, `contrasena` varchar(250) NOT NULL, `tipo`
	 * enum('Administrador','Profesor','Alumno') NOT NULL, `dni` varchar(9) NOT
	 * NULL, PRIMARY KEY (`usuario_id`) )
	 * 
	 */

	// Insertar los usuarios en la base de datos
	public void insertarUsuariosArchivoBBDD() {

		ArrayList<UsuarioBase> usuarios = usuarios();

		ConexionBBDD conexionBBDD = new ConexionBBDD();
		Connection conexion = conexionBBDD.conectar();

		String sqlInsert = "INSERT INTO usuario (nombre, contrasena, tipo, dni) VALUES (?, ?, ?, ?)";
		String sqlSelect = "SELECT usuario_id FROM usuario WHERE nombre = ? AND tipo = 'Alumno'";
		String sqlInsertNota = "INSERT INTO nota (usuario_id, nota) VALUES (?, ?)";

		try {

			PreparedStatement psInsert = conexion.prepareStatement(sqlInsert);

			for (UsuarioBase usuario : usuarios) {
				psInsert.setString(1, usuario.getNombre());
				psInsert.setString(2, usuario.getContrasena());
				psInsert.setString(3, usuario.getTipoUsuario());
				psInsert.setString(4, usuario.getDni());

				psInsert.executeUpdate();

				if (usuario.getTipoUsuario().equals(Constantes.ALUMNO)) {
					PreparedStatement psSelect = conexion.prepareStatement(sqlSelect);
					psSelect.setString(1, usuario.getNombre());

					ResultSet rs = psSelect.executeQuery();

					if (rs.next()) {
						int usuarioId = rs.getInt("usuario_id");

						PreparedStatement psInsertNota = conexion.prepareStatement(sqlInsertNota);
						psInsertNota.setInt(1, usuarioId);
						psInsertNota.setDouble(2, ((Alumno) usuario).getNota());

						psInsertNota.executeUpdate();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexionBBDD.cerrarConexion(conexion);
		}
	}
	
	// ArrayList de usuarios de la BBDD
	public ArrayList<UsuarioBase> usuariosBBDD() {

		ArrayList<UsuarioBase> usuarios = new ArrayList<>();

		ConexionBBDD conexionBBDD = new ConexionBBDD();
		Connection conexion = conexionBBDD.conectar();

		String sql = "SELECT * FROM usuario";

		try {

			PreparedStatement ps = conexion.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String tipo = rs.getString("tipo");
				String nombre = rs.getString("nombre");
				String contrasena = rs.getString("contrasena");
				String dni = rs.getString("dni");

				switch (tipo) {

				case Constantes.PROFESOR:
					usuarios.add(new Profesor(nombre, contrasena, dni));
					break;

				case Constantes.ALUMNO:
					String sqlNota = "SELECT nota FROM nota WHERE usuario_id = ?";
					PreparedStatement psNota = conexion.prepareStatement(sqlNota);
					psNota.setInt(1, rs.getInt("usuario_id"));
					ResultSet rsNota = psNota.executeQuery();

					if (rsNota.next()) {
						double nota = rsNota.getDouble("nota");
						usuarios.add(new Alumno(nombre, contrasena, dni, nota));
					} else {
						System.err.println("No se ha encontrado la nota del alumno: " + nombre);
					}

					break;

				case Constantes.ADMINISTRADOR:
					usuarios.add(new Administrador(nombre, contrasena, dni));
					break;

				default:
					System.err.println("Tipo de usuario desconocido: " + tipo);
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexionBBDD.cerrarConexion(conexion);
		}

		return usuarios;
	}

	// Borrar la nota y los usuarios de la base de datos
	public void borrarUsuarios() {

		ConexionBBDD conexionBBDD = new ConexionBBDD();
		Connection conexion = conexionBBDD.conectar();

		String sqlDeleteNota = "DELETE FROM nota";
		String sqlDeleteUsuario = "DELETE FROM usuario";

		try {

			PreparedStatement psDeleteNota = conexion.prepareStatement(sqlDeleteNota);
			psDeleteNota.executeUpdate();

			PreparedStatement psDeleteUsuario = conexion.prepareStatement(sqlDeleteUsuario);
			psDeleteUsuario.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexionBBDD.cerrarConexion(conexion);
		}
	}

	// Comprobar si hay datos en usuario con count
	public boolean comprobarDatos() {

		ConexionBBDD conexionBBDD = new ConexionBBDD();
		Connection conexion = conexionBBDD.conectar();

		String sql = "SELECT COUNT(*) FROM usuario";

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

	// Insertar un usuario en la base de datos
	public void insertarUsuario(UsuarioBase usuario) {

		ConexionBBDD conexionBBDD = new ConexionBBDD();
		Connection conexion = conexionBBDD.conectar();

		String sqlInsert = "INSERT INTO usuario (nombre, contrasena, tipo, dni) VALUES (?, ?, ?, ?)";
		String sqlSelect = "SELECT usuario_id FROM usuario WHERE nombre = ? AND tipo = 'Alumno'";
		String sqlInsertNota = "INSERT INTO nota (usuario_id, nota) VALUES (?, ?)";

		try {

			PreparedStatement psInsert = conexion.prepareStatement(sqlInsert);
			psInsert.setString(1, usuario.getNombre());
			psInsert.setString(2, usuario.getContrasena());
			psInsert.setString(3, usuario.getTipoUsuario());
			psInsert.setString(4, usuario.getDni());
			psInsert.executeUpdate();

			if (usuario.getTipoUsuario().equals(Constantes.ALUMNO)) {
				PreparedStatement psSelect = conexion.prepareStatement(sqlSelect);
				psSelect.setString(1, usuario.getNombre());
				ResultSet rs = psSelect.executeQuery();

				if (rs.next()) {
					int usuarioId = rs.getInt("usuario_id");
					PreparedStatement psInsertNota = conexion.prepareStatement(sqlInsertNota);
					psInsertNota.setInt(1, usuarioId);
					psInsertNota.setDouble(2, ((Alumno) usuario).getNota());
					psInsertNota.executeUpdate();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			conexionBBDD.cerrarConexion(conexion);
		}
	}

	// Login con BBDD
	public UsuarioBase login(String nombre, String contrasena) {

		ConexionBBDD conexionBBDD = new ConexionBBDD();
		Connection conexion = conexionBBDD.conectar();

		String sql = "SELECT * FROM usuario WHERE nombre = ? AND contrasena = ?";

		try {

			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, nombre);
			ps.setString(2, contrasena);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String tipo = rs.getString("tipo");
				String dni = rs.getString("dni");

				switch (tipo) {

				case Constantes.PROFESOR:
					return new Profesor(nombre, contrasena, dni);
					

				case Constantes.ALUMNO:
					String sqlNota = "SELECT nota FROM nota WHERE usuario_id = ?";
					PreparedStatement psNota = conexion.prepareStatement(sqlNota);
					psNota.setInt(1, rs.getInt("usuario_id"));
					ResultSet rsNota = psNota.executeQuery();

					if (rsNota.next()) {
						double nota = rsNota.getDouble("nota");
						return new Alumno(nombre, contrasena, dni, nota);
					} else {
						System.err.println("No se ha encontrado la nota del alumno: " + nombre);
					}
					
					break;

				case Constantes.ADMINISTRADOR:
					return new Administrador(nombre, contrasena, dni);

				default:
					System.err.println("Tipo de usuario desconocido: " + tipo);
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexionBBDD.cerrarConexion(conexion);
		}

		return null;
	}
	
}
