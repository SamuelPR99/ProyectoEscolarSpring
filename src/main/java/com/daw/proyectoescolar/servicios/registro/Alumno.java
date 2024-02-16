package com.daw.proyectoescolar.servicios.registro;

class Alumno extends Usuario {
    private String dni;

    public Alumno(String nombreUsuario, String contrasena, String dni) {
        super(nombreUsuario, contrasena);
        this.dni = dni;
    }
}
