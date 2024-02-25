package com.daw.proyectoescolar.controladores;

import java.util.Scanner;

import com.daw.proyectoescolar.entidades.UsuarioBase;
import com.daw.proyectoescolar.repositorio.Colores;
import com.daw.proyectoescolar.servicios.recomendador.SistemaRecomendacion;

public class Controlador {

    public static void main(String[] args) {
    	
        // Clase para unir el proyecto

        Scanner sc = new Scanner(System.in);
        
        // AQUI IRIA LOGIN DE ZAMUDIO, DE MOMENTO USAR EL MIO
        SistemaRecomendacion login_improvisao = new SistemaRecomendacion();
        
        System.out.println("Bienvenido a la aplicacion escolar");

        UsuarioBase usuario = login_improvisao.login(sc);

        try {
        	  	
            System.out.println("\nBienvenido " + Colores.ANSI_BOLD + usuario.getTipoUsuario() + ", " + usuario.getNombre() + Colores.ANSI_RESET);

            if (usuario.getTipoUsuario().equals("Alumno") 
            		|| usuario.getTipoUsuario().equals("Profesor") 
            		|| usuario.getTipoUsuario().equals("Administrador")) {
            	
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