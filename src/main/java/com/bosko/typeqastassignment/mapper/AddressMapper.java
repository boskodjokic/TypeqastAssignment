package com.bosko.typeqastassignment.mapper;

import com.bosko.typeqastassignment.dto.AddressDTO;
import com.bosko.typeqastassignment.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressDTO transformToDTO(Address address);

    Address transformToEntity(AddressDTO addressDTO);
}
