package com.practice.evaluation.products.util.callhttp.typecall;

import okhttp3.Request;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * CallOperationHttpService
 * Gestiona el armado de la petición que se quiere realizar
 */
public interface CallOperationHttpService<T> {

    /**
     * handlerOperation
     * Gestionará el armado de las peticiones
     *
     * @param uri de tipo {@link String}
     * @param method de tipo {@link String}
     * @param header de tipo {@link Map}&lt;{@link String},{@link String}&gt;
     * @param params de tipo {@link Map}&lt;{@link String},{@link String}&gt;
     * @return {@link Request.Builder}
     */
    Request.Builder handlerOperation(T request, @NotNull  String uri, Map<String,String> header, Map<String,String> params, @NotNull String method);
}
