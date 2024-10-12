package bios.obligatorio.envios.obligatorio_envios.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sucursales")
public class ControladorSucursales {

    @GetMapping
    public String sucursales() {

        
        return "sucursales/index";
    }
    
}
