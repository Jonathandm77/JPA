package aplicacion.controlador;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import aplicacion.modelo.Alumno;
import aplicacion.modelo.Pedido;
import aplicacion.persistencia.AlumnoDAO;
@RequestMapping("/alumnos")
@Controller
public class AlumnosController {
	
	AlumnoDAO alumnoDAO=new AlumnoDAO();
	@GetMapping(value={"","/"})
	String homealumnos(Model model) {
		//Salir a buscar a la BBDD
		ArrayList<Alumno> misAlumnos=alumnoDAO.listarAlumnosJPA();
		model.addAttribute("listaAlumnos", misAlumnos);
		model.addAttribute("alumnoaEditar", new Alumno());
		model.addAttribute("alumnoNuevo", new Alumno());
		return "alumnos";
	}
	@PostMapping("/edit/{id}")
	public String editarAlumno(@PathVariable Integer id, @ModelAttribute("alumnoaEditar") Alumno alumnoEditado, BindingResult bidingresult) {
		Alumno alumnoaEditar=alumnoDAO.buscarIDJPA(id);
		alumnoaEditar.setNombre(alumnoEditado.getNombre());
		alumnoDAO.editarAlumnoJPA(alumnoaEditar);
		return "redirect:/alumnos";
	}
	@GetMapping({"/{id}"})
	String idAlumno(Model model, @PathVariable Integer id) {
		Alumno alumnoMostrar=alumnoDAO.buscarIDJPA(id);
		model.addAttribute("alumnoMostrar", alumnoMostrar);
		return "alumno";
	}
	@GetMapping({"/delete/{id}"})
	String deleteAlumno(Model model, @PathVariable Integer id) {
		Alumno alumnoaEliminar=alumnoDAO.buscarIDJPA(id);
		alumnoDAO.eliminarAlumnoJPA(alumnoaEliminar);
		return "redirect:/alumnos";
	}
	@PostMapping("/add")
	public String addAlumno(@ModelAttribute("alumnoNuevo") Alumno alumnoNew, BindingResult bidingresult) {
		alumnoDAO.insertarAlumnoJPA(alumnoNew);
		return "redirect:/alumnos";
	}
}
