package com.jameszmapepa.zsestockpriceapi.model.data;


import com.jameszmapepa.zsestockpriceapi.model.Partner;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeMapper {

    Partner map(PartnerDTO partnerDTO);
}
