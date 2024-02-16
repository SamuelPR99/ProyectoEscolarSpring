package com.daw.proyectoescolar.servicios.tareas;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		System.out.println("Bienvenido, ¿eres alumno o profesor? 1 Profesor y 2 Alumno");
		Scanner sc= new Scanner(System.in);
		int opcion=sc.nextInt();
		
		if(opcion==1) {
			new Profesor().mostrarMenu();
		}else if(opcion==2) {	
		new Alumno().mostrarMenu();
		
		}else {
			System.out.println("Opción no válida");
		}
		sc.close();
	}

}
