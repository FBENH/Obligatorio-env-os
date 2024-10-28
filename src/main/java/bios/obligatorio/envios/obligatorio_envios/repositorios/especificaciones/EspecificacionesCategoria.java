package bios.obligatorio.envios.obligatorio_envios.repositorios.especificaciones;

import org.springframework.data.jpa.domain.Specification;

import bios.obligatorio.envios.obligatorio_envios.dominio.Categoria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class EspecificacionesCategoria {
    public static Specification<Categoria> numeroCategorialIgualA(String textoId) {
        if (textoId == null) return null;

        Long id;

        try {
            id = Long.parseLong(textoId);
        } catch (NumberFormatException e) {
            return null;
        }

        return new Specification<Categoria>() {
            @Override
            public Predicate toPredicate(Root<Categoria> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("id"), id);
            }
        };
    }

    public static Specification<Categoria> nombreCategoriaContiene(String nombre) {
        if (nombre == null) return null;

        return new Specification<Categoria>() {
            @Override
            public Predicate toPredicate(Root<Categoria> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("nombre"), "%" + nombre + "%");
            }
        };
    }

    public static Specification<Categoria> buscar(String criterio) {
        if (criterio == null || criterio.isBlank()) return null;

        return Specification.where(numeroCategorialIgualA(criterio)).or(nombreCategoriaContiene(criterio));
    }
}
