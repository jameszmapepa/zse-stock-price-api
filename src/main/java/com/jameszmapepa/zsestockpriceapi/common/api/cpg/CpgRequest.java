package com.jameszmapepa.zsestockpriceapi.common.api.cpg;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.io.Serializable;


@JsonPropertyOrder({
        "transactionRequest"
})
@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CpgRequest implements Serializable {

    @JsonProperty("transactionRequest")
    public TransactionRequest transactionRequest;

    private static final long serialVersionUID = 1400258964251011629L;
}