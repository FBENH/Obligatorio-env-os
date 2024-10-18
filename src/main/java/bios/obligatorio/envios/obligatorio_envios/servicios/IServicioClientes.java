package bios.obligatorio.envios.obligatorio_envios.servicios;

import bios.obligatorio.envios.obligatorio_envios.dominio.Cliente;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionProyectoEnvios;

public interface IServicioClientes { 
        
    void registrar(Cliente cliente) throws ExcepcionProyectoEnvios;
    Cliente obtener(String nombreUsuario);
    void modificar(Cliente cliente, Boolean cambiarClave) throws ExcepcionProyectoEnvios;

    
}
