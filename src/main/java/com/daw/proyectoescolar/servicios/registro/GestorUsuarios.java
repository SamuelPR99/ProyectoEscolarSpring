package com.daw.proyectoescolar.servicios.registro;

import java.util.Scanner;

public class GestorUsuarios {
    private String[][] usuarios;
    private int numUsuarios;
    private Scanner scanner;

    public GestorUsuarios() {
        usuarios = new String[100][3];
        numUsuarios = 0;
        scanner = new Scanner(System.in);
    }

    public void ejecutarGestionUsuarios() {
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
                    crearUsuario();
                    break;
                case 2:
                    iniciarSesion();
                    break;
                case 3:
                    cambiarContrasena();
                    break;
                case 4:
                    mostrarUsuariosRegistrados();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.err.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }

        } while (!salir);

        System.out.println("¡Saliste del programa!");
        scanner.close();
    }

    public void crearUsuario() {
        System.out.print("Ingresa un nombre de usuario (mínimo 3 caracteres): ");
        String nuevoUsuario = scanner.nextLine();

        if (validarNombreUsuario(nuevoUsuario)) {
            System.out.print("Ingresa un DNI (8 números y 1 letra en mayúscula): ");
            String nuevoDNI = scanner.nextLine();

            if (validarDNI(nuevoDNI)) {
                System.out.print("Ingresa una contraseña (mínimo 6 caracteres, al menos 1 mayúscula y 1 carácter especial, sin espacios): ");
                String nuevaContrasena = scanner.nextLine();

                if (validarContrasena(nuevaContrasena)) {
                    // Almacenar los datos del nuevo usuario en la matriz
                    usuarios[numUsuarios][0] = nuevoUsuario;
                    usuarios[numUsuarios][1] = nuevoDNI;
                    usuarios[numUsuarios][2] = nuevaContrasena;
                    numUsuarios++;

                    System.out.println("Usuario creado con éxito.");
                } else {
                    System.err.println("Contraseña no cumple con los requisitos. Inténtalo de nuevo.");
                }
            } else {
                System.err.println("Formato de DNI incorrecto. Inténtalo de nuevo.");
            }
        } else {
            System.err.println("Nombre de usuario no cumple con los requisitos. Inténtalo de nuevo.");
        }
    }

    public void iniciarSesion() {
        System.out.print("Ingresa tu nombre de usuario: ");
        String usuario = scanner.nextLine();

        int indiceUsuario = buscarUsuario(usuario);

        if (indiceUsuario != -1) {
            System.out.print("Ingresa tu contraseña: ");
            String contrasena = scanner.nextLine();

            if (contrasena.equals(usuarios[indiceUsuario][2])) {
                System.out.println("Inicio de sesión exitoso. ¡Bienvenido!");
            } else {
                System.err.println("Contraseña incorrecta. ¿Deseas cambiarla? (S/N)");
                String opcion = scanner.nextLine();

                if (opcion.equalsIgnoreCase("S")) {
                    cambiarContrasena(indiceUsuario);
                }
            }
        } else {
            System.err.println("Usuario no encontrado. ¿Quieres crear uno nuevo? (S/N)");
            String opcion = scanner.nextLine();

            if (opcion.equalsIgnoreCase("S")) {
                crearUsuario();
            }
        }
    }

    public boolean validarDNI(String dni) {
        if (dni.length() == 9) {
            for (int i = 0; i < 8; i++) {
                char c = dni.charAt(i);
                if (c < '0' || c > '9') {
                    return false;
                }
            }
            char lastChar = dni.charAt(8);
            return lastChar >= 'A' && lastChar <= 'Z';
        } else {
            return false;
        }
    }

    public boolean validarNombreUsuario(String usuario) {
        // Eliminar espacios al principio
        while (usuario.length() > 0 && usuario.charAt(0) == ' ') {
            usuario = usuario.substring(1);
        }

        // Eliminar espacios al final
        while (usuario.length() > 0 && usuario.charAt(usuario.length() - 1) == ' ') {
            usuario = usuario.substring(0, usuario.length() - 1);
        }

        if (usuario.length() >= 3) {
            return true;
        } else {
            System.err.println("Error: El nombre de usuario debe tener al menos 3 caracteres. Inténtalo de nuevo.");
            return false;
        }
    }

    public boolean validarContrasena(String contrasena) {
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
                System.err.println("Error: La contraseña debe tener al menos 6 caracteres, incluir al menos 1 mayúscula y 1 carácter especial. Inténtalo de nuevo.");
                return false;
            }
        } else {
            System.err.println("Error: La contraseña debe tener al menos 6 caracteres y no debe contener espacios. Inténtalo de nuevo.");
            return false;
        }
    }

    public void cambiarContrasena() {
        System.out.print("Ingresa tu nombre de usuario: ");
        String usuario = scanner.nextLine();

        int indiceUsuario = buscarUsuario(usuario);

        if (indiceUsuario != -1) {
            cambiarContrasena(indiceUsuario);
        } else {
            System.err.println("Usuario no encontrado. Inténtalo de nuevo.");
        }
    }

    public void cambiarContrasena(int indiceUsuario) {
        System.out.print("Ingresa tu nueva contraseña: ");
        String nuevaContrasena = scanner.nextLine();

        if (validarContrasena(nuevaContrasena)) {
            usuarios[indiceUsuario][2] = nuevaContrasena;
            System.out.println("Contraseña cambiada con éxito.");
        } else {
            System.err.println("La nueva contraseña no cumple con los requisitos, debe tener al menos 6 caracteres, incluir al menos 1 mayúscula y 1 carácter especial. Inténtalo de nuevo.");
        }
    }

    public boolean usuarioExistente(String usuario) {
        for (int i = 0; i < numUsuarios; i++) {
            if (usuarios[i][0].equals(usuario)) {
                return true;
            }
        }
        return false;
    }

    public int buscarUsuario(String usuario) {
        for (int i = 0; i < numUsuarios; i++) {
            if (usuarios[i][0].equals(usuario)) {
                return i;
            }
        }
        return -1;
    }

    public void mostrarUsuariosRegistrados() {
        System.out.println("Usuarios registrados:");

        if (numUsuarios > 0) {
            for (int i = 0; i < numUsuarios; i++) {
                System.out.println("- " + usuarios[i][0]);
            }
        } else {
            System.err.println("No hay usuarios registrados.");
        }
    }

    public static void main(String[] args) {
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        gestorUsuarios.ejecutarGestionUsuarios();
    }
}


