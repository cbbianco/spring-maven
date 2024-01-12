package com.practice.evaluation.products.commons.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @apiNote ProductException, Excepción encargada de gestionar los posibles errores que puedan ocurrir en la gestión de Productos
 *
 * @version 1.0.0
 */
@Getter
public class ProductException extends RuntimeException {


    /**
     * @apiNote Determina el estado resultante, status de tipo {@link HttpStatus}
     */
    private final HttpStatus status;

    /**
     * @apiNote ProductException, Constructor de la Excepción
     *
     * @param  message de tipo {@link String}
     * @param  status de tipo {@link HttpStatus}
     */
    public ProductException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
