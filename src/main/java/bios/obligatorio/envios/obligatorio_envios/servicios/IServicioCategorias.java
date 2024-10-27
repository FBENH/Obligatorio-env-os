package bios.obligatorio.envios.obligatorio_envios.servicios;

import java.util.List;

import bios.obligatorio.envios.obligatorio_envios.dominio.Categoria;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionProyectoEnvios;

public interface IServicioCategorias {

    List<Categoria> buscar(String criterio);
    void agregar(Categoria categoria) throws ExcepcionProyectoEnvios;
    Categoria obtener(Integer id);
    void modificar(Categoria categoria) throws ExcepcionProyectoEnvios;
    void eliminar(Integer id) throws ExcepcionProyectoEnvios;
    List<Categoria> listar();

}
