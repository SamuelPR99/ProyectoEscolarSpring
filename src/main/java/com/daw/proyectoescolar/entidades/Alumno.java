package com.daw.proyectoescolar.entidades;

import java.util.ArrayList;
import java.util.Scanner;

import com.daw.proyectoescolar.repositorio.Colores;
import com.daw.proyectoescolar.repositorio.GestionLogs;
import com.daw.proyectoescolar.servicios.gestionusuarios.GestionadorUsuarios;

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
    
    public Alumno(String nombre, String contraseña, String dni, double nota) {
        super(nombre, contraseña, dni);
        this.nota = nota;
        
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
    public void verMenu(Scanner sc, ArrayList<UsuarioBase> usuarios, ArrayList<Alumno> alumnos) {
    	
        GestionadorUsuarios gestor = new GestionadorUsuarios();
       
        String opcion;

        do {
        	
            System.out.println(Colores.ANSI_YELLOW + Colores.ANSI_UNDERLINE +"\nSeleccione una opcion:\n" + Colores.ANSI_RESET + Colores.ANSI_YELLOW
                    + "1. Ver nota\n"
                    + "2. Recomendar tarea\n"
                    + "3. Consultar tareas pendientes\n"
                    + "4. Entregar tarea\n"
                    + "5. Cambiar contraseña\n"
                    + "6. Salir del menu" + Colores.ANSI_RESET);

            opcion = sc.nextLine().toLowerCase();

            switch (opcion) {
            
                case "1", "ver nota":
                    GestionLogs.logOpcionMenu("Menu Alumno", "Ver nota");
                    System.out.println("Nota actual: " + getNota());
                    break;

                case "2", "recomendar tarea":
                    GestionLogs.logOpcionMenu("Menu Alumno", "Recomendar tarea");
                    gestor.recomendarTareaYMostrar(this);
                    break;

                case "3", "consultar tareas pendientes":
                    GestionLogs.logOpcionMenu("Menu Alumno", "Consultar tareas pendientes");
                    gestor.consultarTareasPendientes(this);
                    break;

                case "4", "entregar tarea":
                	GestionLogs.logOpcionMenu("Menu Alumno", "Entregar tarea");
                    gestor.marcarTareaCompletada(this, sc);
                    break;
                    
                case "5", "Cambiar contraseña":
					GestionLogs.logOpcionMenu("Menu Alumno", "Cambiar contraseña");
	            	gestor.cambiarContraseña(sc, this);
                	break;

                case "6", "salir del menu", "salir", "salir del":
                	GestionLogs.logOpcionMenu("Menu Alumno", "Salir del menu");
                    System.out.println(Colores.ANSI_BOLD + "Saliendo del menu de alumno..." + Colores.ANSI_RESET);
                    break;

                default:
                    System.err.println("Opcion no valida. Por favor, elige una opcion valida.");
                    GestionLogs.errorLogs("Opcion no valida en el menu de alumno");
            }
            
        } while (!opcion.equals("6") && !opcion.contains("salir"));
    }

}
