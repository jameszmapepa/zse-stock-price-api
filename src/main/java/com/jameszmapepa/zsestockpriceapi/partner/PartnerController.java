package com.jameszmapepa.zsestockpriceapi.partner;


import com.jameszmapepa.zsestockpriceapi.common.api.ApiConstants;
import com.jameszmapepa.zsestockpriceapi.common.api.ApiResponse;
import com.jameszmapepa.zsestockpriceapi.exception.BusinessException;
import com.jameszmapepa.zsestockpriceapi.exception.RecordNotFoundException;
import com.jameszmapepa.zsestockpriceapi.model.Partner;
import com.jameszmapepa.zsestockpriceapi.model.data.PartnerDTO;
import com.jameszmapepa.zsestockpriceapi.model.data.TypeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/partner")
@RequiredArgsConstructor
public class PartnerController {

    private final PartnerService partnerService;
    private final TypeMapper typeMapper;


    @PostMapping
    public ApiResponse<Partner> save(@RequestBody PartnerDTO partnerDTO) {
        final Partner partner = partnerService.create(typeMapper.map(partnerDTO)).orElseThrow(() -> new BusinessException("Error Occurred. Creating Partner"));
        return new ApiResponse<>(HttpStatus.OK.value(), ApiConstants.SUCCESS_MESSAGE, partner);
    }

    @PostMapping("/update")
    public ApiResponse<Partner> update(@RequestBody PartnerDTO partnerDTO) {
        final Partner partner = partnerService.update(typeMapper.map(partnerDTO)).orElseThrow(() -> new BusinessException("Error Occurred. Updating Partner"));
        return new ApiResponse<>(HttpStatus.OK.value(), ApiConstants.SUCCESS_MESSAGE, partner);
    }

    @GetMapping("/list/page/{page}/size/{size}")
    public ApiResponse<List<Partner>> findAll(@PathVariable("page") int page, @PathVariable("size") int size) {
        size = size == 0 ? 50 : size;

        log.info("List Partners page:{} and size:{}", page, size);
        List<Partner> partnerList = partnerService.findAll(page, size);
        return new ApiResponse<>(HttpStatus.OK.value(), ApiConstants.SUCCESS_MESSAGE, partnerList);
    }

    @GetMapping("/findby-partner-id/{partnerId}")
    public ApiResponse<Partner> findByName(@PathVariable("partnerId") String partnerId) {
        Partner partner = partnerService.findByPartnerId(partnerId).orElseThrow(() -> new RecordNotFoundException("Record Not Found"));
        return new ApiResponse<>(HttpStatus.OK.value(), ApiConstants.SUCCESS_MESSAGE, partner);
    }


}
