package bios.obligatorio.envios.obligatorio_envios.controladores;

import java.security.Principal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
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
    public String miCuenta(Model model, Principal principal) {    
        
        Cliente cliente = servicioClientes.obtener(principal.getName());

        if (cliente != null)
            model.addAttribute("cliente",cliente);
        else
            model.addAttribute("mensaje", "Error. No se pudieron obtener los datos de la cuenta");

        return "clientes/mi-cuenta";
    }

    @GetMapping("/micuenta/editar")
    public String editarCuenta(@ModelAttribute Cliente cliente, Model model, Principal principal) {
        
        cliente = servicioClientes.obtener(principal.getName());

        if (cliente != null) {
            model.addAttribute("contrasenaFalsa", UUID.randomUUID().toString());
            model.addAttribute("cliente",cliente);
        }
        else
            model.addAttribute("mensaje", "Error. No se pudieron obtener los datos de la cuenta");


        return "clientes/editar-cuenta";
    }

    @PostMapping("/micuenta/editar")
    public String procesarEditarCuenta(@ModelAttribute @Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes attributes, 
     Principal principal, String contrasenaFalsa) {        

        if (result.hasErrors()) {
            model.addAttribute("mensaje", "Error al editar los datos de la cuenta.");
            model.addAttribute("contrasenaFalsa", UUID.randomUUID().toString());
            return "clientes/editar-cuenta";
        }
        try {
            if (cliente.getRepetirContrasena() == null || !cliente.getRepetirContrasena().equals(cliente.getClave())) {
                model.addAttribute("contrasenaFalsa", UUID.randomUUID().toString());
    
                throw new ExcepcionProyectoEnvios("Las contraseñas no coinciden.");
            }           

            cliente.setNombreUsuario(principal.getName());
            servicioClientes.modificar(cliente, !cliente.getClave().equals(contrasenaFalsa));            

            attributes.addFlashAttribute("mensaje","Se modificaron los datos de la cuenta con éxito.");
            return "redirect:/micuenta";
        } catch (ExcepcionProyectoEnvios e) {
            model.addAttribute("mensaje", "Error al modificar los datos de la cuenta. " + e.getMessage());            
            return "clientes/editar-cuenta";
        }
    }

    @GetMapping("/micuenta/eliminar")
    public String eliminarCuenta(@ModelAttribute Cliente cliente, Model model, Principal principal) {

        cliente = servicioClientes.obtener(principal.getName());

        if (cliente != null)
            model.addAttribute("cliente",cliente);
        else
            model.addAttribute("mensaje", "Error. No se pudieron obtener los datos de la cuenta");


        return "clientes/eliminar-cuenta";
    }

    @PostMapping("/micuenta/eliminar")
    public String procesarEliminarCuenta(RedirectAttributes attributes, Model model, HttpServletRequest request, HttpServletResponse response, Principal principal) {

        try {
            servicioClientes.eliminar(principal.getName());
            attributes.addFlashAttribute("mensaje", "Se eliminó su cuenta con éxito");
            SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
            logoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());
            return "redirect:/";
        } catch (ExcepcionProyectoEnvios e) {
            model.addAttribute("mensaje", "Error. " + e.getMessage());
            return "clientes/eliminar-cuenta";
        }        
    }   
}
