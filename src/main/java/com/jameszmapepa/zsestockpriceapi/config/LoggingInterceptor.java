package com.jameszmapepa.zsestockpriceapi.config;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@Slf4j
final class LoggingInterceptor implements Interceptor {

    long requestTime;
    long responseTime;

    @NotNull
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        requestTime = System.nanoTime();
        Response response = chain.proceed(request);
        responseTime = System.nanoTime();

        traceRequest(request);
        traceResponse(response);

        return response;
    }

    private void traceRequest(Request request) throws IOException {
        log.info("Request begin");
        log.info("URI         : {}", request.url());
        log.debug("Method      : {}", request.method());
        log.debug("Headers     : {}", request.headers());
        log.info("Request body: {}", request.body());
        log.info("Request End");
    }

    private void traceResponse(Response response) throws IOException {


        log.info("Response Begin");
        log.info("Status code     : {}", response.code());
        log.info("Status message  : {}", response.message());
        log.info("Headers         : {}", response.headers());
        log.info("Response body   : {}", response.body());
        log.info("Response end");
        log.info(String.format("Response received in %.1fms", (responseTime - requestTime) / 1e6d));
    }
}