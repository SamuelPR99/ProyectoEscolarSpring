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
			System.out.println("Conexion establecida a la base de datos.");

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
}
