package com.daw.proyectoescolar.repositorio;

import com.daw.proyectoescolar.servicios.logs.GestionLogs;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBBDD {

	public Connection conectar() {

		Connection conexion = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conexion = DriverManager.getConnection(Constantes.CONEXION_URL, Constantes.USER, Constantes.PASSWORD);
			System.out.println(Colores.ANSI_GREEN + "↪" + Colores.ANSI_RESET + "...");

		} catch (SQLException | ClassNotFoundException ce) {
			System.err.println("Error en la conexion a la base de datos " + ce.getMessage());
			GestionLogs.errorLogs("Error en la conexion a la base de datos " + ce.getMessage());
		}

		return conexion;
	}

	public void cerrarConexion(Connection conexion) {
		try {
			conexion.close();
			System.out.println("..." + Colores.ANSI_RED + "↩" + Colores.ANSI_RESET);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Error al cerrar la conexion a la base de datos " + e.getMessage());
			GestionLogs.errorLogs("Error al cerrar la conexion a la base de datos " + e.getMessage());
		}
	}

}
