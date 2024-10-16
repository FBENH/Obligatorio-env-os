package bios.obligatorio.envios.obligatorio_envios.repositorios;

import java.util.Optional;

/* import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType; */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import bios.obligatorio.envios.obligatorio_envios.dominio.Sucursal;


public interface IRepositorioSucursales extends JpaRepository<Sucursal,Long>, JpaSpecificationExecutor<Sucursal> {
    /* @Override En caso de mostrar empleados en el detalle de sucursal
    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = { "empleados" }) */
    Optional<Sucursal> findById(Long numero);
    
}