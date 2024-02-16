package com.daw.proyectoescolar.entidades;

import java.util.Scanner;

import com.daw.proyectoescolar.repositorio.Colores;
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

    @Override
    public void verMenu() { throw new UnsupportedOperationException(Colores.ANSI_RED + "Menu no implementado" + Colores.ANSI_RESET);

        
    }
}