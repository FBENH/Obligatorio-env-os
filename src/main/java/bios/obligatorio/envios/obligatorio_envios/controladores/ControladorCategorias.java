package bios.obligatorio.envios.obligatorio_envios.controladores;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

import bios.obligatorio.envios.obligatorio_envios.dominio.Categoria;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionProyectoEnvios;
import bios.obligatorio.envios.obligatorio_envios.servicios.IServicioCategorias;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/categorias")
public class ControladorCategorias {

    @Autowired
    private IServicioCategorias servicioCategoria;

    @GetMapping
    public String categorias(@RequestParam(required = false) String criterio, Model model) {
        List<Categoria> categorias = servicioCategoria.buscar(criterio);
        model.addAttribute("categorias", categorias);
        
        return "categorias/index";
    }

    @GetMapping("/{id}")
    public String verDetalleCategoria(@PathVariable Integer id, Model model) {
        Categoria categoria = servicioCategoria.obtener(id);
        if (categoria != null)
            model.addAttribute("categoria", categoria);
        else
            model.addAttribute("mensaje", "Error. No se encontró la categoria de número " + id + ".");
        
        return "categorias/detalle";
    }

     @GetMapping("/agregar")
    public String agregarCategoria(@ModelAttribute Categoria categoria) {        

        return "categorias/agregar";
    }

    @PostMapping("/agregar")
    public String procesarAgregarCategoria(@ModelAttribute @Valid Categoria categoria, BindingResult result, Model model, RedirectAttributes attributes) {
        
        if (result.hasErrors()) {
            return "categorias/agregar";
        }

        try {
            servicioCategoria.agregar(categoria);
            attributes.addFlashAttribute("mensaje","Categoría agregada con éxito");

            return "redirect:/categorias";
        } catch (ExcepcionProyectoEnvios e) {
            model.addAttribute("mensaje", "Error al agregar la categoria. " + e.getMessage());

            return "categorias/agregar";
        }        
    }

    @GetMapping("/modificar")
    public String modificarCategoria(Integer id, Model model) {
        Categoria categoria = servicioCategoria.obtener(id);        

        if (categoria != null) {
            model.addAttribute("categoria",categoria);
        } else {
            model.addAttribute("mensaje","Error. No se encontró la categoria con el idntificador "+ id +".");
        }        

        return "categorias/modificar";
    }

    @PostMapping("/modificar")
    public String procesarModificarCategoria(@ModelAttribute @Valid Categoria categoria, BindingResult result, RedirectAttributes attributes, Model model) {

        if (result.hasErrors())
            return "categorias/modificar";

        try {
            servicioCategoria.modificar(categoria);
            attributes.addFlashAttribute("mensaje","Se modificó la categoria con éxito");
            return "redirect:/categorias";
        } catch (ExcepcionProyectoEnvios e) {
            model.addAttribute("mensaje", "Error al modificar la categoria. " + e.getMessage());
            return "categorias/modificar";
        }        
    }

    @GetMapping("/eliminar")
    public String eliminarCategoria(Integer id, Model model) {

        Categoria categoria = servicioCategoria.obtener(id);
        if (categoria != null) {
            model.addAttribute("categoria",categoria);
        } else {
            model.addAttribute("mensaje","Error. No se encontró la categoria con el número "+ id +".");
        }   

        return "categorias/eliminar";
    }

    @PostMapping("/eliminar")
    public String procesarEliminarCategoria(Integer id, Model model, RedirectAttributes attributes) {
        try {
            servicioCategoria.eliminar(id);
            attributes.addFlashAttribute("mensaje", "Categoria eliminada con éxito.");

            return "redirect:/categorias";
        } catch (ExcepcionProyectoEnvios e) {
            model.addAttribute("mensaje", "Error al eliminar. " + e.getMessage());

            return "categorias/eliminar";
        }        
    }
}
