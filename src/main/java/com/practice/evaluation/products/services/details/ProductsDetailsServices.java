package com.practice.evaluation.products.services.details;

import com.practice.evaluation.products.entitiy.ProductsDetailsEntity;
import com.practice.evaluation.products.entitiy.ProductsEntity;
import com.practice.evaluation.products.model.ProductsRequest;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * @apiNote ProductsDetailsServices, Abstracción encargado de gestionar a los detalles asociados a los productos
 *
 * @version 1.0.0
 */
public interface ProductsDetailsServices {

    /**
     * @apiNote handlerPersistProduct, Gestiona la persistencia del producto
     *
     * @param productsRequest de tipo {@link ProductsRequest}
     * @param idProduct de tipo {@link BigInteger}
     * @return {@link Optional}&gt;&lt;{@link ProductsEntity}&gt;
     */
    Boolean handlerPersistProductDetails(ProductsRequest productsRequest, BigInteger idProduct);
}
