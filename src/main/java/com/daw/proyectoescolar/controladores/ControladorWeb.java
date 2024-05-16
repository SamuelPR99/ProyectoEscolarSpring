package com.daw.proyectoescolar.controladores;

import java.util.ArrayList;

import com.daw.proyectoescolar.entidades.Alumno;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.daw.proyectoescolar.entidades.UsuarioBase;
import com.daw.proyectoescolar.servicios.usuarios.GestionUsuarios;

@Controller

public class ControladorWeb {

	private GestionUsuarios gestionUsuarios = new GestionUsuarios();

	@GetMapping("login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}

	@GetMapping("registro")
	public ModelAndView registro() {
		return new ModelAndView("registro");
	}

	@GetMapping("registroExitoso")
	public ModelAndView registroExitoso() {
		return new ModelAndView("registroExitoso");
	}

	@PostMapping("registro")
	public ModelAndView registrarUsuario(@RequestParam String nombre, @RequestParam String dni,
			@RequestParam String contrasena, @RequestParam String tipo) {
		ModelAndView mav = new ModelAndView();
		ArrayList<UsuarioBase> usuarios = gestionUsuarios.obtenerUsuarios();

		if (gestionUsuarios.validarNombreUsuario(nombre) && gestionUsuarios.validarContrasena(contrasena)
				&& gestionUsuarios.validarDNI(dni)) {
			gestionUsuarios.registro(nombre, dni, contrasena, tipo, usuarios);
			mav.setViewName("registroExitoso");
		} else {
			mav.addObject("mensaje", "Error en algun campo, vuelve a registrarte");
		}
		return mav;
	}

	@PostMapping("login")
	public ModelAndView loguearUsuario(@RequestParam String nombre, @RequestParam String contrasena) {
		ModelAndView mav = new ModelAndView();
		ArrayList<UsuarioBase> usuarios = gestionUsuarios.obtenerUsuarios();
		UsuarioBase usuario = gestionUsuarios.login(nombre, contrasena, usuarios);
		if (usuario != null) {
			mav.addObject("usuario", usuario); // Añadimos el usuario a la vista para poder mostrar su nombre
			if (usuario.getTipoUsuario().equals("Administrador")) {
				mav.setViewName("administrador");
			} else if (usuario.getTipoUsuario().equals("Alumno")) {
				mav.setViewName("alumno");
				mav.addObject("tareasAsignadas", ((Alumno) usuario).getTareasAsignadas()); // Añadimos las tareas asignadas al alumno
			} else if (usuario.getTipoUsuario().equals("Profesor")) {
				mav.setViewName("profesor");
			} else {
				mav.setViewName("error");
			}

		} else {
			mav.setViewName("loginFallido");
		}
		return mav;
	}

	@GetMapping("loginExitoso")
	public ModelAndView loginExitoso() {
		return new ModelAndView("loginExitoso");
	}

	@GetMapping("administrador")
	public ModelAndView admin() {
		return new ModelAndView("admin");
	}

	@GetMapping("alumno")
	public ModelAndView usuario() {
		return new ModelAndView("alumno");
	}

	@GetMapping("profesor")
	public ModelAndView profesor() {
		return new ModelAndView("profesor");
	}

	@GetMapping("logout")
	public ModelAndView logout() {
		return new ModelAndView("logout");
	}

	@GetMapping("error")
	public ModelAndView error() {
		return new ModelAndView("error");
	}

}