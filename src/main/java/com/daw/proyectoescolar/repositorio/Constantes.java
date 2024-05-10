package com.daw.proyectoescolar.repositorio;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Constantes {

	private Constantes() {

	}

	// Tipos de usuario
	public static final String ALUMNO = "Alumno";
	public static final String PROFESOR = "Profesor";
	public static final String ADMINISTRADOR = "Administrador";

	// Mensajes
	public static final String VALID = "Tipo de usuario no valido.";
	public static final String NUM_TAREA_VALID = "Numero de tarea no valido.";
	public static final String NUM_SELEC = " Numero seleccionado: ";
	public static final String ERROR_ESCRIBIR_ARCHIVO = "Error al escribir en el archivo: ";
	public static final String ERROR_LEER_ARCHIVO = "Error al leer el archivo: ";

	// Rutas
	public static final String RUTA_RUTAS_PROPERTIES = "src/main/java/com/daw/proyectoescolar/repositorio/rutas.properties";
	public static final String RUTA_USUARIOS;
	public static final String RUTA_INCIDENCIAS;
	public static final String RUTA_TAREAS;
	public static final String RUTA_TEMAS;
	public static final String CARPETA_LOGS;
	public static final String RUTA_BBDD_PROPERTIES;

	// Menus
	public static final String MENU_PRINCIPAL = "Menu Principal";
	public static final String MENU_ALUMNOS = "Menu Alumnos";
	public static final String MENU_PROFESORES = "Menu Profesores";
	public static final String MENU_ADMINISTRADORES = "Menu Administradores";

	// Dificultad temas
	public static final String AVANZA = "Avanzada";
	public static final String BASICA = "Basica";
	public static final String INTERMEDIA = "Intermedia";

	// Datos de la base de datos
	public static final String CONEXION_URL;
	public static final String USER;
	public static final String PASSWORD;

	// Sacar rutas de un archivo .properties
	static {
		
		Properties rutas = new Properties();
		
		try {
			rutas.load(new FileInputStream(RUTA_RUTAS_PROPERTIES));

			RUTA_USUARIOS = rutas.getProperty("ruta.usuarios");
			RUTA_INCIDENCIAS = rutas.getProperty("ruta.incidencias");
			RUTA_TAREAS = rutas.getProperty("ruta.tareas");
			RUTA_TEMAS = rutas.getProperty("ruta.temas");
			CARPETA_LOGS = rutas.getProperty("carpeta.logs");
			RUTA_BBDD_PROPERTIES = rutas.getProperty("ruta.bbdd");
			
		} catch (IOException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	// Sacar datos de la base de datos de un archivo .properties
	static {

		Properties datosBBDD = new Properties();

		try {
			datosBBDD.load(new FileInputStream(RUTA_BBDD_PROPERTIES));

			CONEXION_URL = datosBBDD.getProperty("database.url");
			USER = datosBBDD.getProperty("database.username");
			PASSWORD = datosBBDD.getProperty("database.password");

		} catch (IOException e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
}
