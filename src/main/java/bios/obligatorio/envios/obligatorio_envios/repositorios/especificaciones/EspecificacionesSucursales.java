package bios.obligatorio.envios.obligatorio_envios.repositorios.especificaciones;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import bios.obligatorio.envios.obligatorio_envios.dominio.Sucursal;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class EspecificacionesSucursales {

    public static Specification<Sucursal> numeroSucursalIgualA(String textoNumero) {
        if (textoNumero == null) return null;

        Long numero;

        try {
            numero = Long.parseLong(textoNumero);
        } catch (NumberFormatException e) {
            return null;
        }

        return new Specification<Sucursal>() {
            @Override
            public Predicate toPredicate(Root<Sucursal> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("numero"), numero);
            }
        };
    }

    public static Specification<Sucursal> nombreSucursalContiene(String nombre) {
        if (nombre == null) return null;

        return new Specification<Sucursal>() {
            @Override
            public Predicate toPredicate(Root<Sucursal> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("nombre"), "%" + nombre + "%");
            }
        };
    }



    public static Specification<Sucursal> buscarConPaginacion(String criterio, Pageable pageable) {
        if (criterio == null || criterio.isBlank()) return null;

        return Specification.where(numeroSucursalIgualA(criterio)).or(nombreSucursalContiene(criterio));
    }

    public static Specification<Sucursal> buscar(String criterio) {
        if (criterio == null || criterio.isBlank()) return null;

        return Specification.where(numeroSucursalIgualA(criterio)).or(nombreSucursalContiene(criterio));
    }
}
