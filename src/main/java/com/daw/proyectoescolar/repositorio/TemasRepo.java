package com.daw.proyectoescolar.repositorio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.daw.proyectoescolar.entidades.Alumno;
import com.daw.proyectoescolar.entidades.Tarea;
import com.daw.proyectoescolar.entidades.Temas;
import com.daw.proyectoescolar.entidades.UsuarioBase;
import com.daw.proyectoescolar.servicios.logs.GestionLogs;

public class TemasRepo {

    // Constructores
    public TemasRepo() {

    }

    // Metodos

    // Lee el archivo de temas y crea un array de temas
    public ArrayList<Temas> archivoTemas() {

        ArrayList<Temas> temas = new ArrayList<>();

        try {
            FileReader fr = new FileReader(Constantes.RUTA_TEMAS);
            BufferedReader br = new BufferedReader(fr);
            String linea = br.readLine();

            HashMap<Integer, ArrayList<Tarea>> hashTareas = hashTareas(); // HashMap que obtiene las tareas de archivoTareas()
            int temaActual = 1;

            while (linea != null) {

                String[] partes = linea.split(";");
                int numeroTema = Integer.parseInt(partes[1]);
                String titulo = partes[1];
                String descripcion = partes[2];

                ArrayList<Tarea> tareasArray = new ArrayList<>();
                
                for (int i = 0; i < 3; i++) { // Cada tema tiene 3 tareas
                    tareasArray.addAll(hashTareas.get(temaActual));
                    temaActual++;
                }

                Temas tema = new Temas(numeroTema, titulo, descripcion, tareasArray);
                temas.add(tema);

                linea = br.readLine();
            }

            br.close();

        } catch (IOException e) {
            System.out.println(Constantes.ERROR_LEER_ARCHIVO);
            GestionLogs.errorLogs(Constantes.ERROR_LEER_ARCHIVO + e.getMessage());
        }

        return temas;
    }

    public ArrayList<Tarea> archivoTareas() {

        ArrayList<Tarea> tareas = new ArrayList<>();

        try {

            FileReader fr = new FileReader(Constantes.RUTA_TAREAS);
            BufferedReader br = new BufferedReader(fr);
            String linea = br.readLine();

            while (linea != null) {
                String[] partes = linea.split(";");
                String tipo = partes[0];
                String nombre = partes[1];
                String descripcion = partes[2];

                Tarea tarea = new Tarea(tipo, nombre, descripcion);
                tareas.add(tarea);
                linea = br.readLine();

            }

            br.close();

        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
            GestionLogs.errorLogs(Constantes.ERROR_LEER_ARCHIVO + e.getMessage());
        }

        return tareas;
    }
    
    // Crea un HashMap de tareas a partir de archivoTareas()
    public HashMap<Integer, ArrayList<Tarea>> hashTareas() {

		HashMap<Integer, ArrayList<Tarea>> tareas = new HashMap<>();
		ArrayList<Tarea> tareasArray = new TemasRepo().archivoTareas();

		int i = 1;
		for (Tarea tarea : tareasArray) {
			ArrayList<Tarea> tareasList = new ArrayList<>();
			tareasList.add(tarea);
			tareas.put(i, tareasList);
			i++;
		}

		return tareas;
	}
    
    //Insertar los temas en la bbdd
    
    
    public void insertarTemasArchivoBBDD() {

    	 ArrayList<Temas> temas = archivoTemas();
		
    	ConexionBBDD conexionBBDD = new ConexionBBDD();
		Connection conexion = conexionBBDD.conectar();
		
		
		String sqlInsert = "INSERT INTO tema (numero_tema, titulo, descripcion) VALUES (?, ?, ?)";


		try {

			PreparedStatement psInsert = conexion.prepareStatement(sqlInsert);

			for (Temas archivoTemas : temas) {
				psInsert.setInt(1, archivoTemas.getNumeroTema());
				psInsert.setString(2, archivoTemas.getNombre());
				psInsert.setString(3, archivoTemas.getDescripcion());

				psInsert.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexionBBDD.cerrarConexion(conexion);
		}
		
    }
}
