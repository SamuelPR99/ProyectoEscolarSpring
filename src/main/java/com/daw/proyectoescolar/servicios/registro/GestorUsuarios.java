package com.daw.proyectoescolar.servicios.registro;

import java.util.ArrayList;

import java.util.Scanner;

import com.daw.proyectoescolar.entidades.Administrador;
import com.daw.proyectoescolar.entidades.Alumno;
import com.daw.proyectoescolar.entidades.Profesor;
import com.daw.proyectoescolar.entidades.UsuarioBase;
import com.daw.proyectoescolar.repositorio.Colores;

public class GestorUsuarios {
	
	// ATRIBUTOS
	
	protected ArrayList<UsuarioBase> usuarios;

    // CONSTRUCTORES
	
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

    
    // METODOS
    
    public void crearUsuario(Scanner sc) {
    	
        System.out.print("Introduzca su nombre: ");
        String nombre = sc.nextLine();
        
        // Validar nombre
		while (!validarNombreUsuario(nombre)) {
			System.err.println("Nombre de usuario no valido. Intentalo de nuevo: ");
			System.out.print("Introduzca su nombre: ");
			nombre = sc.nextLine();
		}
        
        System.out.print("Introduzca su DNI: ");
        String dni = sc.nextLine();
        
        // Validar el DNI
        
		while (!validarDNI(dni)) {
			System.err.println("DNI no valido. Intentalo de nuevo: ");
			System.out.print("Introduzca su DNI: ");
			dni = sc.nextLine();
		}
        
        System.out.print("Introduzca su contraseña: ");
        String contraseña = sc.nextLine();
        
        // Validar la contraseña
        
        while (!validarContraseña(contraseña)) {
            System.err.println("Contraseña no valida. Intentalo de nuevo: ");
            System.out.print("Introduzca su contraseña: ");
            contraseña = sc.nextLine();
                }
        
        System.out.print("¿Es profesor o alumno?: ");
        String tipo = sc.nextLine();
        
        if (tipo.equalsIgnoreCase("profesor")) {
			new Profesor(nombre, contraseña, dni);
		} else if (tipo.equalsIgnoreCase("alumno")) {
			new Alumno(nombre, contraseña, dni);
		} else {
			System.err.println("Tipo de usuario no valido. Intentalo de nuevo.");
		}

	}
	    
	public UsuarioBase login(Scanner sc) {
		
		System.out.print("Introduzca su usuario: ");
		String usuario = sc.nextLine();
		System.out.print("Introduzca su contraseña: ");
		String contraseña = sc.nextLine();

		if (usuarios.isEmpty()) {
			
			System.err.println("La lista de usuarios esta " + Colores.ANSI_UNDERLINE + "vacia" + Colores.ANSI_RESET + ". Por favor, cree un usuario.");
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
		
		mostrarUsuarios();
		UsuarioBase usuario = buscarUsuario(sc);

		if (usuario != null) {
			usuarios.remove(usuario);
			System.out.println(Colores.ANSI_GREEN + "Usuario eliminado con exito." + Colores.ANSI_RESET);
		} else {
			System.err.println("Usuario no encontrado.");
		}
	}

	public void mostrarUsuarios() {

		System.out.println("Usuarios registrados:");
		for (UsuarioBase u : usuarios) {
			System.out.println(u.getNombre() + " - " + u.getTipoUsuario());
		}
		
	}
	
	public void cambiarContraseña(Scanner sc, UsuarioBase usuario) {

		System.out.print("Introduzca su nueva contraseña: ");
		String nuevaContraseña = sc.nextLine();

		if (validarContraseña(nuevaContraseña)) {
			usuario.setContraseña(nuevaContraseña);
			System.out.println(Colores.ANSI_GREEN + "Contraseña cambiada con éxito." + Colores.ANSI_RESET);
		} else {
			System.err.println(
					"La nueva contraseña no cumple con los requisitos.\n "
					+ "Debe tener al menos 6 caracteres.\n"
					+ "Incluir al menos 1 mayúscula y 1 caracter especial. "
					+ "Intentalo de nuevo.");
		}

	}
    	
    public UsuarioBase buscarUsuario(Scanner sc) {

		System.out.print("Introduzca el nombre del usuario que quiere " + Colores.ANSI_RED + Colores.ANSI_UNDERLINE + "eliminar: " + Colores.ANSI_RESET);
		String nombre = sc.nextLine();

		for (UsuarioBase u : usuarios) {
			if (u.getNombre().equals(nombre)) {
				return u;
			}
		}

		return null;
	}

    public boolean validarDNI(String dni) {
    	
        if (dni.length() == 10) {
            for (int i = 0; i < 9; i++) {
                char c = dni.charAt(i);
                if (c < '0' || c > '9') {
                    return false;
                }
            }
            char lastChar = dni.charAt(9);
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
            System.err.println("Error: El nombre de usuario debe tener al menos 3 caracteres. Intentalo de nuevo.");
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
                System.err.println("Error: La contraseña debe tener al menos 6 caracteres.\n "
                		+ "incluir al menos 1 mayúscula y 1 carácter especial.\n"
                		+ "Intentalo de nuevo.");
                return false;
            }
        } else {
            System.err.println("Error: La contraseña debe tener al menos 6 caracteres.\n"
            		+ "no debe contener espacios.\n"
            		+ "Intentalo de nuevo.");
            return false;
        }
        
    }
    
	public void inicio(Scanner sc) {
		
        System.out.println("Bienvenido a la aplicacion escolar");
		
		String opcion;
        
        do {
            
            System.out.println("Seleccione una opción:\n"
                    + "1. Iniciar sesión\n"
                    + "2. Registrarse\n"
                    + "3. Salir");
            
            opcion = sc.nextLine();
            
            switch (opcion) {
            
                case "1":
                	
                    UsuarioBase usuario = login(sc);
                    
                    if (usuario != null) {
                        System.out.println("Bienvenido " + Colores.ANSI_UNDERLINE + Colores.ANSI_BOLD 
                        		+ usuario.getTipoUsuario() + Colores.ANSI_RESET 
                        		+ ", " + usuario.getNombre());
                        
                        usuario.verMenu(sc);
                        
                    } else {
                        System.err.println("Usuario o contraseña incorrectos.");
                    }
                    
                    break;
                case "2":
                	// Cuando cree un usuario vuelva a login
                    crearUsuario(sc);
                    
                    break;
                case "3":
                    System.out.println("Hasta luego. (⌐■_■)");
                    break;
                default:
                    System.err.println("Opción no valida. Intentalo de nuevo.");
            }
            
        } while (!opcion.equals("3"));
        
    }
	
}