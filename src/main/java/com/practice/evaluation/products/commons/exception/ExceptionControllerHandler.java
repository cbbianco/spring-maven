package com.practice.evaluation.products.commons.exception;

import com.practice.evaluation.products.dto.ConstraintViolationDto;
import com.practice.evaluation.products.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @apiNote ProductsController, Controller encargado de gestionar recibir las excepciones asociadas a los productos
 *
 * @version 1.0.0
 */
//@RestControllerAdvice
@Slf4j
public class ExceptionControllerHandler {

    /**
     * Metodo que captura la excepcion al momento de
     * @param ex  Objeto <strong>MethodArgumentNotValidException</strong> con el cual se maneja la excepción
     * @param request Objeto con los datos de la petición
     * @return Objeto con el mensaje de la excepción capturada
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Response<String> methodArgumentNotValidException(HttpRequestMethodNotSupportedException ex, WebRequest request) {

        log.info("MethodArgumentNotValidException: Url Request: {} | Message: {}", request.getContextPath(), ex.getMessage());

        return Response.<String>builder()
                .failure(true)
                .code(HttpStatus.BAD_REQUEST.value())
                .body(ex.getMessage())
                .build();
    }

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
