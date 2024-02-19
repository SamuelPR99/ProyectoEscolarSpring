package com.daw.proyectoescolar.repositorio;

import java.util.ArrayList;
import java.util.Scanner;

import com.daw.proyectoescolar.entidades.Tema;



public class MostrarTemas {


	static Scanner sc= new Scanner(System.in);
	
	protected ArrayList<Tema> temas;
	protected String nombre;
	protected String descripcion;
	
	public MostrarTemas() {
		
	}
	public MostrarTemas(String nombre) {
		this.nombre=nombre;
		
		
		temas= new ArrayList<>();
	}
	public ArrayList<MostrarTemas> getTemas() {
		return temas;
	}
	public void setTemas(ArrayList<MostrarTemas> temas) {
		this.temas = temas;
	}
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

public void menuTemas() {
	
	temas = new ArrayList<Tema>();
	
	Tema t1 = new Tema(" 1: Teoría de la Probabilidad Estocástica");
	temas.add(t1);
	
	Tema t2= new Tema(" 2: Teoría de Números Avanzada");
	temas.add(t2);
	
	Tema t3= new Tema(" 3: Análisis Funcional");
	temas.add(t3);
	
	Tema t4= new Tema(" 4: Topología Algebraica");
	temas.add(t4);
	
	Tema t5= new Tema(" 5: Teoría de Representación de Grupos");
	temas.add(t5);
	
	Tema t6= new Tema(" 6: Teoría de la Aproximación y Funciones Especiales");
	temas.add(t6);

	
	
	

}

}

