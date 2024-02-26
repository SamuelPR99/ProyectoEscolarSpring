package com.daw.proyectoescolar.servicios.registro;


import java.util.Scanner;

abstract class Usuario {
    protected String nombreUsuario;
    protected String contrasena;

    public Usuario(String nombreUsuario, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public boolean autenticar(String contrasena) {
        return this.contrasena.equals(contrasena);
    }

    public abstract boolean validarNombreUsuario(String usuario);
    public abstract boolean validarContrasena(String contrasena);
    public abstract void cambiarContrasena(String nuevaContrasena);
    public abstract void mostrarInformacion();
}



