package com.daw.proyectoescolar.controladores;

import java.util.Scanner;

import com.daw.proyectoescolar.entidades.UsuarioBase;
import com.daw.proyectoescolar.repositorio.Colores;
import com.daw.proyectoescolar.servicios.registro.GestorUsuarios;

public class Controlador {

    public static void main(String[] args) {
    	
        // Clase para unir el proyecto

        Scanner sc = new Scanner(System.in);
        
        new GestorUsuarios().inicio(sc);
        
        /*
         * Por cuestiones de seguridad no puedes crear un usario administrador
         * Para loguearte como administrador puedes usar los siguientes datos:
         * Usuario: Lolo
         * Contraseña: pass1
         */
        
        sc.close();
       
    }
      
}