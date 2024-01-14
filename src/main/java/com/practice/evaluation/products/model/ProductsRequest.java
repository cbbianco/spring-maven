package com.practice.evaluation.products.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.practice.evaluation.products.dto.details.ProductsDetailsDto;
import com.practice.evaluation.products.util.literal.Constants;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @apiNote ProductsRequest,  'Request' Encargada de Gestionar la petición
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
public class ProductsRequest {

    /**
     * @apiNote  Determina el modelo de su producto , model de tipo {@link String}
     */
    @NotNull(message = Constants.NOT_NULL)
    @NotBlank(message = Constants.ERROR_IS_STRING)
    @NotEmpty(message = Constants.ERROR_IS_STRING)
    @JsonProperty("modelo")
    private String model;

    /**
     * @apiNote  Determina el año de salida de su producto , year de tipo {@link String}
     */
    @NotNull(message = Constants.NOT_NULL)
    @NotBlank(message = Constants.ERROR_IS_STRING)
    @NotEmpty(message = Constants.ERROR_IS_STRING)
    @JsonProperty("año_salida")
    private String year;

    /**
     * @apiNote  Determina que tanta ram posee su producto , ram de tipo {@link String}
     */
    @NotNull(message = Constants.NOT_NULL)
    @NotBlank(message = Constants.ERROR_IS_STRING)
    @NotEmpty(message = Constants.ERROR_IS_STRING)
    @JsonProperty("ram")
    private String ram;

    /**
     * @apiNote  Determina que tanta ram posee su producto , ram de tipo {@link String}
     */
    @NotNull(message = Constants.NOT_NULL)
    @Size(min = 1, message = Constants.NOT_SIZE)
    @JsonProperty("detalles")
    private List<@Valid ProductsDetailsDto> productsDetailsDtoList;
}
