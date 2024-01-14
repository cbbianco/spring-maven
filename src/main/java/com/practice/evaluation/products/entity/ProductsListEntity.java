package com.practice.evaluation.products.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

/**
 * @apiNote ProductsEntity, 'entity' Entidad de Productos, haciendo JOIN desde JPA
 *
 * @version 1.0.0
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "productos")
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
public class ProductsListEntity {

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

    /**
     *
     */
    @JsonProperty("detalles")
    @OneToMany(targetEntity = ProductsDetailsEntity.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="productos_id",referencedColumnName = "id")
    private List<ProductsDetailsEntity> productsDetailsEntityList;
}
