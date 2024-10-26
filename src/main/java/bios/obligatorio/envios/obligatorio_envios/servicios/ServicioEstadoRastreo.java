package bios.obligatorio.envios.obligatorio_envios.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import bios.obligatorio.envios.obligatorio_envios.dominio.EstadoRastreo;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionNoExiste;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionProyectoEnvios;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionTieneVinculos;
import bios.obligatorio.envios.obligatorio_envios.repositorios.IRepositorioEstadosRastreo;
import bios.obligatorio.envios.obligatorio_envios.repositorios.IRepositorioPaquetes;
import bios.obligatorio.envios.obligatorio_envios.repositorios.especificaciones.EspecificacionesEstados;

@Service
public class ServicioEstadoRastreo implements IServicioEstadoRastreo{

    @Autowired 
    IRepositorioEstadosRastreo repositorioEstadosRastreo;

    @Autowired
    IRepositorioPaquetes repositorioPaquetes;

    @Override
    public EstadoRastreo obtener(Integer id) {
        return repositorioEstadosRastreo.findById(id).orElse(null);
    }

    @Override
    public Page<EstadoRastreo> buscar(String criterio, Pageable pageable) {
        return repositorioEstadosRastreo.findAll(EspecificacionesEstados.buscar(criterio, pageable),pageable);
    }

    @Override
    public void agregar(EstadoRastreo estado) throws ExcepcionProyectoEnvios {        

        repositorioEstadosRastreo.save(estado);
    }

    @Override
    public void modificar(EstadoRastreo estado) throws ExcepcionProyectoEnvios {
        EstadoRastreo estadoRastreoExistente = repositorioEstadosRastreo.findById(estado.getId()).orElse(null);

        if(estadoRastreoExistente == null) {
            throw new ExcepcionNoExiste("El estado no existe.");
        }

        repositorioEstadosRastreo.save(estado);
    }

    @Override
    public void eliminar(Integer id) throws ExcepcionProyectoEnvios {
        EstadoRastreo existe = repositorioEstadosRastreo.findById(id).orElse(null);

        if (existe == null) throw new ExcepcionNoExiste("El Estado de Rastreo no existe.");

        try {
            repositorioEstadosRastreo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ExcepcionTieneVinculos("Hay paquetes con el estado de rastreo asociado.");
        }
    }

    @Override
    public List<EstadoRastreo> listar() {
        return repositorioEstadosRastreo.findAll(Sort.by("descripcion"));
    }

    // @Override
    // @Transactional
    // public void eliminar(Integer id) throws ExcepcionProyectoEnvios {
    //     EstadoRastreo estadoExiste = repositorioEstadosRastreo.findById(id).orElse(null);

    //     if (estadoExiste == null) { 
    //         throw new ExcepcionNoExiste("El estado no existe");
    //     }

    //     List<Paquete> paquetes = repositorioPaquetes.findByEstadoRastreo(id);

    //     if (paquetes.size() > 0) {
    //         repositorioPaquetes.deleteByEstado(id);
    //     }

    //     repositorioEstadosRastreo.deleteById(id);
    // }
}
