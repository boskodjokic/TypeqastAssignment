package com.bosko.typeqastassignment.mapper;

import com.bosko.typeqastassignment.dto.MeterDTO;
import com.bosko.typeqastassignment.entity.Meter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class MeterMapper {

    public MeterDTO transformToDTO(Meter meter) {
        MeterDTO meterDTO = new MeterDTO();
        BeanUtils.copyProperties(meter, meterDTO);
        return meterDTO;
    }

    public Meter transformToEntity(MeterDTO meterDTO) {
        Meter meter = new Meter();
        BeanUtils.copyProperties(meterDTO, meter);
        return meter;
    }
}
