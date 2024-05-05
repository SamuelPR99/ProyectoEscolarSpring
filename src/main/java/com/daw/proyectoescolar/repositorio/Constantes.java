package com.daw.proyectoescolar.repositorio;

public class Constantes {
	
	private Constantes() {

	}
	
	public static final String ALUMNO = "Alumno";
	public static final String PROFESOR = "Profesor";
	public static final String ADMINISTRADOR = "Administrador";
	
	public static final String VALID = "Tipo de usuario no valido.";
	public static final String NUM_TAREA_VALID = "Numero de tarea no valido.";
	public static final String NUM_SELEC = " Numero seleccionado: ";
	public static final String ERROR_ESCRIBIR_ARCHIVO = "Error al escribir en el archivo: ";
	public static final String ERROR_LEER_ARCHIVO = "Error al leer el archivo: ";
	
	public static final String RUTA_USUARIOS = "src/main/java/com/daw/proyectoescolar/repositorio/usuarios.txt";
	public static final String RUTA_INCIDENCIAS = "src/main/java/com/daw/proyectoescolar/repositorio/incidencias.txt";
	public static final String RUTA_TAREAS = "src/main/java/com/daw/proyectoescolar/repositorio/tareas.txt";
	public static final String RUTA_TEMAS = "src/main/java/com/daw/proyectoescolar/repositorio/temas.txt";
	
	public static final String MENU_PRINCIPAL = "Menu Principal";
	public static final String MENU_ALUMNOS = "Menu Alumnos";
	public static final String MENU_PROFESORES = "Menu Profesores";
	public static final String MENU_ADMINISTRADORES = "Menu Administradores";
	
	
	public static final String AVANZA = "Avanzada";
	public static final String BASICA = "Basica";
	public static final String INTERMEDIA = "Intermedia";
	
	public static final String CONEXION_URL = "jdbc:mariadb://localhost:3306/iesmurcia";
	public static final String USER = "root";
	public static final String PASSWORD = "root";
}
