package com.bosko.typeqastassignment.mapper;

import com.bosko.typeqastassignment.dto.*;
import com.bosko.typeqastassignment.entity.Address;
import com.bosko.typeqastassignment.entity.Client;
import com.bosko.typeqastassignment.entity.Meter;
import com.bosko.typeqastassignment.entity.Reading;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
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

    @Mapping(source = "first_name", target = "firstName")
    @Mapping(source = "last_name", target = "lastName")
    @Mapping(source = "create_date", target = "createDate", dateFormat = "dd.MM.yyyy")
    @Mapping(source = "meter_id", target = "meterId")
    UserReadingDTO transformUserReadingToDTO(UserReading userReading);
}
