
package com.jameszmapepa.zsestockpriceapi.config;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

final class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        request = request.newBuilder()
                .addHeader("Accept", "application/json")
                .build();

        return chain.proceed(request);
    }

}
