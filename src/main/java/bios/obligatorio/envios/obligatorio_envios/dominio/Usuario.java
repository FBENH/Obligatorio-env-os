package bios.obligatorio.envios.obligatorio_envios.dominio;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

    @Id    
    @NotBlank
    @Size(min = 4,max = 25)
    @Column(length = 25)
    String nombreUsuario;

    @NotBlank
    @Size(min = 6, max = 60)
    @Column(length = 60, nullable = false)
    String clave;

    @Transient    
    private String repetirContrasena;
    
    @Size(max = 60)
    @Column(length = 60, nullable = true)
    String correo;

    public String getRepetirContrasena() {
        return repetirContrasena;
    }

    public void setRepetirContrasena(String repetirContrasena) {
        this.repetirContrasena = repetirContrasena;
    }

    public Boolean getActivo() {
        return activo;
    }

    @NotNull    
    Boolean activo;    

    @ManyToMany
    @JoinTable(joinColumns = { @JoinColumn(name = "usuarios_roles") }, inverseJoinColumns = { @JoinColumn(name = "rol_nombre_rol") })
    private Set<Rol> roles;

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public Boolean isActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Usuario(@NotBlank String nombreUsuario, @NotBlank String clave, String correo, boolean activo) {
        this.nombreUsuario = nombreUsuario;
        this.clave = clave;
        this.correo = correo;
        this.activo = activo;
        roles = new HashSet<>();
    }

    public Usuario() {
        this(null, null, null,true);
    }

    

    
    

}
