package com.daw.proyectoescolar.servicios.registro;

import java.util.ArrayList;
import java.util.Scanner;

public class GestionUsuarios {
    private ArrayList<Usuario> usuarios;

    public GestionUsuarios() {
        this.usuarios = new ArrayList<>();
    }

    public void crearUsuario(Scanner scanner) {
        System.out.print("Ingresa un nombre de usuario (mínimo 3 caracteres): ");
        String nuevoUsuario = scanner.nextLine();

        if (validarNombreUsuario(nuevoUsuario)) {
            System.out.print("Ingresa una contraseña (mínimo 6 caracteres, al menos 1 mayúscula y 1 carácter especial, sin espacios): ");
            String nuevaContrasena = scanner.nextLine();

            if (validarContrasena(nuevaContrasena)) {
                usuarios.add(new Usuario(nuevoUsuario, nuevaContrasena));
                System.out.println("Usuario creado con éxito.");
            } else {
                System.out.println("Contraseña no cumple con los requisitos. Inténtalo de nuevo.");
            }
        } else {
            System.out.println("Nombre de usuario no cumple con los requisitos. Inténtalo de nuevo.");
        }
    }

    public void iniciarSesion(Scanner scanner) {
        System.out.print("Ingresa tu nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();

        Usuario usuario = buscarUsuario(nombreUsuario);

        if (usuario != null) {
            System.out.print("Ingresa tu contraseña: ");
            String contrasena = scanner.nextLine();

            if (usuario.getContrasena().equals(contrasena)) {
                System.out.println("Inicio de sesión exitoso. ¡Bienvenido!");
            } else {
                System.out.println("Contraseña incorrecta. ¿Deseas cambiarla? (S/N)");
                String opcion = scanner.nextLine();
                if (opcion.equalsIgnoreCase("S")) {
                    cambiarContrasena(usuario, scanner);
                }
            }
        } else {
            System.out.println("Usuario no encontrado. ¿Quieres crear uno nuevo? (S/N)");
            String opcion = scanner.nextLine();
            if (opcion.equalsIgnoreCase("S")) {
                crearUsuario(scanner);
            }
        }
    }

    public void cambiarContrasena(Usuario usuario, Scanner scanner) {

        System.out.print("Ingresa tu nueva contraseña: ");
        String nuevaContrasena = scanner.nextLine();

        if (validarContrasena(nuevaContrasena)) {
            usuario.setContrasena(nuevaContrasena);
            System.out.println("Contraseña cambiada con éxito.");
        } else {
            System.out.println("La nueva contraseña no cumple con los requisitos, debe tener al menos 6 caracteres, incluir al menos 1 mayúscula y 1 carácter especial. Inténtalo de nuevo.");
        }
    }

    public void mostrarUsuariosRegistrados() {
        System.out.println("Usuarios registrados:");

        if (!usuarios.isEmpty()) {
            for (Usuario usuario : usuarios) {
                System.out.println("- " + usuario.getNombreUsuario());
            }
        } else {
            System.out.println("No hay usuarios registrados.");
        }
    }

    private boolean validarNombreUsuario(String usuario) {
        usuario = usuario.trim();

        if (usuario.length() >= 3) {
            return true;
        } else {
            System.out.println("Error: El nombre de usuario debe tener al menos 3 caracteres. Inténtalo de nuevo.");
            return false;
        }
    }

    private boolean validarContrasena(String contrasena) {
        if (contrasena.length() >= 6 && !contrasena.contains(" ")) {
            boolean tieneMayuscula = false;
            boolean tieneEspecial = false;

            for (int i = 0; i < contrasena.length(); i++) {
                char c = contrasena.charAt(i);

                if ((c >= 65 && c <= 90) || (c >= 192 && c <= 223)) {
                    tieneMayuscula = true;
                }

                if ((c >= 33 && c <= 47) || (c >= 58 && c <= 64) || (c >= 91 && c <= 96) || (c >= 123 && c <= 126)) {
                    tieneEspecial = true;
                }

                if (tieneMayuscula && tieneEspecial) {
                    break;
                }
            }

            if (tieneMayuscula && tieneEspecial) {
                return true;
            } else {
                System.out.println("Error: La contraseña debe tener al menos 6 caracteres, incluir al menos 1 mayúscula y 1 carácter especial. Inténtalo de nuevo.");
                return false;
            }
        } else {
            System.out.println("Error: La contraseña debe tener al menos 6 caracteres y no debe contener espacios. Inténtalo de nuevo.");
            return false;
        }
    }

    private Usuario buscarUsuario(String nombreUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                return usuario;
            }
        }
        return null;
    }
}
