package com.bosko.typeqastassignment.mapper;

import com.bosko.typeqastassignment.dto.MeterDTO;
import com.bosko.typeqastassignment.entity.Meter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MeterMapper {

    MeterMapper INSTANCE = Mappers.getMapper(MeterMapper.class);

    MeterDTO transformToDTO(Meter meter);

    Meter transformToEntity(MeterDTO meterDTO);

}
