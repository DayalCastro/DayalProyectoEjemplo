package com.dayal.proyecto.ejemplo.persistence.crud;

import com.dayal.proyecto.ejemplo.persistence.entity.Producto;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    Optional<List<Producto>> findByCantidadStockLessThanEstado(int quantity, boolean b);
}
