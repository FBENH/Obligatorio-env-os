package bios.obligatorio.envios.obligatorio_envios.servicios;

import java.util.List;

import bios.obligatorio.envios.obligatorio_envios.dominio.Sucursal;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionProyectoEnvios;

public interface IServicioSucursales {
    List<Sucursal> buscar(String criterio);
    void agregar(Sucursal sucursal) throws ExcepcionProyectoEnvios;
    Sucursal obtener(Long numero);
    void modificar(Sucursal sucursal) throws ExcepcionProyectoEnvios;
    void eliminar(Long numero) throws ExcepcionProyectoEnvios;
}
