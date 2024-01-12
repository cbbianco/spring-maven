package com.practice.evaluation.products.services;

import com.practice.evaluation.products.entitiy.ProductsDetailsEntity;
import com.practice.evaluation.products.model.ProductsRequest;

import java.util.List;
import java.util.Optional;

/**
 * @apiNote ProductsServices, Abstracción encargado de gestionar a los productos asociados
 *
 * @version 1.0.0
 */
public interface ProductsServices {


    /**
     * @apiNote handlerPersistProduct, Gestiona la persistencia del producto
     *
     * @param productsRequest de tipo {@link ProductsRequest}
     * @return {@link Optional}&gt;&lt;{@link ProductsDetailsEntity}&gt;
     */
    Boolean handlerPersistProduct(ProductsRequest productsRequest);
}
