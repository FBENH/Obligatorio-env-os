package bios.obligatorio.envios.obligatorio_envios.dominio;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "clientes")
public class Cliente extends Usuario {   
    
    @NotBlank
    @Column(length = 8, nullable = false)
    String cedula;

    @NotBlank
    @Column(length = 80, nullable = false)
    String domicilio;

    @Override
    public String toString() {
        return "Cliente [cedula=" + cedula + ", domicilio=" + domicilio + ", telefono=" + telefono + ", nombreUsuario="
                + nombreUsuario + ", clave=" + clave + ", correo=" + correo + ", activo=" + activo + "]";
    }

    @NotBlank
    @Column(length = 20, nullable = false)
    String telefono;

    @OneToMany
    List<Paquete> paquetes = new ArrayList<>();
    

    public List<Paquete> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(List<Paquete> paquetes) {
        this.paquetes = paquetes;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Cliente() {
    }

    public Cliente(@NotBlank String nombreUsuario, @NotBlank String clave, String correo, @NotNull Boolean activo,
            @NotBlank String cedula, @NotBlank String domicilio, @NotBlank String telefono, List<Paquete> paquetes) {
        super(nombreUsuario, clave, correo,activo);
        this.cedula = cedula;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.paquetes = paquetes;
    }

    public Cliente(@NotBlank String cedula, @NotBlank String domicilio, @NotBlank String telefono,
            List<Paquete> paquetes) {
        this.cedula = cedula;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.paquetes = paquetes;
    }   
    
}
