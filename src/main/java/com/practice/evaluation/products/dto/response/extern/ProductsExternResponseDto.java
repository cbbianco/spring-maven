package com.practice.evaluation.products.dto.response.extern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @apiNote ProductsExternResponseDto,  'dto' Encargada de Gestionar la respuesta externa a la consulta de los productos asociados
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
public class ProductsExternResponseDto {

    /**
     * @apiNote  Determina el nombre externo de su producto , name de tipo {@link String}
     */
    @JsonProperty("nombre_gamer")
    private String name;

    /**
     * @apiNote  Determina el valor de su producto , value de tipo {@link String}
     */
    @JsonProperty("costo")
    private String value;

}
