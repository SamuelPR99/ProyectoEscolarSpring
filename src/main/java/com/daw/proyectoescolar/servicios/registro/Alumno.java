package com.daw.proyectoescolar.servicios.registro;

class Alumno extends Usuario {
    private String dni;

    public Alumno(String nombreUsuario, String contrasena, String dni) {
        super(nombreUsuario, contrasena);
        this.dni = dni;
    }

    @Override
    public boolean validarNombreUsuario(String usuario) {
        // Implementación de la validación del nombre de usuario para un alumno
        return usuario.length() >= 3;
    }

    @Override
    public boolean validarContrasena(String contrasena) {
        // Implementación de la validación de la contraseña para un alumno
        return contrasena.length() >= 6 && !contrasena.contains(" ");
    }

    @Override
    public void cambiarContrasena(String nuevaContrasena) {
        // Implementación del cambio de contraseña para un alumno
        this.contrasena = nuevaContrasena;
    }

    @Override
    public void mostrarInformacion() {
        // Implementación para mostrar la información de un alumno
        System.out.println("Nombre de usuario: " + nombreUsuario);
        System.out.println("DNI: " + dni);
    }
}
