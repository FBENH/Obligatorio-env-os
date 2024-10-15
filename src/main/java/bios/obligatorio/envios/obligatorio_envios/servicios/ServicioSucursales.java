package bios.obligatorio.envios.obligatorio_envios.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import bios.obligatorio.envios.obligatorio_envios.dominio.Sucursal;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionNoExiste;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionProyectoEnvios;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionTieneVinculos;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionYaExiste;
import bios.obligatorio.envios.obligatorio_envios.repositorios.IRepositorioSucursales;
import bios.obligatorio.envios.obligatorio_envios.repositorios.especificaciones.EspecificacionesSucursales;

@Service
public class ServicioSucursales implements IServicioSucursales{

    @Autowired
    private IRepositorioSucursales repositorioSucursales;

    @Override
    public List<Sucursal> buscar(String criterio) {
        return repositorioSucursales.findAll(EspecificacionesSucursales.buscar(criterio));    
    }

    @Override
    public void agregar(Sucursal sucursal) throws ExcepcionProyectoEnvios {
        Sucursal sucursalExiste = repositorioSucursales.findById(sucursal.getNumero()).orElse(null);

        if (sucursalExiste != null) {
            throw new ExcepcionYaExiste("Ya existe una sucursal con ese número.");
        } 

        repositorioSucursales.save(sucursal);
    }

    @Override
    public Sucursal obtener(Long numero) {
        return repositorioSucursales.findById(numero).orElse(null);        
    }

    @Override
    public void modificar(Sucursal sucursal) throws ExcepcionProyectoEnvios {
        Sucursal sucursalExiste = repositorioSucursales.findById(sucursal.getNumero()).orElse(null);

        if (sucursalExiste == null) throw new ExcepcionNoExiste("La sucursal no existe");

        repositorioSucursales.save(sucursal);
    }

    @Override
    public void eliminar(Long numero) throws ExcepcionProyectoEnvios {
        Sucursal sucursalExiste = repositorioSucursales.findById(numero).orElse(null);

        if (sucursalExiste == null) throw new ExcepcionNoExiste("La sucursal no existe");

        try {
            repositorioSucursales.deleteById(numero);            
        } catch (DataIntegrityViolationException e) {
            throw new ExcepcionTieneVinculos("La sucursal tiene empleados asociados.");
        }
    }

    
    
}
