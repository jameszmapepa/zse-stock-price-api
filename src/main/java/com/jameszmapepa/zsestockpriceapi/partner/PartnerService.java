package com.jameszmapepa.zsestockpriceapi.partner;


import com.jameszmapepa.zsestockpriceapi.model.Partner;

import java.util.List;
import java.util.Optional;

public interface PartnerService {

    Optional<Partner> create(Partner partner);

    Optional<Partner> update(Partner partner);

    List<Partner> findAll(int page, int size);

    Optional<Partner> findByPartnerId(String partnerId);

    void resetPartnerKey(String partnerId, String newPassword);

}
