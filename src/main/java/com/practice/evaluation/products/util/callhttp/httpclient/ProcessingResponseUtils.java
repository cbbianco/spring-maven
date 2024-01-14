package com.practice.evaluation.products.util.callhttp.httpclient;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ProcessingResponseUtils
 * Clase de manejo para la gestión de respuestas/peticiones HTTP
 */
@Component
@Slf4j
public class ProcessingResponseUtils implements IProcessingResponseUtils{

    /**
     * @param client  Instancia de OkHttpCliente
     * @param request Request del servicio
     * @return {@link Response} Respuesta del servicio
     */
    @Override
    public Response doRequest(OkHttpClient client, Request request) {
        try {
            log.info("ProcessingResponseUtils@doRequest");
            return client.newCall(request).execute();
        } catch (Exception e) {
            log.info("Error: ProcessingResponseUtils@doRequest: {}", e.getMessage());
            throw new RuntimeException("Error al consultar su producto", e);
        }
    }
}
