package com.bosko.typeqastassignment.mapper;

import com.bosko.typeqastassignment.dto.AddressDTO;
import com.bosko.typeqastassignment.entity.Address;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressDTO transformToDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        BeanUtils.copyProperties(address, addressDTO);
        return addressDTO;
    }

    public Address transformToEntity(AddressDTO addressDTO) {
        Address address = new Address();
        BeanUtils.copyProperties(addressDTO, address);
        return address;
    }
}
