package com.practice.evaluation.products.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.practice.evaluation.products.dto.response.extern.ProductsExternResponseDto;
import com.practice.evaluation.products.entity.ProductsEntity;
import com.practice.evaluation.products.entity.ProductsListEntity;
import lombok.*;

import java.math.BigInteger;
import java.util.List;

/**
 * @apiNote ProductsResponseDto,  'dto' Encargada de Gestionar la respuesta a la consulta de los productos asociados
 *
 * @version 1.0.0
 */
@Getter
@Builder
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductsResponseDto {

    /**
     * @apiNote  Determina el id externo de su producto , id de tipo {@link BigInteger}
     */
    @JsonProperty("id")
    private BigInteger id;

    /**
     * @apiNote  Determina la información interna de su producto , productsListEntity de tipo {@link ProductsListEntity}
     */
    @JsonProperty("datos_local")
    private ProductsListEntity productsListEntity;

    /**
     * @apiNote  Determina la información interna de su producto que están cacheados , productsListEntities de tipo {@link ProductsListEntity}
     */
    @JsonProperty("datos_cache")
    private List<ProductsEntity> productsCache;


    /**
     * @apiNote  Determina la información externa de su producto , name de tipo {@link ProductsExternResponseDto}
     */
    @JsonProperty("datos_externos")
    private ProductsExternResponseDto info;
}
