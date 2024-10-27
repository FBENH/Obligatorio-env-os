package bios.obligatorio.envios.obligatorio_envios.controladores;

import java.security.Principal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import bios.obligatorio.envios.obligatorio_envios.dominio.Paquete;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionProyectoEnvios;
import bios.obligatorio.envios.obligatorio_envios.servicios.IServicioEstadoRastreo;
import bios.obligatorio.envios.obligatorio_envios.servicios.IServicioPaquete;
import bios.obligatorio.envios.obligatorio_envios.servicios.IServicioCategorias;
import bios.obligatorio.envios.obligatorio_envios.servicios.IServicioClientes;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/paquetes")
public class ControladorPaquetes {

    @Autowired
    private IServicioPaquete servicioPaquete;

    @Autowired
    private IServicioEstadoRastreo servicioEstadoRastreo;

    @Autowired
    private IServicioCategorias servicioCategorias;   

    @Autowired
    private IServicioClientes servicioClientes;
    
    @GetMapping
    public String paquetes(@RequestParam(required = false) String criterio, @RequestParam(defaultValue = "0") Integer filtroEstado, 
    @RequestParam(required = false, name = "filtroFecha") String filtroFecha ,Model model, Pageable pageable) {

        LocalDate fecha = null;        
        if (filtroFecha != null && !filtroFecha.isEmpty()) {
            try {                
                fecha = LocalDate.parse(filtroFecha);                
            } catch (Exception e) {
                model.addAttribute("mensaje", "Error. Fecha inválida.");
                return "paquetes/index";
            }
        }

        Page<Paquete> paquetes = servicioPaquete.buscar(criterio, filtroEstado, fecha, pageable);
        model.addAttribute("paquetes", paquetes);

        return "paquetes/index";
    }

    @GetMapping("/{id}")
    public String verDetallePaquete(@PathVariable String id, Model model) {
        Integer idPaquete;
        try {
            idPaquete = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            model.addAttribute("mensaje", "Error. El identificador del paquete debe ser un número.");
            return "sucursales/detalle";
        }
        Paquete paquete = servicioPaquete.obtener(idPaquete);
        if (paquete != null)
            model.addAttribute("paquete", paquete);
        else
            model.addAttribute("mensaje", "Error. No se encontró el paquete " + id + ".");
        
        return "paquetes/detalle";
    }    

    @GetMapping("/agregar")
    public String mostrarAgregarPaquete(@ModelAttribute Paquete paquete, Model model) {        
        model.addAttribute("categorias", servicioCategorias.listar());
        model.addAttribute("estadosRastreo", servicioEstadoRastreo.listar());
        
        return "paquetes/agregar";
    }

    @PostMapping("/agregar")
    public String procesarAgregar(@ModelAttribute @Valid Paquete paquete, BindingResult result, Model model, RedirectAttributes attributes) {       

        if (result.hasErrors()) {            
            model.addAttribute("categorias", servicioCategorias.listar());
            model.addAttribute("estadosRastreo", servicioEstadoRastreo.listar());            
            return "paquetes/agregar";
        }

        try {               
            servicioPaquete.agregar(paquete);  
            attributes.addFlashAttribute("mensaje", "Se creó el paquete con éxito"); 
            return "redirect:/paquetes";
        } catch (ExcepcionProyectoEnvios e) {            
            model.addAttribute("categorias", servicioCategorias.listar());
            model.addAttribute("estadosRastreo", servicioEstadoRastreo.listar());
            model.addAttribute("mensaje", "Error: " + e.getMessage());
            return "paquetes/agregar";
        }
    }

    @GetMapping("/modificar")
    public String modificarPaquete(Integer id, Model model) {
        model.addAttribute("estados", servicioEstadoRastreo.listar());
        model.addAttribute("categorias", servicioCategorias.listar());

        Paquete paquetes = servicioPaquete.obtener(id);        

        if (paquetes != null) {
            model.addAttribute("paquetes",paquetes);
        } else {
            model.addAttribute("mensaje","Error. No se encontró el paquete "+ id +".");
        }        

        return "paquetes/modificar";
    }

    @PostMapping("/modificar")
    public String procesarModificarPaquete(@ModelAttribute @Valid Paquete paquete, BindingResult result, RedirectAttributes attributes, Model model) {

        if (result.hasErrors()) { 
            model.addAttribute("categorias", servicioCategorias.listar());
            model.addAttribute("estadosRastreo", servicioEstadoRastreo.listar());             
            return "paquetes/modificar";
        }

        try {
            servicioPaquete.modificar(paquete);
            attributes.addFlashAttribute("mensaje","Se modificó el paquete con éxito");

            return "redirect:/paquetes";
        } catch (ExcepcionProyectoEnvios e) {
            model.addAttribute("mensaje", "Error al modificar el paquete " + e.getMessage());
            return "paquetes/modificar";
        }        
    }

    @GetMapping("/eliminar")
    public String mostrarEliminarPaquete(Integer id, Model model) {
        Paquete paquete = servicioPaquete.obtener(id);

        if (paquete != null) {
            model.addAttribute("paquete", paquete);
        } else {
            model.addAttribute("mensaje", "Error. No se encontró el paquete con el código " + id + ".");
        }

        return "paquetes/eliminar";
    }

    @PostMapping("/eliminar")
    public String procesarEliminarPaquete(Integer id, Model model, RedirectAttributes attributes) {
        try {
            servicioPaquete.eliminar(id);

            attributes.addFlashAttribute("mensaje", "Paquete eliminado con éxito.");

            return "redirect:/paquetes";
        } catch (ExcepcionProyectoEnvios e) {
            model.addAttribute("mensaje", "Error. " + e.getMessage());

            return "paquetes/eliminar";
        }
    }

    @GetMapping("/registrar")
    public String autoRegistroPaquete(@ModelAttribute Paquete paquete, Model model, Principal principal) {    
        paquete.setCliente(servicioClientes.obtener(principal.getName()));    
        model.addAttribute("categorias", servicioCategorias.listar());
        model.addAttribute("estadosRastreo", servicioEstadoRastreo.listar());        

        return "paquetes/auto-registro";
    }

    @PostMapping("/registrar")
    public String procesarAutoRegistroPaquete(@ModelAttribute @Valid Paquete paquete, BindingResult result, Model model, RedirectAttributes attributes, Principal principal) {       

        if (result.hasErrors()) {            
            model.addAttribute("categorias", servicioCategorias.listar());
            model.addAttribute("estadosRastreo", servicioEstadoRastreo.listar());            
            paquete.setCliente(servicioClientes.obtener(principal.getName()));    

            return "paquetes/auto-registro";
        }

        try {               
            servicioPaquete.agregar(paquete);  
            attributes.addFlashAttribute("mensaje", "Se creó el paquete con éxito"); 
            return "redirect:/paquetes/listar";
        } catch (ExcepcionProyectoEnvios e) {            
            model.addAttribute("categorias", servicioCategorias.listar());
            model.addAttribute("estadosRastreo", servicioEstadoRastreo.listar());
            paquete.setCliente(servicioClientes.obtener(principal.getName()));    
            model.addAttribute("mensaje", "Error: " + e.getMessage());

            return "paquetes/auto-registro";
        }
    }

    @GetMapping("/listar")
    public String listarPaquetesDeCliente(Principal principal, Pageable pageable, Model model) {
        model.addAttribute("paquetes", servicioPaquete.listarPorCliente(principal.getName(), pageable));

        return "paquetes/listar";
    }
}
