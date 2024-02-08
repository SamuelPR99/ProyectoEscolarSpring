package com.daw.proyectoescolar.servicios.tareas;

public class Profesor {

	
		//ATRIBUTOS
		protected String contraseña;
		
		//CONSTRUCTORES
		public Profesor() {
			
		}
		public Profesor(String contraseña) {
			this.contraseña=contraseña;
		}
		
		
		//METODOS
		
		public String getContraseña() {
			return contraseña;
		}
		public void setContraseña(String contraseña) {
			this.contraseña = contraseña;
		}
	

	
}
