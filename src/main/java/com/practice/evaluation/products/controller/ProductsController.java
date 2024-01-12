package com.practice.evaluation.products.controller;

import com.practice.evaluation.products.entitiy.ProductsDetailsEntity;
import com.practice.evaluation.products.entitiy.ProductsEntity;
import com.practice.evaluation.products.model.ProductsRequest;
import com.practice.evaluation.products.model.Response;
import com.practice.evaluation.products.services.ProductsServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @apiNote ProductsController, Controller encargado de gestionar recibir las solicitudes asociadas a los productos
 *
 * @version 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class ProductsController {

    private final ProductsServices productsServices;

    /**
     * @apiNote saveProducts, Endpoint encargado de almacenar un producto y sus detalle
     *
     * @param productsRequest de tipo {@link ProductsRequest}
     * @return {@link ResponseEntity}&lt;{@link Response}&lt;{@link Optional}&gt;&lt;{@link ProductsEntity}&gt;&gt;
     */
    @PostMapping(value = "/productos/guardar")
    public ResponseEntity<Response<Boolean>> saveProducts(@RequestBody  @Valid ProductsRequest productsRequest) {
        return new ResponseEntity<>(Response.<Boolean>builder()
                .failure(false)
                .code(HttpStatus.CREATED.value())
                .body(productsServices.handlerPersistProduct(productsRequest))
                .build(), HttpStatus.CREATED);
    }

    /**
     *
     * @return {@link }
     */
    @PutMapping(value = "/productos/actualizar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Object>> updateProducts() {
        return new ResponseEntity<>(Response.builder().build(), HttpStatus.OK);
    }

    /**
     *
     * @return {@link }
     */
    @GetMapping(value = "/productos/consultar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Object>> consultProduct() {
        return new ResponseEntity<>(Response.builder().build(), HttpStatus.OK);
    }
}
