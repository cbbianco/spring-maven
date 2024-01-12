package com.practice.evaluation.products.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @apiNote Response, 'Response' Encargada de Gestionar la respuesta de los servicios
 *
 * @param <T>
 * @version 1.0.0
 */
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {

    /**
     * @apiNote  Determina si hubo un fallo o no en el servicio , failure de tipo {@link Boolean}
     */
    private Boolean failure;

    /**
     * @apiNote  Determina el código http que resultante , code de tipo {@link Integer}
     */
    private Integer code;

    /**
     * @apiNote  Determina el mensaje resultante , message de tipo {@link Integer}
     */
    private String message;

    /**
     * @apiNote  'body' se establece como el posible cuerpo que podría retornarse en la respuesta , body de tipo {@link T}
     */
    private T body;
}
