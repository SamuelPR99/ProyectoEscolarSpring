package com.daw.proyectoescolar.servicios.registro;

import java.util.ArrayList;

import java.util.Scanner;

import com.daw.proyectoescolar.entidades.Administrador;
import com.daw.proyectoescolar.entidades.Alumno;
import com.daw.proyectoescolar.entidades.Profesor;
import com.daw.proyectoescolar.entidades.UsuarioBase;

public class GestorUsuarios {
	
	// ATRIBUTOS
	
	protected ArrayList<UsuarioBase> usuarios;

    //CONSTRUCTOR
    public GestorUsuarios() {
     
    	// Inicialización del ArrayList de usuarios
        usuarios = new ArrayList<UsuarioBase>();
       
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
    	
    }

    
    //METODOS
    
    public void crearUsuario(Scanner sc) {
    	
        System.out.print("Introduzca su nombre: ");
        String nombre = sc.nextLine();
        
        // Validar nombre
		while (!validarNombreUsuario(nombre)) {
			System.err.println("Nombre de usuario no válido. Inténtalo de nuevo.");
			System.out.print("Introduzca su nombre: ");
			nombre = sc.nextLine();
		}
        
        System.out.print("Introduzca su DNI: ");
        String dni = sc.nextLine();
        
        // Validar el DNI
        
		while (!validarDNI(dni)) {
			System.err.println("DNI no válido. Inténtalo de nuevo.");
			System.out.print("Introduzca su DNI: ");
			dni = sc.nextLine();
		}
        
        System.out.print("Introduzca su contraseña: ");
        String contraseña = sc.nextLine();
        
        // Validar la contraseña
        
        while (!validarContraseña(contraseña)) {
            System.err.println("Contraseña no válida. Inténtalo de nuevo.");
            System.out.print("Introduzca su contraseña: ");
            contraseña = sc.nextLine();
                }
        
        System.out.print("¿Es profesor, alumno o administrador?: ");
        String tipo = sc.nextLine();
        UsuarioBase usuario1 = null;
        
		if (tipo.equalsIgnoreCase("profesor")) {
			usuario1 = new Profesor(nombre, contraseña, dni);
		} else if (tipo.equalsIgnoreCase("alumno")) {
			usuario1 = new Alumno(nombre, contraseña, dni);
		} else if (tipo.equalsIgnoreCase("administrador")) {
			usuario1 = new Administrador(nombre, contraseña, dni);
		} else {
			System.err.println("Tipo de usuario no válido. Inténtalo de nuevo.");
		}

	}
	    
	public UsuarioBase login(Scanner sc) {
		
		System.out.print("Introduzca su usuario: ");
		String usuario = sc.nextLine();
		System.out.print("Introduzca su contraseña: ");
		String contraseña = sc.nextLine();

		if (usuarios.isEmpty()) {
			
			System.out.println("La lista de usuarios está vacía. Por favor, cree un usuario.");
			crearUsuario(sc);
			
		} else {  
			for (UsuarioBase u : usuarios) {
				if (u.getNombre().equals(usuario) && u.getContraseña().equals(contraseña)) {
					return u;
				}
			}
    
		}
    
		return null;
	        
		}
	
	public void borrarUsuario(Scanner sc) {

	
		
	}

    public boolean validarDNI(String dni) {
    	
        if (dni.length() == 9) {
            for (int i = 0; i < 8; i++) {
                char c = dni.charAt(i);
                if (c < '0' || c > '9') {
                    return false;
                }
            }
            char lastChar = dni.charAt(8);
            return lastChar >= 'A' && lastChar <= 'Z';
        } else {
            return false;
        }
        
    }

    public boolean validarNombreUsuario(String usuario) {
    	
        // Eliminar espacios al principio
        while (usuario.length() > 0 && usuario.charAt(0) == ' ') {
            usuario = usuario.substring(1);
        }

        // Eliminar espacios al final
        while (usuario.length() > 0 && usuario.charAt(usuario.length() - 1) == ' ') {
            usuario = usuario.substring(0, usuario.length() - 1);
        }

        if (usuario.length() >= 3) {
            return true;
        } else {
            System.err.println("Error: El nombre de usuario debe tener al menos 3 caracteres. Inténtalo de nuevo.");
            return false;
        }
        
    }

    public boolean validarContraseña(String contraseña) {
    	
        if (contraseña.length() >= 6 && !contraseña.contains(" ")) {
            boolean tieneMayuscula = false;
            boolean tieneEspecial = false;

            for (int i = 0; i < contraseña.length(); i++) {
                char c = contraseña.charAt(i);

                if ((c >= 65 && c <= 90) || (c >= 192 && c <= 223)) {
                    tieneMayuscula = true;
                }

                if ((c >= 33 && c <= 47) || (c >= 58 && c <= 64) || (c >= 91 && c <= 96) || (c >= 123 && c <= 126)) {
                    tieneEspecial = true;
                }

                if (tieneMayuscula && tieneEspecial) {
                    break;
                }
            }

            if (tieneMayuscula && tieneEspecial) {
                return true;
            } else {
                System.err.println("Error: La contraseña debe tener al menos 6 caracteres, incluir al menos 1 mayúscula y 1 carácter especial. Inténtalo de nuevo.");
                return false;
            }
        } else {
            System.err.println("Error: La contraseña debe tener al menos 6 caracteres y no debe contener espacios. Inténtalo de nuevo.");
            return false;
        }
        
    }

	public void cambiarContrasenaDelUsuarioQueSeLoguee(Scanner sc, UsuarioBase usuario) {

		System.out.print("Introduzca su nueva contraseña: ");
		String nuevaContrasena = sc.nextLine();

		if (validarContraseña(nuevaContrasena)) {
			usuario.setContraseña(nuevaContrasena);
			System.out.println("Contraseña cambiada con éxito.");
		} else {
			System.err.println(
					"La nueva contraseña no cumple con los requisitos, "
					+ "debe tener al menos 6 caracteres, "
					+ "incluir al menos 1 mayúscula y 1 carácter especial. "
					+ "Inténtalo de nuevo.");
		}

	}
    	
    public void buscarUsuario(Scanner sc) {

		System.out.print("Introduzca el nombre del usuario que desea buscar: ");
		String usuario = sc.nextLine();
		int indiceUsuario = buscarUsuario(usuario);
		if (indiceUsuario != -1) {
			System.out.println("Usuario encontrado: " + usuarios.get(indiceUsuario).getNombre());
		} else {
			System.err.println("Usuario no encontrado.");
		}

	}

	}	
    	
    public void mostrarUsuariosRegistrados() {
    	
        System.out.println("Usuarios registrados:");

        
    }

    
}


