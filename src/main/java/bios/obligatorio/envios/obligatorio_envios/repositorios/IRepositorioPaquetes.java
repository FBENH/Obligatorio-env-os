package bios.obligatorio.envios.obligatorio_envios.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import bios.obligatorio.envios.obligatorio_envios.dominio.Paquete;

public interface IRepositorioPaquetes extends JpaRepository<Paquete,Integer> {
    
}
