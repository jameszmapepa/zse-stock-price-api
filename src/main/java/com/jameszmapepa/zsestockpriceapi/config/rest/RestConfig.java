package com.jameszmapepa.zsestockpriceapi.config.rest;

import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@RequiredArgsConstructor
public class RestConfig {

    private final HttpClientConfig httpClientConfig;
    private final HttpHeaderConfig httpHeaderConfig;
    private final HeaderInterceptor headerInterceptor;
    private final LoggingInterceptor loggingInterceptor;

    @Bean
    public OkHttpClient okHttpClient() {

        return new OkHttpClient.Builder()
                .connectTimeout(httpClientConfig.connectTimeout, TimeUnit.SECONDS)
                .writeTimeout(httpClientConfig.writeTimeout, TimeUnit.SECONDS)
                .readTimeout(httpClientConfig.readTimeout, TimeUnit.SECONDS)
                .addInterceptor(headerInterceptor)
                .addInterceptor(loggingInterceptor)
                .build();
    }
}
