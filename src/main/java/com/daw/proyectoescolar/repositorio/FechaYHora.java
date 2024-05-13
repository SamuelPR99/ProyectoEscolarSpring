package com.daw.proyectoescolar.repositorio;

public class FechaYHora {
	
	private FechaYHora() {

	}
	
	public static String fechaActual() {
		java.util.Date fecha = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(fecha);
	}

	public static String fechaActualSinHora() {
		java.util.Date fecha = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(fecha);
	}

	public static String horaActual() {
		java.util.Date fecha = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm:ss");
		return sdf.format(fecha);
	}

	public static String horaActualSinSegundos() {
		java.util.Date fecha = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm");
		return sdf.format(fecha);
	}

	public static String fechaHoraActual() {
		java.util.Date fecha = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(fecha);
	}

	public static String fechaHoraActualSinGuiones() {
		java.util.Date fecha = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMddHHmm");
		return sdf.format(fecha);
	}

	public static String fechaHoraActualSinGuionesSinHora() {
		java.util.Date fecha = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMdd");
		return sdf.format(fecha);
	}

	public static String fechaHoraActualSinGuionesSinHoraSinSegundos() {
		java.util.Date fecha = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMddHH");
		return sdf.format(fecha);
	}

}
