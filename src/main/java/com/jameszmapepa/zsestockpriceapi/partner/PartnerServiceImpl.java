package com.jameszmapepa.zsestockpriceapi.partner;


import com.jameszmapepa.zsestockpriceapi.common.enums.PartnerStatus;
import com.jameszmapepa.zsestockpriceapi.exception.RecordExistException;
import com.jameszmapepa.zsestockpriceapi.exception.RecordNotFoundException;
import com.jameszmapepa.zsestockpriceapi.model.Partner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {

    private final PartnerRepository partnerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<Partner> create(Partner partner) {
        if (partnerRepository.findByPartnerId(partner.getPartnerId()).isPresent()) {
            throw new RecordExistException("Partner With That ID does not exist");
        }
        partner.setPartnerKey(passwordEncoder.encode(partner.getPartnerKey()));
        partner.setStatus(PartnerStatus.ACTIVE);

        return Optional.of(partnerRepository.save(partner));
    }

    @Override
    public Optional<Partner> update(Partner partner) {
        final Partner old = this.findByPartnerId(partner.getPartnerId()).orElseThrow(() -> new RecordNotFoundException("Cannot Update a non-existing PartnerRecord"));
        old.setPartnerId(partner.getPartnerId());
        old.setEmail(partner.getEmail());
        old.setPartnerName(partner.getPartnerName());
        old.setStatus(partner.getStatus());

        return Optional.of(partnerRepository.save(old));
    }

    @Override
    public List<Partner> findAll(int page, int size) {
        return partnerRepository.findAll(PageRequest.of(page, size, Sort.by("partnerName"))).getContent();
    }

    @Override
    public Optional<Partner> findByPartnerId(String partnerId) {
        return partnerRepository.findByPartnerId(partnerId);
    }

    @Override
    public void resetPartnerKey(String partnerId, String newPassword) {
        Partner partner = this.findByPartnerId(partnerId).orElseThrow(() -> new RecordNotFoundException("Error Resetting"));
        partner.setPartnerKey(passwordEncoder.encode(newPassword));
        partnerRepository.save(partner);
        //Todo sent reset email
    }
}
