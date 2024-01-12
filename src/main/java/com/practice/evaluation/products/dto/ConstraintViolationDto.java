package com.practice.evaluation.products.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Clase encargada de manejar los mensajes de infracción de restricción
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConstraintViolationDto implements Serializable {

    private static final long serialVersionUID = 161L;

    /**
     *  @apiNote Campo que genero el error, field de tipo {@link String}
     */
    @JsonProperty("campo")
    private String field;

    /**
     *  @apiNote Campo message asociado al error, message de tipo {@link String}
     */
    @JsonProperty("mensaje")
    private String message;
}
