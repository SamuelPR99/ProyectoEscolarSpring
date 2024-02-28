package com.daw.proyectoescolar.entidades;

import java.util.ArrayList;
import java.util.Scanner;

import com.daw.proyectoescolar.repositorio.Colores;
import com.daw.proyectoescolar.servicios.gestionusuarios.GestionadorUsuarios;

public class Administrador extends UsuarioBase {
    
    // Constructores

    public Administrador() {

    }
    
    public Administrador(String nombre, String contraseña) {
        super(nombre, contraseña);
    }
    
    public Administrador(String nombre, String contraseña, String dni) {
        super(nombre, contraseña, dni);
    }
    
    // Getters y setters
    
    // Métodos
    
    @Override
    public String getTipoUsuario() {
        return "Administrador";
    }

    // Menu administrador
    @Override
    public void verMenu(Scanner sc, ArrayList<UsuarioBase> usuarios, ArrayList<Alumno> alumnos) {
    	
    	GestionadorUsuarios gestor = new GestionadorUsuarios();
        String opcion;

        do {
        	
            System.out.println(Colores.ANSI_YELLOW + "\nSeleccione una opción:\n"
                    + "1. Mostrar usuarios registrados\n"
                    + "2. Crear un usuario de forma manual\n"
                    + "3. Borrar un usuario\n"
                    + "4. Cambiar contraseña\n"
                    + "5. lo que sea5\n"
                    + "6. Salir del menú" + Colores.ANSI_RESET);
            
            opcion = sc.nextLine().toLowerCase();

            switch (opcion) {
                case "1", "mostrar usuarios registrados":
                    gestor.mostrarUsuarios(usuarios);
                    break;

                case "2", "crear un usuario de forma manual":
                    gestor.registro(sc, usuarios);
                    break;

                case "3", "borrar un usuario":
                    gestor.borrarUsuario(sc, usuarios);
                    break;

                case "4", "cambiar contraseña":
                    gestor.cambiarContraseña(sc, this);
                    break;

                case "5", "lo que sea5":
                	
                    break;

                case "6", "salir del menú":
                    System.out.println(Colores.ANSI_BOLD + "Saliendo del menú de administrador..." + Colores.ANSI_RESET);
                    break;

                default:
                    System.err.println("Opción no válida. Por favor, elige una opción válida.");
            }
            
        } while (!opcion.equals("6") && !opcion.equals("salir del menú") && !opcion.equals("salir"));
    }
}
