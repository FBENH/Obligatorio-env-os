package bios.obligatorio.envios.obligatorio_envios.servicios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import bios.obligatorio.envios.obligatorio_envios.dominio.Empleado;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionProyectoEnvios;

public interface IServicioEmpleados {

    Page<Empleado> buscar(String criterio, Pageable pageable);
    Empleado obtener(String nombreUsuario);
    void agregar(Empleado empleado) throws ExcepcionProyectoEnvios;
    void modificar(Empleado empleado, Boolean cambiarClave) throws ExcepcionProyectoEnvios;
    void eliminar(String nombreUsuario) throws ExcepcionProyectoEnvios;

}
