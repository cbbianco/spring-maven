package com.practice.evaluation.products.util.callhttp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.evaluation.products.util.callhttp.httpclient.IProcessingResponseUtils;
import com.practice.evaluation.products.util.callhttp.typecall.CallOperationHttpService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * NotificarServices
 * Gestionaria las peticiones hacía el notificador
 */
@Service("NotificarServicesImpl")
@Getter
@RequiredArgsConstructor
@Slf4j
public class NotifiedServicesImpl<T,L> implements NotifiedServices<T,L> {

    /**
     * Atributo utilsSiebel
     */
    private final IProcessingResponseUtils iProcessingResponseUtils;

    /**
     * Atributo callOperationHttpService de tipo {@link CallOperationHttpService}
     */
    private final CallOperationHttpService<RequestBody> callOperationHttpService;
    
    /**
     * Atributo client de tipo {@link OkHttpClient}
     */
    private static final OkHttpClient okHttpClient = new OkHttpClient().newBuilder().connectTimeout(1000, TimeUnit.SECONDS)
            .readTimeout(1000, TimeUnit.SECONDS).writeTimeout(1000, TimeUnit.SECONDS)
            .callTimeout(1000, TimeUnit.SECONDS).build();

    /**
     *
     * @see NotifiedServices#notifiedRequest(Object, Class, Map, Map, String, String) 
     */
    @Override
    public L notifiedRequest(T request, Class<L> response, Map<String,String> header, Map<String,String> params, String method, String uri) throws IOException {
        log.info("NotifiedServicesImpl@notifiedRequest");
        OkHttpClient client = getInstance();
        var mapper = new ObjectMapper();
        var jsonString = mapper.writeValueAsString(request);
        var requestBody = RequestBody.create(jsonString, MediaType.parse("application/json"));
        var requestCall = this.callOperationHttpService.handlerOperation(requestBody,uri, header,params,method);
        log.info("Body: {}", jsonString);
        log.info("Request Build: {}", requestCall);

        var responseNoti = iProcessingResponseUtils.doRequest(client,requestCall.build());
        log.info("Respuesta de la notificación: {}", responseNoti.code());
        return this.generarRespuesta(responseNoti, response);
    }

    /**
     * getInstance
     * Genera la instancia de OkHttpClient
     *
     * @return {@link OkHttpClient}
     */
    private static OkHttpClient getInstance()
    {
        return okHttpClient;
    }

    /**
     * generarRespuesta
     * Genera la respuesta del consumo
     */
    private L generarRespuesta(Response response, Class<L> type) throws IOException {


        ObjectMapper objectMapper = new ObjectMapper();
        if(!Objects.isNull(response.body())) {
            var res = response.body().string();
            log.info("Response: {}", res);
            return objectMapper.readValue(res, type);
        }

        return null;
    }
}
