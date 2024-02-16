package com.daw.proyectoescolar.entidades;

import java.util.ArrayList;

import com.daw.proyectoescolar.repositorio.Colores;
import com.daw.proyectoescolar.servicios.recomendador.TareaAvanzada;
import com.daw.proyectoescolar.servicios.recomendador.TareaBasica;
import com.daw.proyectoescolar.servicios.recomendador.TareaIntermedia;

public class Alumno extends UsuarioBase {
	
	// Atributos

    private double nota;
    private ArrayList<Tarea> tareasPendientes;
    
    // Constructores

    public Alumno(String nombre, String contraseña, double nota) {
        super(nombre, contraseña);
        this.nota = nota;
        this.tareasPendientes = new ArrayList<Tarea>();
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

    @Override
    public void verMenu()  { throw new UnsupportedOperationException(Colores.ANSI_RED + "Menu no implementado" + Colores.ANSI_RESET);

        
    }
}