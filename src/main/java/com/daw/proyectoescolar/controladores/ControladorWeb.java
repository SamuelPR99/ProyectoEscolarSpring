package com.daw.proyectoescolar.controladores;

import java.sql.Date;

import com.daw.proyectoescolar.entidades.Alumno;
import com.daw.proyectoescolar.repositorio.FechaYHora;
import com.daw.proyectoescolar.servicios.incidencias.GestionIncidencias;
import com.daw.proyectoescolar.servicios.logs.GestionLogs;
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

	private final GestionUsuarios gestionUsuarios = new GestionUsuarios();
	private final GestionTemas gestionTemas = new GestionTemas();
	private final GestionIncidencias gestionIncidencias = new GestionIncidencias();

	@GetMapping("login")
	public ModelAndView login() {
		GestionLogs.logOpcionMenu("Login", "Login");
		return new ModelAndView("login");
	}

	@GetMapping("registro")
	public ModelAndView registro() {
		GestionLogs.logOpcionMenu("Registro", "Registro");
		return new ModelAndView("registro");
	}

	@GetMapping("registroExitoso")
	public ModelAndView registroExitoso() {
		GestionLogs.logOpcionMenu("Registro Exitoso", "Registro Exitoso");
		return new ModelAndView("registroExitoso");
	}

	@PostMapping("registro")
	public ModelAndView registrarUsuario(@RequestParam String nombre, @RequestParam String dni,
			@RequestParam String contrasena, @RequestParam String tipo) {

		ModelAndView mav = new ModelAndView();

		if (gestionUsuarios.validarNombreUsuario(nombre) && gestionUsuarios.validarContrasena(contrasena)
				&& gestionUsuarios.validarDNI(dni)) {
			gestionUsuarios.registro(nombre, dni, contrasena, tipo, gestionUsuarios.obtenerUsuarios());
			mav.setViewName("registroExitoso");
			GestionLogs.logOpcionMenu("Registro", "Usuario registrado: " + nombre);
		} else {
			mav.addObject("mensaje", "Error en algun campo, vuelve a registrarte");
		}
		return mav;
	}

	@PostMapping("login")
	public ModelAndView loguearUsuario(@RequestParam String nombre, @RequestParam String contrasena, HttpSession session) {

		ModelAndView mav = new ModelAndView();
		UsuarioBase usuario = gestionUsuarios.login(nombre, contrasena, gestionUsuarios.obtenerUsuarios());

		if (usuario != null) {
			GestionLogs.logOpcionMenu("Login", "Usuario logueado: " + usuario.getNombre());
			session.setAttribute("usuario", usuario); // Guardar el usuario en la sesión
			mav.addObject("usuario", usuario); // Para poder mostrar su nombre
			if (usuario.getTipoUsuario().equals("Administrador")) {
				mav.setViewName("redirect:/administrador");
			} else if (usuario.getTipoUsuario().equals("Alumno")) {
				mav.setViewName("redirect:/alumno");
			} else if (usuario.getTipoUsuario().equals("Profesor")) {
				mav.setViewName("redirect:/profesor");
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

		gestionTemas.entregarTarea(idTarea, idAlumno);
		GestionLogs.logOpcionMenu("Entregar Tarea", "Tarea entregada por el alumno con ID: " + idAlumno);
		mav.setViewName("redirect:/alumno");
		return mav;
	}

	@PostMapping("cambiarNota")
	public ModelAndView cambiarNota(@RequestParam int idAlumno, @RequestParam double nota) {

		ModelAndView mav = new ModelAndView();

		gestionUsuarios.modificarNotaAlumno(idAlumno, nota);
		GestionLogs.logOpcionMenu("Cambiar Nota", "Nota cambiada al alumno con ID: " + idAlumno);
		mav.setViewName("redirect:profesor");
		return mav;
	}

	@PostMapping("asignarTarea")
	public ModelAndView asignarTarea(@RequestParam int idTarea, @RequestParam Date fechaExpiracion, HttpSession session) {

		ModelAndView mav = new ModelAndView();

		UsuarioBase usuario = (UsuarioBase) session.getAttribute("usuario");
		for (Alumno alumno : gestionUsuarios.obtenerAlumnos(gestionUsuarios.obtenerUsuarios())) {
			gestionTemas.asignarTarea(idTarea, alumno.getUsuarioId(), usuario.getUsuarioId(), fechaExpiracion);
		}

		GestionLogs.logOpcionMenu("Asignar Tarea", "Tarea asignada a todos los alumnos");
		mav.setViewName("redirect:profesor");
		return mav;
	}

	@GetMapping("administrador")
	public ModelAndView admin(HttpSession session) {

		ModelAndView mav = new ModelAndView();
		UsuarioBase usuario = (UsuarioBase) session.getAttribute("usuario"); // Recuperar el usuario de la sesión
		if (usuario != null) {
			mav.addObject("usuario", usuario); // Añadir el usuario a la vista
			mav.setViewName("administrador");
			mav.addObject("usuarios", gestionUsuarios.obtenerUsuarios());
			mav.addObject("incidencia", gestionIncidencias.hashMapUsuariosIncidencias());
			GestionLogs.logOpcionMenu("Administrador", "Administrador logueado: " + usuario.getNombre());
		} else {
			mav.setViewName("error");
		}

		return mav;
	}

	@PostMapping("borrarUsuario")
	public ModelAndView borrarUsuario(@RequestParam int usuarioId) {

		ModelAndView mav = new ModelAndView();
		gestionUsuarios.borrarUsuario(usuarioId);
		GestionLogs.logOpcionMenu("Borrar Usuario", "Usuario borrado con ID: " + usuarioId);
		mav.setViewName("redirect:administrador");
		return mav;
	}

	@GetMapping("alumno")
	public ModelAndView alumno(HttpSession session) {

		ModelAndView mav = new ModelAndView();
		UsuarioBase usuario = (UsuarioBase) session.getAttribute("usuario"); // Recuperar el usuario de la sesión
		int id = usuario.getUsuarioId();

		if (usuario != null) {
			mav.addObject("usuario", usuario); // Añadir el usuario a la vista
			mav.setViewName("alumno");
			mav.addObject("tareasAsignadas", gestionTemas.tareasAsignadas(id)); // Añadimos las tareas asignadas al alumno
			mav.addObject("tareasEntregadas", gestionTemas.tareasEntregadas(id)); // Añadimos las tareas entregadas por el alumno
			mav.addObject("fechaActual", FechaYHora.fechaActual()); // Añadir la fecha actual a la vista
			GestionLogs.logOpcionMenu("Alumno", "Alumno logueado: " + usuario.getNombre());
		} else {
			mav.setViewName("error");
		}
		return mav;
	}

	@GetMapping("profesor")
	public ModelAndView profesor(HttpSession session) {

		ModelAndView mav = new ModelAndView();

		UsuarioBase usuario = (UsuarioBase) session.getAttribute("usuario"); // Recuperar el usuario de la sesión
		if (usuario != null) {
			mav.addObject("usuario", usuario); // Añadir el usuario a la vista
			mav.setViewName("profesor");
			mav.addObject("alumnos", gestionUsuarios.obtenerAlumnos(gestionUsuarios.obtenerUsuarios()));
			mav.addObject("temas", gestionTemas.obtenerTemas());
			mav.addObject("tareasEntregadasATiempoPorAlumno", gestionTemas.tareasEntregadasATiempoPorAlumno());
			mav.addObject("notaMediaPorAlumno", gestionUsuarios.obtenerNotaMediaTareasEntregadas());
			GestionLogs.logOpcionMenu("Profesor", "Profesor logueado: " + usuario.getNombre());
		} else {
			mav.setViewName("error");
		}
		return mav;
	}

	@GetMapping("incidencias")
	public ModelAndView incidencias(HttpSession session) {

		ModelAndView mav = new ModelAndView();
		UsuarioBase usuario = (UsuarioBase) session.getAttribute("usuario");
		mav.addObject("usuario", usuario);

		return mav;
	}

	@PostMapping("buscarIncidencias")
	public ModelAndView buscarIncidencias(@RequestParam String busqueda, HttpSession session) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("listaBusqueda", gestionIncidencias.buscadorDeIncidencias(busqueda));

		// Agregar los demás objetos necesarios al modelo
		UsuarioBase usuario = (UsuarioBase) session.getAttribute("usuario");
		mav.addObject("usuario", usuario);
		mav.addObject("usuarios", gestionUsuarios.obtenerUsuarios());
		mav.addObject("incidencia", gestionIncidencias.hashMapUsuariosIncidencias());
		GestionLogs.logOpcionMenu("Buscar Incidencias", "Incidencias buscadas: " + busqueda);
		mav.setViewName("administrador");
		return mav;
	}

	@PostMapping("eliminarIncidencia")
	public ModelAndView eliminarIncidencia(@RequestParam int incidenciaId) {

		ModelAndView mav = new ModelAndView();
		gestionIncidencias.eliminarIncidencias(incidenciaId);
		GestionLogs.logOpcionMenu("Eliminar Incidencia", "Incidencia eliminada con ID: " + incidenciaId);
		mav.setViewName("redirect:administrador");
		return mav;
	}

	@GetMapping("error")
	public ModelAndView error() {
		return new ModelAndView("error");
	}

}