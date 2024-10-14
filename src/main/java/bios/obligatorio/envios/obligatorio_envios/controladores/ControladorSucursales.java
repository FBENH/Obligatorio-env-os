package bios.obligatorio.envios.obligatorio_envios.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sucursales")
public class ControladorSucursales {

    @GetMapping
    public String sucursales() {

        
        return "sucursales/index";
    }

    @GetMapping("/agregar")
    public String agregarSucursal() {

        return "sucursales/agregar";
    }
    
    @PostMapping("/agregar")
    public String procesarAgregarSucursal() {

        return "sucursales/agregar";
    }
    @GetMapping("/modificar")
    public String modificarSucursal() {

        return "sucursales/agregar";
    }

    @PostMapping("/modificar")
    public String procesarModificarSucursal() {

        return "sucursales/agregar";
    }
    @GetMapping("/eliminar")
    public String eliminarSucursal() {

        return "sucursales/agregar";
    }

    @PostMapping("/eliminar")
    public String procesarEliminarSucursal() {

        return "sucursales/agregar";
    }
    
}
