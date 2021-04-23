package com.jameszmapepa.zsestockpriceapi.config.rest;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

@Slf4j
final class LoggingInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        final long requestTime = System.nanoTime();
        Response response = chain.proceed(request);
        final long responseTime = System.nanoTime();

        traceRequest(request);
        traceResponse(response);
        log.info(String.format("Response received in %.1fms", (responseTime - requestTime) / 1e6d));
        log.debug("\n");

        return response;
    }

    private void traceRequest(Request request) throws IOException {
        log.debug("\n");
        log.info("Request begin");
        log.info("URI         : {}", request.url());
        log.debug("Method      : {}", request.method());
        log.debug("Headers     : {}", request.headers().toMultimap());
        log.info("Body        : {}", bodyToString(request));
        log.info("Request End");
    }

    private void traceResponse(Response response) throws IOException {
        log.info("Response Begin");
        log.info("Status code     : {}", response.code());
        log.info("Status message  : {}", response.message());
        log.info("Headers         : {}", response.headers().toMultimap());
        log.info("Body            : {}", bodyToString(response));
        log.info("Response end");
    }

    private static String bodyToString(Response response) {
        try {
            ResponseBody responseBody = response.body();
            assert responseBody != null;
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.getBuffer();

            return buffer.clone().readUtf8();
        } catch (final IOException e) {
            log.error("Could not display response Body: ", e);

            return null;
        }
    }

    private static String bodyToString(final Request request) {
        try {
            final Request newRequest = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            Objects.requireNonNull(newRequest.body()).writeTo(buffer);

            return buffer.readUtf8();
        } catch (final IOException e) {
            log.error("Could not display response Body: ", e);

            return null;
        }
    }
}