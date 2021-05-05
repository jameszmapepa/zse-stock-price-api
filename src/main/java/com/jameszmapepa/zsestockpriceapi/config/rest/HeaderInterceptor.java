
package com.jameszmapepa.zsestockpriceapi.config.rest;

import lombok.RequiredArgsConstructor;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
final class HeaderInterceptor implements Interceptor {

    private final HttpHeaderConfig headerConfig;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        request = request.newBuilder()
                .addHeader("content-type", headerConfig.contentType)
                .addHeader("Authorization", headerConfig.authToken)
                .build();

        return chain.proceed(request);
    }
}
