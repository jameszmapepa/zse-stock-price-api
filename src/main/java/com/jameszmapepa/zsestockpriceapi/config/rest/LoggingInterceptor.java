package com.jameszmapepa.zsestockpriceapi.config.rest;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.Buffer;
import okio.BufferedSource;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
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
        final HttpUrl url = request.url();
        final String method = request.method();
        final Map<String, List<String>> headers = request.headers().toMultimap();
        final String body = bodyToString(request);

        log.debug("\n");
        log.info("Request begin");
        log.info("URI         : {}", url);
        log.debug("Method      : {}", method);
        log.debug("Headers     : {}", headers);
        log.info("Body        : {}", body);
        log.info("Request End");
    }

    private void traceResponse(Response response) throws IOException {
        final int code = response.code();
        final String message = response.message();
        final Map<String, List<String>> headers = response.headers().toMultimap();
        final String body = bodyToString(response);

        log.info("Response Begin");

        log.info("Status code     : {}", code);
        log.info("Status message  : {}", message);
        log.info("Headers         : {}", headers);
        log.info("Body            : {}", body);
        log.info("Response end");
    }

    private static String bodyToString(Response response) {
        try {
            final ResponseBody responseBody = response.body();
            assert responseBody != null;
            final BufferedSource source = responseBody.source();
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