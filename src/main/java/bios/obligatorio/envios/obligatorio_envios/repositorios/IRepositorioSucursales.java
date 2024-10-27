package bios.obligatorio.envios.obligatorio_envios.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import bios.obligatorio.envios.obligatorio_envios.dominio.Sucursal;



public interface IRepositorioSucursales extends JpaRepository<Sucursal,Long>, JpaSpecificationExecutor<Sucursal> {    
    
    Optional<Sucursal> findById(Long numero);
    Page<Sucursal> findAll(Specification<Sucursal> spec, Pageable pageable);
    List<Sucursal> findAll(Specification<Sucursal> spec);
}