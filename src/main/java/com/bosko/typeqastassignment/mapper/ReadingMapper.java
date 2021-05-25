package com.bosko.typeqastassignment.mapper;

import com.bosko.typeqastassignment.dto.ReadingDTO;
import com.bosko.typeqastassignment.entity.Reading;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReadingMapper {

    ReadingMapper INSTANCE = Mappers.getMapper(ReadingMapper.class);

    ReadingDTO transformToDTO(Reading reading);

    Reading transformToEntity(ReadingDTO readingDTO);
}
