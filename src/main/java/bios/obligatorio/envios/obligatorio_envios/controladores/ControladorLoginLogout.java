package bios.obligatorio.envios.obligatorio_envios.controladores;

import java.security.Principal;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ControladorLoginLogout {    
    
    @GetMapping("/ingresar")
    public String login(Principal principal) {
        if (principal == null || principal instanceof AnonymousAuthenticationToken){            
            return "login";
        }            
        else
            return "redirect:/";
    }

    @GetMapping("/salir")
    public String logout() {
        return "logout";
    }   


}
