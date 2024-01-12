package com.practice.evaluation.products.services.details;

import com.practice.evaluation.products.entity.ProductsEntity;
import com.practice.evaluation.products.model.ProductsRequest;

import java.math.BigInteger;
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

    /**
     * @apiNote handlerUpdateProductDetails, Gestiona la actualización del detalle asociado a su producto
     *
     * @param productsRequest de tipo {@link ProductsRequest}
     * @param idProduct de tipo {@link BigInteger}
     * @return {@link Optional}&gt;&lt;{@link ProductsEntity}&gt;
     */
    Boolean handlerUpdateProductDetails(ProductsRequest productsRequest, BigInteger idProduct);
}
