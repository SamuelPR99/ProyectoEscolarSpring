package com.daw.proyectoescolar.servicios.gestionusuarios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.daw.proyectoescolar.entidades.Administrador;
import com.daw.proyectoescolar.entidades.Alumno;
import com.daw.proyectoescolar.entidades.Profesor;
import com.daw.proyectoescolar.entidades.Tarea;
import com.daw.proyectoescolar.entidades.UsuarioBase;
import com.daw.proyectoescolar.repositorio.Colores;
import com.daw.proyectoescolar.repositorio.Constantes;
import com.daw.proyectoescolar.repositorio.GestionLogs;
import com.daw.proyectoescolar.servicios.incidencias.GestionDeIncidencias;

public class GestionadorUsuarios {

 private ArrayList<Tarea> listaDeTareas = new Tarea().archivoTareas();
 
     // Constructor vacio
	 public GestionadorUsuarios() {}
	 
	 // Iniciar el menu principal
	 public void iniciar(Scanner sc) {
		 
		    ArrayList<UsuarioBase> usuarios = usuarios();
		    String opcion;
		    
		    System.out.println(Colores.ANSI_UNDERLINE + Colores.ANSI_BOLD + Colores.ANSI_BLUE_BACKGROUND + "Bienvenido al" 
		    + Colores.ANSI_YELLOW_BACKGROUND + " Campus Virtual " 
		    + Colores.ANSI_BLUE_BACKGROUND + " del IES Murcia." + Colores.ANSI_RESET);

		    do {

		        System.out.println(Colores.ANSI_YELLOW + Colores.ANSI_UNDERLINE +"\nSeleccione una opcion:\n" + Colores.ANSI_RESET + Colores.ANSI_YELLOW 
		        		+ "1. Iniciar sesion\n"
		                + "2. Gestion de incidencias\n"
		                + "3. Salir"
		                + Colores.ANSI_RESET);

				opcion = sc.nextLine().toLowerCase();

				switch (opcion) {

				case "1", "iniciar sesion":

					GestionLogs.logOpcionMenu(Constantes.MENU_PRINCIPAL, "Iniciar sesion");

					UsuarioBase usuario = login(sc, usuarios);

					try {

						System.out.println("Bienvenido " + Colores.ANSI_UNDERLINE + Colores.ANSI_BOLD
								+ usuario.getTipoUsuario() + Colores.ANSI_RESET + ", " + usuario.getNombre());

						usuario.verMenu(sc, usuarios, obtenerAlumnos(usuarios));

					} catch (NullPointerException excepcion) {
						GestionLogs.errorLogs("Usuario o contraseña incorrectos. " + excepcion.getMessage());
						System.err.println("Usuario o contraseña incorrectos. Intentalo de nuevo.");
					} catch (Exception excepcion) {
						GestionLogs.errorLogs("Error al iniciar sesion." + " Error: " + excepcion.getMessage());
						System.err.println("Error al iniciar sesion. Intentalo de nuevo.");
					}

					break;

				case "2", "gestion de incidencias":
					GestionLogs.logOpcionMenu(Constantes.MENU_PRINCIPAL, "Gestion de incidencias");
					GestionDeIncidencias gestionadorIncidencias = new GestionDeIncidencias();
					gestionadorIncidencias.menuPrincipal(sc);
					break;

				case "3", "salir":
					GestionLogs.logOpcionMenu(Constantes.MENU_PRINCIPAL, "Salir");
					System.out.println("Hasta luego. " + Colores.ANSI_GREEN + "(⌐■_■)" + Colores.ANSI_RESET);
					break;

				default:
					System.err.println("Opcion no valida. Intentalo de nuevo.");
					GestionLogs.errorLogs("Opcion no valida seleccionada en el menu principal." + " Opcion: " + opcion);

				}

			} while (!opcion.equals("3") && !opcion.equals("salir"));

		}

