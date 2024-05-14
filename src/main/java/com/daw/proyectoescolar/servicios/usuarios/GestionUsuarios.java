package com.daw.proyectoescolar.servicios.usuarios;

import java.util.ArrayList;
import java.util.Scanner;

import com.daw.proyectoescolar.entidades.Alumno;
import com.daw.proyectoescolar.entidades.Profesor;
import com.daw.proyectoescolar.entidades.UsuarioBase;
import com.daw.proyectoescolar.repositorio.Colores;
import com.daw.proyectoescolar.repositorio.Constantes;
import com.daw.proyectoescolar.repositorio.TemasRepo;
import com.daw.proyectoescolar.repositorio.UsuariosRepo;
import com.daw.proyectoescolar.servicios.incidencias.GestionIncidencias;
import com.daw.proyectoescolar.servicios.logs.GestionLogs;

public class GestionUsuarios {

	private UsuariosRepo uRepo = new UsuariosRepo();

	// Constructor vacio
	public GestionUsuarios() {
	}
	
	// Metodos
	
	// Obtener el ArrayList de usuarios
	public ArrayList<UsuarioBase> obtenerUsuarios() {
		return uRepo.usuariosBBDD();
	}

	// Iniciar el menu principal
	public void iniciar(Scanner sc) {

		inicializarBBDD();

		ArrayList<UsuarioBase> usuarios = obtenerUsuarios();
		String opcion;

		System.out.println(Colores.ANSI_UNDERLINE + Colores.ANSI_BOLD + Colores.ANSI_BLUE_BACKGROUND + "Bienvenido al"
				+ Colores.ANSI_YELLOW_BACKGROUND + " Campus Virtual " + Colores.ANSI_BLUE_BACKGROUND
				+ " del IES Murcia." + Colores.ANSI_RESET);

		do {

			System.out.println(Colores.ANSI_YELLOW + Colores.ANSI_UNDERLINE + "\nSeleccione una opcion:\n"
					+ Colores.ANSI_RESET + Colores.ANSI_YELLOW + "1. Iniciar sesion\n" + "2. Gestion de incidencias\n"
					+ "3. Salir" + Colores.ANSI_RESET);

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
				GestionIncidencias gestionadorIncidencias = new GestionIncidencias();
				gestionadorIncidencias.menuPrincipal(sc);
				break;

			case "3", "salir":
				GestionLogs.logOpcionMenu(Constantes.MENU_PRINCIPAL, "Salir");
				System.out.println("Hasta luego. " + Colores.ANSI_GREEN + "(⌐■_■)" + Colores.ANSI_RESET);
				System.exit(0);
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
	public void registro(String nombre, String dni, String contrasena, String tipo, ArrayList<UsuarioBase> usuarios) {

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
		uRepo.registro(nuevoUsuario);
		// Insertar el usuario en la base de datos
		uRepo.insertarUsuario(nuevoUsuario); 

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

		uRepo.borrarUsuario(nombre);

		// Si el usuario no se encuentra
		System.err.println("Usuario no encontrado.");
		GestionLogs.errorLogs("Usuario no encontrado." + " Nombre: " + nombre);

	}

	// Mostrar los usuarios registrados
	public void mostrarUsuarios(ArrayList<UsuarioBase> usuarios) {

		for (UsuarioBase usuario : usuarios) {
			System.out.println("\nNombre: " + usuario.getNombre() + "\nTipo: " + usuario.getTipoUsuario() + "\nDNI: "
					+ usuario.getDni() + "\nContraseña: " + usuario.getContrasena());
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
		uRepo.cambiarContrasena(usuario);

	}

	public void cambiarContrasena(String nuevaContrasena, UsuarioBase usuario) {

		usuario.setContrasena(nuevaContrasena);
		System.out.println(Colores.ANSI_GREEN + "Contraseña cambiada correctamente." + Colores.ANSI_RESET);

		uRepo.cambiarContrasena(nuevaContrasena, usuario);

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

		return contrasena.length() >= 6 && !contrasena.contains(" ") && contrasena.matches(".*[!@#$%^&*].*")
				&& contrasena.matches(".*[A-Z].*");
	}
	
	public void inicializarBBDD() {
		
		if (!uRepo.comprobarDatos()) { // si la bbdd esta vacia
			uRepo.insertarUsuariosArchivoBBDD(); // cargar los datos de los usuarios
		}
		
		TemasRepo tRepo = new TemasRepo();
		
		if (!tRepo.comprobarDatos()) {
			tRepo.insertarTemasYTareasBBDD();
		}
		
		/*
		 * IncidenciasRepo iRepo = new IncidenciasRepo();
		 * if (!iRepo.comprobarDatos()) {
		 * }
		 */
			
		
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

			uRepo.modificarNotaAlumno(nuevaNota, alumnos.get(numeroAlumno - 1));

		} else {
			System.err.println("Numero de alumno no valido.");
			GestionLogs.errorLogs("Numero de alumno no valido." + Constantes.NUM_SELEC + numeroAlumno);
		}

	}

}