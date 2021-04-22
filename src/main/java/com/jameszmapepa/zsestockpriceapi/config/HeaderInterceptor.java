
package com.jameszmapepa.zsestockpriceapi.config;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

final class HeaderInterceptor implements Interceptor {

    @Value("${cpg.rest-authorization-token}")
    private String cpgAuthorizationKey;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        request = request.newBuilder()
                .addHeader("content-type", "application/json")
                .addHeader("Authorization", cpgAuthorizationKey)
                .build();

        return chain.proceed(request);
    }

}