	// Iniciar sesion
	public UsuarioBase login(Scanner sc, ArrayList<UsuarioBase> usuarios) {


		
		System.out.println("Introduce tu nombre de usuario:");
		String nombre = sc.nextLine();

		System.out.println("Introduce tu contraseña:");
		String contrasena = sc.nextLine();
		
		return login(nombre, contrasena, usuarios);
	}

	public UsuarioBase login(String nombre, String contrasena, ArrayList<UsuarioBase> usuarios) {

		for (UsuarioBase usuario : usuarios) {
			if (usuario.getNombre().equals(nombre) && usuario.getContrasena().equals(contrasena)) {
				return usuario;
			}
		}

		return null;
	}

	// Registro de un nuevo usuario
	public void registro(Scanner sc, ArrayList<UsuarioBase> usuarios) {

		System.out.print("Introduzca su nombre: ");
		String nombre = sc.nextLine();

		// Validar nombre
		while (!validarNombreUsuario(nombre)) {
			System.err.println("Nombre de usuario no valido. Intentalo de nuevo: ");
			GestionLogs.errorLogs("Nombre de usuario no valido." + " Nombre: " + nombre);
			System.out.print("Introduzca su nombre: ");
			nombre = sc.nextLine();
		}

		System.out.print("Introduzca su DNI: ");
		String dni = sc.nextLine();

		// Validar el DNI
		while (!validarDNI(dni)) {
			System.err.println("DNI no valido. Intentalo de nuevo: ");
			GestionLogs.errorLogs("DNI no valido." + " DNI: " + dni);
			System.out.print("Introduzca su DNI: ");
			dni = sc.nextLine();
		}

		System.out.print("Introduzca su contraseña: ");
		String contrasena = sc.nextLine();

		// Validar la contraseña
		while (!validarContrasena(contrasena)) {
			System.err.println("Contraseña no valida. Intentalo de nuevo: ");
			GestionLogs.errorLogs("Contraseña no valida." + " Contraseña: " + contrasena);
			System.out.print("Introduzca su contraseña: ");
			contrasena = sc.nextLine();
		}

		System.out.print("¿Es profesor o alumno?: ");
		String tipo = sc.nextLine();

		// Validar el tipo de usuario
		while (!tipo.equalsIgnoreCase("profesor") && !tipo.equalsIgnoreCase("alumno")) {
			System.err.println(Constantes.VALID + " Intentalo de nuevo: ");
			GestionLogs.errorLogs(Constantes.VALID + " Tipo: " + tipo);
			System.out.print("¿Es profesor o alumno?: ");
			tipo = sc.nextLine();
		}

		registro(nombre, dni, contrasena, tipo, usuarios);

	}

	// Registro de un nuevo usuario
	public void registro(String nombre, String dni, String contrasena, String tipo,
			ArrayList<UsuarioBase> usuarios) {

		UsuarioBase nuevoUsuario = null;

		// Crear el usuario dependiendo del tipo
		if (tipo.equalsIgnoreCase("profesor")) {
			nuevoUsuario = new Profesor(nombre, contrasena, dni);
		} else if (tipo.equalsIgnoreCase("alumno")) {
			nuevoUsuario = new Alumno(nombre, contrasena, dni, 0.0);
		} else {
			System.out.println(Constantes.VALID);
			GestionLogs.errorLogs(Constantes.VALID + " Tipo: " + tipo);
			return;
		}

		// Agregar el nuevo usuario al ArrayList de usuarios
		usuarios.add(nuevoUsuario);

		System.out.println(Colores.ANSI_GREEN + "Usuario creado correctamente." + Colores.ANSI_RESET);

		// Guardar el usuario en el archivo
		guardarUsuario(nuevoUsuario);

	}

