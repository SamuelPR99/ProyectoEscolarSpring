package com.daw.proyectoescolar.servicios.registro;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static GestorUsuarios gestorUsuarios = new GestorUsuarios();

    public static void main(String[] args) {
        gestorUsuarios.ejecutarGestionUsuarios();
    }
}
