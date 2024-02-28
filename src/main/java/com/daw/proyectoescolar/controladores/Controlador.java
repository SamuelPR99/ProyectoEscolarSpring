package com.daw.proyectoescolar.controladores;

import java.util.Scanner;

import com.daw.proyectoescolar.servicios.gestionusuarios.GestionadorUsuarios;

public class Controlador {

    public static void main(String[] args) {
    	
        // Clase para unir el proyecto

        Scanner sc = new Scanner(System.in);
        
        new GestionadorUsuarios().iniciar(sc);
        
        /*
         * Por cuestiones de seguridad no puedes registrarte como un usario administrador
         * Para loguearte como administrador puedes usar los siguientes datos:
         * Usuario: Lolo
         * Contraseña: pass1
         */
        
        sc.close();
       
    }
      
}