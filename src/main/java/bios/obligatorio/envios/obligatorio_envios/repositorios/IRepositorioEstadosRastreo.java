package bios.obligatorio.envios.obligatorio_envios.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;

import bios.obligatorio.envios.obligatorio_envios.dominio.EstadoRastreo;

public interface IRepositorioEstadosRastreo extends JpaRepository<EstadoRastreo,Integer> {

    @Override
    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = {"estados_rastreo"})
    List<EstadoRastreo> findAll();
    
    Page<EstadoRastreo> findAll(Specification<EstadoRastreo> spec, Pageable pageable);    

    Optional<EstadoRastreo> findById(Integer id);
    
}
