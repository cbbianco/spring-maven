package com.practice.evaluation.products.commons.exception;

import com.practice.evaluation.products.dto.ConstraintViolationDto;
import com.practice.evaluation.products.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * @apiNote ProductsController, Controller encargado de gestionar recibir las excepciones asociadas a los productos
 *
 * @version 1.0.0
 */
@RestControllerAdvice
@Slf4j
public class ExceptionControllerHandler {

    /**
     * @apiNote handlerProductException, Excepción para gestionar y renderizar los errores en el procesamiento de la petición
     *
     * @param ex de tipo {@link ProductException}
     * @return {@link ResponseEntity}&lt;{@link Response}&lt;{@link Object}&gt;
     */
    @ExceptionHandler(ProductException.class)
    public ResponseEntity<Response<Object>> handlerProductException(ProductException ex) {
        log.info("Excepcion: {}", ex.getMessage());

        Response<Object> response = Response.builder()
                .failure(true)
                .code(ex.getStatus().value())
                .message(ex.getMessage())
                .body(null)
                .build();

        return ResponseEntity.status(ex.getStatus().value())
                .header("Content-Type", "application/json")
                .body(response);
    }

    /**
     * @apiNote handleMethodArgumentNotValidException, Excepción para gestionar y renderizar los errores de la petición
     *
     * @param ex de tipo {@link MethodArgumentNotValidException}
     * @return {@link ResponseEntity}&lt;{@link Response}&lt;{@link List}&lt;{@link ConstraintViolationDto}&gt;&gt;&gt;
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<List<ConstraintViolationDto>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<ConstraintViolationDto> constraintViolationList = new ArrayList<>();

        result.getFieldErrors().forEach(fieldError ->
                constraintViolationList.add(ConstraintViolationDto.builder().field(fieldError.getField()).message(fieldError.getDefaultMessage()).build())
        );

        Response<List<ConstraintViolationDto>> response = Response.<List<ConstraintViolationDto>>builder()
                .failure(true)
                .code(HttpStatus.BAD_REQUEST.value())
                .body(constraintViolationList)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .header("Content-Type", "application/json")
                .body(response);
    }
}
