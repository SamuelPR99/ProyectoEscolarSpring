package com.daw.proyectoescolar.entidades;

import java.util.ArrayList;
import java.util.Scanner;

public class Tema {
	
	// Atributos
 
	protected String nombre;
	protected String descripcion;
	protected Tarea tarea;
	protected ArrayList<Tema> temas;
	
	// Constructores
	
	public Tema() {
		
	}
	public Tema(String nombre, String descripcion) {
		this.nombre=nombre;
		this.descripcion=descripcion;
		
	}
	
	
	// Getters y setters
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Tarea getTarea() {
		return tarea;
	}
	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}
	
	// Metodos
	public void mostrarTemas(Scanner sc) {
		
		temas = new ArrayList<Tema>();
		
		Tema t1 = new Tema("1:"," Teoría de la Probabilidad Estocástica");
		temas.add(t1);
		
		Tema t2= new Tema("2:"," Teoría de Números Avanzada");
		temas.add(t2);
		
		Tema t3= new Tema("3:"," Análisis Funcional");
		temas.add(t3);
		
		Tema t4= new Tema("4:"," Topología Algebraica");
		temas.add(t4);
		
		Tema t5= new Tema("5:"," Teoría de Representación de Grupos");
		temas.add(t5);
		
		Tema t6= new Tema("6:"," Teoría de la Aproximación y Funciones Especiales");
		temas.add(t6);
	
		System.out.println("Selecciona el número del tema");

		int opcion=sc.nextInt();
		
		for(Tema t : temas) {
			if(opcion<=6 && opcion>=1) {
				new Tarea().mostrarTareas(sc);
				
			}else {
				System.out.println("Error del elección");
			}
		
		
		
	}
}
	
}
