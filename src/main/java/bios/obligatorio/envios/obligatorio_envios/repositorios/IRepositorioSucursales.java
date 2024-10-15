package bios.obligatorio.envios.obligatorio_envios.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import bios.obligatorio.envios.obligatorio_envios.dominio.Sucursal;

public interface IRepositorioSucursales extends JpaRepository<Sucursal,Long>, JpaSpecificationExecutor<Sucursal> {

    
}