	// Guardar un usuario en el archivo
	public void guardarUsuario(UsuarioBase usuario) {

		try (FileWriter fw = new FileWriter(Constantes.RUTA_USUARIOS, true)) { // Si es true: Añadir al final del archivo

			// Escribir el usuario en el archivo con sus atributos separados por punto y
			// coma, si es alumno se añade la nota por defecto (0.0)
			fw.write(usuario.getTipoUsuario() + ";" + usuario.getNombre() + ";" + usuario.getContrasena() + ";"
					+ usuario.getDni() + (usuario.getTipoUsuario().equals(Constantes.ALUMNO) ? ";0.0" : "") + "\n");
			fw.flush();
			fw.close();

		} catch (IOException e) {
			System.err.println(Constantes.ERROR_ARCHIVO + e.getMessage());
			GestionLogs.errorLogs(Constantes.ERROR_ARCHIVO + e.getMessage() + " No se ha guardado el usuario.");
		}

	}

	// Borrar un usuario utilizando Scanner
	public void borrarUsuario(Scanner sc, ArrayList<UsuarioBase> usuarios) {

		System.out.println("Introduce el nombre de usuario que quieres borrar:");
		String nombre = sc.nextLine();
		borrarUsuario(nombre, usuarios);

	}

	// Borrar un usuario
	public void borrarUsuario(String nombre, ArrayList<UsuarioBase> usuarios) {

		for (UsuarioBase usuario : usuarios) {
			if (usuario.getNombre().equals(nombre)) {
				usuarios.remove(usuario);
				System.out.println(Colores.ANSI_GREEN + "Usuario borrado correctamente." + Colores.ANSI_RESET);
				return;
			}
		}

		borrarUsuarioArchivo(nombre);

		// Si el usuario no se encuentra
		System.err.println("Usuario no encontrado.");
		GestionLogs.errorLogs("Usuario no encontrado." + " Nombre: " + nombre);

	}

	// Borrar un usuario del archivo
	public void borrarUsuarioArchivo(String nombre) {

		ArrayList<UsuarioBase> usuarios = usuarios();

		try (FileWriter fw = new FileWriter(Constantes.RUTA_USUARIOS)) {

			for (UsuarioBase usuario : usuarios) {
				if (!usuario.getNombre().equals(nombre)) { // Si el nombre del usuario no coincide, se escribe en el
															// archivo
					fw.write(usuario.getTipoUsuario() + ";" + usuario.getNombre() + ";" + usuario.getContrasena()
							+ ";" + usuario.getDni() + (usuario.getTipoUsuario().equals(Constantes.ALUMNO) ? ";0.0" : "")
							+ "\n"); 
				}

			}

			fw.flush();
			fw.close();

		} catch (IOException e) {
			System.err.println(Constantes.ERROR_ARCHIVO + e.getMessage());
			GestionLogs.errorLogs(Constantes.ERROR_ARCHIVO + e.getMessage() + " No se ha borrado el usuario.");
		}

	}

	// Mostrar los usuarios registrados
	public void mostrarUsuarios(ArrayList<UsuarioBase> usuarios) {

		for (UsuarioBase usuario : usuarios) {
			System.out.println("\nNombre: " + usuario.getNombre() + "\nTipo: " + usuario.getTipoUsuario()
					+ "\nDNI: " + usuario.getDni() + "\nContraseña: " + usuario.getContrasena());
			System.out.println(Colores.ANSI_BOLD + "_______________________________" + Colores.ANSI_RESET);
		}

	}

	// Cambiar la contraseña
	public void cambiarContrasena(Scanner sc, UsuarioBase usuario) {

		System.out.println("Introduce tu nueva contraseña:");
		String nuevaContrasena = sc.nextLine();

		// Validar la contraseña
		while (!validarContrasena(nuevaContrasena)) {
			System.err.println("Contraseña no valida. Intentalo de nuevo: ");
			GestionLogs.errorLogs("Contraseña no valida.");
			System.out.print("Introduce tu nueva contraseña: ");
			nuevaContrasena = sc.nextLine();
		}

		cambiarContrasena(nuevaContrasena, usuario);

	}

	public void cambiarContrasena(String nuevaContrasena, UsuarioBase usuario) {

		usuario.setContrasena(nuevaContrasena);
		System.out.println(Colores.ANSI_GREEN + "Contraseña cambiada correctamente." + Colores.ANSI_RESET);

		cambiarContrasenaArchivo(nuevaContrasena, usuario);

	}

