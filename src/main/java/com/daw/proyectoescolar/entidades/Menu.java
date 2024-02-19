package com.daw.proyectoescolar.entidades;

import java.util.Scanner;

public class Menu {

	protected Profesor profesor;
	protected Alumno alumno;
	
	static Scanner sc= new Scanner(System.in);
	public void main(String args[]) {
		
		System.out.println("Bienvenido, ¿eres profesor o alumno? \n"
		+" Ingresa 1 siendo profesor o 2 siendo alumno ");
		String respuesta= sc.nextLine();
		
		if(respuesta=="profesor") {
			new Profesor().mostrarMenu();
		}else if (respuesta=="alumno") {
			new Alumno().mostrarMenu();
		}else {
			System.out.println("Error de selección");
		}
		
		
	}
}
