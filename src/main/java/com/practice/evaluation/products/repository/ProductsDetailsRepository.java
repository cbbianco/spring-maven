package com.practice.evaluation.products.repository;

import com.practice.evaluation.products.entity.ProductsDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * @apiNote ProductsDetailsRepository, Repositorio encargado de gestionar las conexiones con la entidad de los detalles asociados
 *
 * @version 1.0.0
 */
@Repository
public interface ProductsDetailsRepository extends JpaRepository<ProductsDetailsEntity, BigInteger> {
}