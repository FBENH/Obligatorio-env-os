package bios.obligatorio.envios.obligatorio_envios.repositorios.especificaciones;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import bios.obligatorio.envios.obligatorio_envios.dominio.Paquete;
import jakarta.persistence.criteria.JoinType;


public class EspecificacionesPaquetes {

    public static Specification<Paquete> cedulaClienteContiene(String cedula) {
        if (cedula == null) return null;

        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.join("cliente", JoinType.LEFT).get("cedula"), "%" + cedula + "%");
    }

    public static Specification<Paquete> estadoRastreoIgualA(Integer filtroEstado) {
        if (filtroEstado == null) return null;

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.join("estadoRastreo", JoinType.LEFT).get("id"), filtroEstado);
    }

    public static Specification<Paquete> fechaIgualA(LocalDate filtroFecha) {
        if (filtroFecha == null) return null;

         return (root, query, criteriaBuilder) -> {
            LocalDateTime inicio = filtroFecha.atStartOfDay();
            LocalDateTime fin = filtroFecha.atTime(23, 59, 59);            
            return criteriaBuilder.and(
                criteriaBuilder.greaterThanOrEqualTo(root.get("fechaHora"), inicio),
                criteriaBuilder.lessThanOrEqualTo(root.get("fechaHora"), fin)
            );
    };
    }

    public static Specification<Paquete> buscar(String criterio, Integer filtroEstado, LocalDate filtroFecha, Pageable pageable) {
        Specification<Paquete> especificacion = Specification.where(null);        
        
        if (!(criterio == null || criterio.isBlank())) {
            especificacion = especificacion.and(cedulaClienteContiene(criterio));
        }

        if (filtroFecha != null) {
            especificacion = especificacion.and(fechaIgualA(filtroFecha));
        }

        if (filtroEstado != 0) {
            especificacion = especificacion.and(estadoRastreoIgualA(filtroEstado));
        } 

        return especificacion;
    }
}
