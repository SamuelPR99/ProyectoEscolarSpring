package com.daw.proyectoescolar.servicios.gestionusuarios;

import java.util.ArrayList;
import java.util.Scanner;

import com.daw.proyectoescolar.entidades.Administrador;
import com.daw.proyectoescolar.entidades.Alumno;
import com.daw.proyectoescolar.entidades.Profesor;
import com.daw.proyectoescolar.entidades.Tarea;
import com.daw.proyectoescolar.entidades.UsuarioBase;
import com.daw.proyectoescolar.repositorio.Colores;

public class GestionadorUsuarios {

 private ArrayList<UsuarioBase> usuarios;
 private ArrayList<Tarea> listaDeTareas = Tarea.obtenerTodasLasTareas();
 
	 public GestionadorUsuarios() {
		 this.usuarios = new ArrayList<>();

	    }
	 
	 public void iniciar(Scanner sc) {
		 
	        String opcionInicio;
	        
	        do {
	            
	            System.out.println("\nSeleccione una opcion:\n"
	                    + "1. Iniciar sesion\n"
	                    + "2. Registrarse\n"
	                    + "3. Salir");
	            
	            opcionInicio = sc.nextLine().toLowerCase();
	            
	            switch (opcionInicio) {
	            
	                case "1", "iniciar sesion":
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
	                    
	                case "2", "registrarse":
	                    crearUsuario(sc);
	                    break;
	                    
	                case "3", "salir":
	                    System.out.println("Hasta luego. (⌐■_■)");
	                    break;
	                    
	                default:
	                    System.err.println("Opcion no valida. Intentalo de nuevo.");
	                    
	            }
	            
	        } while (!opcionInicio.equals("3") && !opcionInicio.equals("salir"));
	        
	    }
 
	public UsuarioBase login(Scanner sc) {
		
		System.out.println("Introduce tu nombre de usuario:");
		String nombre = sc.nextLine();

		System.out.println("Introduce tu contraseña:");
		String contraseña = sc.nextLine();

		for (UsuarioBase usuario : usuarios) {
			if (usuario.getNombre().equals(nombre) && usuario.getContraseña().equals(contraseña)) {
				return usuario;
			}
		}

		return null;
	}
	
