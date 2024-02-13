package com.daw.proyectoescolar.servicios.tareas;

import java.util.Scanner;

public class ConFunciones {

		static Scanner sc = new Scanner(System.in);
		
		public static void main(String[] args) {
			System.out.println("Identifícate");
			System.out.println("Indica si eres profesor o alumno");
			System.out.println("Usuario: ");
			String usuario= sc.nextLine();
			String[] alumnos = {"Rafael", "Lola", "Rosario", "Ismael", "María", "Victoria"};
			
			
			String [] contraseñaAlumno2= {"Rafael", "Lola", "Rosario", "Ismael", "María", "Victoria"};
			
			
			double[][] notas = {
		            {7.5, 8.0, 6.5, 7.0, 9.0, 8.5, 4.0, 8.0},  // notas del alumno 1 
		            {6.0, 7.5, 8.0, 7.0, 7.5, 6.5, 8.5, 7.0},  // notas del alumno 2 
		            {8.0, 7.0, 7.5, 7.7, 6.5, 7.0, 8.0, 9.0},  // notas del alumno 3 
		            {7.5, 7.0, 7.0, 7.5, 8.0, 6.0, 8.5, 7.5},  // notas del alumno 4 
		            {8.5, 8.0, 9.0, 8.0, 8.5, 7.0, 9.0, 8.5},  // notas del alumno 5 
		            {7.0, 6.5, 7.5, 7.0, 8.0, 6.5, 7.5, 7.0}   // notas del alumno 6 
		        };
			
			switch(usuario) {
			
			case "profesor":
				System.out.println("Contraseña");
				String contraseñaProfesor= sc.nextLine();
				if(!contraseñaProfesor.equals("contraseña_profesor")) {
		    		System.out.println("Error. Inténtelo de nuevo");
		    		return;
		    		
				}else {
					System.out.println("Bienvenido Profesor");
					System.out.println("Estos son los temas disponibles");
					for(int i=1; i<=8; i++ ) {
						System.out.println(i + ". Tema " + i);
					}
				}
				System.out.println("Seleccione el número del Tema");
				int tema=sc.nextInt();
				sc.nextLine();
				
				System.out.println("Ingrese el nombre del alumno");
				
				for(int i=0;i<alumnos.length;i++) {
					System.out.println(alumnos[i]);
					}
				
				
				String [] array = {"Estas aprobado, enhorabuena. Debes repasar la tarea","Tarea aprobada con éxito", "Estas suspenso, sigue repasando"};
				
				String nombreAlumno= sc.nextLine();
				int posicionAlumno = -1;
				for (int i = 0; i < alumnos.length; i++) {
					if (nombreAlumno.equals(alumnos[i])) {
						posicionAlumno = i;
					}
				}
					
			     System.out.print("Alumno " + (nombreAlumno) + ": ");
	        
	                System.out.print(notas[posicionAlumno][tema] + " ");
	                
	            
	            if(notas[posicionAlumno][tema]<5) {
					System.out.println(array[2]);
				}else if(notas[posicionAlumno][tema]==5|| notas[posicionAlumno][tema]<=7) {
					System.out.println(array[0]);
				}else if(notas[posicionAlumno][tema]>=8) {
					System.out.println(array [1]);
				}else {
					System.out.println("Número introducido no válido");
				}
	            
		      break;
			
			
			
			case "alumno":
				System.out.println("Indique el usuario del alumno");
				
				for(int i=0;i<alumnos.length;i++) {
					System.out.println(alumnos[i]);
					}
				String nombreUsuarioAlumno= sc.nextLine();
			

			    boolean encontrado = false;
		       String contraseñaAlumno = "";
		        for (int i = 0; i < alumnos.length; i++) {
		            if (nombreUsuarioAlumno.equals(alumnos[i])) {
		                encontrado = true;
		                contraseñaAlumno = contraseñaAlumno2[i];
		                break;
		            }
		        }

		        if (encontrado) {
		            System.out.println("Ingrese la contraseña:");
		            String contraseñaIngresada = sc.nextLine();

		            if (contraseñaIngresada.equals(contraseñaAlumno)) {
		                System.out.println("Contraseña correcta. Bienvenido. "+ nombreUsuarioAlumno);
			            return;
			        }else {
			            System.out.println("Usuario no encontrado.");
			        }
		        }else {
		            	System.out.println("Usuario no encontrado");
		            }
				
		        System.out.println("Elige el número del tema de los siguientes disponibles; ");
		        for(int i=1; i<=8; i++ ) {
					System.out.println(i + ". Tema " + i);
				}
		        tema= sc.nextInt();
		        
		        System.out.print("Alumno " + (nombreUsuarioAlumno) + ": ");
		        posicionAlumno = -1;
				for (int i = 0; i < alumnos.length; i++) {
					if (nombreUsuarioAlumno.equals(alumnos[i])) {
						posicionAlumno = i;
					}
				}
	            System.out.print(notas[posicionAlumno][tema] + " ");
	           
	      array = new String[] {"Estas aprobado, enhorabuena. Debes repasar la tarea","Tarea aprobada con éxito", "Estas suspenso, sigue repasando"};
				
	        
	        if(notas[posicionAlumno][tema]<5) {
				System.out.println(array[2]);
			}else if(notas[posicionAlumno][tema]==5|| notas[posicionAlumno][tema]<=7) {
				System.out.println(array[0]);
			}else if(notas[posicionAlumno][tema]>=8) {
				System.out.println(array [1]);
			}else {
				System.out.println("Número introducido no válido");
			}
	        
	      break;
			
			
			}
		}
	
		


	}

	public static void profesor() {
		
	}


