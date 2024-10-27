package bios.obligatorio.envios.obligatorio_envios.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


import bios.obligatorio.envios.obligatorio_envios.dominio.EstadoRastreo;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionProyectoEnvios;
import bios.obligatorio.envios.obligatorio_envios.servicios.IServicioEstadoRastreo;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/estadosrastreo")
public class ControladorEstados {

    @Autowired
    private IServicioEstadoRastreo servicioEstadoRastreo;

    @GetMapping
    public String estados(@RequestParam(required = false) String criterio, Model model, Pageable pageable) {
        Page<EstadoRastreo> estadosRastreo = servicioEstadoRastreo.buscar(criterio, pageable);
        model.addAttribute("estados", estadosRastreo);

        return "estados/index";
    }

    @GetMapping("/agregar")
    public String agregarEstadoRastreo(@ModelAttribute EstadoRastreo estadoRastreo) {

        return "estados/agregar";
    }

    @PostMapping("/agregar")
    public String procesarAgregarEstado(@ModelAttribute @Valid EstadoRastreo estadoRastreo, BindingResult result, Model model, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            return "estados/agregar";
        }

        try {
            
            servicioEstadoRastreo.agregar(estadoRastreo);
            attributes.addFlashAttribute("mensaje","Se agregó el Estado de Rastreo con éxito.");
            return "redirect:/estadosrastreo";   

        } catch (ExcepcionProyectoEnvios e) {
            model.addAttribute("mensaje", "Error al agregar un Estado de Rastreo. " + e.getMessage());
            return "estados/agregar";
        }
    }

    @GetMapping("/{id}")
    public String detalleEstado(@PathVariable String id, Model model) {

        Integer idEstado;
        try {
            idEstado = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            model.addAttribute("mensaje", "Error. El identificador del Estado de Rastreo debe ser un número.");
            return "estados/detalle";
        }

        EstadoRastreo estadoRastreo = servicioEstadoRastreo.obtener(idEstado);

        if (estadoRastreo != null) 
            model.addAttribute("estado", estadoRastreo);
        else
            model.addAttribute("mensaje", "Error. No se encontró el Estado de rastreo con id " + id + ".");


        return "estados/detalle";
    }

    @GetMapping("/modificar")
    public String modificarEstado(Integer id, Model model) {
        EstadoRastreo estadoRastreo = servicioEstadoRastreo.obtener(id);

        if (estadoRastreo != null) {
        model.addAttribute("estado", estadoRastreo); }
        else {
        model.addAttribute("mensaje", "Error. No se encontró el estado " + estadoRastreo + "."); } 

        return "estados/modificar";
    }

    @PostMapping("/modificar")
    public String procesarModificarEstado(@ModelAttribute(name = "estado") @Valid EstadoRastreo estado, BindingResult result, RedirectAttributes attributes, Model model) {
        if (result.hasErrors()) {                       
            return "estados/modificar";
        }
        try {
            servicioEstadoRastreo.modificar(estado);
            attributes.addFlashAttribute("mensaje","Se modificó el Estado de Rastreo con éxito.");
            return "redirect:/estadosrastreo";
        } catch (ExcepcionProyectoEnvios e) {            
            model.addAttribute("mensaje", "Error al modificar un Estado de Rastreo. " + e.getMessage());
            return "estados/modificar";
        }
    }

    @GetMapping("/eliminar")
    public String eliminarEstado(Integer id, Model model) {

        EstadoRastreo estado = servicioEstadoRastreo.obtener(id);
        if (estado != null) {
            model.addAttribute("estado", estado);
        } else {
            model.addAttribute("mensaje","Error. No se encontró el Estado con id " + id + ".");
        }   

        return "estados/eliminar";
    }

    @PostMapping("/eliminar")
    public String procesarEliminarEstado(Integer id, Model model, RedirectAttributes attributes) {
        try {
             servicioEstadoRastreo.eliminar(id);

             attributes.addFlashAttribute("mensaje", "Estado de Rastreo eliminado con exito.");

            return "redirect:/estadosrastreo";
         } 
        
         catch (ExcepcionProyectoEnvios e) {
            model.addAttribute("mensaje", "Error. " + e.getMessage());

          return "estados/eliminar";
         }
    }
    
}
