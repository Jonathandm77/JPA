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
import aplicacion.modelo.Bocadillo;
import aplicacion.modelo.Pedido;
import aplicacion.persistencia.AlumnoDAO;
import aplicacion.persistencia.AlumnoRepo;
import aplicacion.persistencia.BocadilloDAO;
import aplicacion.persistencia.BocadilloRepo;
import aplicacion.persistencia.PedidoDAO;
import aplicacion.persistencia.PedidoRepo;

@RequestMapping("/pedidos")
@Controller
public class PedidoController {

	// private AlumnoServiceImp alumnoService = new AlumnoServiceImp();

	@Autowired
	private PedidoRepo pedidoRepo;
	
	@Autowired
	private AlumnoRepo alumnoRepo;

	@Autowired
	private BocadilloRepo bocadilloRepo;

	@GetMapping(value = { "", "/" })
	String alumnos(Model model) {
		 	
		ArrayList<Pedido> mispedidos=(ArrayList<Pedido>) pedidoRepo.findAll();
        ArrayList<Alumno> misAlumnos= (ArrayList<Alumno>) alumnoRepo.findAll();
        ArrayList<Bocadillo> misBocadillos= (ArrayList<Bocadillo>) bocadilloRepo.findAll();
       
        model.addAttribute("listaPedidos", mispedidos);
        model.addAttribute("listaAlumnos", misAlumnos);
        model.addAttribute("listaBocadillos", misBocadillos);
		model.addAttribute("pedidoNuevo", new Pedido());
		

		return "pedidos";
	}

	@PostMapping("/add")
	public String addPedido(@ModelAttribute("pedidoNuevo") Pedido pedidoNuevo, BindingResult bindingResult) {

		pedidoNuevo.calcularPrecio();

		//Alumno alumnoNuevo = crudAlumno.buscarPorIdJPA(pedidoNuevo.getAlumno().getId());

		
		Alumno alumnoNuevo = alumnoRepo.findById(pedidoNuevo.getAlumno().getId()).get();
		
		alumnoNuevo.getPedidos().add(pedidoNuevo);
		pedidoNuevo.setAlumno(alumnoNuevo);

		pedidoRepo.save(pedidoNuevo);

		for (Bocadillo b : pedidoNuevo.getBocadillos()) {
			b.getPedidos().add(pedidoNuevo);
			//crudBocadillo.modificarBocadilloJPA(b);
			bocadilloRepo.save(b);
		}

		return "redirect:/pedidos";
	}

	@GetMapping({ "/{id}" })
	String idPedido(Model model, @PathVariable Integer id) {
		ArrayList<Pedido> mispedidos=(ArrayList<Pedido>) pedidoRepo.findAll();
        ArrayList<Alumno> misAlumnos= (ArrayList<Alumno>) alumnoRepo.findAll();
        ArrayList<Bocadillo> misBocadillos= (ArrayList<Bocadillo>) bocadilloRepo.findAll();
       
        model.addAttribute("listaPedidos", mispedidos);
        model.addAttribute("listaAlumnos", misAlumnos);
        model.addAttribute("listaBocadillos", misBocadillos);
		Pedido pedidoMostrar = pedidoRepo.findById(id).get();
		model.addAttribute("pedidoMostrar", pedidoMostrar);

		return "pedido";
	}
	@PostMapping("/edit/{id}")
	public String editarPedido(Model model, @PathVariable Integer id, @ModelAttribute ("pedidoMostrar") Pedido pedidoEditado) {
		Alumno a=alumnoRepo.findById(pedidoEditado.getAlumno().getId()).get();
		pedidoEditado.setAlumno(a);
		Pedido pedidoaEditar=pedidoRepo.findById(id).get();
		for(Bocadillo b:pedidoaEditar.getBocadillos()) {
			if(!pedidoEditado.getBocadillos().contains(b)) {
				b.getPedidos().remove(pedidoaEditar);
			}
		}
		for(Bocadillo b:pedidoEditado.getBocadillos()) {
			if(!pedidoaEditar.getBocadillos().contains(b)) {
				b.getPedidos().add(pedidoEditado);
			}
		}
		pedidoEditado.calcularPrecio();
		pedidoRepo.save(pedidoEditado);
		return "redirect:/pedidos";
	}
	@GetMapping({ "/buscar/{nombre}" })
	public String obtenerPedido(@PathVariable String nombre) {
		return "pedido";
	}

	@GetMapping({ "/delete/{id}" })
	String deletePedido(Model model, @PathVariable Integer id) {
		
		pedidoRepo.deleteById(id);

		return "redirect:/pedidos";

	}

}
