package com.jameszmapepa.zsestockpriceapi.common.user;

import com.jameszmapepa.zsestockpriceapi.common.api.ApiConstants;
import com.jameszmapepa.zsestockpriceapi.common.api.ApiResponse;
import com.jameszmapepa.zsestockpriceapi.common.cpg.CPGRequest;
import com.jameszmapepa.zsestockpriceapi.common.cpg.CPGResponse;
import com.jameszmapepa.zsestockpriceapi.common.cpg.TransactionRequest;
import com.jameszmapepa.zsestockpriceapi.invoker.CPGInvoker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class EcocashUserController {

    private final CPGInvoker invoker;
    @Value("${cpg.rest-url}")
    private String url;


    @GetMapping("/details")
    public ApiResponse<CPGResponse> findByName() throws Exception {
        log.info("Pushing request to CPG");
        final CPGResponse cpgResponse = invoker.process(buildCPGRequest(), HttpMethod.POST, url);
        log.info("Returned CPG Response: {}", cpgResponse);
        return new ApiResponse<>(HttpStatus.OK.value(), ApiConstants.SUCCESS_MESSAGE, cpgResponse);
    }


    public CPGRequest buildCPGRequest() {
        return CPGRequest.builder()
                .transactionRequest(getTransactionRequest())
                .build();
    }

    public TransactionRequest getTransactionRequest() {
        return TransactionRequest.builder()
                .field1("GIGAIOTBPFT")
                .field2("b9ceb559e12a8c7dc6c1a6044f9ddc8ceabb6bf4c793888936ad7cda3b8e267f")
                .field3("774222479")
                .field6("checksum")
                .field7("000021")
                .field8("ecocashzw")
                .field10(LocalDateTime.now().toString())
                .field13("ZWL")
                .build();
    }
}
