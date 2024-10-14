package bios.obligatorio.envios.obligatorio_envios.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "empleados")
public class Empleado extends Usuario {
    
    @ManyToOne
    Sucursal sucursal;

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }    

    public Empleado(@NotBlank String nombreUsuario, @NotBlank String clave, String correo, @NotNull Boolean activo,
            Sucursal sucursal) {
        super(nombreUsuario, clave, correo, activo);
        this.sucursal = sucursal;
    }

    public Empleado(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
    
    public Empleado() {}
}
