package com.daw.proyectoescolar.servicios.recomendador;

import java.util.Scanner;

import com.daw.proyectoescolar.repositorio.Colores;

public class RecomendadorTareas {

    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);
        SistemaRecomendacion sistema = new SistemaRecomendacion();

        System.out.println(Colores.ANSI_CYAN + "Bienvenido al sistema de recomendación de tareas" + Colores.ANSI_RESET);

        Usuario usuario = sistema.login(sc);

        if (usuario != null) {
            System.out.println("\nBienvenido " + usuario.getTipoUsuario());

            if (usuario instanceof Alumno) {
                sistema.menuAlumno((Alumno) usuario, sc);
            } else if (usuario instanceof Profesor) {
                sistema.menuProfesor((Profesor) usuario, sc);
            }
        } else {
            System.err.println("Usuario o contraseña incorrectos");
        }

        sc.close();
    }
}