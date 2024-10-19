package bios.obligatorio.envios.obligatorio_envios.controladores;

import java.security.Principal;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ControladorLoginLogout {    
    
    @GetMapping("/ingresar")
    public String login(Principal principal, @RequestParam(required = false, value = "error") String error, Model model) {        
        if (error != null) {            
            model.addAttribute("mensaje", "Nombre de usuario o contraseña incorrectos.");
            return "login";
        }
        if (principal == null || principal instanceof AnonymousAuthenticationToken){            
            return "login";
        }            
        
        return "redirect:/";
    }

    @GetMapping("/salir")
    public String logout() {
        return "logout";
    }   


}
