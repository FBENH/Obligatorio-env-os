package bios.obligatorio.envios.obligatorio_envios.servicios;

import java.util.HashSet;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bios.obligatorio.envios.obligatorio_envios.dominio.Empleado;
import bios.obligatorio.envios.obligatorio_envios.dominio.Rol;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionNoExiste;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionProyectoEnvios;
import bios.obligatorio.envios.obligatorio_envios.excepciones.ExcepcionYaExiste;
import bios.obligatorio.envios.obligatorio_envios.repositorios.IRepositorioEmpleados;
import bios.obligatorio.envios.obligatorio_envios.repositorios.especificaciones.EspecificacionesEmpleados;

@Service
public class ServicioEmpleados implements IServicioEmpleados{
    @Autowired
    IRepositorioEmpleados repositorioEmpleados;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public Empleado obtener(String nombreUsuario) {
        return repositorioEmpleados.findById(nombreUsuario).orElse(null);
    }

    @Override
    public Page<Empleado> buscar(String criterio, org.springframework.data.domain.Pageable pageable) {
        return repositorioEmpleados.findAll(EspecificacionesEmpleados.buscar(criterio, pageable),pageable);
    }

    @Override
    @Transactional
    public void agregar(Empleado empleado) throws ExcepcionProyectoEnvios {
        Empleado existe = repositorioEmpleados.findById(empleado.getNombreUsuario()).orElse(null);

        if (existe != null) throw new ExcepcionYaExiste("Ya existe un empleado con el nombre de usuario " + empleado.getNombreUsuario() + ".");

        String contrasenaEncriptada = passwordEncoder.encode(empleado.getClave());
        empleado.setClave(contrasenaEncriptada);

        Set<Rol> roles = new HashSet<>();
        roles.add(new Rol("Empleado"));
        empleado.setRoles(roles);

        repositorioEmpleados.save(empleado);
    }

    @Override
    @Transactional
    public void modificar(Empleado empleado, Boolean cambiarClave) throws ExcepcionProyectoEnvios {

        Empleado existe = repositorioEmpleados.findById(empleado.getNombreUsuario()).orElse(null);

        if (existe == null) throw new ExcepcionNoExiste("No existe un empleado con el nombre de usuario " + empleado.getNombreUsuario() + ".");

        if (cambiarClave) {
            String contrasenaEncriptada = passwordEncoder.encode(empleado.getClave());
            empleado.setClave(contrasenaEncriptada);
        } else {
            empleado.setClave(existe.getClave());
        }        

        Set<Rol> roles = new HashSet<>();
        roles.add(new Rol("Empleado"));
        empleado.setRoles(roles);

        repositorioEmpleados.save(empleado);
    }

    @Override
    @Transactional
    public void eliminar(String nombreUsuario) throws ExcepcionProyectoEnvios {
        Empleado existe = repositorioEmpleados.findById(nombreUsuario).orElse(null);

        if (existe == null) throw new ExcepcionNoExiste("No existe un empleado con el nombre de usuario " + nombreUsuario + ".");

        repositorioEmpleados.deleteById(nombreUsuario);
        // Si se quiere baja logica settear activo = false
    }   

    
}
