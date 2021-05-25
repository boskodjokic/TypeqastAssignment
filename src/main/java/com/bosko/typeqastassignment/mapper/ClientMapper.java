package com.bosko.typeqastassignment.mapper;

import com.bosko.typeqastassignment.dto.ClientDTO;
import com.bosko.typeqastassignment.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDTO transformToDTO(Client client);

    Client transformToEntity(ClientDTO clientDTO);
}
