package com.daw.proyectoescolar.servicios.gestionusuarios;

import java.util.ArrayList;
import java.util.Scanner;

import com.daw.proyectoescolar.entidades.Administrador;
import com.daw.proyectoescolar.entidades.Alumno;
import com.daw.proyectoescolar.entidades.Profesor;
import com.daw.proyectoescolar.entidades.Tarea;
import com.daw.proyectoescolar.entidades.UsuarioBase;
import com.daw.proyectoescolar.repositorio.Colores;
import com.daw.proyectoescolar.servicios.incidencias.GestionDeIncidencias;

public class GestionadorUsuarios {

 private ArrayList<Tarea> listaDeTareas = Tarea.obtenerTodasLasTareas();
 
	 public GestionadorUsuarios() {}
	 
	 public void iniciar(Scanner sc) {
		 
		    ArrayList<UsuarioBase> usuarios = usuarios(new ArrayList<UsuarioBase>());
		    String opcion;
		    
		    System.out.println(Colores.ANSI_UNDERLINE + Colores.ANSI_BOLD + Colores.ANSI_BLUE_BACKGROUND + "Bienvenido al" 
		    + Colores.ANSI_YELLOW_BACKGROUND + " Campus Virtual " 
		    + Colores.ANSI_BLUE_BACKGROUND + " del IES Murcia." + Colores.ANSI_RESET);

		    do {

		        System.out.println(Colores.ANSI_YELLOW + Colores.ANSI_UNDERLINE +"\nSeleccione una opcion:\n" + Colores.ANSI_RESET
		                + Colores.ANSI_YELLOW +"1. Iniciar sesion\n"
		                + "2. Gestion de incidencias\n"
		                + "3. Salir" + Colores.ANSI_RESET);

		        opcion = sc.nextLine().toLowerCase();

		        switch (opcion) {

		            case "1", "iniciar sesion":
		                UsuarioBase usuario = login(sc, usuarios);

		                try {
		                	
		                    System.out.println("Bienvenido " + Colores.ANSI_UNDERLINE + Colores.ANSI_BOLD
		                            + usuario.getTipoUsuario() + Colores.ANSI_RESET
		                            + ", " + usuario.getNombre());

		                    usuario.verMenu(sc, usuarios, obtenerAlumnos(usuarios));

		                } catch (NullPointerException excepcion) {
		                    System.err.println("Usuario o contraseña incorrectos.");
		                }

		                break;
		                
		            case "2", "gestion de incidencias":
	            	    GestionDeIncidencias gestionadorIncidencias = new GestionDeIncidencias();
                        gestionadorIncidencias.menuPrincipal(sc);    	
		                break;

		            case "3", "salir":
		                System.out.println("Hasta luego. " + Colores.ANSI_GREEN + "(⌐■_■)" + Colores.ANSI_RESET);
		                break;

		            default:
		                System.err.println("Opcion no valida. Intentalo de nuevo.");

		        }

		    } while (!opcion.equals("3") && !opcion.equals("salir"));

		}
 
