package com.practice.evaluation.products.repository;

import com.practice.evaluation.products.entity.ProductsListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * @apiNote ProductsListRepository, Repositorio encargado de gestionar las conexiones con la entidad de producto y sus detalles,
 * Con Opción de Manejo desde JPA para las relaciones
 *
 * @version 1.0.0
 */
@Repository
public interface ProductsListRepository extends JpaRepository<ProductsListEntity, BigInteger> {
}
