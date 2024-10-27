package bios.obligatorio.envios.obligatorio_envios.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "estados_rastreo")
public class EstadoRastreo {
    
    @Id
    @Min(0)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotBlank 
    @Size(max = 100)   
    @Column(length = 100, nullable = false)
    String descripcion;

    @NotNull
    Boolean activo;

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoRastreo() {
        this.activo = true;
    }

    public Boolean isActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    


}
