package com.daw.proyectoescolar.controladores;

import java.util.Scanner;

import com.daw.proyectoescolar.entidades.Administrador;
import com.daw.proyectoescolar.entidades.Alumno;
import com.daw.proyectoescolar.entidades.Profesor;
import com.daw.proyectoescolar.entidades.Usuario;
import com.daw.proyectoescolar.repositorio.Colores;
import com.daw.proyectoescolar.servicios.recomendador.SistemaRecomendacion;

public class Controlador {

    public static void main(String[] args) {
    	
        // Clase para unir el proyecto

        Scanner sc = new Scanner(System.in);
        
        // AQUI IRIA LOGIN DE ZAMUDIO, DE MOMENTO USAR EL MIO
        SistemaRecomendacion login_improvisao = new SistemaRecomendacion();
        
        System.out.println("Bienvenido a la aplicacion escolar");

        Usuario usuario = login_improvisao.login(sc);

        try {
        	
            System.out.println("\nBienvenido " + Colores.ANSI_BOLD + usuario.getTipoUsuario() + Colores.ANSI_RESET);

            if (usuario instanceof Alumno) {
                usuario.verMenu(sc);
            } else if (usuario instanceof Profesor) {
                usuario.verMenu(sc);
            } else if (usuario instanceof Administrador) {
            	usuario.verMenu(sc);
            } else {
                System.err.println("Usuario no reconocido");
            }
            
        } catch (NullPointerException e) {
            System.err.println("Usuario o contraseña incorrectos");
    	
        }
        
        sc.close();
       
    }
      
}