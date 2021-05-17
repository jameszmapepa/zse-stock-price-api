
package com.jameszmapepa.zsestockpriceapi.common.cpg;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonPropertyOrder({
        "return"
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CPGResponse implements Serializable {

    @JsonProperty("return")
    public Return cpgReturn;

    private static final long serialVersionUID = -1818259832585867013L;
}
