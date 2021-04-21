package com.jameszmapepa.zsestockpriceapi.config;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class RestConfig {

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .authenticator(getAuthenticator())
                .build();
    }

    private Authenticator getAuthenticator() {
        return new Authenticator() {
            @Override
            public Request authenticate(Route route, @NotNull Response response) throws IOException {
                if (response.request().header("Authorization") != null) {
                    return null; // Give up, we've already attempted to authenticate.
                }

                log.info("Authenticating for response: {}", response);
                log.info("Challenges: {}", response.challenges());
                String credential = Credentials.basic("user", "password");
                return response.request().newBuilder()
                        .header("Authorization", credential)
                        .build();
            }
        };
    }
}
