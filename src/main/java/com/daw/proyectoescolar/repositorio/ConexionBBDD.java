package com.daw.proyectoescolar.repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBBDD {

	public Connection conectar() {

		Connection conexion = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conexion = DriverManager.getConnection(Constantes.CONEXION_URL, Constantes.USER, Constantes.PASSWORD);
			System.out.println("Conexión establecida a la base de datos.");

		} catch (SQLException | ClassNotFoundException ce) {
			ce.printStackTrace();
		}

		return conexion;
	}

	public void cerrarConexion(Connection conexion) {
		try {
			conexion.close();
			System.out.println("Conexion cerrada con exito");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
