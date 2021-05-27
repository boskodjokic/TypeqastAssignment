package com.bosko.typeqastassignment.mapper;

import com.bosko.typeqastassignment.dto.*;
import com.bosko.typeqastassignment.entity.Address;
import com.bosko.typeqastassignment.entity.Client;
import com.bosko.typeqastassignment.entity.Meter;
import com.bosko.typeqastassignment.entity.Reading;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapStructMapper {

    MapStructMapper INSTANCE = Mappers.getMapper(MapStructMapper.class);

    ClientDTO transformClientToDTO(Client client);

    Client transformClientDTOToEntity(ClientDTO clientDTO);

    MeterDTO transformMeterToDTO(Meter meter);

    Meter transformMeterDTOToEntity(MeterDTO meterDTO);

    ReadingDTO transformToReadingDTO(Reading reading);

    Reading transformReadingDTOToEntity(ReadingDTO readingDTO);

    AddressDTO transformAddressToDTO(Address address);

    Address transformAddressDTOToEntity(AddressDTO addressDTO);

}
