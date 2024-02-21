package com.daw.proyectoescolar.entidades;

import java.util.ArrayList;

import java.util.Scanner;

import com.daw.proyectoescolar.repositorio.Colores;
import com.daw.proyectoescolar.servicios.recomendador.SistemaRecomendacion;
import com.daw.proyectoescolar.servicios.recomendador.TareaAvanzada;
import com.daw.proyectoescolar.servicios.recomendador.TareaBasica;
import com.daw.proyectoescolar.servicios.recomendador.TareaIntermedia;

public class Alumno extends UsuarioBase {
	
	// Atributos
static Scanner sc= new Scanner(System.in);
    protected double nota;
    protected String dni;
    protected ArrayList<Tarea> tareasPendientes;
    
    // Constructores
    
    public Alumno() {
	}

    public Alumno(String nombre, String contraseña, double nota) {
        super(nombre, contraseña);
        this.nota = nota;
        this.tareasPendientes = new ArrayList<Tarea>();
    }

	public Alumno(String nombreUsuario, String contrasena, String dni) {
        super(nombreUsuario, contrasena);
        this.dni = dni;
    }
    
    public void mostrarMenu() {
    	int opcion;
    	do {
    		System.out.println("Bienvenido Alumno, seleccione una opcion: \n"
    				+ "1. Ver listado de temas"
    				+ "2. Salir");
    		opcion= sc.nextInt();
    		switch(opcion) {
    		case 1:
    			System.out.println("Listado de Temas");
    			//new Tema().
    		break;
    		case 2: 
    			System.out.println("Saliendo...");
    			
    		break;
    		}
    	
    	
    	
    	}while(opcion!=2);
    }
    // Getters y setters

    public double getNota() {
        return nota;
    }
    
    public void setNota(double nuevaNota) {
        this.nota = nuevaNota;
    }

    public ArrayList<Tarea> getTareasPendientes() {
        return tareasPendientes;
    }
    
    // Metodos
    
    @Override
    public String getTipoUsuario() {
        return "Alumno";
    }

    public void agregarTareaPendiente(Tarea tarea) {
        tareasPendientes.add(tarea);
    }

    public void recomendarTarea() {
        // Lógica para recomendar tarea según la nota del alumno
        Tarea recomendacion;

        if (nota >= 7.0) {
            recomendacion = new TareaAvanzada();
        } else if (nota >= 5.0) {
            recomendacion = new TareaIntermedia();
        } else {
            recomendacion = new TareaBasica();
        }

        System.out.println("Recomendación de tarea para " + getNombre() + ":");
        recomendacion.mostrarRecomendacion();
        agregarTareaPendiente(recomendacion);
    }
    
    // Menu alumno
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
                    recomendarTarea();
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
                    System.err.println("Opcion no valida. Por favor, elige una opción valida.");
            }
        } while (!opcion.equals("5") && !opcion.equals("salir del menu"));
    }
}