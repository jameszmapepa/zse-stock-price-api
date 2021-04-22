package com.jameszmapepa.zsestockpriceapi.config.rest;

import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;


final class ErrorInterceptor implements Interceptor {
    @NotNull
    public Response intercept(@NotNull Chain chain) throws IOException {
        Intrinsics.checkParameterIsNotNull(chain, "chain");
        Request request = chain.request();
        Response response = chain.proceed(request);
        switch (response.code()) {
            case 500:

            case 404:

            default:
                return response;
        }
    }
}
