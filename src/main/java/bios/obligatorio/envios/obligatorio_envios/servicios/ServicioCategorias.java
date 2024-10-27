package bios.obligatorio.envios.obligatorio_envios.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bios.obligatorio.envios.obligatorio_envios.dominio.Categoria;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionProyectoEnvios;
import bios.obligatorio.envios.obligatorio_envios.repositorios.IRepositorioCategorias;

@Service
public class ServicioCategorias implements IServicioCategorias {

    @Autowired
    IRepositorioCategorias repositorioCategorias;

    @Override
    public List<Categoria> buscar(String criterio) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscar'");
    }

    @Override
    public void agregar(Categoria categoria) throws ExcepcionProyectoEnvios {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'agregar'");
    }

    @Override
    public Categoria obtener(Integer id) {
        return repositorioCategorias.findById(id).orElse(null);
    }

    @Override
    public void modificar(Categoria categoria) throws ExcepcionProyectoEnvios {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public void eliminar(Integer id) throws ExcepcionProyectoEnvios {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

    @Override
    public List<Categoria> listar() {
        return repositorioCategorias.findAll();
    }
    
}
