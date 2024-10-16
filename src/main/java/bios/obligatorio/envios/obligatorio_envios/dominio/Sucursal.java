package bios.obligatorio.envios.obligatorio_envios.dominio;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "sucursales")
public class Sucursal {
    
    @Id
    @Min(0)
    @NotNull
    Long numero;

    @NotBlank
    @Column(length = 50, nullable = false)
    String nombre;

    @OneToMany(mappedBy = "sucursal")
    List<Empleado> empleados = new ArrayList<>();

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Sucursal() {
    }   


}
