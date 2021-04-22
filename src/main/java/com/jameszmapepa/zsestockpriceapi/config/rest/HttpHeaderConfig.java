package com.jameszmapepa.zsestockpriceapi.config.rest;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "http.header")
@Getter
@Setter
public class HttpHeaderConfig {

    String authToken;
    String contentType;

}
