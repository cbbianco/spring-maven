package com.practice.evaluation.products.repository;

import com.practice.evaluation.products.entitiy.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * @apiNote ProductsRepository, Repositorio encargado de gestionar las conexiones con la entidad de producto y sus detalles
 *
 * @version 1.0.0
 */
@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity, BigInteger> {

    /**
     * @apiNote findByModel, Gestiona si el módelo ya fue creado con anterioridad
     *
     * @param model de tipo {@link String}
     * @return {@link ProductsEntity}
     */
    ProductsEntity findByModel(@Param("model") String model);
}
