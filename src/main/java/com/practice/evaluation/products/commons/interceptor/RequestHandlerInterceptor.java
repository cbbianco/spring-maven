package com.practice.evaluation.products.commons.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 *  @apiNote RequestHandlerInterceptor, Interceptor
 *
 * @version 1.0.0
 */
@Slf4j
public class RequestHandlerInterceptor implements HandlerInterceptor {

    /**
     * @apiNote Ruta del Archivo, route de tipo {@link String}
     */
    @Value("${app.route}")
    private String route;

    /**
     * @apiNote Tiempo Inicial, startTime de tipo {@link Long}
     */
    private long startTime;

    /**
     * @apiNote preHandle, Gestiona la solicitud antes de llegar a su destino
     *
     * @param request de tipo {@link HttpServletRequest}
     * @param response de tipo {@link HttpServletResponse}
     * @param handler de tipo {@link Object}
     * @return {@link Boolean}
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        startTime = System.currentTimeMillis();
        return true;
    }

    /**
     * @apiNote postHandle, Gestiona la solicitud después de llegar a su destino
     *
     * @param request de tipo {@link HttpServletRequest}
     * @param response de tipo {@link HttpServletResponse}
     * @param handler de tipo {@link Object}
     * @param modelAndView de tipo {@link ModelAndView}
     * @throws Exception Excepción que puede ocurrir
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        log.info("URL: {} - Tiempo de respuesta: {} ms", request.getRequestURI(), executionTime);

        var file = new File(route);
        if(!file.exists()) {
            log.info("Resultado de la creación: {}", file.createNewFile());
        }

        try (FileWriter fileWriter = new FileWriter(file, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            String logMessage = String.format("URL: %s - Tiempo de respuesta: %d ms - %s%n",
                    request.getRequestURI(), executionTime, LocalDateTime.now());
            bufferedWriter.write(logMessage);
        } catch (IOException e) {
            log.info("Hubo un error: {}", e.getMessage());
        }
    }
}
