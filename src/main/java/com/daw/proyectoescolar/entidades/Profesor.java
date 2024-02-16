package com.daw.proyectoescolar.entidades;

import java.util.Scanner;

import com.daw.proyectoescolar.repositorio.Colores;
import com.daw.proyectoescolar.servicios.recomendador.SistemaRecomendacion;
import com.daw.proyectoescolar.servicios.tareas.Tema;


public class Profesor extends UsuarioBase {
	static Scanner sc= new Scanner(System.in);
	// Atributos

    // Constructores

	public Profesor() {
		
	}
	public Profesor(String nombre, String contraseña) {
        super(nombre, contraseña);
    }
	

    // Getters y setters
	
	// Metodos
	
	public void mostrarMenu() {
		
		int opcion;
		
		do {
			System.out.println("Bienvenido Profesor");
			System.out.println("1. Ver Listado de Alumnos");
			System.out.println("2. Ver Listado de Temas");
			System.out.println("3. Salir");
			opcion=sc.nextInt();
		
			switch(opcion) {
			case 1:
				System.out.println("Lista alumnos");
				break;
			case 2:
				System.out.println("Lista temas y tareas");
				new Tema().mostrarTemas();
				new Tarea().mostrarTareas();
				break;
			case 3:
				System.out.println("Salir");
				break;
				
			default:
				System.out.println("Opción no válida");
			}
		}while(opcion!=3);
		
	}
	
	

    @Override
    public String getTipoUsuario() {
        return "profesor";
    }

   
    // Menu profesor
    @Override
    public void verMenu(Scanner sc) {
        SistemaRecomendacion sistema = new SistemaRecomendacion();
        boolean salir = false;

        while (!salir) {
            System.out.println(Colores.ANSI_YELLOW + "\nSeleccione una opción:\n"
                    + "1. Ver notas de alumnos\n"
                    + "2. Modificar nota de alumno\n"
                    + "3. Ver estadísticas\n"
                    + "4. Agregar nueva tarea\n"
                    + "5. Modificar tarea\n"
                    + "6. Salir del menu" + Colores.ANSI_RESET);

            String opcion = sc.nextLine().toLowerCase();

            switch (opcion) {
                case "1", "ver notas de alumnos":
                    sistema.verNotasAlumnos();
                    break;

                case "2", "modificar nota de alumno":
                    sistema.modificarNotaAlumno(sc);
                    break;

                case "3", "ver estadísticas":
                    sistema.verEstadisticas();
                    break;

                case "4", "agregar nueva tarea":
                    sistema.agregarNuevaTarea(sc);
                    break;

                case "5", "modificar tarea":
                    sistema.modificarTarea(sc);
                    break;

                case "6", "salir del menú":
                    salir = true;
                    System.out.println(Colores.ANSI_BOLD + "Saliendo del menú de profesor..." + Colores.ANSI_RESET);
                    break;

                default:
                    System.err.println("Opción no válida. Por favor, elige una opción válida.");
            }
        }
    }
}