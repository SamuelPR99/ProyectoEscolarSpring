package com.daw.proyectoescolar.entidades;

import java.util.ArrayList;

import com.daw.proyectoescolar.entidades.tareas.Tarea;
import com.daw.proyectoescolar.entidades.tareas.Tema;

public class Tema {

 
	protected String nombre;
	protected String descripcion;
	protected ArrayList<Tarea>tareas;
	protected ArrayList<Tema> temas;
	protected Tarea tarea;
	
}
