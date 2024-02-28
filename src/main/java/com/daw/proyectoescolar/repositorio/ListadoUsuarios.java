package com.daw.proyectoescolar.repositorio;

import java.util.ArrayList;

import com.daw.proyectoescolar.entidades.Administrador;
import com.daw.proyectoescolar.entidades.Alumno;
import com.daw.proyectoescolar.entidades.Profesor;
import com.daw.proyectoescolar.entidades.UsuarioBase;

public class ListadoUsuarios {
	
	public static ArrayList<UsuarioBase> obtenerUsuarios() {
		
		// Inicialización del ArrayList de usuarios
        ArrayList<UsuarioBase> usuarios = new ArrayList<UsuarioBase>();
        
        // Agregar algunos datos de ejemplo
        
        // Profesores
        usuarios.add(new Profesor("Guillamon", "pass1"));
        usuarios.add(new Profesor("Lidia", "pass2"));
        usuarios.add(new Profesor("David", "pass3"));
        usuarios.add(new Profesor("Paco", "pass4"));

        // Alumnos
        usuarios.add(new Alumno("Samuel", "123", 9.0));
        usuarios.add(new Alumno("Paula", "123", 5.0));
        usuarios.add(new Alumno("Hugo", "123", 7.5));
        usuarios.add(new Alumno("Zamudio", "123", 3.0));
        
        // Administradores
        usuarios.add(new Administrador("Lolo", "pass1"));
        
		return usuarios;
    	
    }
}

