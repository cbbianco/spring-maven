package com.practice.evaluation.products.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * @apiNote ProductsListDetailsEntity, 'entity' Entidad de los Detalles, haciendo JOIN desde JPA
 *
 * @version 1.0.0
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "detalles")
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
public class ProductsListDetailsEntity {


    /**
     * @apiNote  Determina el id de su detalle , storage de id {@link BigInteger}
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigInteger id;

    /**
     * @apiNote  Determina el id asociado a su producto, storage de idProduct {@link BigInteger}
     */
    @Column(name = "productos_id")
    private BigInteger idProduct;

    /**
     * @apiNote  Determina el almacenamiento de su producto , storage de tipo {@link String}
     */
    @JsonProperty("almacenamiento")
    @Column(name = "almacenamiento")
    private String storage;

    /**
     * @apiNote  Determina la gpu de su producto , gpu de tipo {@link String}
     */
    @JsonProperty("gpu")
    @Column(name = "gpu")
    private String gpu;
}
