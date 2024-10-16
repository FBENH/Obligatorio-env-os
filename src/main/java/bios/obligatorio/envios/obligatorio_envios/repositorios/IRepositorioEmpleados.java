package bios.obligatorio.envios.obligatorio_envios.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import bios.obligatorio.envios.obligatorio_envios.dominio.Empleado;

public interface IRepositorioEmpleados extends JpaRepository<Empleado,String>, JpaSpecificationExecutor<Empleado> {
    
    @Override
    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = {"roles"})
    List<Empleado> findAll();

    //Metodo con paginacion
    Page<Empleado> findAll(Specification<Empleado> spec, Pageable pageable);

    @Override
    @EntityGraph(type = EntityGraphType.LOAD,attributePaths = {"roles"})
    Optional<Empleado> findById(String id);
}
