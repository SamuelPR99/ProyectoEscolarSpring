package com.daw.proyectoescolar.repositorio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	public List<UsuarioBase> usuarios() {

		List<UsuarioBase> usuariosDefecto = new ArrayList<>();

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

		List<UsuarioBase> usuarios = usuarios();

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

		List<UsuarioBase> usuarios = usuarios();

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

		List<UsuarioBase> usuarios = usuarios();

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

	// Insertar los usuarios en la base de datos
	public void insertarUsuariosArchivoBBDD() {

		List<UsuarioBase> usuarios = usuarios();

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
			GestionLogs.errorLogs("Error al insertar los usuarios en la base de datos: " + e.getMessage());
		} finally {
			conexionBBDD.cerrarConexion(conexion);
		}
	}
	
	// ArrayList de usuarios de la BBDD
	public List<UsuarioBase> usuariosBBDD() {

		List<UsuarioBase> usuarios = new ArrayList<>();

		ConexionBBDD conexionBBDD = new ConexionBBDD();
		Connection conexion = conexionBBDD.conectar();

		String sql = "SELECT * FROM usuario";

		try {
			PreparedStatement ps = conexion.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
			    int usuarioId = rs.getInt("usuario_id");
			    String tipo = rs.getString("tipo");
			    String nombre = rs.getString("nombre");
			    String contrasena = rs.getString("contrasena");
			    String dni = rs.getString("dni");

			    switch (tipo) {
			        case Constantes.PROFESOR:
			            usuarios.add(new Profesor(usuarioId, nombre, contrasena, dni));
			            break;

			        case Constantes.ALUMNO:
			            String sqlNota = "SELECT nota FROM nota WHERE usuario_id = ?";
			            PreparedStatement psNota = conexion.prepareStatement(sqlNota);
			            psNota.setInt(1, usuarioId);
			            ResultSet rsNota = psNota.executeQuery();

			            if (rsNota.next()) {
			                double nota = rsNota.getDouble("nota");
			                usuarios.add(new Alumno(usuarioId, nombre, contrasena, dni, nota));
			            } else {
			                System.err.println("No se ha encontrado la nota del alumno: " + nombre);
			            }
			            break;

			        case Constantes.ADMINISTRADOR:
			            usuarios.add(new Administrador(usuarioId, nombre, contrasena, dni));
			            break;

			        default:
			            System.err.println("Tipo de usuario desconocido: " + tipo);
			            break;
			    }
			}

		} catch (Exception e) {
			e.printStackTrace();
			GestionLogs.errorLogs("Error al obtener los usuarios de la base de datos: " + e.getMessage());
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
			GestionLogs.errorLogs("Error al borrar los usuarios de la base de datos: " + e.getMessage());
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
			GestionLogs.errorLogs("Error al comprobar los datos de la base de datos: " + e.getMessage());
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
			GestionLogs.errorLogs("Error al insertar el usuario en la base de datos: " + e.getMessage());
		}
		finally {
			conexionBBDD.cerrarConexion(conexion);
		}
	}

	public void cambiarContrasena(UsuarioBase usuario) {
		
		ConexionBBDD conexionBBDD = new ConexionBBDD();
		Connection conexion = conexionBBDD.conectar();
		
		String sql = "UPDATE usuario SET contrasena = ? WHERE nombre = ?";
		
		try {
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, usuario.getContrasena());
			ps.setString(2, usuario.getNombre());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			GestionLogs.errorLogs("Error al cambiar la contraseña en la base de datos: " + e.getMessage());
		} finally {
			conexionBBDD.cerrarConexion(conexion);
		}
	}
	
	public List<Alumno> obtenerAlumnos() {

	    List<Alumno> alumnos = new ArrayList<>();

	    ConexionBBDD conexionBBDD = new ConexionBBDD();
	    Connection conexion = conexionBBDD.conectar();

	    String sql = "SELECT * FROM usuario WHERE tipo = 'alumno'";

	    try {
	        PreparedStatement ps = conexion.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            int id = rs.getInt("usuario_id");
	            String nombre = rs.getString("nombre");
	            String contrasena = rs.getString("contrasena");
	            String dni = rs.getString("dni");
	           
	            Alumno alumno = new Alumno(id, nombre, contrasena, dni);
	            alumnos.add(alumno);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        conexionBBDD.cerrarConexion(conexion);
	    }

	    return alumnos;
	}

	// Modificar la nota de un alumno en la base de datos a traves de id
	public void modificarNotaAlumno(int usuarioId, double nota) {

		ConexionBBDD conexionBBDD = new ConexionBBDD();
		Connection conexion = conexionBBDD.conectar();

		String sql = "UPDATE nota SET nota = ? WHERE usuario_id = ?";

		try {
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setDouble(1, nota);
			ps.setInt(2, usuarioId);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			GestionLogs.errorLogs("Error al modificar la nota del alumno en la base de datos: " + e.getMessage());
		} finally {
			conexionBBDD.cerrarConexion(conexion);
		}
	}

	// Borrar un usuario de la BBDD
	public void borrarUsuarioBBDD (int usuarioId) {

		ConexionBBDD conexionBBDD = new ConexionBBDD();
		Connection conexion = conexionBBDD.conectar();

		String sql = "DELETE FROM usuario WHERE usuario_id = ?";

		try {
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, usuarioId);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			GestionLogs.errorLogs("Error al borrar usuario con id: " + usuarioId + " " + e.getMessage());
		} finally {
			conexionBBDD.cerrarConexion(conexion);
		}
	}

}