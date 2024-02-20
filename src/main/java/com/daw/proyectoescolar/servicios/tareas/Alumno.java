package com.daw.proyectoescolar.servicios.tareas;

import java.util.Scanner;

public class Alumno {

	//ATRIBUTOS
	protected String usuario;
	protected String contraseña;
	
	
	//CONSTRUCTORES
	public Alumno() {
	
	}

	public Alumno(String usuario, String contraseña) {
		this.usuario=usuario;
		this.contraseña=contraseña;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public void mostrarMenu() {
		Scanner sc= new Scanner(System.in);
		int opcion;
		do {
			System.out.println("Bienvenido alumn@");
			System.out.println("Introduce una opción");
			System.out.println("1.Listado de Temas");
			System.out.println("2.Salir");
			opcion=sc.nextInt();
			
			switch(opcion){
			case 1:
				System.out.println("Listado de Temas");
				
				break;
			case 2:
				 System.out.println("Saliendo...");
				 break;
			default:
				System.out.println("Opción no válida");
			}
		}while(opcion!=3);
		
		
		sc.close();
		
	}
	
}
