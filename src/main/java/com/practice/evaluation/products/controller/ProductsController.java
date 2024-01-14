package com.practice.evaluation.products.controller;

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

    /**
     * @apiNote Servicio Facade de gestión de los productos y sus detalles, productsServices de tipo {@link ProductsServices}
     */
    private final ProductsServices productsServices;

    /**
     * @apiNote saveProducts, Endpoint encargado de almacenar un producto y sus detalle
     *
     * @param productsRequest de tipo {@link ProductsRequest}
     * @return {@link ResponseEntity}&lt;{@link Response}&lt;{@link String}&gt;&gt;
     */
    @PostMapping(value = "/productos/guardar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<String>> saveProducts(@RequestBody  @Valid ProductsRequest productsRequest) {
        return new ResponseEntity<>(Response.<String>builder()
                .failure(false)
                .code(HttpStatus.CREATED.value())
                .message(productsServices.handlerPersistProduct(productsRequest))
                .body(null)
                .build(), HttpStatus.CREATED);
    }
    /**
     * @apiNote saveProducts, Endpoint encargado de almacenar un producto y sus detalle
     *
     * @param productsRequest de tipo {@link ProductsRequest}
     * @param productId de tipo {@link String}
     * @return {@link ResponseEntity}&lt;{@link Response}&lt;{@link String}&gt;&gt;
     */
    @PutMapping(value = "/productos/actualizar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<String>> updateProducts(@RequestBody  @Valid ProductsRequest productsRequest, @RequestParam("producto") String productId) {
        return new ResponseEntity<>(Response.<String>builder()
                .failure(false)
                .code(HttpStatus.OK.value())
                .message(productsServices.handlerUpdateProduct(productsRequest, productId))
                .body(null)
                .build(), HttpStatus.OK);
    }

    /**
     * @apiNote consultProduct, Endpoint encargado de consultar un producto y sus detalle
     *
     * @param productId de tipo {@link String}
     * @return {@link ResponseEntity}&lt;{@link Response}&lt;{@link Object}&gt;&gt;
     */
    @GetMapping(value = "/productos/consultar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Object>> consultProduct(@RequestParam("producto") String productId) {
        return new ResponseEntity<>(Response.builder()
                .failure(false)
                .code(HttpStatus.OK.value())
                .message("")
                .body(productsServices.handlerConsultProduct(productId))
                .build(), HttpStatus.OK);
    }
}
