
package com.jameszmapepa.zsestockpriceapi.config;

import kotlin.jvm.internal.Intrinsics;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

@Slf4j
final class BasicAuthInterceptor implements Interceptor {

    private static final String AUTHORIZATION = "Authorization";

    @Value("${rest.basic-auth-username}")
    private String username;
    @Value("${rest.basic-auth-password}")
    private String password;


    @NotNull
    public Response intercept(@NotNull Chain chain) throws IOException {
        Intrinsics.checkParameterIsNotNull(chain, "chain");
        Request originalRequest = chain.request();

        log.debug("Authenticating for request: {}", originalRequest);
        String credential = Credentials.basic(username, password);
        Builder requestBuilder = originalRequest.newBuilder()
                .header(AUTHORIZATION, credential);
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
