
package com.jameszmapepa.zsestockpriceapi.config;

import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

final class BasicAuthInterceptor implements Interceptor {

    @NotNull
    public Response intercept(@NotNull Chain chain) throws IOException {
        Intrinsics.checkParameterIsNotNull(chain, "chain");
        Request originalRequest = chain.request();
        Builder requestBuilder = originalRequest.newBuilder().header("Authorization", "AuthToken");
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
