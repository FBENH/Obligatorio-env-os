package bios.obligatorio.envios.obligatorio_envios.servicios;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import bios.obligatorio.envios.obligatorio_envios.dominio.Cliente;
import bios.obligatorio.envios.obligatorio_envios.dominio.Rol;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionProyectoEnvios;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionYaExiste;
import bios.obligatorio.envios.obligatorio_envios.repositorios.IRepositorioClientes;

@Service
public class ServicioClientes implements IServicioClientes{

    @Autowired
    private IRepositorioClientes repositorioClientes;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void registrar(Cliente cliente) throws ExcepcionProyectoEnvios {
        
        Cliente existe = repositorioClientes.findById(cliente.getNombreUsuario()).orElse(null);

        if (existe != null)
            throw new ExcepcionYaExiste("Error. Ya existe una cuenta con el nombre de usuario seleccionado.");

        String contrasenaEncriptada = passwordEncoder.encode(cliente.getClave());
        cliente.setClave(contrasenaEncriptada);

        Set<Rol> roles = new HashSet<>();
        roles.add(new Rol("Cliente"));
        
        cliente.setRoles(roles);

        repositorioClientes.save(cliente);
    }
    
}
