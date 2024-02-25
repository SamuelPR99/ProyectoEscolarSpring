package com.daw.proyectoescolar.entidades;

import java.util.ArrayList;
import java.util.Scanner;

import com.daw.proyectoescolar.repositorio.Colores;
import com.daw.proyectoescolar.servicios.recomendador.SistemaRecomendacion;

public class Alumno extends UsuarioBase {
    
    // Atributos
    protected double nota;
    protected ArrayList<Tarea> tareasAsignadas = new ArrayList<>();

    // Constructores
    public Alumno() {
    }

    public Alumno(String nombre, String contraseña, double nota) {
        super(nombre, contraseña);
        this.nota = nota;
    }

    public Alumno(String nombreUsuario, String contrasena, String dni) {
        super(nombreUsuario, contrasena);
        this.dni = dni;
    }
    
    // Getters y setters
    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
    
    public ArrayList<Tarea> getTareasAsignadas() {
        return tareasAsignadas;
    }

    public void agregarTarea(Tarea tarea) {
        tareasAsignadas.add(tarea);
    }

    public void eliminarTarea(Tarea tarea) {
        tareasAsignadas.remove(tarea);
    }
        
    // Métodos
    @Override
    public String getTipoUsuario() {
        return "Alumno";
    }

    @Override
    public void verMenu(Scanner sc) {
        SistemaRecomendacion sistema = new SistemaRecomendacion();
        String opcion;

        do {
            System.out.println(Colores.ANSI_YELLOW + "\nSeleccione una opcion:\n"
                    + "1. Ver nota\n"
                    + "2. Recomendar tarea\n"
                    + "3. Consultar tareas pendientes\n"
                    + "4. Entregar tarea\n"
                    + "5. Salir del menu" + Colores.ANSI_RESET);

            opcion = sc.nextLine().toLowerCase();

            switch (opcion) {
                case "1", "ver nota":
                    System.out.println("Nota actual: " + getNota());
                    break;

                case "2", "recomendar tarea":
                    sistema.recomendarTareaYMostrar(this);
                    break;

                case "3", "consultar tareas pendientes": 
                    sistema.consultarTareasPendientes(this);
                    break;

                case "4", "entregar tarea":
                    sistema.marcarTareaCompletada(this, sc);
                    break;

                case "5", "salir del menu":
                    System.out.println(Colores.ANSI_BOLD + "Saliendo del menu de alumno..." + Colores.ANSI_RESET);
                    break;

                default:
                    System.err.println("Opcion no valida. Por favor, elige una opcion valida.");
            }
            
        } while (!opcion.equals("5") && !opcion.equals("salir del menu"));
    }

}
