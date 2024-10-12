package bios.obligatorio.envios.obligatorio_envios.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Rol {
    @Id
    @Column(length = 25)
    private String nombreRol;


    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }


    public Rol() {
        this(null);
    }

    public Rol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}
