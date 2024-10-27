package bios.obligatorio.envios.obligatorio_envios.repositorios.especificaciones;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import bios.obligatorio.envios.obligatorio_envios.dominio.EstadoRastreo;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;



public class EspecificacionesEstados {

    public static Specification<EstadoRastreo> descripcionContiene(String descripcion) {
        if (descripcion == null || descripcion.isBlank()) return null;

        return new Specification<EstadoRastreo>() {
            @Override
            public Predicate toPredicate(Root<EstadoRastreo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("descripcion"), "%" + descripcion + "%");
            }
        };
    }

    public static Specification<EstadoRastreo> activo() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(root.get("activo"));
    }
    

    public static Specification<EstadoRastreo> buscar(String criterio, Pageable pageable) {
        return Specification.where(descripcionContiene(criterio)).and(activo());
    }
}
