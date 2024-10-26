package bios.obligatorio.envios.obligatorio_envios.servicios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import bios.obligatorio.envios.obligatorio_envios.dominio.EstadoRastreo;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionProyectoEnvios;

public interface IServicioEstadoRastreo {

    List<EstadoRastreo> listar();
    Page<EstadoRastreo> buscar(String criterio, Pageable pageable);
    EstadoRastreo obtener(Integer id);
    void agregar(EstadoRastreo estado) throws ExcepcionProyectoEnvios;
    void modificar(EstadoRastreo empleado) throws ExcepcionProyectoEnvios;
    void eliminar(Integer id) throws ExcepcionProyectoEnvios;
    
}
