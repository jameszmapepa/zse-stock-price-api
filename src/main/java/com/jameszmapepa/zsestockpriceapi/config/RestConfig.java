package com.jameszmapepa.zsestockpriceapi.config;

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

    @Bean
    public OkHttpClient okHttpClient() {

        return new OkHttpClient.Builder()
                .connectTimeout(httpClientConfig.connectTimeout, TimeUnit.SECONDS)
                .writeTimeout(httpClientConfig.writeTimeout, TimeUnit.SECONDS)
                .readTimeout(httpClientConfig.readTimeout, TimeUnit.SECONDS)
                .addInterceptor(new HeaderInterceptor(httpHeaderConfig))
                .addInterceptor(new LoggingInterceptor())
                .build();
    }
}
