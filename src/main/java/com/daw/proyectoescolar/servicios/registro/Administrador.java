package com.daw.proyectoescolar.servicios.registro;


class Administrador extends Usuario {
    public Administrador(String nombreUsuario, String contrasena) {
        super(nombreUsuario, contrasena);
    }

    @Override
    public boolean validarNombreUsuario(String usuario) {
        // Implementación de la validación del nombre de usuario para un administrador
        return usuario.equals("admin"); // Ejemplo de validación
    }

    @Override
    public boolean validarContrasena(String contrasena) {
        // Implementación de la validación de la contraseña para un administrador
        return contrasena.equals("admin123"); // Ejemplo de validación
    }

    @Override
    public void cambiarContrasena(String nuevaContrasena) {
        // Implementación del cambio de contraseña para un administrador
        this.contrasena = nuevaContrasena;
    }

    @Override
    public void mostrarInformacion() {
        // Implementación para mostrar la información de un administrador
        System.out.println("Nombre de usuario: " + nombreUsuario);
    }
}
