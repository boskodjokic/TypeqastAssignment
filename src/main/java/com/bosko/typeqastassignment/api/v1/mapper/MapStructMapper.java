package com.bosko.typeqastassignment.api.v1.mapper;

import com.bosko.typeqastassignment.api.v1.dto.ClientDTO;
import com.bosko.typeqastassignment.api.v1.dto.ReadingDTO;
import com.bosko.typeqastassignment.entity.Client;
import com.bosko.typeqastassignment.entity.Reading;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapStructMapper {

    MapStructMapper INSTANCE = Mappers.getMapper(MapStructMapper.class);

    ClientDTO transformClientToDTO(Client client);

    Client transformClientDTOToEntity(ClientDTO clientDTO);

    ReadingDTO transformToReadingDTO(Reading reading);

    Reading transformReadingDTOToEntity(ReadingDTO readingDTO);


}
