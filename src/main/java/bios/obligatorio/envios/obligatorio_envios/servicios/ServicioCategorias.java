package bios.obligatorio.envios.obligatorio_envios.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import bios.obligatorio.envios.obligatorio_envios.dominio.Categoria;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionNoExiste;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionProyectoEnvios;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionTieneVinculos;
import bios.obligatorio.envios.obligatorio_envios.repositorios.IRepositorioCategorias;
import bios.obligatorio.envios.obligatorio_envios.repositorios.especificaciones.EspecificacionesCategoria;

@Service
public class ServicioCategorias implements IServicioCategorias {

    @Autowired
    IRepositorioCategorias repositorioCategorias;

    @Override
    public List<Categoria> buscar(String criterio) {
        return repositorioCategorias.findAll(EspecificacionesCategoria.buscar(criterio));
    }

    @Override
    public void agregar(Categoria categoria) throws ExcepcionProyectoEnvios {
        repositorioCategorias.save(categoria);        
    }

    @Override
    public Categoria obtener(Integer id) {
        return repositorioCategorias.findById(id).orElse(null);
    }

    

    @Override
    public void modificar(Categoria categoria) throws ExcepcionProyectoEnvios {
        Categoria categoriaExiste = repositorioCategorias.findById(categoria.getId()).orElse(null);

        if (categoriaExiste == null) throw new ExcepcionNoExiste("La categoria no existe");

        repositorioCategorias.save(categoria);
    }

    @Override
    public void eliminar(Integer id) throws ExcepcionProyectoEnvios {
        Categoria categoriaExiste = repositorioCategorias.findById(id).orElse(null);

        if (categoriaExiste == null) throw new ExcepcionNoExiste("La categoria no existe");

        try {
            repositorioCategorias.deleteById(id);            
        } catch (DataIntegrityViolationException e) {
            throw new ExcepcionTieneVinculos("La categoria tiene paquetes asociados.");
        }
    }

    @Override
    public List<Categoria> listar() {
        return repositorioCategorias.findAll();
    }
    
}
