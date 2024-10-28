package bios.obligatorio.envios.obligatorio_envios.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import bios.obligatorio.envios.obligatorio_envios.dominio.EstadoRastreo;
import bios.obligatorio.envios.obligatorio_envios.dominio.Paquete;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionNoExiste;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionProyectoEnvios;
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
        return repositorioEstadosRastreo.findByIdAndActivoTrue(id).orElse(null);
    }

    @Override
    public Page<EstadoRastreo> buscar(String criterio, Pageable pageable) {
        return repositorioEstadosRastreo.findAll(EspecificacionesEstados.buscar(criterio, pageable), pageable);
    }

    @Override
    public void agregar(EstadoRastreo estado) throws ExcepcionProyectoEnvios {        

        repositorioEstadosRastreo.save(estado);
    }

    @Override
    public void modificar(EstadoRastreo estado) throws ExcepcionProyectoEnvios {
        EstadoRastreo estadoRastreoExistente = repositorioEstadosRastreo.findByIdAndActivoTrue(estado.getId()).orElse(null);

        if(estadoRastreoExistente == null) {
            throw new ExcepcionNoExiste("El estado no existe.");
        }

        repositorioEstadosRastreo.save(estado);
    }    

    @Override
    public List<EstadoRastreo> listar() {
        return repositorioEstadosRastreo.findAllByActivoTrue();
    }

    @Override    
     public void eliminar(Integer id) throws ExcepcionProyectoEnvios {
         EstadoRastreo estadoExiste = repositorioEstadosRastreo.findByIdAndActivoTrue(id).orElse(null);

         if (estadoExiste == null) { 
             throw new ExcepcionNoExiste("El estado no existe");
         }

         List<Paquete> paquetes = repositorioPaquetes.findByEstadoRastreo_Id(id);

        if (paquetes.size() > 0) {
            estadoExiste.setActivo(false);
            repositorioEstadosRastreo.save(estadoExiste);
         } else {
            repositorioEstadosRastreo.deleteById(id);            
         }

     }
}
