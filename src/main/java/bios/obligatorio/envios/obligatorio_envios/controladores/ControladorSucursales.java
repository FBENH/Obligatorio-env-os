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

import bios.obligatorio.envios.obligatorio_envios.dominio.Sucursal;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionProyectoEnvios;
import bios.obligatorio.envios.obligatorio_envios.servicios.IServicioSucursales;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/sucursales")
public class ControladorSucursales {


    @Autowired
    private IServicioSucursales servicioSucursales;

    @GetMapping
    public String sucursales(@RequestParam(required = false) String criterio, Model model, Pageable pageable) {
        Page<Sucursal> sucursales = servicioSucursales.buscarConPaginacion(criterio, pageable);        
        model.addAttribute("sucursales", sucursales);
        
        return "sucursales/index";
    }

    @GetMapping("/agregar")
    public String agregarSucursal(@ModelAttribute Sucursal sucursal) {        

        return "sucursales/agregar";
    }
    
    @PostMapping("/agregar")
    public String procesarAgregarSucursal(@ModelAttribute @Valid Sucursal sucursal, BindingResult result, Model model, RedirectAttributes attributes) {
        
        if (result.hasErrors()) {
            return "sucursales/agregar";
        }

        try {
            servicioSucursales.agregar(sucursal);
            attributes.addFlashAttribute("mensaje","Sucursal agregada con éxito");

            return "redirect:/sucursales";
        } catch (ExcepcionProyectoEnvios e) {
            model.addAttribute("mensaje", "Error al agregar la sucursal. " + e.getMessage());

            return "sucursales/agregar";
        }        
    }

    @GetMapping("/modificar")
    public String modificarSucursal(Long numero, Model model) {
        Sucursal sucursal = servicioSucursales.obtener(numero);        

        if (sucursal != null) {
            model.addAttribute("sucursal",sucursal);
        } else {
            model.addAttribute("mensaje","Error. No se encontró la sucursal con el número "+ numero+".");
        }        

        return "sucursales/modificar";
    }

    @PostMapping("/modificar")
    public String procesarModificarSucursal(@ModelAttribute @Valid Sucursal sucursal, BindingResult result, RedirectAttributes attributes, Model model) {

        if (result.hasErrors())
            return "sucursales/modificar";

        try {
            servicioSucursales.modificar(sucursal);
            attributes.addFlashAttribute("mensaje","Se modificó la sucursal con éxito");
            return "redirect:/sucursales";
        } catch (ExcepcionProyectoEnvios e) {
            model.addAttribute("mensaje", "Error al modificar la sucursal. " + e.getMessage());
            return "sucursales/modificar";
        }        
    }
    @GetMapping("/eliminar")
    public String eliminarSucursal(Long numero, Model model) {

        Sucursal sucursal = servicioSucursales.obtener(numero);
        if (sucursal != null) {
            model.addAttribute("sucursal",sucursal);
        } else {
            model.addAttribute("mensaje","Error. No se encontró la sucursal con el número "+ numero+".");
        }   

        return "sucursales/eliminar";
    }

    @PostMapping("/eliminar")
    public String procesarEliminarSucursal(Long numero, Model model, RedirectAttributes attributes) {
        try {
            servicioSucursales.eliminar(numero);
            attributes.addFlashAttribute("mensaje", "Sucursal eliminada con éxito.");

            return "redirect:/sucursales";
        } catch (ExcepcionProyectoEnvios e) {
            model.addAttribute("mensaje", "Error al eliminar. " + e.getMessage());

            return "sucursales/eliminar";
        }        
    }

    @GetMapping("/{numero}")
    public String verDetalleSucursal(@PathVariable String numero, Model model) {
        Long idSucursal;
        try {
            idSucursal = Long.parseLong(numero);
        } catch (NumberFormatException e) {
            model.addAttribute("mensaje", "Error. El identificador de la sucursal debe ser un número.");
            return "sucursales/detalle";
        }

        Sucursal sucursal = servicioSucursales.obtener(idSucursal);
        if (sucursal != null)
            model.addAttribute("sucursal", sucursal);
        else
            model.addAttribute("mensaje", "Error. No se encontró la sucursal de número " + numero + ".");
        
        return "sucursales/detalle";
    }
    
}
