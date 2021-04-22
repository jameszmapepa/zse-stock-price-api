package com.jameszmapepa.zsestockpriceapi.invoker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jameszmapepa.zsestockpriceapi.common.cpg.CPGRequest;
import com.jameszmapepa.zsestockpriceapi.common.cpg.CPGResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component

public class CPGInvokerImpl implements CPGInvoker {

    @Autowired
    private OkHttpClient okHttpClient;
    @Autowired
    private Gson gson;
    @Autowired
    private ObjectMapper objectMapper;
    @Value("${cpg.rest-url}")
    private String url;
    @Value("${cpg.rest-authorization-token}")
    private String cpgAuthorizationKey;

    @Override
    public CPGResponse process(CPGRequest cpgRequest) throws Exception {

        final String jsonRequest = gson.toJson(cpgRequest);
        final MediaType mediaType = MediaType.parse("application/json");

        final RequestBody requestBody = RequestBody.create(jsonRequest, mediaType);
        final String requestMethod = String.valueOf(HttpMethod.POST);

        Request request = new Request.Builder()
                .url(url)
                .method(requestMethod, requestBody)
                .addHeader("content-type", "application/json")
                .addHeader("authorization", cpgAuthorizationKey)
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                final String responseData = response.body().string();
                // final CPGResponse cpgResponse = gson.fromJson(responseData, CPGResponse.class); FIXME GSON not working with JSON object called return
                final CPGResponse cpgResponse = objectMapper.readValue(responseData, CPGResponse.class);
                if (cpgResponse != null) {
                    log.debug("The CPG response =====> {}", cpgResponse);

                    return cpgResponse;
                }
            }
        } catch (IOException ex) {
            log.error("RequestError", ex);

        } catch (Exception ex) {
            log.error("Error", ex);
        }
        log.info("Returned null object");
        return null;
    }
}
