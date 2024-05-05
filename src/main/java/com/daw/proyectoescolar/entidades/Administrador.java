package com.daw.proyectoescolar.entidades;

import java.util.ArrayList;
import java.util.Scanner;

import com.daw.proyectoescolar.logs.GestionLogs;
import com.daw.proyectoescolar.repositorio.Colores;
import com.daw.proyectoescolar.repositorio.Constantes;
import com.daw.proyectoescolar.servicios.usuarios.GestionUsuarios;

public class Administrador extends UsuarioBase {
    
    // Constructores

    public Administrador() {

    }
    
    public Administrador(String nombre, String contrasena) {
        super(nombre, contrasena);
    }
    
    public Administrador(String nombre, String contrasena, String dni) {
        super(nombre, contrasena, dni);
    }
    
    // Getters y setters
    
    // Metodos
    
    @Override
    public String getTipoUsuario() {
        return Constantes.ADMINISTRADOR;
    }
    
    
    
    // Menu administrador
    @Override
    public void verMenu(Scanner sc, ArrayList<UsuarioBase> usuarios, ArrayList<Alumno> alumnos) {
    	
    	GestionUsuarios gestor = new GestionUsuarios();
   
        String opcion;

        do {
        	
            System.out.println(Colores.ANSI_YELLOW + Colores.ANSI_UNDERLINE +"\nSeleccione una opcion:\n" + Colores.ANSI_RESET + Colores.ANSI_YELLOW
                    + "1. Mostrar usuarios registrados\n"
                    + "2. Crear un usuario\n"
                    + "3. Borrar un usuario\n"
                    + "4. Cambiar contraseña\n"
                    + "5. Salir del menu" + Colores.ANSI_RESET);
            
            opcion = sc.nextLine().toLowerCase();

            switch (opcion) {
            
                case "1", "mostrar usuarios registrados":
                    GestionLogs.logOpcionMenu(Constantes.MENU_ADMINISTRADORES, "Mostrar usuarios registrados");
                    gestor.mostrarUsuarios(usuarios);
                    break;

                case "2", "crear un usuario":
                    GestionLogs.logOpcionMenu(Constantes.MENU_ADMINISTRADORES, "Crear un usuario");
                    gestor.registro(sc, usuarios);
                    break;

                case "3", "borrar un usuario":
                    GestionLogs.logOpcionMenu(Constantes.MENU_ADMINISTRADORES, "Borrar un usuario");
                    gestor.borrarUsuario(sc, usuarios);
                    break;

                case "4", "cambiar contraseña":
                    GestionLogs.logOpcionMenu(Constantes.MENU_ADMINISTRADORES, "Cambiar contraseña");
                    gestor.cambiarContrasena(sc, this);
                    break;

                case "5", "salir del menu", "salir", "salir del":
                    GestionLogs.logOpcionMenu(Constantes.MENU_ADMINISTRADORES, "Salir del menu");
                    System.out.println(Colores.ANSI_BOLD + "Saliendo del menu de administrador..." + Colores.ANSI_RESET);
                    break;

                default:
                    System.err.println("Opcion no valida. Por favor, elige una opción valida.");
                    GestionLogs.errorLogs("Opcion no valida seleccionada en el menu de administrador. " + opcion + " no es una opcion valida.");

            }
            
        } while (!opcion.equals("5") && !opcion.contains("salir"));
    }

}
