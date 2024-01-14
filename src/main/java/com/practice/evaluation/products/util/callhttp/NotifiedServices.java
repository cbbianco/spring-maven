package com.practice.evaluation.products.util.callhttp;

import java.io.IOException;
import java.util.Map;

/**
 * NotificarServices
 * Manejador Generico de Peticiones hacía el notificador
 */
public interface NotifiedServices<T,L> {

    /**
     * notifiedRequest
     *
     * @param request de tipo {@link T}
     * @param type de tipo {@link L}
     * @param header de tipo {@link Map}&lt;{@link String},{@link String}&gt;
     * @param params de tipo {@link Map}&lt;{@link String},{@link String}&gt;
     * @param method de tipo {@link String}
     * @param uri de tipo {@link String}
     * @return {@link String}
     */
    L notifiedRequest(T request, Class<L> type, Map<String,String> header, Map<String,String> params, String method, String uri) throws IOException;
}
