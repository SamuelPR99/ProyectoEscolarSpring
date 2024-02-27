package com.daw.proyectoescolar.entidades;

import java.util.ArrayList;
import java.util.Scanner;

import com.daw.proyectoescolar.repositorio.Colores;
import com.daw.proyectoescolar.servicios.recomendador.SistemaRecomendacion;

public class Alumno extends UsuarioBase {
    
    // Atributos
    protected double nota;
    protected ArrayList<Tarea> tareasAsignadas = new ArrayList<Tarea>();

    // Constructores
    public Alumno() {
    }

    public Alumno(String nombre, String contraseña, double nota) {
        super(nombre, contraseña);
        this.nota = nota;
    }

    public Alumno(String nombre, String contraseña, String dni) {
        super(nombre, contraseña, dni);
        
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

 	public void setTareasAsignadas(ArrayList<Tarea> tareasAsignadas) {
 		this.tareasAsignadas = tareasAsignadas;
    
        
    // Metodos
    
	}

	public void agregarTarea(Tarea tarea) {
        tareasAsignadas.add(tarea);
    }

    public void eliminarTarea(Tarea tarea) {
        tareasAsignadas.remove(tarea);
    }
    
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
    
    // Zamudio
    
    @Override
    public boolean validarNombreUsuario(String usuario) {
        // Implementación de la validación del nombre de usuario para un alumno
        return usuario.length() >= 3;
    }

    @Override
    public boolean validarContrasena(String contrasena) {
        // Implementación de la validación de la contraseña para un alumno
        return contrasena.length() >= 6 && !contrasena.contains(" ");
    }

    @Override
    public void cambiarContrasena(String nuevaContrasena) {
        // Implementación del cambio de contraseña para un alumno
        this.contraseña = nuevaContrasena;
    }

    @Override
    public void mostrarInformacion() {
        // Implementación para mostrar la información de un alumno
        System.out.println("Nombre de usuario: " + nombre);
        System.out.println("DNI: " + dni);
    }

}
