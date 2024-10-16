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

import bios.obligatorio.envios.obligatorio_envios.dominio.Empleado;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionProyectoEnvios;
import bios.obligatorio.envios.obligatorio_envios.servicios.IServicioEmpleados;
import bios.obligatorio.envios.obligatorio_envios.servicios.IServicioSucursales;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/empleados")
public class ControladorEmpleados {
    
    @Autowired
    private IServicioEmpleados servicioEmpleados;
    @Autowired
    private IServicioSucursales servicioSucursales;

    @GetMapping
    public String empleados(@RequestParam(required = false) String criterio, Model model, Pageable pageable) {
        Page<Empleado> empleados = servicioEmpleados.buscar(criterio,pageable);
        model.addAttribute("empleados", empleados);

        return "empleados/index";
    }

    @GetMapping("/agregar")
    public String agregarEmpleado(@ModelAttribute Empleado empleado, Model model) {
        model.addAttribute("sucursales", servicioSucursales.buscar(null));

        return "empleados/agregar";
    }

    @PostMapping("/agregar")
    public String procesarAgregarEmpleado(@ModelAttribute @Valid Empleado empleado, BindingResult result, Model model, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            model.addAttribute("mensaje", "Error al agregar un empleado.");
            model.addAttribute("sucursales", servicioSucursales.buscar(null));
            return "empleados/agregar";
        }
        try {
            servicioEmpleados.agregar(empleado);
            attributes.addFlashAttribute("mensaje","Se agregó el empleado con éxito.");
            return "redirect:/empleados";            
        } catch (ExcepcionProyectoEnvios e) {
            model.addAttribute("mensaje", "Error al agregar un empleado. " + e.getMessage());
            model.addAttribute("sucursales", servicioSucursales.buscar(null));
            return "empleados/agregar";
        }
    }

    @GetMapping("/{nombreUsuario}")
    public String detalleEmpleado(@PathVariable String nombreUsuario, Model model) {
        Empleado empleado = servicioEmpleados.obtener(nombreUsuario);

        if (empleado != null) 
            model.addAttribute("empleado",empleado);
        else
            model.addAttribute("mensaje", "Error. No se encontró un empleado con nombre de usuario " + nombreUsuario + ".");


        return "empleados/detalle";
    }

    @GetMapping("/modificar")
    public String modificarEmpleado(String nombreUsuario, Model model) {
        model.addAttribute("sucursales", servicioSucursales.buscar(null));
        model.addAttribute("empleado", servicioEmpleados.obtener(nombreUsuario));
        return "empleados/modificar";
    }

    @PostMapping("/modificar")
    public String procesarModificarEmpleado(@ModelAttribute @Valid Empleado empleado, BindingResult result, RedirectAttributes attributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("mensaje", "Error al modificar un empleado.");
            model.addAttribute("sucursales", servicioSucursales.buscar(null));
            return "empleados/modificar";
        }
        try {
            servicioEmpleados.modificar(empleado);
            attributes.addFlashAttribute("mensaje","Se modificó un empleado con éxito.");
            return "redirect:/empleados";
        } catch (ExcepcionProyectoEnvios e) {
            model.addAttribute("mensaje", "Error al modificar un empleado. " + e.getMessage());
            model.addAttribute("sucursales", servicioSucursales.buscar(null));
            return "empleados/modificar";
        }
    }

    @GetMapping("/eliminar")
    public String eliminarEmpleado(String nombreUsuario, Model model) {
        model.addAttribute("empleado", servicioEmpleados.obtener(nombreUsuario));

        return "empleados/eliminar";
    }

    @PostMapping("/eliminar")
    public String procesarEliminarEmpleado(String nombreUsuario, RedirectAttributes attributes, Model model) {
        try {
            servicioEmpleados.eliminar(nombreUsuario);
            attributes.addFlashAttribute("mensaje", "Se eliminó el empleado");

            return "redirect:/empleados";
        } catch (ExcepcionProyectoEnvios e) {
            model.addAttribute("mensaje", "Error al eliminar el Empleado. " + e.getMessage());
            return "empleados/eliminar";
        }
    }
}
