package bios.obligatorio.envios.obligatorio_envios.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import bios.obligatorio.envios.obligatorio_envios.dominio.Categoria;

public interface IRepositorioCategorias extends JpaRepository<Categoria,Integer>, JpaSpecificationExecutor<Categoria> {
    
    Optional<Categoria> findById(Integer id);
}
