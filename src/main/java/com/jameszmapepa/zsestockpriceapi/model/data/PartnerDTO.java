package com.jameszmapepa.zsestockpriceapi.model.data;

import com.jameszmapepa.zsestockpriceapi.common.enums.PartnerStatus;
import lombok.Data;

import java.io.Serializable;

@Data
public class PartnerDTO implements Serializable {

    private String partnerId;
    private String partnerKey;
    private String partnerName;
    private String email;
    private PartnerStatus status;
}
