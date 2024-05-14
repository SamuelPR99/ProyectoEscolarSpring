package com.daw.proyectoescolar.entidades;

import java.util.ArrayList;
import java.util.Scanner;

import com.daw.proyectoescolar.repositorio.Colores;
import com.daw.proyectoescolar.repositorio.Constantes;
import com.daw.proyectoescolar.servicios.logs.GestionLogs;
import com.daw.proyectoescolar.servicios.temas.GestionTemas;
import com.daw.proyectoescolar.servicios.usuarios.GestionUsuarios;

public class Alumno extends UsuarioBase {

	// Atributos
	protected double nota;
	protected ArrayList<Tarea> tareasAsignadas = new ArrayList<>();

	// Constructores
	public Alumno() {

	}

	public Alumno(String nombre, String contrasena, double nota) {
		super(nombre, contrasena);
		this.nota = nota;
	}

	public Alumno(String nombre, String contrasena, String dni) {
		super(nombre, contrasena, dni);
	}

	public Alumno(String nombre, String contrasena, String dni, double nota) {
		super(nombre, contrasena, dni);
		this.nota = nota;
	}

	public Alumno(int usuarioId, String nombre, String contrasena, String dni, double nota) {
		super(usuarioId, nombre, contrasena, dni);
		this.nota = nota;
	}

	public Alumno(int usuarioId, String nombre, String contrasena, String dni) {
		super(usuarioId, nombre, contrasena, dni);
	}

	// Getters y setters
	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public ArrayList<Tarea> getTareasAsignadas() {
		return tareasAsignadas;
	}

	public void setTareasAsignadas(ArrayList<Tarea> tareasAsignadas) {
		this.tareasAsignadas = tareasAsignadas;

		// Metodos

	}

	public void agregarTarea(Tarea tarea) {
		tareasAsignadas.add(tarea);
	}

	public void eliminarTarea(Tarea tarea) {
		tareasAsignadas.remove(tarea);
	}

	@Override
	public String getTipoUsuario() {
		return Constantes.ALUMNO;
	}

	@Override
	public void verMenu(Scanner sc, ArrayList<UsuarioBase> usuarios, ArrayList<Alumno> alumnos) {

		GestionUsuarios gestor = new GestionUsuarios();
		GestionTemas gestorTemas = new GestionTemas();

		String opcion;

		do {

			System.out.println(Colores.ANSI_YELLOW + Colores.ANSI_UNDERLINE + "\nSeleccione una opcion:\n"
					+ Colores.ANSI_RESET + Colores.ANSI_YELLOW + "1. Ver nota\n" + "2. Recomendar tarea\n"
					+ "3. Entregar tarea\n" + "4. Cambiar contraseña\n" + "5. Salir del menu" + Colores.ANSI_RESET);

			opcion = sc.nextLine().toLowerCase();

			switch (opcion) {

			case "1", "ver nota":
				GestionLogs.logOpcionMenu(Constantes.MENU_ALUMNOS, "Ver nota");
				System.out.println("Nota actual: " + getNota());
				break;

			case "2", "recomendar tarea":
				GestionLogs.logOpcionMenu(Constantes.MENU_ALUMNOS, "Recomendar tarea");
				gestorTemas.recomendarTareaYMostrar(this);
				break;

			case "3", "entregar tarea":
				GestionLogs.logOpcionMenu(Constantes.MENU_ALUMNOS, "Entregar tarea bbdd");
				gestorTemas.marcarTareaCompletadaBBDD(sc, this.usuarioId);
				break;

			case "4", "Cambiar contraseña":
				GestionLogs.logOpcionMenu(Constantes.MENU_ALUMNOS, "Cambiar contraseña");
				gestor.cambiarContrasena(sc, this);
				break;

			case "5", "salir del menu", "salir", "salir del":
				GestionLogs.logOpcionMenu(Constantes.MENU_ALUMNOS, "Salir del menu");
				System.out.println(Colores.ANSI_BOLD + "Saliendo del menu de alumno..." + Colores.ANSI_RESET);
				break;

			default:
				System.err.println("Opcion no valida. Por favor, elige una opcion valida.");
				GestionLogs.errorLogs("Opcion no valida en el menu de alumno. " + opcion + " no es una opcion valida.");
			}

		} while (!opcion.equals("5") && !opcion.contains("salir"));
	}

}
