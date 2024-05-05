package com.daw.proyectoescolar.repositorio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
			GestionLogs.errorLogs(Constantes.ERROR_ESCRIBIR_ARCHIVO + e.getMessage() + " No se ha guardado el usuario.");
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
			GestionLogs.errorLogs(Constantes.ERROR_ESCRIBIR_ARCHIVO + e.getMessage() + " No se ha cambiado la contraseña.");
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

}
