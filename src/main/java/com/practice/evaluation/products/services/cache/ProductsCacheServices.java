package com.practice.evaluation.products.services.cache;

import com.practice.evaluation.products.entity.ProductsEntity;

import java.util.List;

/**
 * @apiNote ProductsCacheServices, Abstracción encargado de gestionar a los productos en el cache
 *
 * @version 1.0.0
 */
public interface ProductsCacheServices {


    /**
     * @apiNote handlerProductsCache, Encargado de gestionar y cechear las consultas
     * @return {@link List}&lt;{@link ProductsEntity}&gt;
     */
    List<ProductsEntity> handlerProductsCache();
}
