package com.practice.evaluation.products.util.callhttp.httpclient;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * IProcessingResponseUtils
 * Abstracción para la gestión de respuestas HTTP
 */
public interface IProcessingResponseUtils {

    /**
     * doRequest
     *
     * @param client {@link OkHttpClient}
     * @param request {@link Request}
     * @return {@link Response}
     */
    Response doRequest(OkHttpClient client, Request request);
}
