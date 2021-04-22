package com.jameszmapepa.zsestockpriceapi.partner;


import com.jameszmapepa.zsestockpriceapi.model.Partner;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartnerRepository extends PagingAndSortingRepository<Partner, Long> {

    Optional<Partner> findByPartnerId(String partnerId);

}
