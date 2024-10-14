package bios.obligatorio.envios.obligatorio_envios.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bios.obligatorio.envios.obligatorio_envios.dominio.Cliente;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionProyectoEnvios;
import bios.obligatorio.envios.obligatorio_envios.servicios.IServicioClientes;
import jakarta.validation.Valid;

public class ControladorClientes {
    
    @Autowired
    IServicioClientes servicioClientes;

    @GetMapping("/registrarcliente")
    public String registro(@ModelAttribute Cliente cliente) {
        return "registro";
    }

    @PostMapping("/registrarcliente")
    public String procesarRegistro(@ModelAttribute @Valid Cliente cliente, BindingResult result,Model model, RedirectAttributes attributes, @RequestParam(value = "passwordrepetida") String passwordrepetida) {

        if (result.hasErrors() || !cliente.getClave().equals(passwordrepetida)) {            
            if (!cliente.getClave().equals(passwordrepetida)) {                
                result.rejectValue("clave", "error.clave", "Las contraseñas no coinciden.");
            }            
            return "registro";
        }
        try{            
            servicioClientes.registrar(cliente);
            attributes.addFlashAttribute("mensaje","Se creó la cuenta con éxito.");
            return "redirect:/";
        }
        catch(ExcepcionProyectoEnvios e) {
            model.addAttribute("mensaje", "Error "+ e.getMessage());
            return "registro";
        }        
    }

    @GetMapping("/micuenta")
    public String miCuenta() {
        return "clientes/mi-cuenta";
    }

    @GetMapping("/micuenta/editar")
    public String editarCuenta() {

        return "clientes/editar-cuenta";
    }

    @PostMapping("/micuenta/editar")
    public String procesarEditarCuenta() {

        return "clientes/editar-cuenta";
    }

    @GetMapping("/micuenta/eliminar")
    public String eliminarCuenta() {

        return "clientes/eliminar-cuenta";
    }

    @PostMapping("/micuenta/eliminar")
    public String procesarEliminarCuenta() {

        return "clientes/eliminar-cuenta";
    }



}