	// Cambiar la contraseña en el archivo
	public void cambiarContrasenaArchivo(String nuevaContrasena, UsuarioBase usuario) {

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
			System.err.println(Constantes.ERROR_ARCHIVO + e.getMessage());
			GestionLogs.errorLogs(Constantes.ERROR_ARCHIVO + e.getMessage() + " No se ha cambiado la contraseña.");
		}

	}

	// Validar el DNI
	public boolean validarDNI(String dni) {

		return dni.length() == 9 && dni.substring(0, 8).matches("[0-9]+") && dni.substring(8).matches("[a-zA-Z]");
	}

	// Validar el nombre del usuario
	public boolean validarNombreUsuario(String usuario) {
	
		return usuario.length() >= 3 && !usuario.contains(" ");
	}

	// Debe contener 6 caracteres, no puede tener espacios, y debe contener minimo
	// un caracter especial y una mayuscula
	public boolean validarContrasena(String contrasena) {

		return contrasena.length() >= 6 && !contrasena.contains(" ") && contrasena.matches(".*[!@#$%^&*].*") && contrasena.matches(".*[A-Z].*");
	}

	// Leer los usuarios del archivo
	public ArrayList<UsuarioBase> usuarios() {

		ArrayList<UsuarioBase> usuariosDefecto = new ArrayList<>();
		
		String linea = null;

		try (BufferedReader br = new BufferedReader(
				new FileReader(Constantes.RUTA_USUARIOS))) {

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
			GestionLogs.errorLogs(
					"Error al leer el archivo: " + e.getMessage() + " No se han cargado los usuarios por defecto. " + linea);
		}

		return usuariosDefecto;
	}

	/*---------------------------------------------------------------------------------------------------------*/

	// Recomendar una tarea al alumno
	public Tarea recomendarTarea(Alumno alumno) {

		double nota = alumno.getNota();
		String tipoTarea;

		if (nota >= 9.0) {
			tipoTarea = "Avanzada";
		} else if (nota >= 7.0) {
			tipoTarea = "Intermedia";
		} else {
			tipoTarea = "Basica";
		}

		// Buscar la primera tarea del tipo recomendado
		for (Tarea tarea : listaDeTareas) {
			if (tarea.getTipo().equals(tipoTarea)) {
				alumno.agregarTarea(tarea);
				return tarea;

			}
		}

		// Si no hay tareas del tipo que se recomienda, busca la primera tarea de
		// cualquier tipo
		if (!listaDeTareas.isEmpty()) {
			Tarea tareaRecomendada = listaDeTareas.get(0);
			alumno.agregarTarea(tareaRecomendada);
			return listaDeTareas.get(0);
		}

		return null;
	}

	// Ver estadisticas de los alumnos
	public void verEstadisticas(ArrayList<UsuarioBase> usuarios) {

		ArrayList<Alumno> alumnos = obtenerAlumnos(usuarios);

		if (alumnos.isEmpty()) {
			System.err.println("No hay alumnos para mostrar estadisticas.");
			return;
		}

		double sumaNotas = 0;
		double notaMaxima = Double.MIN_VALUE;
		double notaMinima = Double.MAX_VALUE;

		for (Alumno alumno : alumnos) {
			double nota = alumno.getNota();
			sumaNotas += nota;

			if (nota > notaMaxima) {
				notaMaxima = nota;
			}

			if (nota < notaMinima) {
				notaMinima = nota;
			}
		}

		double promedio = sumaNotas / alumnos.size();

		System.out.println(Colores.ANSI_PURPLE + "Promedio de notas: " + promedio + Colores.ANSI_RESET);
		System.out.println(Colores.ANSI_GREEN + "Nota mas alta: " + notaMaxima + Colores.ANSI_RESET);
		System.out.println(Colores.ANSI_RED + "Nota mas baja: " + notaMinima + Colores.ANSI_RESET);
	}

	// Obtener los alumnos del ArrayList de usuarios
	public ArrayList<Alumno> obtenerAlumnos(ArrayList<UsuarioBase> usuarios) {

		ArrayList<Alumno> alumnos = new ArrayList<>();

		for (UsuarioBase usuario : usuarios) {
			if (usuario.getTipoUsuario().equals(Constantes.ALUMNO)) {
				alumnos.add((Alumno) usuario);
			}
		}

		return alumnos;

	}

	// Consultar la tarea pendiente del alumno
	public void consultarTareasPendientes(Alumno alumno) {

		ArrayList<Tarea> tareasAsignadas = alumno.getTareasAsignadas();

		if (tareasAsignadas.isEmpty()) {
			System.out.println(Colores.ANSI_GREEN + "No tienes tareas pendientes." + Colores.ANSI_RESET);
		} else {
			System.out.println("Tareas Pendientes:");
			for (Tarea tarea : tareasAsignadas) {
				System.out.println("Tipo: " + tarea.getTipo());

			}
		}
	}

	// Marcar la tarea actual del alumno como completada
	public void marcarTareaCompletada(Alumno alumno, Scanner sc) {
		ArrayList<Tarea> tareasAsignadas = alumno.getTareasAsignadas();

		if (tareasAsignadas.isEmpty()) {
			System.out.println(
					Colores.ANSI_GREEN + "No tienes tareas pendientes para entregar." + Colores.ANSI_RESET);
		} else {
			System.out.println("Tareas Pendientes:");
			for (int i = 0; i < tareasAsignadas.size(); i++) {
				System.out.println((i + 1) + ". Tipo: " + tareasAsignadas.get(i).getTipo());
			}

			System.out.print("Seleccione el numero de la tarea que va a entregar: ");
			int numeroTarea = sc.nextInt();
			sc.nextLine(); // Si no pongo esto, el scanner no lee bien el siguiente string

			marcarTareaCompletada(alumno, numeroTarea - 1);
		}
	}

	public void marcarTareaCompletada(Alumno alumno, int indiceTarea) {
		ArrayList<Tarea> tareasAsignadas = alumno.getTareasAsignadas();

		if (indiceTarea >= 0 && indiceTarea < tareasAsignadas.size()) {
			Tarea tareaEntregada = tareasAsignadas.remove(indiceTarea);
			System.out.println(Colores.ANSI_GREEN + "Tarea \"" + tareaEntregada.getTipo()
					+ "\" entregada correctamente." + Colores.ANSI_RESET);
		} else {
			System.err.println(Constantes.NUM_TAREA_VALID);
			GestionLogs.errorLogs(Constantes.NUM_TAREA_VALID + Constantes.NUM_SELEC + indiceTarea);
		}
	}

	// Ver las notas de los alumnos
	public void verNotasAlumnos(ArrayList<UsuarioBase> usuarios) {

		ArrayList<Alumno> alumnos = obtenerAlumnos(usuarios);

		for (Alumno alumno : alumnos) {
			System.out.println("Nombre: " + alumno.getNombre() + " / Nota: " + alumno.getNota());
		}

	}

	// Modificar la nota de un alumno y en el archivo
	public void modificarNotaAlumno(Scanner sc, ArrayList<UsuarioBase> usuarios) {

		ArrayList<Alumno> alumnos = obtenerAlumnos(usuarios);

		System.out.println("Lista de alumnos:");
		for (int i = 0; i < alumnos.size(); i++) {
			System.out.println((i + 1) + ". " + alumnos.get(i).getNombre());
		}

		System.out.print("Introduzca el numero del alumno a modificar: ");
		int numeroAlumno = sc.nextInt();
		sc.nextLine(); // Si no pongo esto, el scanner no lee bien el siguiente string

		if (numeroAlumno >= 1 && numeroAlumno <= alumnos.size()) {
			System.out.print("Introduzca la nueva nota del alumno: ");
			double nuevaNota = sc.nextDouble();
			sc.nextLine(); // Si no pongo esto, el scanner no lee bien el siguiente string

			alumnos.get(numeroAlumno - 1).setNota(nuevaNota);
			System.out.println(Colores.ANSI_GREEN + "Nota modificada correctamente." + Colores.ANSI_RESET);

			modificarNotaAlumnoArchivo(nuevaNota, alumnos.get(numeroAlumno - 1));

		} else {
			System.err.println("Numero de alumno no valido.");
			GestionLogs.errorLogs("Numero de alumno no valido." + Constantes.NUM_SELEC + numeroAlumno);
		}

	}

	// Modificar la nota de un alumno en el archivo
	public void modificarNotaAlumnoArchivo(double nuevaNota, Alumno alumno) {

		ArrayList<UsuarioBase> usuarios = usuarios();

		try (FileWriter fw = new FileWriter("src/main/java/com/daw/proyectoescolar/repositorio/usuarios.csv")) {

			for (UsuarioBase usuario : usuarios) {
				if (usuario.getNombre().equals(alumno.getNombre())) {
					((Alumno) usuario).setNota(nuevaNota);
				}

				// Cuando se detecta un alumno, se escribe en el archivo con su nota del array
				fw.write(usuario.getTipoUsuario() + ";" + usuario.getNombre() + ";" + usuario.getContrasena() + ";"
						+ usuario.getDni()
						+ (usuario.getTipoUsuario().equals("Alumno") ? ";" + ((Alumno) usuario).getNota() : "")
						+ "\n");
			}

			fw.flush();
			fw.close();

		} catch (IOException e) {
			System.err.println(Constantes.ERROR_ARCHIVO + e.getMessage());
			GestionLogs.errorLogs(Constantes.ERROR_ARCHIVO + e.getMessage());
		}

	}

	// Ver las tareas disponibles
	public void verTareasDisponibles() {

		if (listaDeTareas.isEmpty()) {
			System.out.println(Colores.ANSI_GREEN + "No hay tareas disponibles." + Colores.ANSI_RESET);
		} else {
			System.out.println("Tareas Disponibles:");
			for (Tarea tarea : listaDeTareas) {
				System.out.println("Tipo: " + tarea.getTipo());
			}
		}
	}

	// Agregar una nueva tarea del tipo que se quiera
	public void agregarNuevaTarea(Scanner sc) {

		System.out.print("Introduzca el tipo de la nueva tarea: ");
		String tipoTarea = sc.nextLine();

		Tarea nuevaTarea = new Tarea(tipoTarea);
		listaDeTareas.add(nuevaTarea);

		System.out.println(Colores.ANSI_GREEN + "Nueva tarea \"" + tipoTarea + "\" agregada correctamente."
				+ Colores.ANSI_RESET);

	}

	// Modificar el tipo de una tarea
	public void modificarTarea(Scanner sc) {

		System.out.println("Lista de tareas:");
		for (int i = 0; i < listaDeTareas.size(); i++) {
			System.out.println((i + 1) + ". " + listaDeTareas.get(i).getTipo());
		}

		System.out.print("Introduzca el numero de la tarea a modificar: ");
		int numeroTarea = sc.nextInt();
		sc.nextLine(); // Si no pongo esto, el scanner no lee bien el siguiente string

		if (numeroTarea >= 1 && numeroTarea <= listaDeTareas.size()) {
			System.out.print("Introduzca el nuevo tipo de la tarea: ");
			String nuevoTipo = sc.nextLine();
			listaDeTareas.get(numeroTarea - 1).setTipo(nuevoTipo);
			System.out.println(Colores.ANSI_GREEN + "Tarea modificada correctamente." + Colores.ANSI_RESET);
		} else {
			System.err.println(Constantes.NUM_TAREA_VALID);
			GestionLogs.errorLogs(Constantes.NUM_TAREA_VALID + Constantes.NUM_SELEC + numeroTarea);
		}

	}

	// Recomendar una tarea al alumno y mostrarla
	public void recomendarTareaYMostrar(Alumno alumno) {

		Tarea tareaRecomendada = recomendarTarea(alumno);
		tareaRecomendada.mostrarRecomendacion();

	}

}