package com.bosko.typeqastassignment.mapper;

import com.bosko.typeqastassignment.dto.ReadingDTO;
import com.bosko.typeqastassignment.entity.Reading;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ReadingMapper {

    public ReadingDTO transformToDTO(Reading reading) {
        ReadingDTO readingDTO = new ReadingDTO();
        BeanUtils.copyProperties(reading, readingDTO);
        return readingDTO;
    }

    public Reading transformToEntity(ReadingDTO readingDTO) {
        Reading reading = new Reading();
        BeanUtils.copyProperties(readingDTO, reading);
        return  reading;
    }
}
