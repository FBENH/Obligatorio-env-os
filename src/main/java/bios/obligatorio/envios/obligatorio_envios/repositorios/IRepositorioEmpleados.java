package bios.obligatorio.envios.obligatorio_envios.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;

import bios.obligatorio.envios.obligatorio_envios.dominio.Empleado;

public interface IRepositorioEmpleados extends JpaRepository<Empleado,String> {
    
    @Override
    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = {"roles"})
    List<Empleado> findAll();

    @Override
    @EntityGraph(type = EntityGraphType.LOAD,attributePaths = {"roles"})
    Optional<Empleado> findById(String id);
}
