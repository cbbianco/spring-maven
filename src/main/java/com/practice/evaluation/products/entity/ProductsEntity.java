package com.practice.evaluation.products.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.practice.evaluation.products.commons.audit.Audit;
import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productos")
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
public class ProductsEntity extends Audit<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigInteger id;

    /**
     * @apiNote  Determina el modelo de su producto , model de tipo {@link String}
     */
    @JsonProperty("modelo")
    @Column(name = "modelo")
    private String model;

    /**
     * @apiNote  Determina el año de salida de su producto , year de tipo {@link String}
     */
    @JsonProperty("año_salida")
    @Column(name = "año_salida")
    private String year;

    /**
     * @apiNote  Determina que tanta ram posee su producto , ram de tipo {@link String}
     */
    @JsonProperty("ram")
    @Column(name = "ram")
    private String ram;
}
