package com.jameszmapepa.zsestockpriceapi.invoker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jameszmapepa.zsestockpriceapi.common.cpg.CPGRequest;
import com.jameszmapepa.zsestockpriceapi.common.cpg.CPGResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class CPGInvokerImpl implements CPGInvoker {

    private final OkHttpClient okHttpClient;
    private final Gson gson;
    private final ObjectMapper objectMapper;

    @Value("${http.header.contentType}")
    private String contentType;

    @Override
    public CPGResponse process(CPGRequest cpgRequest, HttpMethod httpMethod, String url) throws Exception {

        final Request request = buildRequest(cpgRequest, HttpMethod.POST, url);

        return execute(request);
    }

    private Request buildRequest(CPGRequest cpgRequest, HttpMethod httpMethod, String url) {
        final RequestBody requestBody = buildRequestBody(cpgRequest);
        final String requestMethod = String.valueOf(httpMethod);

        return new Request.Builder()
                .url(url)
                .method(requestMethod, requestBody)
                .build();
    }

    private RequestBody buildRequestBody(CPGRequest cpgRequest) {
        final String jsonRequest = gson.toJson(cpgRequest);
        final MediaType mediaType = MediaType.parse(contentType);

        return RequestBody.create(jsonRequest, mediaType);
    }

    private CPGResponse execute(Request request) {
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                final String responseBody = Objects.requireNonNull(response.body()).string();
                final CPGResponse cpgResponse = objectMapper.readValue(responseBody, CPGResponse.class);
                if (cpgResponse != null) {
                    log.debug("CPG response: {}", cpgResponse);
                    return cpgResponse;
                }
            }
        } catch (IOException ex) {
            log.error("Request Error", ex);
        } catch (Exception ex) {
            log.error("Error", ex);
        }
        log.info("Returned null Response");
        return null;
    }
}
