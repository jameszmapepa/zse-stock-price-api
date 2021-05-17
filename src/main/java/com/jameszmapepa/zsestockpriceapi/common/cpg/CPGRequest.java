package com.jameszmapepa.zsestockpriceapi.common.cpg;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@JsonPropertyOrder({"transactionRequest"})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CPGRequest implements Serializable {

    @JsonProperty("transactionRequest")
    public TransactionRequest transactionRequest;

    private static final long serialVersionUID = 1400258964251011629L;
}