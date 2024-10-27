package bios.obligatorio.envios.obligatorio_envios.servicios;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import bios.obligatorio.envios.obligatorio_envios.dominio.Categoria;
import bios.obligatorio.envios.obligatorio_envios.dominio.Cliente;
import bios.obligatorio.envios.obligatorio_envios.dominio.EstadoRastreo;
import bios.obligatorio.envios.obligatorio_envios.dominio.Paquete;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionNoExiste;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionProyectoEnvios;
import bios.obligatorio.envios.obligatorio_envios.repositorios.IRepositorioPaquetes;
import bios.obligatorio.envios.obligatorio_envios.repositorios.especificaciones.EspecificacionesPaquetes;

@Service
public class ServicioPaquete implements IServicioPaquete{

    @Autowired
    private IRepositorioPaquetes repositorioPaquetes;

    @Autowired
    private IServicioClientes servicioClientes;

    @Autowired
    private IServicioCategorias servicioCategorias;

    @Autowired
    private IServicioEstadoRastreo servicioEstadoRastreo;

    @Override
    public Page<Paquete> buscar(String criterio, Integer filtroEstado, LocalDate filtroFecha, Pageable pageable) {
        return repositorioPaquetes.findAll(EspecificacionesPaquetes.buscar(criterio, filtroEstado, filtroFecha, pageable),pageable);
    }

    @Override
    public Page<Paquete> listarPorCliente(String nombreUsuario, Pageable pageable) {
        return repositorioPaquetes.findByCliente_NombreUsuario(nombreUsuario, pageable);
    }


    @Override
    public void agregar(Paquete paquete) throws ExcepcionProyectoEnvios {

        Cliente clienteExiste = servicioClientes.obtener(paquete.getCliente().getNombreUsuario());

        if (clienteExiste == null) {
            throw new ExcepcionNoExiste("No existe el Cliente " + paquete.getCliente().getNombreUsuario());
        }

        Categoria categoriaExiste = servicioCategorias.obtener(paquete.getCategoria().getId());

        if (categoriaExiste == null) {

            throw new ExcepcionNoExiste("No existe la Categoría " + paquete.getCategoria().getId());
        }

        EstadoRastreo estadoExiste = servicioEstadoRastreo.obtener(paquete.getEstadoRastreo().getId());

        if (estadoExiste == null) {
            throw new ExcepcionNoExiste("No existe el Estado de Rastreo " + paquete.getEstadoRastreo().getId());
        }


        repositorioPaquetes.save(paquete);
    }

    @Override
    public Paquete obtener(Integer id) {
        return repositorioPaquetes.findById(id).orElse(null);
    }

    @Override
    public void modificar(Paquete paquete) throws ExcepcionProyectoEnvios {
        Paquete paqueteExiste = repositorioPaquetes.findById(paquete.getId()).orElse(null);

        if (paqueteExiste == null) throw new ExcepcionNoExiste("El paquete no existe");

        Cliente clienteExiste = servicioClientes.obtener(paquete.getCliente().getNombreUsuario());

        if (clienteExiste == null) {
            throw new ExcepcionNoExiste("No existe el Cliente " + paquete.getCliente().getNombreUsuario());
        }

        Categoria categoriaExiste = servicioCategorias.obtener(paquete.getCategoria().getId());

        if (categoriaExiste == null) {

            throw new ExcepcionNoExiste("No existe la Categoría " + paquete.getCategoria().getId());
        }

        EstadoRastreo estadoExiste = servicioEstadoRastreo.obtener(paquete.getEstadoRastreo().getId());

        if (estadoExiste == null) {
            throw new ExcepcionNoExiste("No existe el Estado de Rastreo " + paquete.getEstadoRastreo().getId());
        }


        repositorioPaquetes.save(paquete);
    }

    @Override
    public void eliminar(Integer id) throws ExcepcionProyectoEnvios {
        Paquete paqueteExistente = repositorioPaquetes.findById(id).orElse(null);

        if(paqueteExistente == null) {
            throw new ExcepcionNoExiste("El paquete no existe.");
        }

        repositorioPaquetes.deleteById(id);
    }
    
}
