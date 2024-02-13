package com.daw.proyectoescolar.servicios.tareas;

import java.util.Scanner;

public class ConFunciones {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[]args) {
    	 System.out.println("Identifícate");
         System.out.println("Indica si eres profesor o alumno");
         System.out.println("Usuario: ");
         String usuario = sc.nextLine();


         String[] alumnos = {"Rafael", "Lola", "Rosario", "Ismael", "María", "Victoria"};
         String[] contraseñaAlumno2 = {"Rafael", "Lola", "Rosario", "Ismael", "María", "Victoria"};

         double[][] notas = {
             {7.5, 8.0, 6.5, 7.0, 9.0, 8.5, 4.0, 8.0},  // notas del alumno 1 
             {6.0, 7.5, 8.0, 7.0, 7.5, 6.5, 8.5, 7.0},  // notas del alumno 2 
             {8.0, 7.0, 7.5, 7.7, 6.5, 7.0, 8.0, 9.0},  // notas del alumno 3 
             {7.5, 7.0, 7.0, 7.5, 8.0, 6.0, 8.5, 7.5},  // notas del alumno 4 
             {8.5, 8.0, 9.0, 8.0, 8.5, 7.0, 9.0, 8.5},  // notas del alumno 5 
             {7.0, 6.5, 7.5, 7.0, 8.0, 6.5, 7.5, 7.0}   // notas del alumno 6 
         };
         
         switch (usuario) {
             case "profesor":
                 profesor(alumnos, contraseñaAlumno2, notas);
                 break;
             case "alumno":
                 alumno(alumnos, contraseñaAlumno2, notas);
                 break;
             default:
                 System.out.println("Opción no válida");
         }
    }
    
    public static String login(String alumno) {
    	
    }

    public static void profesor(String[] alumnos, String[] contraseñaAlumno2, double[][] notas) {
        System.out.println("Contraseña");
        String contraseñaProfesor = sc.nextLine();

        if (!contraseñaProfesor.equals("contraseña_profesor")) {
            System.out.println("Error. Inténtelo de nuevo");
            return;
        }

        System.out.println("Bienvenido Profesor");
        System.out.println("Estos son los temas disponibles");
        for (int i = 1; i <= 8; i++) {
            System.out.println(i + ". Tema " + i);
        }

        System.out.println("Seleccione el número del Tema");
        int tema = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingrese el nombre del alumno");
        for (String alumno : alumnos) {
            System.out.println(alumno);
        }

        String nombreAlumno = sc.nextLine();
        int posicionAlumno = -1;
        for (int i = 0; i < alumnos.length; i++) {
            if (nombreAlumno.equals(alumnos[i])) {
                posicionAlumno = i;
            }
        
        }
    }
   
    
    public static void alumno(String []alumnos, String[] contraseñaAlumno2,double[][] notas ) {
    	
    	
    	
    }
    
    public static void tema() {
    	
    }
    
    public static void tarea() {
    	
    }
 	public static void nota() {
    	
    }
    
}