	public void crearUsuario(Scanner sc) {
		
		usuariosPrueba();

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
        	usuarios.add(new Profesor(nombre, contraseña, dni));
		} else if (tipo.equalsIgnoreCase("alumno")) {
			usuarios.add(new Alumno(nombre, contraseña, dni, 0.0));
		} else {
			System.err.println("Tipo de usuario no valido. Intentalo de nuevo.");
		}

	}
	
	public void borrarUsuario(Scanner sc) {
		
		System.out.println("Introduce el nombre de usuario que quieres borrar:");
		String nombre = sc.nextLine();
		
		for (UsuarioBase usuario : usuarios) {
			if (usuario.getNombre().equals(nombre)) {
                usuarios.remove(usuario);
                System.out.println("Usuario borrado correctamente.");
                return;
            }
        }
	}
    
	public void mostrarUsuarios() {

		if (usuarios.isEmpty()) {
			System.err.println("La lista de usuarios esta vacia.");
		} else {
			for (UsuarioBase usuario : usuarios) {
				System.out.println(usuario.getNombre() + "\n" + usuario.getTipoUsuario() + "\n" + usuario.getDni() + "\n" + usuario.getContraseña() + "\n");
			}
		}
	}
	
	public void cambiarContraseña(Scanner sc, UsuarioBase usuario) {
		    
            System.out.println("Introduce tu nueva contraseña:");
            String nuevaContraseña = sc.nextLine();
            usuario.setContraseña(nuevaContraseña);
            System.out.println("Contraseña cambiada correctamente.");
        
    }
	
	public void verEstadisticas() {
		
		ArrayList<Alumno> alumnos = obtenerAlumnos(usuarios);
        
        if (alumnos.isEmpty()) {
            System.err.println("No hay alumnos para mostrar estadísticas.");
            return;
        }
        
        double sumaNotas = 0;
        double notaMaxima = Double.MIN_VALUE;
        double notaMinima = Double.MAX_VALUE;
        
        for (Alumno alumno : alumnos) {
            double nota = alumno.getNota();
            sumaNotas += nota;
            
            if (nota > notaMaxima) {
                notaMaxima = nota;
            }
            
            if (nota < notaMinima) {
                notaMinima = nota;
            }
        }
        
        double promedio = sumaNotas / alumnos.size();
        
        System.out.println(Colores.ANSI_PURPLE + "Promedio de notas: " + promedio + Colores.ANSI_RESET);
        System.out.println(Colores.ANSI_GREEN + "Nota más alta: " + notaMaxima + Colores.ANSI_RESET);
        System.out.println(Colores.ANSI_RED + "Nota más baja: " + notaMinima + Colores.ANSI_RESET);
    }
	
	public ArrayList<Alumno> obtenerAlumnos(ArrayList<UsuarioBase> usuarios) {
		    
            ArrayList<Alumno> alumnos = new ArrayList<>();

            for (UsuarioBase usuario : usuarios) {
                if (usuario.getTipoUsuario().equals("Alumno")) {
                    alumnos.add((Alumno) usuario);
                }
            }

            return alumnos;
        
	}
	
	public Tarea recomendarTarea(Alumno alumno) {
		
		double nota = alumno.getNota();
		String tipoTarea;

		if (nota >= 9.0) {
			tipoTarea = "Avanzada";
		} else if (nota >= 7.0) {
			tipoTarea = "Intermedia";
		} else {
			tipoTarea = "Basica";
		}

		// Buscar la primera tarea del tipo recomendado
		for (Tarea tarea : listaDeTareas) {
			if (tarea.getTipo().equals(tipoTarea)) {
				alumno.agregarTarea(tarea);
				return tarea;
				
			}
		}

		// Si no hay tareas del tipo que se recomienda, busca la primera tarea de cualquier tipo
		if (!listaDeTareas.isEmpty()) {
			Tarea tareaRecomendada = listaDeTareas.get(0);
	        alumno.agregarTarea(tareaRecomendada);
			return listaDeTareas.get(0);
		}

		// Si no hay tareas, pos error
		return null;
	}
	
	public void recomendarTareaYMostrar(Alumno alumno) {
		
		 Tarea tareaRecomendada = recomendarTarea(alumno);
	     tareaRecomendada.mostrarRecomendacion();
	        
    }
	
	// Consultar la tarea pendiente del alumno
    public void consultarTareasPendientes(Alumno alumno) {
        ArrayList<Tarea> tareasAsignadas = alumno.getTareasAsignadas();

        if (tareasAsignadas.isEmpty()) {
            System.out.println(Colores.ANSI_GREEN + "No tienes tareas pendientes." + Colores.ANSI_RESET);
        } else {
            System.out.println("Tareas Pendientes:");
            for (Tarea tarea : tareasAsignadas) {
                System.out.println("Tipo: " + tarea.getTipo());
            }
        }
    }

    // Marcar la tarea actual del alumno como completada
    public void marcarTareaCompletada(Alumno alumno, Scanner sc) {
        ArrayList<Tarea> tareasAsignadas = alumno.getTareasAsignadas();

        if (tareasAsignadas.isEmpty()) {
            System.out.println(Colores.ANSI_GREEN + "No tienes tareas pendientes para entregar." + Colores.ANSI_RESET);
        } else {
            System.out.println("Tareas Pendientes:");
            for (int i = 0; i < tareasAsignadas.size(); i++) {
                System.out.println((i + 1) + ". Tipo: " + tareasAsignadas.get(i).getTipo());
            }

            System.out.print("Seleccione el número de la tarea que va a entregar: ");
            int numeroTarea = sc.nextInt();
            sc.nextLine(); // Si no pongo esto, el scanner no lee bien el siguiente string
            
            if (numeroTarea >= 1 && numeroTarea <= tareasAsignadas.size()) {
                Tarea tareaEntregada = tareasAsignadas.remove(numeroTarea - 1);
                System.out.println(Colores.ANSI_GREEN + "Tarea \"" + tareaEntregada.getTipo() + "\" entregada correctamente." + Colores.ANSI_RESET);
            } else {
                System.err.println("Numero de tarea no válido.");
            }
        }
    }
    
    public void verNotasAlumnos() {

		ArrayList<Alumno> alumnos = obtenerAlumnos(usuarios);

		for (Alumno alumno : alumnos) {
			System.out.println("Nombre: " + alumno.getNombre() + " Nota: " + alumno.getNota());
		}
		
    }
    
    public void modificarNotaAlumno(Scanner sc) {
    	
		System.out.println("Introduce el nombre del alumno:");
		String nombre = sc.nextLine();

		for (UsuarioBase usuario : usuarios) {
			if (usuario.getNombre().equals(nombre)) {
				if (usuario.getTipoUsuario().equals("Alumno")) {
					Alumno alumno = (Alumno) usuario;
					System.out.println("Introduce la nueva nota:");
					double nota = sc.nextDouble();
					alumno.setNota(nota);
					System.out.println("Nota modificada correctamente.");
					return;
				}
			}
		}

		System.err.println("No se ha encontrado el alumno.");

    }
    
    public void agregarNuevaTarea(Scanner sc) {
    	
    	
        System.out.print("Introduzca el tipo de la nueva tarea: ");
        String tipoTarea = sc.nextLine();

        Tarea nuevaTarea = new Tarea(tipoTarea);
        listaDeTareas.add(nuevaTarea);

        System.out.println(Colores.ANSI_GREEN + "Nueva tarea \"" + tipoTarea + "\" agregada correctamente." + Colores.ANSI_RESET);
        
    }
    
    public void modificarTarea(Scanner sc) {
    	
        System.out.println("Lista de tareas:");
        for (int i = 0; i < listaDeTareas.size(); i++) {
            System.out.println((i + 1) + ". " + listaDeTareas.get(i).getTipo());
        }

        System.out.print("Introduzca el numero de la tarea a modificar: ");
        int numeroTarea = sc.nextInt();
        sc.nextLine(); // Si no pongo esto, el scanner no lee bien el siguiente string

        if (numeroTarea >= 1 && numeroTarea <= listaDeTareas.size()) {
            System.out.print("Introduzca el nuevo tipo de la tarea: ");
            String nuevoTipo = sc.nextLine();
            listaDeTareas.get(numeroTarea - 1).setTipo(nuevoTipo);
            System.out.println(Colores.ANSI_GREEN + "Tarea modificada correctamente." + Colores.ANSI_RESET);
        } else {
            System.err.println("Numero de tarea no valido.");
        }
        
    }
    
    private boolean validarDNI(String dni) {
    	
		if (dni.length() == 9) {
			for (int i = 0; i < 8; i++) {
				if (!Character.isDigit(dni.charAt(i))) {
					return false;
				}
			}

			if (!Character.isLetter(dni.charAt(8))) {
				return false;
			}

			return true;
		}
        return false;
    }

    private boolean validarNombreUsuario(String usuario) {
    	
		if (usuario.length() >= 6) {
			return true;
		}

    	return false;
    }

    private boolean validarContraseña(String contraseña) {
    	
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
                 System.err.println("Error: La contraseña debe tener al menos 6 caracteres.\n"
                 		+ "incluir al menos 1 mayúscula y 1 carácter especial.\n"
                 		+ "Intentalo de nuevo.");
                 return false;
             }
         } else {
             System.err.println("Error: La contraseña debe tener al menos 6 caracteres.\n"
             		+ "No debe contener espacios.\n"
             		+ "Intentalo de nuevo.");
             return false;
         }
         
     }
    
	public void usuariosPrueba () {
	
			// Inicializacion del ArrayList de usuarios
	        ArrayList<UsuarioBase> usuarios = new ArrayList<UsuarioBase>();
	        
	        // Agregar algunos datos de ejemplo
	        
	        // Profesores
	        usuarios.add(new Profesor("Guillamon", "pass1", "76429580M"));
	        usuarios.add(new Profesor("Lidia", "pass2", "76429581M"));
	        usuarios.add(new Profesor("David", "pass3", "76429582M"));
	        usuarios.add(new Profesor("Paco", "pass4", "76429583M"));
	
	        // Alumnos
	        usuarios.add(new Alumno("Samuel", "123", "76429584M", 9.0));
	        usuarios.add(new Alumno("Paula", "123", "76429585M",5.0));
	        usuarios.add(new Alumno("Hugo", "123", "76429586M" , 7.5));
	        usuarios.add(new Alumno("Zamudio", "123", "76429587M", 3.0));
	        
	        // Administradores
	        usuarios.add(new Administrador("Lolo", "pass1"));
	        	    		
	}

}