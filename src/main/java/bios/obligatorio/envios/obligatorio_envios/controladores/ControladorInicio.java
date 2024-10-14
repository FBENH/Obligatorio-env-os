package bios.obligatorio.envios.obligatorio_envios.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class ControladorInicio {
    
    @GetMapping("/")
    public String mostrarInicio(Model model) {
        return "/inicio/index.html";
    }

}
