package com.jameszmapepa.zsestockpriceapi.model;


import com.jameszmapepa.zsestockpriceapi.common.enums.PartnerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "partner")
@EqualsAndHashCode(callSuper = true)
public class Partner extends BaseEntity {
    @Column(name = "partner_id", nullable = false)
    private String partnerId;
    @Column(name = "partner_key", nullable = false)
    private String partnerKey;
    @Column(name = "partner_name", nullable = false)
    private String partnerName;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PartnerStatus status;
}
