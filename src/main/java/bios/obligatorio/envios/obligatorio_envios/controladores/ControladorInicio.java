package bios.obligatorio.envios.obligatorio_envios.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorInicio {
    
    @GetMapping("/")
    public String mostrarInicio() {
        return "/inicio/index.html";
    }

}
