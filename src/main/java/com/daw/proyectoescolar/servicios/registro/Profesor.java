package com.daw.proyectoescolar.servicios.registro;

class Profesor extends Usuario {
    private String departamento;

    public Profesor(String nombreUsuario, String contrasena, String departamento) {
        super(nombreUsuario, contrasena);
        this.departamento = departamento;
    }

    @Override
    public boolean validarNombreUsuario(String usuario) {
        // Implementación de la validación del nombre de usuario para un profesor
        return usuario.length() >= 5; // Ejemplo de validación
    }

    @Override
    public boolean validarContrasena(String contrasena) {
        // Implementación de la validación de la contraseña para un profesor
        return contrasena.length() >= 8; // Ejemplo de validación
    }

    @Override
    public void cambiarContrasena(String nuevaContrasena) {
        // Implementación del cambio de contraseña para un profesor
        this.contrasena = nuevaContrasena;
    }

    @Override
    public void mostrarInformacion() {
        // Implementación para mostrar la información de un profesor
        System.out.println("Nombre de usuario: " + nombreUsuario);
        System.out.println("Departamento: " + departamento);
    }
}
