package bios.obligatorio.envios.obligatorio_envios.servicios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bios.obligatorio.envios.obligatorio_envios.dominio.Rol;
import bios.obligatorio.envios.obligatorio_envios.dominio.Usuario;
import bios.obligatorio.envios.obligatorio_envios.repositorios.IRepositorioClientes;
import bios.obligatorio.envios.obligatorio_envios.repositorios.IRepositorioEmpleados;

@Service
public class ServicioDetalleUsuarios implements UserDetailsService {

    @Autowired
    IRepositorioEmpleados repositorioEmpleados;

    @Autowired
    IRepositorioClientes repositorioClientes;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         Usuario usuario = repositorioEmpleados.findByNombreUsuarioAndActivoTrue(username).orElse(null);

        if (usuario == null) {
            usuario = repositorioClientes.findByNombreUsuarioAndActivoTrue(username).orElse(null);
        }

        if (usuario == null || !usuario.isActivo()) throw new UsernameNotFoundException("El usuario no existe");

        List<GrantedAuthority> roles = new ArrayList<>();

        for (Rol r : usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(r.getNombreRol()));
        }

        return new DetallesUsuario(usuario);
    }

    public class DetallesUsuario implements UserDetails {
 
        private Usuario usuario;
 
 
        public DetallesUsuario(Usuario usuario) {
            this.usuario = usuario;
        }
 
 
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<GrantedAuthority> roles = new ArrayList<>();
 
            for (Rol r : usuario.getRoles()) {
                roles.add(new SimpleGrantedAuthority(r.getNombreRol()));
            }
 
            return roles;
        }
 
        @Override
        public String getPassword() {
            return usuario.getClave();
        }
 
        @Override
        public String getUsername() {
            return usuario.getNombreUsuario();
        }
 
        @Override
        public boolean isAccountNonExpired() {
            return true;
        }
 
        @Override
        public boolean isAccountNonLocked() {
            return true;
        }
 
        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }
 
        @Override
        public boolean isEnabled() {
            return usuario.isActivo();
        }
 
    }
    
}
