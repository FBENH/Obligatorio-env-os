package bios.obligatorio.envios.obligatorio_envios.controladores;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice(basePackageClasses = ControladorCompartido.class)
public class ControladorCompartido {    

    @ExceptionHandler
    public String manejarExcepcion(Exception e, Model model) {
        model.addAttribute("mensaje", "No se pudo procesar la solicitud.");

        return "excepcion";
    }
}