	 public UsuarioBase login(Scanner sc, ArrayList<UsuarioBase> usuarios) {

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
	
	public void registro(Scanner sc, ArrayList<UsuarioBase> usuarios) {
		
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
        UsuarioBase nuevoUsuario = null;

        // Crear el usuario dependiendo del tipo
        if (tipo.equalsIgnoreCase("profesor")) {
        	nuevoUsuario = new Profesor(nombre, contraseña, dni);
		} else if (tipo.equalsIgnoreCase("alumno")) {
			nuevoUsuario = new Alumno(nombre, contraseña, dni, 0.0);
		} else {
			System.err.println("Tipo de usuario no valido. Intentalo de nuevo.");
		}
        
        // Agregar el nuevo usuario al ArrayList de usuarios
        usuarios.add(nuevoUsuario);

        System.out.println(Colores.ANSI_GREEN + "Usuario creado correctamente." + Colores.ANSI_RESET);
        
	}
	
	public void borrarUsuario(Scanner sc, ArrayList<UsuarioBase> usuarios) {
		
		System.out.println("Introduce el nombre de usuario que quieres borrar:");
		String nombre = sc.nextLine();
		
		for (UsuarioBase usuario : usuarios) {
			if (usuario.getNombre().equals(nombre)) {
                usuarios.remove(usuario);
                System.out.println(Colores.ANSI_GREEN + "Usuario borrado correctamente." + Colores.ANSI_RESET);
                return;
            }
        }
	}
    
	public void mostrarUsuarios(ArrayList<UsuarioBase> usuarios) {

		for (UsuarioBase usuario : usuarios) {
			System.out.println("\nNombre: " + usuario.getNombre() 
			+ "\nTipo: " + usuario.getTipoUsuario() 
			+ "\nDNI: " + usuario.getDni() 
			+ "\nContraseña: " + usuario.getContraseña());
			System.out.println(Colores.ANSI_BOLD + "_______________________________" + Colores.ANSI_RESET);
		}
	
	}
	
	public void cambiarContraseña(Scanner sc, UsuarioBase usuario) {
		    
            System.out.println("Introduce tu nueva contraseña:");
            String nuevaContraseña = sc.nextLine();
            
            usuario.setContraseña(nuevaContraseña);
            System.out.println(Colores.ANSI_GREEN + "Contraseña cambiada correctamente." + Colores.ANSI_RESET);
        
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

    // Debe contener 6 caracteres, no puede tener espacios, y debe contener minimo un caracter especial
    private boolean validarContraseña(String contraseña) {
    	
        if (contraseña.length() >= 6 && !contraseña.contains(" ") && contraseña.matches(".*[!@#$%^&*].*")) {
			return true;
		}
        
        return false;
    
     }
    
    public static ArrayList<UsuarioBase> usuarios(ArrayList<UsuarioBase> usuariosRegistrados) {

        // Inicialización del ArrayList de usuarios
        ArrayList<UsuarioBase> usuariosDefecto = new ArrayList<UsuarioBase>();

        // Agregar usuarios registrados al ArrayList de usuarios por defecto
        usuariosDefecto.addAll(usuariosRegistrados);

        // Agregar algunos datos de ejemplo en caso de que no se hayan registrado usuarios aún

        // Profesores
        usuariosDefecto.add(new Profesor("Guillamon", "pass1", "76429580M"));
        usuariosDefecto.add(new Profesor("Lidia", "pass2", "76429581M"));
        usuariosDefecto.add(new Profesor("David", "pass3", "76429582M"));
        usuariosDefecto.add(new Profesor("Paco", "pass4", "76429583M"));

        // Alumnos
        usuariosDefecto.add(new Alumno("Samuel", "123", "76429584M", 9.0));
        usuariosDefecto.add(new Alumno("Paula", "123", "76429585M", 5.0));
        usuariosDefecto.add(new Alumno("Hugo", "123", "76429586M", 7.5));
        usuariosDefecto.add(new Alumno("Zamudio", "123", "76429587M", 3.0));

        // Administradores
        usuariosDefecto.add(new Administrador("Lolo", "pass1", "76429588M"));

        return usuariosDefecto;
    }
    
    /*---------------------------------------------------------------------------------------------------------*/
	
    // Recomendar una tarea al alumno
	private Tarea recomendarTarea(Alumno alumno) {
			
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
		
	// Ver estadisticas de los alumnos
	public void verEstadisticas(ArrayList<UsuarioBase> usuarios) {
		
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
	
	// Obtener los alumnos del ArrayList de usuarios
	private ArrayList<Alumno> obtenerAlumnos(ArrayList<UsuarioBase> usuarios) {
		    
            ArrayList<Alumno> alumnos = new ArrayList<>();

            for (UsuarioBase usuario : usuarios) {
                if (usuario.getTipoUsuario().equals("Alumno")) {
                    alumnos.add((Alumno) usuario);
                }
            }

            return alumnos;
        
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
    
    // Ver las notas de los alumnos
    public void verNotasAlumnos(ArrayList<UsuarioBase> usuarios) {

		ArrayList<Alumno> alumnos = obtenerAlumnos(usuarios);

		for (Alumno alumno : alumnos) {
			System.out.println("Nombre: " + alumno.getNombre() + " Nota: " + alumno.getNota());
		}
		
    }
    
    // Modificar la nota de un alumno, esto tambien sirve para ponerle la nota a un alumno que cree el administrador
    public void modificarNotaAlumno(Scanner sc,  ArrayList<Alumno> alumnos) {
    	
		System.out.println("Lista de alumnos:");
		for (int i = 0; i < alumnos.size(); i++) {
			System.out.println((i + 1) + ". " + alumnos.get(i).getNombre());
		}

		System.out.print("Introduzca el numero del alumno a modificar: ");
		int numeroAlumno = sc.nextInt();
		sc.nextLine(); // Si no pongo esto, el scanner no lee bien el siguiente string

		if (numeroAlumno >= 1 && numeroAlumno <= alumnos.size()) {
			
			System.out.print("Introduzca la nueva nota del alumno: ");
			double nuevaNota = sc.nextDouble();
			sc.nextLine(); // Si no pongo esto, el scanner no lee bien el siguiente string
			
			alumnos.get(numeroAlumno - 1).setNota(nuevaNota);
			
			System.out.println(Colores.ANSI_GREEN + "Nota modificada correctamente." + Colores.ANSI_RESET);
			
		} else {
			System.err.println("Numero de alumno no valido.");
		}
    	

    }
    
    // Agregar una nueva tarea del tipo que se quiera
    public void agregarNuevaTarea(Scanner sc) {
    	
        System.out.print("Introduzca el tipo de la nueva tarea: ");
        String tipoTarea = sc.nextLine();

        Tarea nuevaTarea = new Tarea(tipoTarea);
        listaDeTareas.add(nuevaTarea);

        System.out.println(Colores.ANSI_GREEN + "Nueva tarea \"" + tipoTarea + "\" agregada correctamente." + Colores.ANSI_RESET);
        
    }
    
    // Modificar el tipo de una tarea
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
	
    // Recomendar una tarea al alumno y mostrarla
	public void recomendarTareaYMostrar(Alumno alumno) {
		
		 Tarea tareaRecomendada = recomendarTarea(alumno);
	     tareaRecomendada.mostrarRecomendacion();
	        
    }
	
}