package com.daw.proyectoescolar.servicios.registro;

class Profesor extends Usuario {
    private String departamento;

    public Profesor(String nombreUsuario, String contrasena, String departamento) {
        super(nombreUsuario, contrasena);
        this.departamento = departamento;
    }
}
