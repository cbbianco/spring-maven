package com.practice.evaluation.products.util.callhttp.typecall;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service("CallOperationHttpServiceImpl")
@RequiredArgsConstructor
@Slf4j
public class CallOperationHttpServiceImpl<T> implements CallOperationHttpService<T> {

    /**
     * @see CallOperationHttpService#handlerOperation(Object, String, Map, Map, String)
     */
    @Override
    public Request.Builder handlerOperation(T request, String uri, Map<String,String> header, Map<String,String> params, String method) {
        log.info("CallOperationHttpServiceImpl@handlerOperation");
        log.info("Request: {}", request);
        var urlBuilder = Objects.requireNonNull(HttpUrl.parse(uri)).newBuilder();
        log.info("Params: {}", params);
        if(!Objects.isNull(params)) {
            for(Map.Entry<String,String> param: params.entrySet()) {
                urlBuilder.addQueryParameter(param.getKey(),param.getValue());
            }
        }

        var uriBuild = urlBuilder.build().toString();
        var requestCall = new Request.Builder().url(uriBuild);

        switch (method) {
            case "POST":
                requestCall.post((RequestBody) request);
                break;
            case "PUT":
                requestCall.put((RequestBody) request);
                break;
            default:
                break;
        }

        log.info("Header: {}", header);
        if(!Objects.isNull(header)) {
            for(Map.Entry<String,String> headers: header.entrySet()) {
                requestCall.header(headers.getKey(),headers.getValue());
            }
        }

        return requestCall;
    }
}


