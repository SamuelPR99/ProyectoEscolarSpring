package com.daw.proyectoescolar.controladores;

import java.sql.Date;
import java.util.List;

import com.daw.proyectoescolar.entidades.Alumno;
import com.daw.proyectoescolar.repositorio.FechaYHora;
import com.daw.proyectoescolar.repositorio.TemasRepo;
import com.daw.proyectoescolar.servicios.temas.GestionTemas;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;

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
		List<UsuarioBase> usuarios = gestionUsuarios.obtenerUsuarios();

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
	public ModelAndView loguearUsuario(@RequestParam String nombre, @RequestParam String contrasena, HttpSession session) {

		ModelAndView mav = new ModelAndView();
		List<UsuarioBase> usuarios = gestionUsuarios.obtenerUsuarios();

		UsuarioBase usuario = gestionUsuarios.login(nombre, contrasena, usuarios);

		if (usuario != null) {
			int id = usuario.getUsuarioId();
			GestionTemas gt = new GestionTemas();

			session.setAttribute("usuario", usuario); // Guardar el usuario en la sesión
			mav.addObject("usuario", usuario); // Añadimos el usuario a la vista para poder mostrar su nombre
			if (usuario.getTipoUsuario().equals("Administrador")) {
				mav.setViewName("administrador");
			} else if (usuario.getTipoUsuario().equals("Alumno")) {
				mav.setViewName("alumno");
				mav.addObject("tareasAsignadas", gt.tareasAsignadas(id)); // Añadimos las tareas asignadas al alumno
				mav.addObject("tareasEntregadas", gt.tareasEntregadas(id)); // Añadimos las tareas entregadas por el alumno
				mav.addObject("fechaActual", FechaYHora.fechaActual()); // Añadir la fecha actual a la vista
			} else if (usuario.getTipoUsuario().equals("Profesor")) {
				mav.setViewName("profesor");
				mav.addObject("alumnos", gestionUsuarios.obtenerAlumnos(usuarios));
				mav.addObject("temas", gt.obtenerTemas());
			} else {
				mav.addObject("mensaje", "Usuario o contraseña incorrectos");
				mav.setViewName("login");
			}
		} else {
			mav.addObject("mensaje", "Usuario o contraseña incorrectos");
			mav.setViewName("login");
		}
		return mav;
	}

	@PostMapping("entregarTarea")
	public ModelAndView entregarTarea(@RequestParam int idTarea, @RequestParam int idAlumno) {
		ModelAndView mav = new ModelAndView();
		GestionTemas gt = new GestionTemas();
		gt.entregarTarea(idTarea, idAlumno);
		mav.setViewName("redirect:/alumno"); // Redirige de nuevo a la página del alumno
		return mav;
	}

	@PostMapping("cambiarNota")
	public ModelAndView cambiarNota(@RequestParam int idAlumno, @RequestParam double nota) {
		ModelAndView mav = new ModelAndView();
		GestionUsuarios gu = new GestionUsuarios();
		gu.modificarNotaAlumno(idAlumno, nota);
		mav.setViewName("redirect:profesor"); // Redirige de nuevo a la página del profesor
		return mav;
	}

	@PostMapping("asignarTarea")
	public ModelAndView asignarTarea(@RequestParam int idTarea, @RequestParam Date fechaExpiracion, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		GestionUsuarios gu = new GestionUsuarios();
		GestionTemas gt = new GestionTemas();
		UsuarioBase usuario = (UsuarioBase) session.getAttribute("usuario");
		List<Alumno> alumnos = gu.obtenerAlumnos(gu.obtenerUsuarios());
		for (Alumno alumno : alumnos) {
			gt.asignarTarea(idTarea, alumno.getUsuarioId(), usuario.getUsuarioId(), fechaExpiracion);
		}
		mav.setViewName("redirect:profesor"); // Redirige de nuevo a la página del profesor
		return mav;
	}

	@GetMapping("loginExitoso")
	public ModelAndView loginExitoso() {
		return new ModelAndView("loginExitoso");
	}

	@GetMapping("administrador")
	public ModelAndView admin(HttpSession session) {
		return new ModelAndView("admin");
	}

	@GetMapping("alumno")
	public ModelAndView alumno(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		UsuarioBase usuario = (UsuarioBase) session.getAttribute("usuario"); // Recuperar el usuario de la sesión
		int id = usuario.getUsuarioId();
		GestionTemas gt = new GestionTemas();
		if (usuario != null) {
			mav.addObject("usuario", usuario); // Añadir el usuario a la vista
			mav.setViewName("alumno");
			mav.addObject("tareasAsignadas", gt.tareasAsignadas(id)); // Añadimos las tareas asignadas al alumno
			mav.addObject("tareasEntregadas", gt.tareasEntregadas(id)); // Añadimos las tareas entregadas por el alumno
			mav.addObject("fechaActual", FechaYHora.fechaActual()); // Añadir la fecha actual a la vista
		} else {
			mav.setViewName("error");
		}
		return mav;
	}

	@GetMapping("profesor")
	public ModelAndView profesor(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		GestionTemas gt = new GestionTemas();
		UsuarioBase usuario = (UsuarioBase) session.getAttribute("usuario"); // Recuperar el usuario de la sesión
		if (usuario != null) {
			mav.addObject("usuario", usuario); // Añadir el usuario a la vista
			mav.setViewName("profesor");
			mav.addObject("alumnos", gestionUsuarios.obtenerAlumnos(gestionUsuarios.obtenerUsuarios()));
			mav.addObject("temas", gt.obtenerTemas());
		} else {
			mav.setViewName("error");
		}
		return mav;
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