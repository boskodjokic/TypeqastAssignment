package com.bosko.typeqastassignment.mapper;

import com.bosko.typeqastassignment.dto.ClientDTO;
import com.bosko.typeqastassignment.dto.ReadingDTO;
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
