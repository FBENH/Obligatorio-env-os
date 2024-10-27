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
import org.springframework.lang.Nullable;

import bios.obligatorio.envios.obligatorio_envios.dominio.Paquete;

public interface IRepositorioPaquetes extends JpaRepository<Paquete,Integer>, JpaSpecificationExecutor<Paquete>{

    @Override
    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = { "paquetes" })
    List<Paquete> findAll();

    @Override
    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = { "paquetes" })
    List<Paquete> findAll(@Nullable Specification<Paquete> spec);

    //Metodo con paginacion
    Page<Paquete> findAll(Specification<Paquete> spec, Pageable pageable);

    Page<Paquete> findByCliente_NombreUsuario(String nombreUsuario, Pageable pageable);

    List<Paquete> findByEstadoRastreo_Id(Integer id);

    Optional<Paquete> findById(Integer id);
}
