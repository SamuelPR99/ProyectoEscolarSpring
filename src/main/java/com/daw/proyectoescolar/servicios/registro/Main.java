package com.daw.proyectoescolar.servicios.registro;

import java.util.Scanner;

public class Main {
    private static final Usuario String = null;

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestionUsuarios gestionUsuarios = new GestionUsuarios();
        Usuario usuario = new Usuario();
        
        int opcion;
        boolean salir = false;

        do {
            System.out.println("1. Crear usuario");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Cambiar contraseña");
            System.out.println("4. Mostrar usuarios registrados");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 
            

            switch (opcion) {
                case 1:
                	gestionUsuarios.crearUsuario(scanner);
                    break;
                case 2:
                	gestionUsuarios.iniciarSesion(scanner);
                    break;
                case 3:
                	gestionUsuarios.cambiarContrasena(usuario, scanner);
                    break;
                case 4:
                	gestionUsuarios.mostrarUsuariosRegistrados();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        } while (!salir);

        System.out.println("¡Saliste del programa!");
        scanner.close();
    }
}

