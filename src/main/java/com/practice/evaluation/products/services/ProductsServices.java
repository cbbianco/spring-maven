package com.practice.evaluation.products.services;

import com.practice.evaluation.products.dto.response.ProductsResponseDto;
import com.practice.evaluation.products.model.ProductsRequest;

import java.util.List;

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
     *
     * @return {@link String}
     */
    String handlerPersistProduct(ProductsRequest productsRequest);


    /**
     * @apiNote handlerUpdateProduct, Gestiona la actualización del producto

     * @param productsRequest de tipo {@link ProductsRequest}
     * @param productId de tipo {@link String}
     *
     * @return {@link String}
     */
    String handlerUpdateProduct(ProductsRequest productsRequest, String productId);

    /**
     * @apiNote handlerConsultProduct, Gestiona la consulta del producto

     * @param productId de tipo {@link String}
     *
     * @return {@link List}&lt;{@link ProductsResponseDto}&gt;
     */
    List<ProductsResponseDto> handlerConsultProduct(String productId);
}
