package com.daw.proyectoescolar.servicios.registro;


public class Usuario {
    private String nombreUsuario;
    private String contrasena;

   public Usuario() {
	   
   }
    public Usuario(String nombreUsuario, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
