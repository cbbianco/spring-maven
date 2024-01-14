package com.practice.evaluation.products.dto.details;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.practice.evaluation.products.util.literal.Constants;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @apiNote ProductsDetailsDto,  'dto' Encargada de Gestionar los detalles asociado al producto
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
public class ProductsDetailsDto {

    /**
     * @apiNote  Determina el almacenamiento de su producto , storage de tipo {@link String}
     */
    @NotNull(message = Constants.NOT_NULL)
    @NotBlank(message = Constants.ERROR_IS_STRING)
    @NotEmpty(message = Constants.ERROR_IS_STRING)
    @JsonProperty("almacenamiento")
    private String storage;

    /**
     * @apiNote  Determina la gpu de salida de su producto , gpu de tipo {@link String}
     */
    @NotNull(message = Constants.NOT_NULL)
    @NotBlank(message = Constants.ERROR_IS_STRING)
    @NotEmpty(message = Constants.ERROR_IS_STRING)
    @JsonProperty("gpu")
    private String gpu;
}
