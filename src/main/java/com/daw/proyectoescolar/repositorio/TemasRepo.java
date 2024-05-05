package com.daw.proyectoescolar.repositorio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.daw.proyectoescolar.entidades.Tarea;
import com.daw.proyectoescolar.entidades.Temas;
import com.daw.proyectoescolar.logs.GestionLogs;
import com.daw.proyectoescolar.servicios.temas.GestionTemas;

public class TemasRepo {

    // Constructores
    public TemasRepo() {

    }

    // Metodos

    public ArrayList<Temas> archivoTemas() {

        ArrayList<Temas> temas = new ArrayList<>();

        try {
            FileReader fr = new FileReader(Constantes.RUTA_TEMAS);
            BufferedReader br = new BufferedReader(fr);
            String linea = br.readLine();

            HashMap<Integer, ArrayList<Tarea>> hashTareas = new GestionTemas().hashTareas();
            int temaActual = 1;

            while (linea != null) {

                String[] partes = linea.split(";");
                String nombre = partes[0];
                String descripcion = partes[1];

                ArrayList<Tarea> tareasArray = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    tareasArray.addAll(hashTareas.get(temaActual));
                    temaActual++;
                }

                Temas tema = new Temas(nombre, descripcion, tareasArray);
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

}
