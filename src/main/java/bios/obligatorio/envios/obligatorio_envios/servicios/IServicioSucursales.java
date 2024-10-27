package bios.obligatorio.envios.obligatorio_envios.servicios;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import bios.obligatorio.envios.obligatorio_envios.dominio.Sucursal;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionProyectoEnvios;

public interface IServicioSucursales {
    Page<Sucursal> buscarConPaginacion(String criterio, Pageable pageable);
    List<Sucursal> buscar(String criterio);
    void agregar(Sucursal sucursal) throws ExcepcionProyectoEnvios;
    Sucursal obtener(Long numero);
    void modificar(Sucursal sucursal) throws ExcepcionProyectoEnvios;
    void eliminar(Long numero) throws ExcepcionProyectoEnvios;
}
