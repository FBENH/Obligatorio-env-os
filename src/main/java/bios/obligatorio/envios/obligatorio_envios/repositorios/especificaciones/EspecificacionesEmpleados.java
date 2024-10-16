package bios.obligatorio.envios.obligatorio_envios.repositorios.especificaciones;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import bios.obligatorio.envios.obligatorio_envios.dominio.Empleado;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class EspecificacionesEmpleados {    

    public static Specification<Empleado> nombreUsuarioContiene(String nombreUsuario) {
        if (nombreUsuario == null) return null;

        return new Specification<Empleado>() {
            @Override
            public Predicate toPredicate(Root<Empleado> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("nombreUsuario"), "%" + nombreUsuario + "%");
            }
        };
    }



    public static Specification<Empleado> buscar(String criterio, Pageable pageable) {
        if (criterio == null || criterio.isBlank()) return null;

        return Specification.where(nombreUsuarioContiene(criterio));
    }
}
